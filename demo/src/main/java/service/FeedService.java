package service;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Comment;
import model.FeedPost;

public class FeedService {

    private static final ObservableList<FeedPost> POSTS =
            FXCollections.observableArrayList();

    static {

        // 🔹 Post 1
        FeedPost p1 = new FeedPost(
                "Garbage overflow near FC Road",
                "Bins have not been cleared for 3 days.",
                "Garbage",
                "Rahul",
                LocalDateTime.now().minusHours(5)
        );
        p1.getComments().add(
                new Comment(
                        "Anita",
                        "Same issue in my lane too.",
                        LocalDateTime.now().minusHours(3)
                )
        );
        p1.getComments().add(
                new Comment(
                        "You",
                        "Great work reporting this 👍",
                        LocalDateTime.now().minusHours(1)
                )
        );

        // 🔹 Post 2
        FeedPost p2 = new FeedPost(
                "Potholes causing traffic jam",
                "Huge potholes near University Chowk.",
                "Roads",
                "Suresh",
                LocalDateTime.now().minusDays(1)
        );
        p2.getComments().add(
                new Comment(
                        "You",
                        "This area is really dangerous.",
                        LocalDateTime.now().minusHours(6)
                )
        );

        // 🔹 Post 3
        FeedPost p3 = new FeedPost(
                "Street lights not working in Aundh",
                "Entire stretch is dark after 8 PM.",
                "Electricity",
                "Neha",
                LocalDateTime.now().minusHours(10)
        );
        p3.getComments().add(
                new Comment(
                        "Amit",
                        "Faced the same yesterday.",
                        LocalDateTime.now().minusHours(4)
                )
        );

        // 🔹 Post 4
        FeedPost p4 = new FeedPost(
                "Water leakage near Baner Road",
                "Pipe leakage causing water wastage.",
                "Water",
                "Amit",
                LocalDateTime.now().minusDays(2)
        );

        // 🔹 Post 5
        FeedPost p5 = new FeedPost(
                "Illegal parking near metro station",
                "Cars blocking the main road and bus stop.",
                "Traffic",
                "Rohit",
                LocalDateTime.now().minusHours(20)
        );
        p5.getComments().add(
                new Comment(
                        "Suresh",
                        "Traffic police should act on this.",
                        LocalDateTime.now().minusHours(8)
                )
        );

        POSTS.addAll(p1, p2, p3, p4, p5);
    }

    public static ObservableList<FeedPost> getPosts() {
        return POSTS;
    }

    public static void addComment(FeedPost post, Comment comment) {
        post.getComments().add(comment);
    }
}
