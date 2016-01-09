package chat.mobilecomputationproject.database.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "worldinchat.db";

    private static UserTableManager userTableManager;
    private static ChatTableManager chatTableManager;

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, 1);

        userTableManager = new UserTableManager(this);
        chatTableManager = new ChatTableManager(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createDatabase(SQLiteDatabase db){
        try{
            db = SQLiteDatabase.openDatabase(DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e){
            db.execSQL("CREATE TABLE " + UserTableManager.USER + " ( "
                    + UserTableManager.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + UserTableManager.USERNAME + " TEXT)");

            // chat table creation
            db.execSQL("CREATE TABLE " + ChatTableManager.CHAT + " ( "
                    + ChatTableManager.MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + UserTableManager.USERNAME + " TEXT, "
                    + ChatTableManager.MESSAGE + " TEXT, "
                    + ChatTableManager.CHAT_ID + " INTEGER, "
                    + ChatTableManager.DATE + " TEXT, "
                    + ChatTableManager.TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)");
        }
    }

    public ChatTableManager getChatTableManager() {
        return chatTableManager;
    }

    public UserTableManager getUserTableManager() {
        return userTableManager;
    }
}
