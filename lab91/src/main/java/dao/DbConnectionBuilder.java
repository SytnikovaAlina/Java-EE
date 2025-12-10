package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionBuilder implements ConnectionBuilder {

    public DbConnectionBuilder() {
        try {
            String driver = ConnectionProperty.getProperty("db.driver.class");
            System.out.println("Loading driver: " + driver);
            Class.forName(driver);
            System.out.println("MySQL driver loaded OK");
        } catch (ClassNotFoundException | NullPointerException ex) {
            System.out.println("ERROR: DB driver not loaded");
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = ConnectionProperty.getProperty("db.url");
        String login = ConnectionProperty.getProperty("db.login");
        String password = ConnectionProperty.getProperty("db.password");
        System.out.println("Trying DB connection to: " + url + " as " + login);
        return DriverManager.getConnection(url, login, password);
    }
}
