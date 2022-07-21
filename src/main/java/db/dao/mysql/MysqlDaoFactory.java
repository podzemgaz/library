package db.dao.mysql;

import db.dao.DAOFactory;
import db.dao.UserDao;
import db.entity.Entity;

public class MysqlDaoFactory extends DAOFactory {
    private UserDao userDao;
    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new MysqlUserDao();
        }
        return userDao;
    }
}
