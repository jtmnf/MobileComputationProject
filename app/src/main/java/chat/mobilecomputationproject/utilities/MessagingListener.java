/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package chat.mobilecomputationproject.utilities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.Objects;

import chat.mobilecomputationproject.R;
import chat.mobilecomputationproject.activities.chat_room.ChatMessage;
import chat.mobilecomputationproject.activities.chat_room.ChatRoomActivity;

public class MessagingListener extends GcmListenerService {

    private static ChatRoomActivity chatRoomActivity;

    public MessagingListener() {
    }

    public MessagingListener(ChatRoomActivity chatRoomActivity) {
        this.chatRoomActivity = chatRoomActivity;
    }

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        ChatMessage chatMessage = new ChatMessage(
                false,
                data.getString("message"),
                data.getString("username"),
                data.getString("date"),
                data.getString("id")
        );

        new ReceiveMessage().execute(chatMessage);
    }

    class ReceiveMessage extends AsyncTask<ChatMessage, Void, String> {
        @Override
        protected String doInBackground(ChatMessage... params) {
            sendNotification(params[0]);
            return null;
        }

        private void sendNotification(final ChatMessage chatMessage) {
            if (chatRoomActivity != null) {
                chatRoomActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatRoomActivity.receiveMessage(chatMessage);
                    }
                });
            }
        }
    }
}
