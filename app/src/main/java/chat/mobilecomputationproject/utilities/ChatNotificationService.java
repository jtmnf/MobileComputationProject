package chat.mobilecomputationproject.utilities;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;

import chat.mobilecomputationproject.R;

/**
 * Inspired on the provided course materials, all credit goes to professor Tiago Cruz.
 */

public class ChatNotificationService extends Service {

    public final static String ACTION = "NotifyServiceAction";
    public final static String STOP_SERVICE_BROADCAST_KEY = "StopServiceBroadcastKey";
    public final static int RQS_STOP_SERVICE = 1;

    NotifyServiceReceiver notifyServiceReceiver;

    @Override
    public void onCreate() {
        notifyServiceReceiver = new NotifyServiceReceiver();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        registerReceiver(notifyServiceReceiver, intentFilter);

        // Send Notification
        Context context = getApplicationContext();
        String notificationTitle = "Super Notification!";
        String notificationText = "Fun stuff";

        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.slowbro.org/"));

        /*
        Intent myIntent = new Intent(context, ChatRoomActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        */

        PendingIntent pendingIntent
                = PendingIntent.getActivity(getBaseContext(),
                0, myIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);


        Notification notification = new Notification.Builder(this)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText).setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);

        return(START_STICKY);
    }

    @Override
    public void onDestroy() {
        this.unregisterReceiver(notifyServiceReceiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    public class NotifyServiceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            int rqs = arg1.getIntExtra(STOP_SERVICE_BROADCAST_KEY, 0);

            if (rqs == RQS_STOP_SERVICE){
                stopSelf();
                //Disable all notifications in the bar
                ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).cancelAll();
            }
        }
    }

}
