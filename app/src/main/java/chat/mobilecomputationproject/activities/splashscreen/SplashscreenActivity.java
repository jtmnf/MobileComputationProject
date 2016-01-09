package chat.mobilecomputationproject.activities.splashscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.Timer;
import java.util.TimerTask;

import chat.mobilecomputationproject.R;
import chat.mobilecomputationproject.activities.select_chat_room.SelectChatRoomActivity;
import chat.mobilecomputationproject.database.managers.DatabaseManager;
import chat.mobilecomputationproject.utilities.RegistrationService;

public class SplashscreenActivity extends AppCompatActivity {

    private static final long SPLASHSCREEN_DELAY = 2000; // 3000 feels too "long"
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private DatabaseManager databaseManager;

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static SplashscreenActivity parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseManager = new DatabaseManager(this);
        parent = this;

        setContentView(R.layout.activity_splashscreen);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

                boolean sentToken = sharedPreferences.getBoolean("sentTokenToServer", false);

                if (sentToken) {
                    Log.i("Token", "Received");
                } else {
                    parent.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(parent.getBaseContext(), R.string.token_problem, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                }
            }
        };

        if(checkPlayServices()){
            startService(new Intent(this, RegistrationService.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter("registrationComplete"));

        //Wait a few seconds before checking for an internet connection and moving on to the chatroom selector activity

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (isNetworkAvailable()) {
                    Log.i("Network Status", "Network Available");
                    goToSelectChatRoomActivity();

                } else {
                    Log.i("Network Status", "Network Not Available");
                    parent.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(parent.getBaseContext(), R.string.no_internet_connection, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }, SPLASHSCREEN_DELAY);
    }

    private void goToSelectChatRoomActivity() {
        final Intent intent = new Intent(this, SelectChatRoomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i("ProjectCM", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
