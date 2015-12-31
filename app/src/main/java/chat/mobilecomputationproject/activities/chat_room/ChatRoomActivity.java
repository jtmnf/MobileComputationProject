package chat.mobilecomputationproject.activities.chat_room;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import chat.mobilecomputationproject.R;

public class ChatRoomActivity extends AppCompatActivity {

    private ChatArrayAdapter adp;
    private ListView list;
    private EditText chatText;
    private Button send;
    private List<ChatMessage> msg= new ArrayList<ChatMessage>();
    private boolean mySide = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //buscar componentes graficos
        send = (Button) findViewById(R.id.btn);
        list = (ListView) findViewById(R.id.listview);
        chatText = (EditText) findViewById(R.id.chat);

        //adaptador para a lista de mensagens
        adp = new ChatArrayAdapter(getApplicationContext(), R.layout.chat_message, msg);

        //colocacao de um listener no botao
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendChatMessage();
            }
        });

        //Scroll automatico sempre que surgir uma nova mensagem
        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        //insercao do adaptador na lista
        list.setAdapter(adp);


        /*adp.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                list.setSelection(adp.getCount() - 1);
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private boolean sendChatMessage() {
        adp.add(new ChatMessage(mySide,chatText.getText().toString()));
        chatText.setText("");

        mySide = !mySide;

        return true;
    }
}
