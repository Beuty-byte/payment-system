package dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class JdbcConnectionPool {
    List<Connection> availableConnection = new ArrayList<>();

    public JdbcConnectionPool(){
        initializeConnectionPool();
    }

    private Connection createNewConnectionForPool(){
        Configuration config = Configuration.getInstance();
        try {
            Class.forName(config.DB_DRIVER);
            return DriverManager.getConnection(config.DB_URL, config.DB_USER_NAME, config.DB_PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private void initializeConnectionPool(){
        while (!checkIfConnectionPoolIsFull()){
            availableConnection.add(createNewConnectionForPool());
        }
    }

    private synchronized boolean checkIfConnectionPoolIsFull(){
        final int MAX_POOL_SIZE = Configuration.getInstance().DB_MAX_CONNECTION;
        return availableConnection.size() >= MAX_POOL_SIZE;
    }

    public synchronized Connection getConnectionFromPool(){
        Connection connection = null;
        if(availableConnection.size() > 0){
            connection = availableConnection.get(0);
            availableConnection.remove(0);
        }
        return connection;
    }

    public synchronized void returnConnectionToPool(Connection connection){
        availableConnection.add(connection);
    }


}
