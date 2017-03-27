package is.hi.hbv402g.hotel.db;

import java.util.ArrayList;
import java.util.Date;

import is.hi.hbv402g.hotel.models.Hotel;
import is.hi.hbv402g.hotel.models.Room;

public class MockDataManager implements IDataManager
{

	public ArrayList<Room> findHotelRooms(String hotelName, String location, Date availabilityFrom, Date availabilityTo)
	{
		Hotel hotel = new Hotel(666);
		hotel.setName(hotelName);
		
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(createRoom(1, 1, 0, false, 5000, hotel));
		rooms.add(createRoom(2, 0, 1, true, 9000, hotel));
		rooms.add(createRoom(3, 0, 2, false, 12000, hotel));
		rooms.add(createRoom(4, 3, 3, true, 15000, hotel));
		
		return rooms;
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
