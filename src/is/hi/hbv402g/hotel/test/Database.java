package is.hi.hbv402g.hotel.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    private static final String databaseString = "jdbc:sqlite:test.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseString);
    }
}
