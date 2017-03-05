package is.hi.hbv402g.hotel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{

    private static final String databaseString = "jdbc:sqlite:hotel.db";

    public static Connection getConnection() throws SQLException
    {
	return DriverManager.getConnection(databaseString);
    }
}
