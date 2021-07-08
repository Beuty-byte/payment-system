package dao.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static JdbcConnectionPool poll = new JdbcConnectionPool();

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        return poll.getConnectionFromPool();
    }

    public static void returnConnection(Connection connection){
        poll.returnConnectionToPool(connection);
    }
}
