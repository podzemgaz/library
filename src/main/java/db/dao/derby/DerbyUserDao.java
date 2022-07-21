package db.dao.derby;

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
    public User findUserByLogin(String login) {
        System.out.println("derby find user");
        return null;
    }

    @Override
    public void checkConnection() {

    }
}
