import io.Config;

import java.sql.*;

/**
 * class connection to DB with parameters from app.properties
 * adding table with JDBC
 * @since 09/04/2021
 */

public class ConnectionDemo {

    private static Connection getConnection() throws SQLException {
        Config config = new Config("./chapter_007/src/main/resources/app.properties");
        config.load();
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        return DriverManager.getConnection(url, login, password);
    }

    private static void createTable(String tableName) throws SQLException {
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

    public static String getTableScheme(String tableName) throws SQLException {
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

    public static void main(String[] args) throws SQLException {
        String tableName = "DemoTable01";
        ConnectionDemo.createTable(tableName.toLowerCase());
        String result = ConnectionDemo.getTableScheme(tableName.toLowerCase());
        System.out.println(result);
    }
}
