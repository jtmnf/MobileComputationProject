package chat.mobilecomputationproject.database.data_objects;

import java.util.Date;

/**
 * Created by aclima on 29/12/15.
 */
public class ChatMessage {

    private Integer userId;
    private String username;
    private String message;
    private Date timestamp;

    public ChatMessage(Integer userId, String username, String message, Date timestamp) {
        this.userId = userId;
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
