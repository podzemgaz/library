package db.dao.derby;

import db.dao.DaoException;
import db.dao.UserDao;
import db.entity.Entity;
import db.entity.User;

import java.util.List;

public class DerbyUserDao implements UserDao {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void checkConnection() {
    }

    @Override
    public List<User> findByLoginUsePattern(String pattern) {
        return null;
    }

    @Override
    public User findByLogin(String pattern) throws DaoException {
        return null;
    }

    @Override
    public boolean insertUser(User user) throws DaoException {
        return false;
    }
}
