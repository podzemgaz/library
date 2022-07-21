package db.dao.mysql;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import db.dao.DaoException;
import resource.MyConfigManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConnectionPool {
    private final static String URL;
    private final static String USER_NAME;
    private final static String PASSWORD;
    private final static String FULL_URL;

    static {
        URL = MyConfigManager.getProperty("mysqlUrl");
        USER_NAME = MyConfigManager.getProperty("mysqlUserName");
        PASSWORD = MyConfigManager.getProperty("mysqlPassword");
        FULL_URL = URL + "?user=" + USER_NAME + "&password=" + PASSWORD;
    }
    private static DataSource getPooledConnectionDataSource() {
        MysqlDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setURL(FULL_URL);
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return getPooledConnectionDataSource().getConnection();
    }

}
