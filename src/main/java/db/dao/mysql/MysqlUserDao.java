package db.dao.mysql;

import db.dao.AbstractDao;
import db.dao.DaoException;
import db.dao.UserDao;
import db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDao extends AbstractDao implements UserDao {
    private static MysqlUserDao instance;
    private MysqlUserDao() {
    }

    public static MysqlUserDao getInstance() {
        if (instance == null) {
            instance = new MysqlUserDao();
        }
        return instance;
    }

    private static final String SQL_INSERT_USER = "INSERT INTO user (login, password) VALUES (?, ?)";

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection con = MysqlConnectionPool.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM library.user")) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole_id(rs.getInt("role_id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    public static boolean insertUser(User user) throws DaoException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = MysqlConnectionPool.getConnection();
            stmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            int count = stmt.executeUpdate();
            if (count > 0) {
                try (ResultSet rs = con.prepareStatement("select * from user").executeQuery()){
                    if (rs.next()) {
                        /*user.setId(rs.getInt(1));
                        user.setCreateTime(rs.getDate(5));*/
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e.getCause());
        } finally {
            close(stmt);
            close(con);
        }

        return true;
    }

    public void checkConnection() throws DaoException {

        try (Connection con = MysqlConnectionPool.getConnection()) {
            System.out.println("connected " + con);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage() + ", cause: " + e.getCause());
        }
    }
}
