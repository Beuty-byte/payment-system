package dao.connectionpool;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

class Configuration {
    public static String DB_USER_NAME;
    public static String DB_PASSWORD;
    public static String DB_URL;
    public static String DB_DRIVER;
    public static Integer DB_MAX_CONNECTION = 50;

    static {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Path.of("src/main/resources/database.properties"))) {
            props.load(in);

            DB_URL = props.getProperty("url");
            DB_USER_NAME = props.getProperty("username");
            DB_PASSWORD = props.getProperty("password");
            DB_DRIVER = props.getProperty("dbDriver");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
