package chat.mobilecomputationproject.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aclima on 26/12/15.
 */
public class ChatTableManager {

    // table name
    public static final String CHAT = "chat";

    // table attributes
    public static final String MESSAGE_ID = "message_id";
    public static final String USER_ID = "user_id";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";

    private DatabaseManager dbm;

    ChatTableManager(DatabaseManager dbm) {
        this.dbm = dbm;
    }

    public boolean addChatMessage(Integer user_id, String message){

        boolean success;
        SQLiteDatabase db = dbm.getWritableDatabase();

        try {
            db.beginTransaction();

            ContentValues cv=new ContentValues();
            cv.put(USER_ID, user_id);
            cv.put(MESSAGE, message);
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
