package db.dao;

import db.entity.User;

public interface UserDao extends BaseDao<User>{
    User findUserByLogin(String login) throws DaoException;
    public void checkConnection() throws DaoException;

}
