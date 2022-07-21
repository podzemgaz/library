package db.dao;

public abstract class AbstractDao {

    protected static void close(AutoCloseable stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
