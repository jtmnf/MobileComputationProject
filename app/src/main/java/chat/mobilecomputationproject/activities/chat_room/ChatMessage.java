package chat.mobilecomputationproject.activities.chat_room;

public class ChatMessage {
    private boolean mySide;
    private String message;
    private String userName;
    private String date;
    private String roomID;

    public ChatMessage(boolean mySide, String message, String userName, String date, String roomID) {
        super();
        this.mySide = mySide;
        this.message = message;
        this.userName = userName;
        this.date = date;
        this.roomID = roomID;
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

    public String getRoomID() {
        return roomID;
    }

    public void setMySide(boolean mySide) {
        this.mySide = mySide;
    }
}
