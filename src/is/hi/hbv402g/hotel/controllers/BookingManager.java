package is.hi.hbv402g.hotel.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import is.hi.hbv402g.hotel.db.DatabaseManager;
import is.hi.hbv402g.hotel.db.IDataManager;
import is.hi.hbv402g.hotel.models.BookingNight;
import is.hi.hbv402g.hotel.models.Guest;
import is.hi.hbv402g.hotel.models.Payment;
import is.hi.hbv402g.hotel.models.Room;

public class BookingManager
{
	private IDataManager db;
	private Date dateFrom;
	private Date dateTo;
	private ArrayList<BookingNight> bookingNights;
	private Guest guest;
	private Payment payment;
	
	public BookingManager(IDataManager db)
	{
		this.db = db;
	}
	
	public Date getDateFrom()
	{
		return dateFrom;
	}
	
	public void setDateFrom(Date dateFrom)
	{
		this.dateFrom = dateFrom;
	}
	
	public Date getDateTo()
	{
		return dateTo;
	}
	
	public void setDateTo(Date dateTo)
	{
		this.dateTo = dateTo;
	}
	
	public ArrayList<BookingNight> getBookingNights()
	{
		return bookingNights;
	}
	
	public void setBookingNights(ArrayList<BookingNight> bookingNights)
	{
		this.bookingNights = bookingNights;
	}
	
	public Guest getGuest()
	{
		return guest;
	}
	
	public void setGuest(Guest guest)
	{
		this.guest = guest;
	}
	
	public Payment getPayment()
	{
		return payment;
	}
	
	public void setPayment(Payment payment)
	{
		this.payment = payment;
	}
	
	public void cancel(ArrayList<BookingNight> bookingNights)
	{
		
	}
	
	public ArrayList<BookingNight> book(Guest guest, Room room, Payment payment, Date dateFrom, Date dateTo)
	{
		LocalDate start = dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		Integer guestId = db.saveGuest(guest);
		if (guestId != null)
			guest.setId(guestId.intValue());
		
		ArrayList<BookingNight> bookingNights = new ArrayList<>();
		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1))
		{
			BookingNight bn = new BookingNight();
			bn.setGuest(guest);
			bn.setRoom(room);
			bn.setDate(java.sql.Date.valueOf(date));
			bookingNights.add(bn);
		}
		
		db.saveBookingNights(bookingNights);
		
		return bookingNights;
	}

}
