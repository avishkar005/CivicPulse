package service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChatMessage;
import model.ChatUser;

public class ChatService {

    private static final ObservableList<ChatUser> users =
            FXCollections.observableArrayList(
                    new ChatUser("Rahul"),
                    new ChatUser("Anita"),
                    new ChatUser("Suresh"),
                    new ChatUser("Neha"),
                    new ChatUser("Amit")
            );

    private static final Map<String, ObservableList<ChatMessage>> chatHistory =
            new HashMap<>();

    private static final Random random = new Random();

    static {
        for (ChatUser user : users) {

            ObservableList<ChatMessage> messages =
                    FXCollections.observableArrayList();

            messages.add(
                    new ChatMessage(
                            user.getName(),
                            "Hi, did you see the issue updates?",
                            LocalDateTime.now().minusMinutes(5)
                    )
            );

            messages.add(
                    new ChatMessage(
                            "You",
                            "Yes, CivicPulse is helpful 👍",
                            LocalDateTime.now().minusMinutes(2)
                    )
            );

            chatHistory.put(user.getName(), messages);
        }
    }

    public static ObservableList<ChatUser> getUsers() {
        return users;
    }

    public static ObservableList<ChatMessage> getMessages(String user) {
        return chatHistory.computeIfAbsent(
                user,
                k -> FXCollections.observableArrayList()
        );
    }

    public static void sendMessage(String user, String text) {

        if (user == null || text == null || text.isBlank()) {
            return;
        }

        ObservableList<ChatMessage> messages =
                chatHistory.computeIfAbsent(
                        user,
                        k -> FXCollections.observableArrayList()
                );

        messages.add(
                new ChatMessage(
                        "You",
                        text,
                        LocalDateTime.now()
                )
        );

        // ✅ smarter dummy reply generator
        messages.add(
                new ChatMessage(
                        user,
                        generateDummyReply(text),
                        LocalDateTime.now()
                )
        );
    }

    // =========================
    // DUMMY AI LOGIC
    // =========================

    private static String generateDummyReply(String text) {

        text = text.toLowerCase();

        if (text.contains("road") || text.contains("pothole"))
            return pick(
                    "Road complaint noted 👍",
                    "Municipal team usually fixes roads within 3–5 days",
                    "You should upload a photo for faster action"
            );

        if (text.contains("water"))
            return pick(
                    "Water supply issues are high priority 💧",
                    "Please mention area name also",
                    "Pipeline complaints get quick response"
            );

        if (text.contains("garbage"))
            return pick(
                    "Garbage issue reported — good job",
                    "Sanitation department will handle it",
                    "Add location for faster pickup"
            );

        if (text.contains("status"))
            return pick(
                    "You can check status in My Reports tab",
                    "Status updates appear after officer review",
                    "Pending → In Progress → Resolved"
            );

        if (text.contains("hello") || text.contains("hi"))
            return pick(
                    "Hello 👋 How can I help?",
                    "Hi there — what civic issue are you facing?",
                    "Hey — ready to report something?"
            );

        // default varied replies
        return pick(
                "Got it 👍",
                "Thanks — noted",
                "Understood",
                "That makes sense",
                "Okay — recorded"
        );
    }

    private static String pick(String... options) {
        return options[random.nextInt(options.length)];
    }
}
