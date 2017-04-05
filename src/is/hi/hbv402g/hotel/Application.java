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
		ArrayList<Room> rooms = dataManager.findHotelRooms(null, null, null, null);
		
		for (Room r : rooms)
		{
			System.out.println(r);
		}
	}

}
