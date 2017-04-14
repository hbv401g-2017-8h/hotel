package is.hi.hbv402g.hotel.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
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
	
	public BookingManager(IDataManager db)
	{
		
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
	
	public ArrayList<BookingNight> book(Guest guest, Payment payment, Date dateFrom, Date dateTo)
	{
		System.out.println("A book is booking");
		System.out.println("Guest: " + guest);
		System.out.println("Payment: " + payment);
		System.out.println("Date from: " + dateFrom);
		System.out.println("Date to: " + dateTo);
		
		LocalDate start = dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1))
		{
			System.out.println(date);
		}
		
		return bookingNights;
	}

}
