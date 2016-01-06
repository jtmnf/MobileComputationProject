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
import chat.mobilecomputationproject.activities.select_chat_room.dummy.DummyContent;
import chat.mobilecomputationproject.database.data_objects.ChatRoom;

public class SelectChatRoomListFragment extends ListFragment {

    List<ChatRoom> chatRooms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_chat_room, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        // TODO: get the info here
        chatRooms = new DummyContent().getItems();

        ChatRoomAdapter adapter = new ChatRoomAdapter(getActivity(), R.layout.select_chat_room_item, chatRooms);
        setListAdapter(adapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // go to the login screen for this chat room
                // TODO: pass chat room?
                ChatRoom selectedChatRoom = chatRooms.get(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                intent.putExtra("selectedChatRoom", selectedChatRoom);
                startActivity(intent);
            }
        });
    }
}
