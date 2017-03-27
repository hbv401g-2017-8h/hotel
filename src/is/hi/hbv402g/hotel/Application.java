package is.hi.hbv402g.hotel;

import java.util.Date;
import java.util.ArrayList;

import is.hi.hbv402g.hotel.db.IDataManager;
import is.hi.hbv402g.hotel.db.MockDataManager;
import is.hi.hbv402g.hotel.models.*;

public class Application
{

	public static void main(String[] args)
	{
		IDataManager dataManager = new MockDataManager();
		
		ArrayList<Room> rooms = dataManager.findHotelRooms(
				"Shitty hotel", "A location", new Date(2017, 1, 1), new Date(2017, 12, 1));
		
		for (Room r : rooms)
		{
			System.out.println(r);
		}
	}

}
