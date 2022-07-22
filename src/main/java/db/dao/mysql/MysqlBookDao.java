package db.dao.mysql;

import db.constants.DBBookConstants;
import db.constants.DBConstants;
import db.dao.AbstractDao;
import db.dao.BookDao;
import db.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlBookDao extends AbstractDao implements BookDao {
    private static MysqlBookDao instance;

    private MysqlBookDao() {
    }

    public static MysqlBookDao getInstance() {
        if (instance == null) {
            instance = new MysqlBookDao();
        }
        return instance;
    }


    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection con = MysqlConnectionPool.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(DBBookConstants.GET_ALL_BOOKS)) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt(DBConstants.ID));
                book.setName(rs.getString(DBBookConstants.NAME));
                book.setCount(rs.getInt(DBBookConstants.COUNT));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findByName(String pattern) {
        return null;
    }
}
