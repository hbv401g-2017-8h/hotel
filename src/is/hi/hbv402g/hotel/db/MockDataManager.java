package is.hi.hbv402g.hotel.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import is.hi.hbv402g.hotel.models.BookingNight;
import is.hi.hbv402g.hotel.models.Guest;
import is.hi.hbv402g.hotel.models.Room;

public class MockDataManager implements IDataManager
{
	private ArrayList<Room> rooms;
	
	// Create a mock data manager which returns the given list of rooms 
	// regardless of input in findHotelRooms 
	public MockDataManager(ArrayList<Room> rooms)
	{
		this.rooms = rooms;
	}

	// Return the list of rooms given in the constructor
	public ArrayList<Room> findHotelRooms(
			String hotelName, 
			String location, 
			Date availabilityFrom, 
			Date availabilityTo)
	{
		return this.rooms;
	}
	
	public void saveBookingNights(ArrayList<BookingNight> bookingNights)
	{
		
	}
	
	public Integer saveGuest(Guest g)
	{
		return null;
	}
	
	public ArrayList<String> getListOfAmenities()
	{
		return new ArrayList<String>(Arrays.asList("Wifi", "Room service"));
	}

}
