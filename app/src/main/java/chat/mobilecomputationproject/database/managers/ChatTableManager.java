package chat.mobilecomputationproject.database.managers;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by aclima on 26/12/15.
 */
public class ChatTableManager {

    // table name
    public static final String CHAT = "chat";

    // table attributes
    public static final String MESSAGE_ID = "message_id";
    public static final String USER_NAME = "username";
    public static final String CHAT_ID = "chat_id";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";

    private DatabaseManager dbm;

    ChatTableManager(DatabaseManager dbm) {
        this.dbm = dbm;
    }

    public boolean addChatMessage(String user_name, String message, Integer chat_id){
        boolean success = false;

        SQLiteDatabase db = dbm.getWritableDatabase();

        try {
            db.beginTransaction();

            ContentValues cv = new ContentValues();
            cv.put(USER_NAME, user_name);
            cv.put(MESSAGE, message);
            cv.put(CHAT_ID, chat_id);

            db.insert(CHAT, MESSAGE, cv);

            db.setTransactionSuccessful();
            success = true;
        }
        catch (Exception e){
            e.printStackTrace();
            success = false;
        }
        finally {
            db.endTransaction();
        }

        return success;
    }

}
