package db.dao.mysql;

import db.constants.UserHasBookConstants;
import db.dao.AbstractDao;
import db.dao.DaoException;
import db.dao.UserHasBookDao;
import db.entity.UserHasBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MysqlUserHasBookDao extends AbstractDao implements UserHasBookDao {
    private static MysqlUserHasBookDao instance;

    private MysqlUserHasBookDao() {
    }

    public static MysqlUserHasBookDao getInstance() {
        if (instance == null) {
            instance = new MysqlUserHasBookDao();
        }

        return instance;
    }

    @Override
    public List<UserHasBook> findAll() throws DaoException {
        List<UserHasBook> usersHasBooks = new ArrayList<>();
        try (Connection con = MysqlConnectionPool.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(UserHasBookConstants.GET_USERS_HAS_BOOKS)) {
            while (rs.next()) {
                usersHasBooks.add(mapUserHasBook(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        return usersHasBooks;
    }

    @Override
    public boolean insertUserHasBook() throws DaoException {
        return false;
    }

    @Override
    public Set<Integer> getBooksIdsByUserId(int id) throws DaoException {
        Set<Integer> booksIds = new HashSet<>();
        try (Connection con = MysqlConnectionPool.getConnection();
             PreparedStatement pstmt = con.prepareStatement(UserHasBookConstants.GET_BOOKS_IDS_BY_USER_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    booksIds.add(rs.getInt(UserHasBookConstants.BOOK_ID));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        return booksIds;
    }

    private UserHasBook mapUserHasBook(ResultSet rs) throws SQLException {
        UserHasBook userHasBook = new UserHasBook();
        userHasBook.setUserId(rs.getInt(UserHasBookConstants.USER_ID));
        userHasBook.setBookId(rs.getInt(UserHasBookConstants.BOOK_ID));
        return userHasBook;
    }
}
