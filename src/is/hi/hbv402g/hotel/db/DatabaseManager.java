 	package is.hi.hbv402g.hotel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import is.hi.hbv402g.hotel.exceptions.DoubleBookedException;
import is.hi.hbv402g.hotel.models.BookingNight;
import is.hi.hbv402g.hotel.models.Guest;
import is.hi.hbv402g.hotel.models.Hotel;
import is.hi.hbv402g.hotel.models.Room;

public class DatabaseManager implements IDataManager
{

	private static final String databaseString = "jdbc:sqlite:hotel.db";

	private static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(databaseString);
	}

	public ArrayList<Room> findHotelRooms(String hotelName, String location, Date availabilityFrom, Date availabilityTo)
	{
		ArrayList<Room> rooms = new ArrayList<>();
		
		// Create the basic query which will stand alone if no conditions are set
		String sql = "";
		sql += "SELECT ";
		sql += "r.id, r.hotelId, r.numSingleBeds, r.numDoubleBeds, r.bathroom, r.costPerNight, ";
		sql += "h.id, h.name, h.streetAddress, h.city, h.postalCode, h.country, h.starCount, GROUP_CONCAT(a.amenity) ";
		
		// Count number of booked nights if availability is specified
		if (availabilityFrom != null || availabilityTo != null)
		{
			ArrayList<String> availabilityCond = new ArrayList<>();
			if (availabilityFrom != null)
			{
				availabilityCond.add("bn.date >= ?");
			}
			if (availabilityTo != null)
			{
				availabilityCond.add("bn.date <= ?");
			}
			
			if (availabilityCond.size() > 0)
				sql += ",(SELECT count(*) FROM BookingNight as bn WHERE " + String.join(" AND ", availabilityCond) + " AND bn.roomId = r.id) as bookedNights ";
			else
				sql += ",(SELECT count(*) FROM BookingNight as bn WHERE bn.roomId = r.id) as bookedNights ";
		}
		
		sql += "FROM Room as r ";
		sql += "INNER JOIN Hotel as h ON (r.hotelId = h.id) ";
		sql += "LEFT OUTER JOIN Amenities as a ON (a.hotelId = h.id) ";
		
		// Collect search constraints
		ArrayList<String> conditions = new ArrayList<>();
		if(hotelName != null && !hotelName.isEmpty())
		{
			conditions.add("h.name LIKE ? ");
		}
		
		if (location != null && !location.isEmpty())
		{
			conditions.add("(h.streetAddress LIKE ? OR h.city LIKE ? OR h.postalCode LIKE ? OR h.country LIKE ?) ");
		}

		if (availabilityFrom != null || availabilityTo != null)
		{
			conditions.add("bookedNights == 0 ");
		}
		
		// Add the search constraints to the sql query
		if (conditions.size() > 0)
		{
			sql += "WHERE " + String.join(" AND ", conditions);
		}
		
		sql += "GROUP BY ";
		sql += "r.id, r.hotelId, r.numSingleBeds, r.numDoubleBeds, r.bathroom, r.costPerNight, ";
		sql += "h.id, h.name, h.streetAddress, h.city, h.postalCode, h.country, h.starCount "; 

		int i = 1;
		try
		{
			// Create the prepared statement
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// Limit the booked nights count check
			SimpleDateFormat iso8601DateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (availabilityFrom != null)
			{
				statement.setString(i++, iso8601DateFormat.format(availabilityFrom));
			}
			
			if (availabilityTo != null)
			{
				statement.setString(i++, iso8601DateFormat.format(availabilityTo));
			}

			// Add the search constraints to the prepared statement
			if(hotelName != null && !hotelName.isEmpty())
			{
				statement.setString(i++, "%"+hotelName+"%");
			}
			
			if(location != null && !location.isEmpty())
			{
				statement.setString(i++, "%"+location+"%");
				statement.setString(i++, "%"+location+"%");
				statement.setString(i++, "%"+location+"%");
				statement.setString(i++, "%"+location+"%");
			}
			
			ResultSet results = statement.executeQuery();
	
			// Create the hotels and the room instances
			HashMap<Integer, Hotel> hotels = new HashMap<>();
			while (results.next())
			{
				// Read hotel information
				Hotel h = hotels.get(results.getInt(7));
				if (h == null)
				{
					h = new Hotel(results.getInt(7));
					h.setName(results.getString(8));
					h.setStreetAddress(results.getString(9));
					h.setCity(results.getString(10));
					h.setPostalCode(results.getString(11));
					h.setCountry(results.getString(12));
					h.setStarCount(results.getInt(13));
					h.setAmenities(new ArrayList<String>(Arrays.asList(results.getString(14).split(","))));
					hotels.put(h.getId(), h);
				}
				
				// Read room information
				Room r = new Room(results.getInt(1));
				r.setHotel(h);
				r.setNumberOfSingleBeds(results.getInt(3));
				r.setNumberOfDoubleBeds(results.getInt(4));
				r.setEnSuiteBathroom(results.getBoolean(5));
				r.setCostPerNight(results.getInt(6));
				
				rooms.add(r);
			}
		}
		catch (SQLException exc)
		{
			System.err.println(exc.getMessage());
			return null;
		}
		return rooms;
	}
	
	public void saveBookingNights(ArrayList<BookingNight> bookingNights) throws DoubleBookedException
	{
		try
		{
			Connection connection = getConnection();
			for (BookingNight bn : bookingNights)
			{
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO BookingNight (roomId, guestId, date) VALUES (?, ?, ?)");
				statement.setInt(1, bn.getRoom().getId());
				statement.setInt(2, bn.getGuest().getId());
				SimpleDateFormat iso8601DateFormat = new SimpleDateFormat("yyyy-MM-dd");
				statement.setString(3, iso8601DateFormat.format(bn.getDate()));

				statement.executeUpdate();
			}
		}
		catch (SQLException exc)
		{
			if (exc.getMessage().startsWith("[SQLITE_CONSTRAINT_PRIMARYKEY]"))
				throw new DoubleBookedException();
			System.err.println(exc.getMessage());
			return;
		}
	}
	
	public Integer saveGuest(Guest g)
	{
		Integer id = null;
		try
		{
			Connection connection = getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Guest (name, email, phoneNumber, numberOfAdults, " +
					"numberOfChildren) VALUES (?, ?, ?, ?, ?)");
			statement.setString(1, g.getName());
			statement.setString(2, g.getEmail());
			statement.setString(3, g.getPhoneNumber());
			statement.setInt(4, g.getNumberOfAdults());
			statement.setInt(5, g.getNumberOfChildren());

			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next())
			{
				id = rs.getInt(1);
			}
		}
		catch (SQLException exc)
		{
			System.err.println(exc.getMessage());
			return null;
		}
		
		return id;
	}

	public static Hotel getHotelById(int id)
	{
		try
		{
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT name, streetAddress, city, postalCode, country, starCount FROM Hotel WHERE id = ?");
			statement.setInt(1, id);

			ResultSet results = statement.executeQuery();

			Hotel hotel = null;
			if (results.next())
			{
				hotel = new Hotel(id);
				hotel.setName(results.getString("name"));
				hotel.setStreetAddress(results.getString("streetAddress"));
				hotel.setCity(results.getString("city"));
				hotel.setPostalCode(results.getString("postalCode"));
				hotel.setCountry(results.getString("country"));
				hotel.setStarCount(results.getInt("starCount"));
			}

			return hotel;
		}
		catch (SQLException exc)
		{
			System.err.println(exc.getMessage());
			return null;
		}
	}
	
	public static ArrayList<Room> getRoomsByHotel(Hotel hotel)
	{
		try
		{
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT id, numSingleBeds, numDoubleBeds, bathroom, costPerNight FROM Room WHERE hotelId = ?");
			statement.setInt(1, hotel.getId());

			ResultSet results = statement.executeQuery();
			
			ArrayList<Room> rooms = new ArrayList<>();
			
			while (results.next() == true)
			{
				Room room = new Room(results.getInt("id"));
				room.setHotel(hotel);
				room.setCostPerNight(results.getInt("costPerNight"));
				room.setEnSuiteBathroom(results.getBoolean("bathroom"));
				room.setNumberOfSingleBeds(results.getInt("numSingleBeds"));
				room.setNumberOfDoubleBeds(results.getInt("numDoubleBeds"));
				rooms.add(room);
			}
			
			return rooms;
		}
		catch (SQLException exc)
		{
			System.err.println(exc.getMessage());
			return null;
		}
	}
	
	public static Guest getGuestById(int id)
	{
		try
		{
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT name, email, phoneNumber, numberOfAdults, numberOfChildren FROM Guest WHERE id = ?");
			statement.setInt(1, id);

			ResultSet results = statement.executeQuery();

			Guest guest = null;
			if (results.next())
			{
				guest = new Guest(id);
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
			System.err.println(exc.getMessage());
			return null;
		}
	}
	
	public ArrayList<String> getListOfAmenities()
	{
		ArrayList<String> amenities = new ArrayList<>();
		try
		{
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT DISTINCT amenity FROM Amenities");
			
			ResultSet results = statement.executeQuery();

			if (results.next())
			{
				amenities.add(results.getString("amenity"));
			}

			return amenities;
		}
		catch (SQLException exc)
		{
			System.err.println(exc.getMessage());
			return null;
		}
	}
}
