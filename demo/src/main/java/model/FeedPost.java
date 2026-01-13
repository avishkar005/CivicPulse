package model;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FeedPost {

    private final String title;
    private final String description;
    private final String category;
    private final String author;
    private final LocalDateTime time;

    private final ObservableList<Comment> comments =
            FXCollections.observableArrayList();

    public FeedPost(String title, String description,
                    String category, String author,
                    LocalDateTime time) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
        this.time = time;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getAuthor() { return author; }
    public LocalDateTime getTime() { return time; }

    public ObservableList<Comment> getComments() {
        return comments;
    }
}
