package chat.mobilecomputationproject.activities.select_chat_room;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import chat.mobilecomputationproject.R;
import chat.mobilecomputationproject.database.data_objects.ChatRoom;

/**
 * Created by aclima on 30/12/15.
 */
public class ChatRoomAdapter extends ArrayAdapter<ChatRoom> {

    private Context context;
    private int resource;
    private List<ChatRoom> objects;

    public ChatRoomAdapter(Context context, int resource, List<ChatRoom> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    /**
     * Creates a custom View for each ChatRoom item that will be displayed in the ListFragment.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ChatRoomHolder holder = new ChatRoomHolder();

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder.nameTextView = (TextView) row.findViewById(R.id.name);
            holder.descriptionTextView = (TextView) row.findViewById(R.id.description);

            row.setTag(holder);
        }
        else
        {
            holder = (ChatRoomHolder) row.getTag();
        }

        ChatRoom chatRoom = objects.get(position);
        holder.nameTextView.setText(chatRoom.getName());
        holder.descriptionTextView.setText(chatRoom.getDescription());

        return row;
    }

    static class ChatRoomHolder{
        TextView nameTextView;
        TextView descriptionTextView;
    }

}
