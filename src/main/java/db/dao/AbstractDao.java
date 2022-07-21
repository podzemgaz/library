package db.dao;

import db.entity.Entity;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDao {

    protected static void close(AutoCloseable stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
