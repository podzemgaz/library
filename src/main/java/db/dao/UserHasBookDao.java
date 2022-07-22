package db.dao;

import db.entity.UserHasBook;

import java.util.Set;

public interface UserHasBookDao extends BaseDao<UserHasBook> {
    boolean insertUserHasBook() throws DaoException;

    Set<Integer> getBooksIdsByUserId(int id) throws DaoException;
}
