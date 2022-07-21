//demonstration
import db.dao.DAOFactory;
import db.dao.DaoException;
import db.dao.UserDao;
import db.entity.Entity;
import resource.MyConfigManager;

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
        try {
            userDao.checkConnection();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
