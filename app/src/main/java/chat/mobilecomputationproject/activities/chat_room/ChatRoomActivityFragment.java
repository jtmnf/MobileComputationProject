package chat.mobilecomputationproject.activities.chat_room;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chat.mobilecomputationproject.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChatRoomActivityFragment extends Fragment {

    public ChatRoomActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }
}
