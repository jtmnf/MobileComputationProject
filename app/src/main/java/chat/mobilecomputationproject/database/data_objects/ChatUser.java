package chat.mobilecomputationproject.database.data_objects;

import java.io.Serializable;

/**
 * Created by aclima on 29/12/15.
 */
public class ChatUser implements Serializable {

    private long id;
    private String username;

    public ChatUser(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
