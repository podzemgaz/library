package db.dao.derby;

import db.dao.DAOFactory;
import db.dao.UserDao;
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
}