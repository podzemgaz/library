//demonstration
import db.dao.BookDao;
import db.dao.DAOFactory;
import db.dao.DaoException;
import db.dao.UserDao;
import db.entity.Book;
import db.entity.Entity;
import db.entity.User;
import resource.MyConfigManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args)  {


        DAOFactory.setDaoFactoryFCN(MyConfigManager.getProperty("daoFactoryFCN"));
        DAOFactory daoFactory = null;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert daoFactory != null;
        UserDao userDao = daoFactory.getUserDao();
        BookDao bookDao = daoFactory.getBookDao();
        try {
            userDao.checkConnection();
            List<User> users = userDao.findAll();
            for (User u : users) {
                System.out.println(u);
            }
            List<Book> books = bookDao.findAll();
            for (Book book : books) {
                System.out.println(book);
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
