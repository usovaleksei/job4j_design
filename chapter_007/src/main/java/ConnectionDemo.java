import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * class connection to DB with parameters from app.properties
 * adding table with JDBC
 * @since 09/04/2021
 */

public class ConnectionDemo {

    private static Connection getConnection() throws SQLException, IOException {
        ClassLoader loader = ConnectionDemo.class.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(io);
            String url = config.getProperty("url");
            String login = config.getProperty("login");
            String password = config.getProperty("password");
            return DriverManager.getConnection(url, login, password);
        }
    }

    private static void createTable(String tableName) throws SQLException, IOException {
        String sql = String.format(
                "create table if not exists %s (%s, %s);",
                tableName,
                "id serial primary key",
                "name varchar(255)"
        );

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static String getTableScheme(String tableName) throws SQLException, IOException {
        StringBuilder scheme = new StringBuilder();
        try (Connection connection = getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
                scheme.append(String.format("%-15s %-15s%n", "column", "type"));
                while (columns.next()) {
                    scheme.append(String.format("%-15s %-15s%n",
                            columns.getString("COLUMN_NAME"),
                            columns.getString("TYPE_NAME")));
                }
            }
        }
        return scheme.toString();
    }

    public static void main(String[] args) throws SQLException, IOException {
        String tableName = "DemoTable01";
        ConnectionDemo.createTable(tableName.toLowerCase());
        String result = ConnectionDemo.getTableScheme(tableName.toLowerCase());
        System.out.println(result);
    }
}
