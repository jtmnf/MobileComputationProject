package chat.mobilecomputationproject.activities.select_chat_room.dummy;

import java.util.ArrayList;
import java.util.List;

import chat.mobilecomputationproject.database.data_objects.ChatRoom;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ChatRoom> ITEMS = new ArrayList<ChatRoom>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            ITEMS.add(createChatRoom(i));
        }
    }

    private static ChatRoom createChatRoom(int position) {
        return new ChatRoom(position, "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here. Don't be shy, click right here --> and have a lot of fun!");
        }
        return builder.toString();
    }

    public List<ChatRoom> getItems(){
        return ITEMS;
    }
}
