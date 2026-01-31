package model;

import java.time.LocalDateTime;

public class ChatMessage {

    private final String sender;
    private final String message;
    private final LocalDateTime time;

    public ChatMessage(String sender, String message, LocalDateTime time) {
        this.sender = sender;
        this.message = message;
        this.time = time;
    }

    public String getSender() { return sender; }
    public String getMessage() { return message; }
    public LocalDateTime getTime() { return time; }
}
