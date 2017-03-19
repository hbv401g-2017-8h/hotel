package is.hi.hbv402g.hotel;

import java.util.ArrayList;

import is.hi.hbv402g.hotel.models.*;

public class Application
{

	public static void main(String[] args)
	{
		Hotel hotel = Hotel.getHotelById(666);

		System.out.println(hotel);
		
		ArrayList<Room> rooms = hotel.getRooms();
		
		for (Room r : rooms)
		{
			System.out.println(r);
		}
	}

}
