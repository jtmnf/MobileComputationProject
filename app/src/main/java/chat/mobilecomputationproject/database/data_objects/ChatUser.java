package chat.mobilecomputationproject.database.data_objects;

/**
 * Created by aclima on 29/12/15.
 */
public class ChatUser {

    private Integer userId;
    private String username;

    public ChatUser(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
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
}
