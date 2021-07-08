package dao.connectionpool;

public class Configuration {
    private static Configuration configuration = new Configuration();
    public String DB_USER_NAME;
    public String DB_PASSWORD;
    public String DB_URL;
    public String DB_DRIVER;
    public Integer DB_MAX_CONNECTION;

    public static Configuration getInstance(){
        return configuration;
    }
    private Configuration() {
        init();
    }

    private void init(){
        DB_USER_NAME = "bestuser";
        DB_PASSWORD = "bestuser";
        DB_URL = "jdbc:mysql://localhost:3306/payment_system?characterEncoding=UTF-8";
        DB_DRIVER = "com.mysql.jdbc.Driver";
        DB_MAX_CONNECTION = 50;
    }
}
