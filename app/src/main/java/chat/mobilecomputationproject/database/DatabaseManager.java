package chat.mobilecomputationproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "worldinchat.db";

    private UserManager userManager;
    private ChatManager chatManager;

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase(db);
        userManager = new UserManager(this);
        chatManager = new ChatManager(this);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createDatabase(SQLiteDatabase db){
        try{
            db = SQLiteDatabase.openDatabase(DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);
            db.beginTransaction();

            // user table creation
            db.execSQL("CREATE TABLE " + UserManager.USER + " ( " + UserManager.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + UserManager.USERNAME + " TEXT, " + UserManager.PASSWORD + " TEXT)");

            // chat table creation
            db.execSQL("CREATE TABLE " + ChatManager.CHAT + " ( " + ChatManager.MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + UserManager.USER_ID + " INTEGER, " + ChatManager.MESSAGE + " TEXT, " + ChatManager.TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)");

            db.setTransactionSuccessful();

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }
}
