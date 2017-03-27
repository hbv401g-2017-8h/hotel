package is.hi.hbv402g.hotel.controllers;

import java.util.ArrayList;
import java.util.Date;

import is.hi.hbv402g.hotel.models.Room;

public class Search
{
	private ArrayList<Room> availableRooms;
	private ArrayList<Room> filteredRooms;
	private String hotelName;
	private String hotelStreetAddress;
	private String hotelCity;
	private String hotelPostalCode;
	private String hotelCountry;
	private Date availabilityFrom;
	private Date availabilityTo;
	private ArrayList<String> amenities;
	private int minimumSingleBeds;
	private int maximumSingleBeds;
	private int minimumDoubleBeds;
	private int maximumDoubleBeds;
	private int minimumPrice;
	private int maximumPrice;
	private int minimumStarCount;
	private int maximumStarCount;
	private int enSuiteBathroom;
	private boolean attributesChanged;
	
	public void setHotelName(String name)
	{
		this.hotelName = name;
	}
	
//	public ArrayList<>
}