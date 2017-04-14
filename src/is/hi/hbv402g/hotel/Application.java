package is.hi.hbv402g.hotel;

import java.util.Date;
import java.util.ArrayList;

import is.hi.hbv402g.hotel.db.DatabaseManager;
import is.hi.hbv402g.hotel.db.IDataManager;
import is.hi.hbv402g.hotel.db.MockDataManager;
import is.hi.hbv402g.hotel.models.*;

public class Application
{

	public static void main(String[] args)
	{
		IDataManager dataManager = new DatabaseManager();
		//ArrayList<Room> rooms = dataManager.findHotelRooms("saga", "eykjavík", new Date(2017, 4, 12), new Date(2017, 4, 12));
		ArrayList<Room> rooms = dataManager.findHotelRooms("saga", "eykjavík", new Date(2017, 4, 15), new Date(2017, 4, 17));
		
		for (Room r : rooms)
		{
			System.out.println(r.getHotel() + " - " + r);
		}
	}

}
