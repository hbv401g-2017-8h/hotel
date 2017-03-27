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
	
	public static Room createRoom(int id, int numberOfSingleBeds,
			int numberOfDoubleBeds, boolean enSuiteBathroom, int costPerNight,
			Hotel hotel)
	{
		Room r = new Room(id);
		
		r.setNumberOfSingleBeds(numberOfSingleBeds);
		r.setNumberOfDoubleBeds(numberOfDoubleBeds);
		r.setEnSuiteBathroom(enSuiteBathroom);
		r.setCostPerNight(costPerNight);
		r.setHotel(hotel);
		
		ArrayList<Room> hotelRooms = hotel.getRooms();
		hotelRooms.add(r);
		
		return r;
	}

}
