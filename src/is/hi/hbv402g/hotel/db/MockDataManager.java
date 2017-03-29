package is.hi.hbv402g.hotel.db;

import java.util.ArrayList;
import java.util.Date;

import is.hi.hbv402g.hotel.models.Hotel;
import is.hi.hbv402g.hotel.models.Room;

public class MockDataManager implements IDataManager
{
	private ArrayList<Room> rooms;
	
	public MockDataManager(ArrayList<Room> rooms)
	{
		this.rooms = rooms;
	}

	public ArrayList<Room> findHotelRooms(String hotelName, String location, Date availabilityFrom, Date availabilityTo)
	{
		return this.rooms;
	}

}
