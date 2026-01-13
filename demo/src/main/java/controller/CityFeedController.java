package controller;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Comment;
import model.FeedPost;
import service.FeedService;

public class CityFeedController {

    @FXML
    private ListView<FeedPost> feedList;

    @FXML
    public void initialize() {

        feedList.setItems(FeedService.getPosts());

        feedList.setCellFactory(list ->
            new ListCell<FeedPost>() {

                @Override
                protected void updateItem(FeedPost post, boolean empty) {
                    super.updateItem(post, empty);

                    if (empty || post == null) {
                        setGraphic(null);
                        return;
                    }

                    VBox box = new VBox(8);
                    box.getStyleClass().add("card");

                    Label title = new Label(post.getTitle());
                    title.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: white;");

                    Label meta = new Label(
                            post.getCategory() + " • by " + post.getAuthor()
                    );
                    meta.getStyleClass().add("sub-heading");

                    Label desc = new Label(post.getDescription());
                    desc.setWrapText(true);
                    desc.setStyle("-fx-text-fill: #e5e7eb;");

                    // COMMENTS
                    VBox commentsBox = new VBox(6);
                    for (Comment c : post.getComments()) {
                        Label cl = new Label(c.getUser() + ": " + c.getText());
                        cl.setStyle("-fx-text-fill: #cbd5f5;");
                        commentsBox.getChildren().add(cl);
                    }

                    // ADD COMMENT
                    TextField commentField = new TextField();
                    commentField.setPromptText("Add a comment…");
                    commentField.setPrefWidth(300);

                    Button addBtn = new Button("Comment");
                    addBtn.getStyleClass().add("secondary-btn");

                    addBtn.setOnAction(e -> {
                        if (!commentField.getText().isBlank()) {
                            FeedService.addComment(
                                    post,
                                    new Comment(
                                            "You",
                                            commentField.getText(),
                                            LocalDateTime.now()
                                    )
                            );
                            feedList.refresh();
                            commentField.clear();
                        }
                    });

                    HBox commentRow = new HBox(10, commentField, addBtn);

                    box.getChildren().addAll(
                            title,
                            meta,
                            desc,
                            commentsBox,
                            commentRow
                    );

                    setGraphic(box);
                }
            }
        );
    }
}
