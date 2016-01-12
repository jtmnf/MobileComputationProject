package chat.mobilecomputationproject.activities.select_chat_room;

import java.util.ArrayList;
import java.util.List;

import chat.mobilecomputationproject.database.data_objects.ChatRoom;

/**
 * List of pre-defined chat rooms that a user can join.
 */
public class DefaultChatRooms {

    public static final List<ChatRoom> ITEMS = new ArrayList<ChatRoom>();

    static {

        int i = 1;
        ITEMS.add(new ChatRoom(++i, "General", "Talk about everything and anything. ( ͡° \u035Cʖ ͡°)"));
        ITEMS.add(new ChatRoom(++i, "Android", "Discuss the freedom of choice."));
        ITEMS.add(new ChatRoom(++i, "iOS", "Share experiences regarding modern day slavery."));
        ITEMS.add(new ChatRoom(++i, "Windows", "PC master race."));
        ITEMS.add(new ChatRoom(++i, "Linux", "With great power come great kernel panics."));
        ITEMS.add(new ChatRoom(++i, "Mac", "Overpriced facebook machines aren't just for hipsters."));

    }

    public List<ChatRoom> getItems(){
        return ITEMS;
    }
}
