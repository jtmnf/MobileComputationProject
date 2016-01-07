package chat.mobilecomputationproject.activities.chat_room;



/**
 * Created by chris on 28/12/2015.
 */
public class ChatMessage {
    private boolean mySide;
    private String message;
    private String userName;
    private String date;

    public ChatMessage(boolean mySide, String message, String userName, String date) {
        super();
        this.mySide=mySide;
        this.message = message;
        this.userName = userName;
        this.date = date;
    }

    public String getMessage(){
        return message;
    }

    public boolean getMySide(){
        return mySide;
    }

    public String getUserName(){
        return userName;
    }

    public String getDate(){
        return date;
    }
}
