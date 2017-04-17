package is.hi.hbv402g.hotel;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import is.hi.hbv402g.hotel.db.DatabaseManager;
import is.hi.hbv402g.hotel.db.IDataManager;
import is.hi.hbv402g.hotel.db.MockDataManager;
import is.hi.hbv402g.hotel.models.*;

public class Application
{

	public static void main(String[] args)
	{
//		Guest g = new Guest();
//		g.setName("Viktor");
//		g.setEmail("Email");
//		g.setPhoneNumber("1234567");
//		g.setNumberOfAdults(1);
//		g.setNumberOfChildren(2);
		
		IDataManager dataManager = new DatabaseManager();
//		Integer gId = dataManager.saveGuest(g);
//		System.out.println(gId);
		
		Calendar calFrom = Calendar.getInstance();
		calFrom.set(2017, Calendar.APRIL, 25);

		Calendar calTo = Calendar.getInstance();
		calTo.set(2017, Calendar.MAY, 5);
		
		//ArrayList<Room> rooms = dataManager.findHotelRooms("grand", "eykjavík", new Date(2017, 4, 12), new Date(2017, 4, 12));
		//ArrayList<Room> rooms = dataManager.findHotelRooms("grand", "eykjavík", calFrom.getTime(), calTo.getTime());
		ArrayList<Room> rooms = dataManager.findHotelRooms("grand", "eykjavík", null, null);
		
		for (Room r : rooms)
		{
			System.out.println(r.getHotel() + " - " + r);
		}
	}

}
