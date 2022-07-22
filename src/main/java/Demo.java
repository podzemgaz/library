//demonstration
import db.dao.*;
import db.entity.*;
import resource.MyConfigManager;

import java.util.ArrayList;
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
            printList(users);
            List<Book> books = bookDao.findAll();
            printList(books);

            users = userDao.findByLoginUsePattern("client");
            printList(users);

            User user = new User();
            user.setLogin("goga");
            user.setPassword("shvili");

            if (userDao.insertUser(user)) {
                System.out.println("inserted user: " + user);
            }
            users = userDao.findAll();
            printList(users);


            UserHasBookDao userHasBookDao = daoFactory.getUserHasBookDao();
            List<UserHasBook> userHasBooks = userHasBookDao.findAll();
            printList(userHasBooks);

            user = userDao.findByLogin("goga");
            UserCard userCard = new UserCard();
            userCard.setId(user.getId());
            userCard.setBooks(userHasBookDao.getBooksIdsByUserId(userCard.getId()));

            System.out.println(userCard);


        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private static <T extends Entity> void printList(List<T> users) {
        for (T t : users) {
            System.out.println(t);
        }
    }
}
