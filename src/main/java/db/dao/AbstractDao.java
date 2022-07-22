package db.dao;

import db.dao.mysql.MysqlConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDao {

    public void checkConnection() throws DaoException {

        try (Connection con = MysqlConnectionPool.getConnection()) {
            System.out.println("connected " + con);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage() + ", cause: " + e.getCause());
        }
    }

    protected static String escapeForLike (String param) {
        return param.replace("!", "!!").replace("%", "!%")
                .replace("_", "!_").replace("[", "![");
    }

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
