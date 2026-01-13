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
        FeedPost p1 = new FeedPost(
                "Garbage overflow near FC Road",
                "Bins have not been cleared for 3 days.",
                "Garbage",
                "Rahul",
                LocalDateTime.now().minusHours(5)
        );
        p1.getComments().add(
                new Comment("Anita",
                        "Same issue in my lane too.",
                        LocalDateTime.now().minusHours(3))
        );

        FeedPost p2 = new FeedPost(
                "Potholes causing traffic jam",
                "Huge potholes near University Chowk.",
                "Roads",
                "Suresh",
                LocalDateTime.now().minusDays(1)
        );

        POSTS.addAll(p1, p2);
    }

    public static ObservableList<FeedPost> getPosts() {
        return POSTS;
    }

    public static void addComment(FeedPost post, Comment comment) {
        post.getComments().add(comment);
    }
}
