package chat.mobilecomputationproject.activities.chat_room;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import chat.mobilecomputationproject.R;
import chat.mobilecomputationproject.database.data_objects.ChatRoom;
import chat.mobilecomputationproject.database.data_objects.ChatUser;
import chat.mobilecomputationproject.database.managers.DatabaseManager;
import chat.mobilecomputationproject.utilities.MessagingListener;
import chat.mobilecomputationproject.utilities.MessagingSender;

public class ChatRoomActivity extends AppCompatActivity {

    private ChatRoom chatRoom;
    private ChatUser chatUser;

    private ChatArrayAdapter adp;
    private ListView list;
    private EditText chatText;
    private Button send;
    private List<ChatMessage> msg= new ArrayList<ChatMessage>();

    // Messaging Handler
    private MessagingSender messagingSender;

    // Database Handler
    private DatabaseManager databaseManager;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        databaseManager = new DatabaseManager(getApplicationContext());
        database = databaseManager.getWritableDatabase();

        messagingSender = new MessagingSender();
        new MessagingListener(this);

		chatRoom = (ChatRoom) getIntent().getSerializableExtra("" + ChatRoom.class);
        chatUser = (ChatUser) getIntent().getSerializableExtra("" + ChatUser.class);

        setTitle(chatRoom.getName());

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_change:
                Intent intent = new Intent(this, ThemeActivity.class);
                startActivityForResult(intent, 123);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean sendChatMessage() {
        String message = chatText.getText().toString();

        DateFormat df = new SimpleDateFormat("h:mm a - dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        adp.add(new ChatMessage(false, message,chatUser.getUsername(),date));
        chatText.setText("");

        messagingSender.sendMessage(message, chatUser.getUsername(), chatRoom.getId(), date);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)	{
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode==RESULT_OK)	{
            if (requestCode==123){
                String theme = intent.getStringExtra("theme");

                if(theme.equals("1")){
                    adp.setTheme(1);
                }
                if(theme.equals("2")){
                    adp.setTheme(2);
                }
                if(theme.equals("3")){
                    adp.setTheme(3);
                }
            }
        }
    }

    public void receiveMessage(String message, String username, String id, String date){
        databaseManager.getChatTableManager().addChatMessage(username, message, Integer.parseInt(id), date);

        if(id.equals(String.valueOf(chatRoom.getId()))) {
            if (!username.equals(chatUser.getUsername())) {
                adp.add(new ChatMessage(true, message, username, date));
            } else {
                Log.i("ChatRoomMessage", "You sended a message that was delivered correctly!");
            }
        }
    }
}
