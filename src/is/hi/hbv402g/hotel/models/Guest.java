package is.hi.hbv402g.hotel.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import is.hi.hbv402g.hotel.db.Database;

public class Guest
{
	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private int numberOfAdults;
	private int numberOfChildren;
	
	public static Guest getGuestById(int id)
	{
		try
		{
			Connection connection = Database.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT name, email, phoneNumber, numberOfAdults, numberOfChildren FROM Guest WHERE id = ?");
			statement.setInt(1, id);

			ResultSet results = statement.executeQuery();

			Guest guest = new Guest();
			if (results.next())
			{
				guest.id = id;
				guest.setName(results.getString("name"));
				guest.setEmail(results.getString("email"));
				guest.setPhoneNumber(results.getString("phoneNumber"));
				guest.setNumberOfAdults(results.getInt("numberOfAdults"));
				guest.setNumberOfChildren(results.getInt("numberOfChildren"));
			}

			return guest;
		}
		catch (SQLException exc)
		{
			System.out.println(exc.getMessage());
			return null;
		}
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public int getNumberOfAdults()
	{
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults)
	{
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren()
	{
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren)
	{
		this.numberOfChildren = numberOfChildren;
	}

	public int getId()
	{
		return id;
	}
}
