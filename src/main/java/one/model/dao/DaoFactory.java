package one.model.dao;


import one.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract CarDao createCarDao();
    public abstract CarRentalDao createCarRentalDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){

                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }


}
