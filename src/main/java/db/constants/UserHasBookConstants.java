package db.constants;

public abstract class UserHasBookConstants {
    //FIELDS
    public static final String USER_ID = "user_id";
    public static final String BOOK_ID = "book_id";

    //QUERIES
    public static final String GET_USERS_HAS_BOOKS = "SELECT * FROM library.user_has_book ORDER BY user_id;";
    public static final String GET_BOOKS_IDS_BY_USER_ID = "SELECT book_id FROM library.user_has_book WHERE user_id = ?";
    private UserHasBookConstants() {
    }
}
