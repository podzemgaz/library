package db.dao.mysql;

import db.dao.BookDao;
import db.dao.DAOFactory;
import db.dao.UserDao;

public class MysqlDaoFactory extends DAOFactory {

    @Override
    public UserDao getUserDao() {
        return MysqlUserDao.getInstance();
    }

    @Override
    public BookDao getBookDao() {
        return MysqlBookDao.getInstance();
    }
}
