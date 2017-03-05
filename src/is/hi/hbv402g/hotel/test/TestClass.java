package is.hi.hbv402g.hotel.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestClass
{

    public static ArrayList<Integer> getIdsForName(String name)
    {
	try
	{
	    Connection connection = Database.getConnection();
	    PreparedStatement statement = connection.prepareStatement("SELECT distinct id FROM t WHERE name = ?");
	    statement.setString(1, name);

	    ResultSet results = statement.executeQuery();

	    ArrayList<Integer> ids = new ArrayList<>();
	    while (results.next())
	    {
		ids.add(results.getInt("id"));
	    }

	    return ids;
	} catch (SQLException exc)
	{
	    System.out.println(exc.getMessage());
	    return null;
	}
    }
}
