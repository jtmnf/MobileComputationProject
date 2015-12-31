package chat.mobilecomputationproject.activities.chat_room;

/**
 * Created by chris on 28/12/2015.
 */
public class ChatMessage {
    private boolean mySide;
    private String message;

    public ChatMessage(boolean mySide, String message) {
        super();
        this.mySide=mySide;
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public boolean getMySide(){
        return mySide;
    }
}
