package chat.mobilecomputationproject.database;

/**
 * Created by aclima on 26/12/15.
 */
public class UserManager {

    // table name
    public static final String USER = "user";

    // table atributes
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";


    private DatabaseManager dbm;

    UserManager(DatabaseManager dbm) {
        this.dbm = dbm;
    }

}
