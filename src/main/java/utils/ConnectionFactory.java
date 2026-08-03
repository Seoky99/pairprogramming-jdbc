package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory instance;

    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PWD;

    private ConnectionFactory() {
        this.DB_URL = System.getenv("DB_URL");
        this.DB_USER = System.getenv("DB_USER");
        this.DB_PWD = System.getenv("DB_PWD");

        // Fail-fast validation: Verify environment setup
        if (this.DB_URL == null || this.DB_USER == null || DB_PWD == null) {
            throw new IllegalStateException(
                    "Critical Error: Database environment variables (DB_URL, DB_USER, DB_PASS) are not configured."
            );
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PWD);
    }

}
