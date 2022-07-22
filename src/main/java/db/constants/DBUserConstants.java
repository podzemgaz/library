package db.constants;

public abstract class DBUserConstants {

    //fields
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE_ID = "role_id";
    //queries
    public static final String GET_ALL_USERS = "SELECT * FROM library.user";
    public static final String FIND_USER_BY_LOGIN_USE_PATTERN = "SELECT * FROM library.user u WHERE u.login LIKE ?";
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM library.user u WHERE u.login = ?";
    public static final String SQL_INSERT_USER = "INSERT INTO user (login, password) VALUES (?, ?)";
    public static final String SELECT_ROLE_BY_USER_ID = "SELECT role_id FROM library.user WHERE id = ";

    //private constructor
    private DBUserConstants() {
    }
}
