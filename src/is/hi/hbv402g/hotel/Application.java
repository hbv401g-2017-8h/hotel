package is.hi.hbv402g.hotel;

import is.hi.hbv402g.hotel.models.*;

public class Application
{

	public static void main(String[] args)
	{
		Hotel hotel = Hotel.getHotelById(0);

		System.out.println(hotel);
	}

}
