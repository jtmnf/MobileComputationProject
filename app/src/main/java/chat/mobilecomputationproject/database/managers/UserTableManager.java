package chat.mobilecomputationproject.database.managers;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aclima on 26/12/15.
 *
 * Class that manages the Chat table in the DB.
 */
public class UserTableManager {

    // table name
    public static final String USER = "user";

    // table attributes
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";


    private DatabaseManager dbm;

    UserTableManager(DatabaseManager dbm) {
        this.dbm = dbm;
    }

    /**
     * Transactional function to insert a user in the local DB.
     *
     * @param username
     * @return
     */
    public boolean addUser(String username){

        boolean success;
        SQLiteDatabase db = dbm.getWritableDatabase();

        try {
            db.beginTransaction();

            ContentValues cv = new ContentValues();
            cv.put(USERNAME, username);
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
