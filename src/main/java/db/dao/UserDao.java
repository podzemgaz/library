package db.dao;

import db.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<User>{
    List<User> findByLoginUsePattern(String pattern) throws DaoException;
    User findByLogin(String pattern) throws DaoException;
    boolean insertUser(User user) throws DaoException;
}
