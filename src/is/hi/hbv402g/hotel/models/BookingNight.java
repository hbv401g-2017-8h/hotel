package is.hi.hbv402g.hotel.models;

import java.util.ArrayList;
import java.util.Date;

public class BookingNight
{
	private int id;
	private Date date;
	private ArrayList<BookingNight> associatedBookings;
	private int guestId;
	private Guest guest;
	private int paymentId;
	private Payment payment;
	private boolean cancelled;

	public int getId()
	{
		return id;
	}
	
	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public ArrayList<BookingNight> getAssociatedBookings()
	{
		return associatedBookings;
	}

	public void setAssociatedBookings(ArrayList<BookingNight> associatedBookings)
	{
		this.associatedBookings = associatedBookings;
	}
	
	public int getGuestId()
	{
		return guestId;
	}

	public Guest getGuest()
	{
		return guest;
	}

	public void setGuest(Guest guest)
	{
		this.guest = guest;
	}
	
	public int getPaymentId()
	{
		return paymentId;
	}

	public Payment getPayment()
	{
		return payment;
	}

	public void setPayment(Payment payment)
	{
		this.payment = payment;
	}

	public boolean isCancelled()
	{
		return cancelled;
	}

	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}
}
