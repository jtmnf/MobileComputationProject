package chat.mobilecomputationproject.activities.select_chat_room;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import chat.mobilecomputationproject.R;
import chat.mobilecomputationproject.activities.login.LoginActivity;
import chat.mobilecomputationproject.database.data_objects.ChatRoom;

public class SelectChatRoomListFragment extends ListFragment {

    List<ChatRoom> chatRooms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_chat_room, container, false);
    }

    /**
     * Uses the custom Adapter to create the item views and populates the list fragment.
     * Each item is clickable and will redirect the user to login screen of the selected chat room.
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        chatRooms = new DefaultChatRooms().getItems();

        ChatRoomAdapter adapter = new ChatRoomAdapter(getActivity(), R.layout.select_chat_room_item, chatRooms);
        setListAdapter(adapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // go to the login screen for this chat room
                ChatRoom selectedChatRoom = chatRooms.get(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                intent.putExtra("" + ChatRoom.class, selectedChatRoom);
                startActivity(intent);
            }
        });
    }
}
