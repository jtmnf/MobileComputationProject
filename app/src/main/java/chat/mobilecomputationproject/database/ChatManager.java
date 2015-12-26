package chat.mobilecomputationproject.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.SensorManager;

/**
 * Created by aclima on 26/12/15.
 */
public class ChatManager {

    // table name
    public static final String CHAT = "chat";

    // table atributes
    public static final String MESSAGE_ID = "message_id";
    public static final String USER_ID = "user_id";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";

    private DatabaseManager dbm;

    ChatManager(DatabaseManager dbm) {
        this.dbm = dbm;
    }

    public boolean addChatMessage(Integer user_id, String message){
        SQLiteDatabase db = dbm.getWritableDatabase();

        try {
            db.beginTransaction();

            ContentValues cv=new ContentValues();
            cv.put(USER_ID, user_id);
            cv.put(MESSAGE, message);
            db.insert(CHAT, MESSAGE, cv);

            db.setTransactionSuccessful();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }

}
