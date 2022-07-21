package db.dao.mysql;

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
    private MysqlBookDao() {
    }

    public static MysqlBookDao getInstance() {
        if (instance == null) {
            instance = new MysqlBookDao();
        }
        return instance;
    }

    private static MysqlBookDao instance;
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection con = MysqlConnectionPool.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM library.book")) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setCount(rs.getInt("count"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
