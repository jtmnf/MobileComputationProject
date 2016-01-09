package chat.mobilecomputationproject.utilities;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import chat.mobilecomputationproject.activities.chat_room.ChatMessage;

public class MessagingSender {

    private static String API_KEY = "AIzaSyBbzKKCuyJCm4DEM1PWnrCbKtmlC2sNMmE";

    public void sendMessage(ChatMessage chatMessage) {
        new SendingMessageAsync().execute(chatMessage);
    }

    class SendingMessageAsync extends AsyncTask<ChatMessage, Void, String>{

        @Override
        protected String doInBackground(ChatMessage... params) {
            try {
                ChatMessage chatMessage = params[0];
                JSONObject jGcmData = new JSONObject();
                JSONObject jData = new JSONObject();

                jData.put("message", chatMessage.getMessage());
                jData.put("date", chatMessage.getDate());
                jData.put("id", chatMessage.getRoomID());
                jData.put("username", chatMessage.getUserName());

                jGcmData.put("to", "/topics/global");

                // What to send in GCM message.
                jGcmData.put("data", jData);

                // Create connection to send GCM Message request.
                URL url = new URL("https://android.googleapis.com/gcm/send");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Authorization", "key = " + API_KEY);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                // Send GCM message content.
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(jGcmData.toString(). getBytes());

                InputStream inputStream = conn.getInputStream();
                String resp = IOUtils.toString(inputStream);
                //System.out.println(resp);
                //System.out.println("Check your device/emulator for notification or logcat for confirmation of the receipt of the GCM message.");
            } catch (Exception e) {
                return "RESULT_NO_OK";
            }

            return "RESULT_OK";
        }
    }
}
