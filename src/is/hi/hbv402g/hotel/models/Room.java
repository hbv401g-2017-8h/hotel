package is.hi.hbv402g.hotel.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import is.hi.hbv402g.hotel.db.Database;

public class Room
{
	private int id;
	private int numberOfSingleBeds;
	private int numberOfDoubleBeds;
	private boolean enSuiteBathroom;
	private Hotel hotel;
	private int hotelId;
	private int costPerNight;

	public int getNumberOfSingleBeds()
	{
		return numberOfSingleBeds;
	}

	public void setNumberOfSingleBeds(int numberOfSingleBeds)
	{
		this.numberOfSingleBeds = numberOfSingleBeds;
	}

	public int getNumberOfDoubleBeds()
	{
		return numberOfDoubleBeds;
	}

	public void setNumberOfDoubleBeds(int numberOfDoubleBeds)
	{
		this.numberOfDoubleBeds = numberOfDoubleBeds;
	}

	public boolean getEnSuiteBathroom()
	{
		return enSuiteBathroom;
	}

	public void setEnSuiteBathroom(boolean enSuiteBathroom)
	{
		this.enSuiteBathroom = enSuiteBathroom;
	}

	public Hotel getHotel()
	{
		if(hotel == null)
			hotel = Hotel.getHotelById(hotelId);
		return hotel;
	}

	public void setHotel(Hotel hotel)
	{
		this.hotel = hotel;
		this.hotelId = hotel.getId();
	}

	public int getCostPerNight()
	{
		return costPerNight;
	}

	public void setCostPerNight(int costPerNight)
	{
		this.costPerNight = costPerNight;
	}

	public int getId()
	{
		return id;
	}

	public String toString()
	{
		return "[Room hotel=" + this.hotelId + 
				", costPerNight=" + this.getCostPerNight() +
				", enSuiteBathroom=" + this.getEnSuiteBathroom() + 
				", numberOfSingleBeds=" + this.getNumberOfSingleBeds() +
				", numberOfDoubleBeds=" + this.getNumberOfDoubleBeds() + "]";
	}

	public static ArrayList<Room> getRoomsByHotel(Hotel hotel)
	{
		try
		{
			Connection connection = Database.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT id, numSingleBeds, numDoubleBeds, bathroom, costPerNight FROM Room WHERE hotelId = ?");
			statement.setInt(1, hotel.getId());

			ResultSet results = statement.executeQuery();
			
			ArrayList<Room> rooms = new ArrayList<>();
			
			while (results.next() == true)
			{
				Room room = new Room();
				room.id = results.getInt("id");
				room.hotelId = hotel.getId();
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
			System.out.println(exc.getMessage());
			return null;
		}
	}
}
