package db.dao;

import db.entity.Entity;

public abstract class DAOFactory {
    /**
     * закрытое поле класса, которое хранит объект DAOFactory
     */
    private static DAOFactory instance;
    /**
     * содержит полное квалифицированное имя класса, объект которого
     * будет возвращать {@link #getInstance()}
     */
    private static String daoFactoryFCN;
    public static void setDaoFactoryFCN(String daoFactoryFCN) {
        instance = null;
        DAOFactory.daoFactoryFCN = daoFactoryFCN;
    }

    /**
     * конструктор для возможности наследовать данный класс
     */
    protected DAOFactory() {
    }

    /**
     * Метод получения объекта DAOFactory. Какая именно DAOFactory
     * будет возвращена определяют настройки фарбрики
     *
     * @return экземпляр потомка DAOFactory, имя которого содержится
     *          {@link #daoFactoryFCN}
     */
    public static synchronized DAOFactory getInstance() throws Exception {
        if (instance == null) {
            Class<?> clazz = Class.forName(DAOFactory.daoFactoryFCN);
            instance = (DAOFactory) clazz.getDeclaredConstructor().newInstance();
        }
        return instance;
    }
    public abstract UserDao getUserDao();
    public abstract BookDao getBookDao();
}
