package dao.connectionpool;

import java.io.IOException;
import java.sql.Connection;


public class ConnectionData {
    private static final ConnectionPool poll = new ConnectionPool();

    public static Connection getConnection() {
        return poll.getConnectionFromPool();
    }

    public static void returnConnection(Connection connection) {
        poll.returnConnectionToPool(connection);
    }

    public static void closeConnectionPool() {
        try {
            poll.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
