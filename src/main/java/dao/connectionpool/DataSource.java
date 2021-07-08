package dao.connectionpool;

import dao.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    static JdbcConnectionPool poll = new JdbcConnectionPool();

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection connection = poll.getConnectionFromPool();
        return connection;
    }

    public static void returnConnection(Connection connection){
        poll.returnConnectionToPool(connection);
    }
}
