package chat.mobilecomputationproject.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import chat.mobilecomputationproject.R;
import chat.mobilecomputationproject.activities.chat_room.ChatRoomActivity;

public class SplashscreenActivity extends AppCompatActivity {

    private static final long SPLASHSCREEN_DELAY = 2000; // 3000 feels too "long"
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //Wait a few seconds before checking for an internet connection and moving on to the chatroom selector activity

        final Context context = this;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                if (isNetworkAvailable()) {
                    goToChatRoomActivity();
                } else {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
                }

            }
        }, SPLASHSCREEN_DELAY);
    }

    private void goToChatRoomActivity() {
        final Intent intent = new Intent(this, ChatRoomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
