package chat.mobilecomputationproject.database.data_objects;

import java.io.Serializable;

public class ChatRoom implements Serializable{
    private long id;
    private String name;
    private String description;

    public ChatRoom(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
