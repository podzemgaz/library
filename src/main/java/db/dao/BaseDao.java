package db.dao;

import db.dao.mysql.MysqlConnectionPool;
import db.entity.Entity;
import db.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface BaseDao <T extends Entity> {
     List<T> findAll();

}
