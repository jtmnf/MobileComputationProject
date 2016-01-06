package chat.mobilecomputationproject.database.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "worldinchat.db";

    private UserTableManager userTableManager;
    private ChatTableManager chatTableManager;

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase(db);
        userTableManager = new UserTableManager(this);
        chatTableManager = new ChatTableManager(this);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createDatabase(SQLiteDatabase db){
        try{
            db = SQLiteDatabase.openDatabase(DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);
            db.beginTransaction();

            // user table creation
            db.execSQL("CREATE TABLE " + UserTableManager.USER + " ( " + UserTableManager.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + UserTableManager.USERNAME + " TEXT)");

            // chat table creation
            db.execSQL("CREATE TABLE " + ChatTableManager.CHAT + " ( " + ChatTableManager.MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + UserTableManager.USERNAME + " TEXT, "
                    + ChatTableManager.MESSAGE + " TEXT, "
                    + ChatTableManager.CHAT_ID + " INTEGER, "
                    + ChatTableManager.TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)");

            db.setTransactionSuccessful();

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }

    public ChatTableManager getChatTableManager() {
        return chatTableManager;
    }
}
