package db.dao.mysql;

import db.constants.DBConstants;
import db.constants.DBUserConstants;
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

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection con = MysqlConnectionPool.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(DBUserConstants.GET_ALL_USERS)) {
            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        return users;
    }
    @Override
    public List<User> findByLoginUsePattern(String pattern) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection con = MysqlConnectionPool.getConnection();
             PreparedStatement pstmt = con.prepareStatement(DBUserConstants.FIND_USER_BY_LOGIN_USE_PATTERN)) {
            pstmt.setString(1, "%" + escapeForLike(pattern) +"%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    users.add(mapUser(rs));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        return users;
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        User user = new User();
        try (Connection con = MysqlConnectionPool.getConnection();
             PreparedStatement pstmt = con.prepareStatement(DBUserConstants.FIND_USER_BY_LOGIN)) {
            pstmt.setString(1, login);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    user = mapUser(rs);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        return user;
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(DBConstants.ID));
        user.setLogin(rs.getString(DBUserConstants.LOGIN));
        user.setPassword(rs.getString(DBUserConstants.PASSWORD));
        user.setRole_id(rs.getInt(DBUserConstants.ROLE_ID));
        return user;
    }

    public boolean insertUser(User user) throws DaoException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = MysqlConnectionPool.getConnection();
            con.setAutoCommit(false);
            addUser(user, con);
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                rollback(con);
            }
            if (e.getMessage().startsWith("Duplicate entry")) {
                return false;
            } else
            throw new DaoException(e.getMessage(), e.getCause());
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }

        return true;
    }

    private void addUser(User user, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(DBUserConstants.SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)){
            int k = 0;
            pstmt.setString(++k, user.getLogin());
            pstmt.setString(++k, user.getPassword());
            int count = pstmt.executeUpdate();
            if (count > 0) {
                try (Statement stmt = con.createStatement();
                     ResultSet rs = pstmt.getGeneratedKeys()){
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        user.setId(id);
                        try (ResultSet rs1 = stmt.executeQuery(DBUserConstants.SELECT_ROLE_BY_USER_ID + id)) {
                            if (rs1.next()) {
                                user.setRole_id(rs1.getInt(DBUserConstants.ROLE_ID));
                            }
                        }
                    }
                }
            }
        }
    }

    private void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
