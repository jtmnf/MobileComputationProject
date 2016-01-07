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

    // Database
    private DatabaseManager databaseManager;

    public ChatArrayAdapter(Context context, int  textViewResourceId, List<ChatMessage> objects) {
        super(context,  textViewResourceId, objects);
        databaseManager = new DatabaseManager(this.getContext());
    }

    public void add(ChatMessage object) {
        messageList.add(object);
        super.add(object);
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
        chatText.setBackgroundResource(messageObj.getMySide() ? R.drawable.ballon1 : R.drawable.ballon2);
        layout.setGravity(messageObj.getMySide() ? Gravity.LEFT : Gravity.RIGHT);

        headChat.setText(messageObj.getUserName() + " | " + messageObj.getDate());
        return v;
    }


}
