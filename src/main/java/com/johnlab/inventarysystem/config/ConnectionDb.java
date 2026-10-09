package main.java.com.johnlab.inventarysystem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {

    private static Connection conn;

    public static Connection getConnectionDb() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(CredentialsDb.URL_DB, CredentialsDb.USER_DB, CredentialsDb.PASS_DB);
        }
        return conn;
    }

}
