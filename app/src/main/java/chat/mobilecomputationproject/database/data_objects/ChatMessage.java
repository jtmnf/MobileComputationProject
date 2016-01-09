package chat.mobilecomputationproject.database.data_objects;

import java.util.Date;

/**
 * Created by aclima on 29/12/15.
 */
public class ChatMessage {

    private long userId;
    private String username;
    private String message;
    private Date timestamp;

    public ChatMessage(long userId, String username, String message, Date timestamp) {
        this.userId = userId;
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
