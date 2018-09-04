package dao.util;

import dao.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger logger = Logger.getLogger(TransactionManager.class.getSimpleName());
    private static Connection connection;
    private static boolean isTransaction = false;

    private TransactionManager() {
    }

    public static void startTransaction() {
        try {
            connection = ConnectionPool.getDataSource().getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        isTransaction = true;
    }

    public static void rollback() {
        isTransaction = false;
        if (connection !=null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            connection = null;
        }
    }

    public static void endTransaction() {

        isTransaction = false;
        try {
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        connection = null;

    }

    public static Connection getConnection() {
        return connection;
    }

    public static boolean isTransactional() {
        return isTransaction;
    }

}
