package is.hi.hbv402g.hotel.db;

import java.util.ArrayList;
import java.util.Date;

import is.hi.hbv402g.hotel.exceptions.DoubleBookedException;
import is.hi.hbv402g.hotel.models.BookingNight;
import is.hi.hbv402g.hotel.models.Guest;
import is.hi.hbv402g.hotel.models.Room;

public interface IDataManager
{
	public ArrayList<Room> findHotelRooms(String hotelName, String location, Date availabilityFrom, Date availabilityTo);
	public void saveBookingNights(ArrayList<BookingNight> bookingNights) throws DoubleBookedException;
	public Integer saveGuest(Guest g);
	public ArrayList<String> getListOfAmenities();
}
