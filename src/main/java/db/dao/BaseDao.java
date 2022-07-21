package db.dao;

import db.entity.Entity;
import java.util.List;

public interface BaseDao <T extends Entity> {
     List<T> findAll();

}
