package db.dao.derby;

import db.dao.BookDao;
import db.dao.DAOFactory;
import db.dao.UserDao;
import db.dao.UserHasBookDao;
import db.entity.Entity;

public class DerbyDaoFactory extends DAOFactory {
    private UserDao userDao;
    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new DerbyUserDao();
        }
        return userDao;
    }

    @Override
    public BookDao getBookDao() {
        return null;
    }

    @Override
    public UserHasBookDao getUserHasBookDao() {
        return null;
    }
}
