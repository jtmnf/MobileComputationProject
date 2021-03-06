package chat.mobilecomputationproject.activities.chat_room;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import chat.mobilecomputationproject.R;
import chat.mobilecomputationproject.database.data_objects.ChatRoom;
import chat.mobilecomputationproject.database.data_objects.ChatUser;
import chat.mobilecomputationproject.database.managers.DatabaseManager;
import chat.mobilecomputationproject.utilities.ChatNotificationService;
import chat.mobilecomputationproject.utilities.MessagingListener;
import chat.mobilecomputationproject.utilities.MessagingSender;


/**
 * Activity that controls the chat interaction
 */
public class ChatRoomActivity extends AppCompatActivity {

    private ChatRoom chatRoom;
    private ChatUser chatUser;

    private ChatArrayAdapter adp;
    private ListView list;
    private EditText chatText;
    private Button send;
    private List<ChatMessage> msg = new ArrayList<ChatMessage>();

    // Messaging Handler
    private MessagingSender messagingSender;

    // Database Handler
    private DatabaseManager databaseManager;
    private SQLiteDatabase database;

    // Back button
    private boolean backButtonPress = false;

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

        /**
         * get graphic components
         */
        send = (Button) findViewById(R.id.btn);
        list = (ListView) findViewById(R.id.listview);
        chatText = (EditText) findViewById(R.id.chat);

        /**
         * Adapter to the list of messages
         */
        adp = new ChatArrayAdapter(getApplicationContext(), R.layout.chat_message, msg);

        /**
         * Automatic scrool every time that a new message appears
         */
        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        /**
         * Put the adapter in the list
         */
        list.setAdapter(adp);

        /**
         * Send a message when a user enter
         */
        DateFormat df = new SimpleDateFormat("h:mm a - dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        messagingSender.sendMessage(new ChatMessage(false, "*" + chatUser.getUsername() + " entered the chat*", chatUser.getUsername(), date, String.valueOf(chatRoom.getId())));

    }

    /**
     * Update the action bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }


    /**
     * When clicked to change the theme
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         *  Take appropriate action for each action item click
         */
        switch (item.getItemId()) {
            case R.id.action_change:
                Intent intent = new Intent(this, ThemeActivity.class);
                startActivityForResult(intent, 123);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Disable the notifications for this chat room when we leave
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent();
        intent.setAction(ChatNotificationService.ACTION);
        intent.putExtra(ChatNotificationService.STOP_SERVICE_BROADCAST_KEY, ChatNotificationService.RQS_STOP_SERVICE);
        sendBroadcast(intent);
    }


    /**
     * Send a Chat Message
     */
    public void sendChatMessage(View view) {
        String message = chatText.getText().toString();

        if (message.length() > 0) {
            DateFormat df = new SimpleDateFormat("h:mm a - dd/MM/yyyy");
            String date = df.format(Calendar.getInstance().getTime());

            ChatMessage chatMessage = new ChatMessage(false, message, chatUser.getUsername(), date, String.valueOf(chatRoom.getId()));

            adp.add(chatMessage);
            chatText.setText("");

            messagingSender.sendMessage(chatMessage);
        } else {
            chatText.setError(getString(R.string.empty_messages));
        }
    }

    /**
     * Receive the activityTheme response and change the theme
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            if (requestCode == 123) {
                String theme = intent.getStringExtra("theme");

                if (theme.equals("1")) {
                    adp.setTheme(1);
                }
                if (theme.equals("2")) {
                    adp.setTheme(2);
                }
                if (theme.equals("3")) {
                    adp.setTheme(3);
                }

                adp.notifyDataSetChanged();
            }
        }
    }

    /**
     * Responsible for handling the received messages
     */
    public void receiveMessage(ChatMessage chatMessage) {

        /**
         * Handle the message database-wise
         */
        databaseManager.getChatTableManager().addChatMessage(chatMessage.getUserName(), chatMessage.getMessage(), Integer.parseInt(chatMessage.getRoomID()), chatMessage.getDate());

        /**
         * Display message
         */
        if (!backButtonPress && chatMessage.getRoomID().equals(String.valueOf(chatRoom.getId()))) {
            if (!chatMessage.getUserName().equals(chatUser.getUsername())) {
                chatMessage.setMySide(true);
                adp.add(chatMessage);

                // call the notification service for the message
                Intent intent = new Intent(ChatRoomActivity.this, ChatNotificationService.class);
                intent.putExtra("" + ChatRoom.class, chatRoom);
                startService(intent);

            } else {
                Log.i("ChatRoomMessage", "You sended a message that was delivered correctly!");
            }
        }
    }


    /**
     * Able to go back
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backButtonPress = true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
