package chat.mobilecomputationproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "worldinchat.db";

    private static final String LOGIN_TABLE = "login";
    private static final String LOGIN_USER = "username";
    private static final String LOGIN_PASS = "password";

    private static final String CHAT_TABLE = "chat";
    private static final String CHAT_MESSAGE = "message";
    private static final String CHAT_USER = "username";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, 1);
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
            // user table creation
            db.execSQL("CREATE TABLE user ( user_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");

            // chat table creation
            db.execSQL("CREATE TABLE chat ( message_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, message TEXT, Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");

        }
    }
}
