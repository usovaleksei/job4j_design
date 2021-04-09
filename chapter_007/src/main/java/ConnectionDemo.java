import io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class connection to DB with parameters from app.properties
 * @author Aleksei Usov
 * @since 09/04/2021
 */

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //driver loading
        Class.forName("org.postgresql.Driver");

        //reading url, login, password from app.properties
        Config config = new Config("./chapter_007/src/main/resources/app.properties");
        config.load();
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");

        //connection to BD server
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
