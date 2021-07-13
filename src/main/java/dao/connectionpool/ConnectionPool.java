package dao.connectionpool;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ConnectionPool implements Closeable {


    BlockingQueue<Connection> connections =
            new ArrayBlockingQueue<>(Configuration.DB_MAX_CONNECTION);

    ConnectionPool() {
        initializeConnectionPool();
    }

    private Connection createNewConnectionForPool() {
        try {
            Class.forName(Configuration.DB_DRIVER);
            return DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER_NAME, Configuration.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initializeConnectionPool() {
        while (connections.size() < Configuration.DB_MAX_CONNECTION) {
            connections.add(Objects.requireNonNull(createNewConnectionForPool()));
        }
    }


    Connection getConnectionFromPool() {
        return connections.poll();
    }

    void returnConnectionToPool(Connection connection) {
            connections.offer(connection);
    }


    @Override
    public void close() throws IOException {
        connections.forEach(c -> {
            try {
                c.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
