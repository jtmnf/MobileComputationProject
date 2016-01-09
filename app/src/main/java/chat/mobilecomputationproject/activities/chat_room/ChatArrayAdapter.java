package chat.mobilecomputationproject.activities.chat_room;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chat.mobilecomputationproject.R;
import chat.mobilecomputationproject.database.managers.DatabaseManager;


public class ChatArrayAdapter extends ArrayAdapter<ChatMessage>{
    private TextView chatText;
    private TextView headChat;
    private List<ChatMessage> messageList = new ArrayList<ChatMessage>();
    private LinearLayout layout;
    private int themeType;

    // Database
    private DatabaseManager databaseManager;

    public ChatArrayAdapter(Context context, int  textViewResourceId, List<ChatMessage> objects) {
        super(context,  textViewResourceId, objects);
        databaseManager = new DatabaseManager(this.getContext());
        themeType=1;
    }

    public void add(ChatMessage object) {
        messageList.add(object);
        super.add(object);
    }

    public void setTheme(int theme){
        themeType = theme;
    }

    public int getCount(){
        return this.messageList.size();
    }

    public ChatMessage getItem(int index){
        return this.messageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.chat_message, parent, false);
        }

        layout = (LinearLayout) v.findViewById(R.id.my_message);
        ChatMessage messageObj = getItem(position);
        chatText = (TextView) v.findViewById(R.id.singleMessage);
        headChat = (TextView) v.findViewById(R.id.headChat);

        chatText.setText(messageObj.getMessage());

        if(themeType==1) {
            chatText.setBackgroundResource(messageObj.getMySide() ? R.drawable.ballon2_1 : R.drawable.ballon1_1);
        }
        else if(themeType==2) {
            chatText.setBackgroundResource(messageObj.getMySide() ? R.drawable.ballon2_2 : R.drawable.ballon1_2);
        }
        else if(themeType==3) {
            chatText.setBackgroundResource(messageObj.getMySide() ? R.drawable.ballon2_3 : R.drawable.ballon1_3);
        }

        layout.setGravity(messageObj.getMySide() ? Gravity.LEFT : Gravity.RIGHT);

        headChat.setText(messageObj.getUserName() + " | " + messageObj.getDate());
        return v;
    }
}
