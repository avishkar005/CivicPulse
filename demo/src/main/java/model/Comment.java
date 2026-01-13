package model;

import java.time.LocalDateTime;

public class Comment {

    private final String user;
    private final String text;
    private final LocalDateTime time;

    public Comment(String user, String text, LocalDateTime time) {
        this.user = user;
        this.text = text;
        this.time = time;
    }

    public String getUser() { return user; }
    public String getText() { return text; }
    public LocalDateTime getTime() { return time; }
}
