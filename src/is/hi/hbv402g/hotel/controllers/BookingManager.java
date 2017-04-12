package is.hi.hbv402g.hotel.controllers;

import java.util.ArrayList;
import java.util.Date;

import is.hi.hbv402g.hotel.db.IDataManager;
import is.hi.hbv402g.hotel.models.BookingNight;
import is.hi.hbv402g.hotel.models.Guest;
import is.hi.hbv402g.hotel.models.Payment;

public class BookingManager
{
	private Date dateFrom;
	private Date dateTo;
	private ArrayList<BookingNight> bookingNights;
	private Guest guest;
	private Payment payment;
	
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
	
	BookingManager(IDataManager db)
	{
		
	}
	
	public void cancel(ArrayList<BookingNight> bookingNights)
	{
		
	}
	
	public ArrayList<BookingNight> book(Guest guest, Payment payment, Date dateFrom, Date dateTo)
	{
		return bookingNights;
	}

}
