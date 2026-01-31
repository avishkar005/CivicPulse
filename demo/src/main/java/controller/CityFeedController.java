package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ChatMessage;
import model.ChatUser;
import model.Comment;
import model.FeedPost;
import service.ChatService;
import service.FeedService;

public class CityFeedController {

    @FXML private ListView<FeedPost> feedList;
    @FXML private ListView<ChatUser> userList;
    @FXML private ListView<ChatMessage> chatList;
    @FXML private TextField chatField;

    private ChatUser selectedUser;

    @FXML
    public void initialize() {

        /* ---------- FEED ---------- */
        feedList.setItems(FeedService.getPosts());

        feedList.setCellFactory(list -> new ListCell<FeedPost>() {

            @Override
            protected void updateItem(FeedPost post, boolean empty) {
                super.updateItem(post, empty);

                if (empty || post == null) {
                    setGraphic(null);
                    setText(null);
                    return;
                }

                VBox box = new VBox(8);
                box.getStyleClass().add("card");

                Label title = new Label(post.getTitle());
                title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
                title.setWrapText(true);

                Label desc = new Label(post.getDescription());
                desc.setWrapText(true);

                // ✅ LIKE BUTTON
                Button likeBtn = new Button("👍 Like (" + post.getLikes() + ")");
                likeBtn.setOnAction(e -> {
                    post.setLikes(post.getLikes() + 1);
                    likeBtn.setText("👍 Like (" + post.getLikes() + ")");
                });

                // ✅ COMMENT INPUT
                TextField commentField = new TextField();
                commentField.setPromptText("Write a comment...");

                Button commentBtn = new Button("Comment");

                VBox commentBox = new VBox(4);

                // load existing comments
                for (Comment c : post.getComments()) {
                    commentBox.getChildren().add(
                            new Label("💬 " + c.getText())
                    );
                }

                commentBtn.setOnAction(e -> {
                    String text = commentField.getText();
                    if (text != null && !text.isBlank()) {
                        Comment c = new Comment("You", text, null);
                        post.getComments().add(c);
                        commentBox.getChildren().add(
                                new Label("💬 " + text)
                        );
                        commentField.clear();
                    }
                });

                HBox actionRow = new HBox(8, likeBtn, commentField, commentBtn);

                box.getChildren().addAll(title, desc, actionRow, commentBox);
                setGraphic(box);
            }
        });

        /* ---------- CHAT USERS ---------- */
        userList.setItems(ChatService.getUsers());

        userList.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldUser, newUser) -> {
                    selectedUser = newUser;

                    if (newUser != null) {
                        chatList.setItems(
                                ChatService.getMessages(newUser.getName())
                        );
                        chatList.scrollTo(chatList.getItems().size() - 1);
                    }
                });

        /* ---------- CHAT MESSAGES ---------- */
        chatList.setCellFactory(list -> new ListCell<ChatMessage>() {
            @Override
            protected void updateItem(ChatMessage msg, boolean empty) {
                super.updateItem(msg, empty);
                if (empty || msg == null) {
                    setText(null);
                } else {
                    setText(msg.getSender() + ": " + msg.getMessage());
                }
            }
        });
    }

    @FXML
    private void sendMessage() {

        if (selectedUser == null) return;

        String message = chatField.getText();
        if (message == null || message.isBlank()) return;

        ChatService.sendMessage(
                selectedUser.getName(),
                message
        );

        chatField.clear();
        chatList.scrollTo(chatList.getItems().size() - 1);
    }
}
