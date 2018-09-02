package dao;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static javax.sql.DataSource dataSource;
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getSimpleName());

    static {
        try{
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:/comp/env");
            dataSource = (javax.sql.DataSource) envCtx.lookup("jdbc/cruiseDB");
        } catch (NamingException e) {
            logger.error(e.getMessage());
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }
}
