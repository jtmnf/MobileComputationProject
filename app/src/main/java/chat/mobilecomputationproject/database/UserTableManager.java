package chat.mobilecomputationproject.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aclima on 26/12/15.
 */
public class UserTableManager {

    // table name
    public static final String USER = "user";

    // table attributes
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";


    private DatabaseManager dbm;

    UserTableManager(DatabaseManager dbm) {
        this.dbm = dbm;
    }

    public boolean addUser(String username, String password){

        boolean success;
        SQLiteDatabase db = dbm.getWritableDatabase();

        try {
            db.beginTransaction();

            ContentValues cv=new ContentValues();
            cv.put(USERNAME, username);
            cv.put(PASSWORD, password);
            db.insert(USER, USERNAME, cv);

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
