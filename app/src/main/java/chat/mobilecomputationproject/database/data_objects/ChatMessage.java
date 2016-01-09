package chat.mobilecomputationproject.database.data_objects;

import java.util.Date;

/**
 * Created by aclima on 29/12/15.
 */
public class ChatMessage {

    private ChatUser user;
    private String message;
    private Date timestamp;

    public ChatMessage(ChatUser user, String message, Date timestamp) {
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ChatUser getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
