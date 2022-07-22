package db.dao;

import db.entity.Book;

import java.util.List;

public interface BookDao extends BaseDao<Book>{
    List<Book> findByName(String pattern);
}
