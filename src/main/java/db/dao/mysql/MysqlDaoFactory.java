package db.dao.mysql;

import db.dao.BookDao;
import db.dao.DAOFactory;
import db.dao.UserDao;
import db.entity.Entity;

public class MysqlDaoFactory extends DAOFactory {
    private UserDao userDao;
    private BookDao bookDao;
    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new MysqlUserDao();
        }
        return userDao;
    }

    @Override
    public BookDao getBookDao() {
        if (bookDao == null) {
            bookDao = new MysqlBookDao();
        }
        return bookDao;
    }
}
