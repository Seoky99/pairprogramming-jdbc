package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory instance;

    private String DB_URL;
    private String DB_USER;
    private String DB_PWD;

    private ConnectionFactory() {
        /*this.DB_URL = System.getenv("DB_URL");
        this.DB_USER = System.getenv("DB_USER");
        this.DB_PWD = System.getenv("DB_PWD"); */

        //Removed env for testing purposes
        this.DB_URL = "test";
        this.DB_USER = "test";
        this.DB_PWD = "test";


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

    //Using H2 to simulate
    public void configure(String url, String username, String pwd) {
        this.DB_URL = url;
        this.DB_USER = username;
        this.DB_PWD = pwd;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PWD);
    }

}
