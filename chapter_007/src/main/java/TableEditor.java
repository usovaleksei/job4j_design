import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    private static final Logger LOG = LoggerFactory.getLogger(TableEditor.class.getName());

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (SQLException e) {
            LOG.error("Connection error", e);
        }
    }

    private static Properties loadProperties() {
        ClassLoader loader = TableEditor.class.getClassLoader();
        Properties config = new Properties();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("Error of execution of query SQL", e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format("create table if not exists %s ()", tableName);
        execute(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("drop table if exists %s", tableName);
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("alter table %s add if not exists %s %s", tableName, columnName, type);
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("alter table if exists %s drop %s", tableName, columnName);
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("alter table if exists %s rename %s to %s", tableName, columnName, newColumnName);
        execute(sql);
    }

    public String getScheme(String tableName) {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
        } catch (SQLException e) {
            LOG.error("Get metadata error", e);
        }
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        } catch (SQLException e) {
            LOG.error("Database access error", e);
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }
}