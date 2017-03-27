package is.hi.hbv402g.hotel.controllers;

import java.util.ArrayList;
import java.util.Date;

import is.hi.hbv402g.hotel.models.Room;

public class Search
{
	//private IDatabaseManager db;
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
	
	public void setAvailabilityFrom(Date availabilityFrom)
	{
		this.availabilityFrom = availabilityFrom;
	}

	public void setAvailabilityTo(Date availabilityTo)
	{
		this.availabilityTo = availabilityTo;
	}

	public void setHotelStreetAddress(String hotelStreetAddress)
	{
		this.hotelStreetAddress = hotelStreetAddress;
	}

	public void setHotelCity(String hotelCity)
	{
		this.hotelCity = hotelCity;
	}

	public void setHotelPostalCode(String hotelPostalCode)
	{
		this.hotelPostalCode = hotelPostalCode;
	}

	public void setHotelCountry(String hotelCountry)
	{
		this.hotelCountry = hotelCountry;
	}

	

	public void addAmenity(String amenity)
	{
		this.amenities.add(amenity);
	}
	
	public void removeAmenity(String amenity)
	{
		this.amenities.remove(amenity); //alert if not completed?
		
	}

	public void setNumberOfSingleBeds(int minimum, int maximum)
	{
		this.minimumSingleBeds = minimum;
		this.maximumSingleBeds = maximum;
	}

	public void setNumberOfDoubleBeds(int minimum, int maximum)
	{
		this.minimumDoubleBeds = minimum;
		this.maximumDoubleBeds = maximum;
	}

	public void setPriceRange(int minimum, int maximum)
	{
		this.minimumPrice = minimum;
		this.maximumPrice = maximum;
	}

	public void setStarCount(int minimum, int maximum)
	{
		this.minimumStarCount = minimumStarCount;
		this.maximumStarCount = maximumStarCount;
	}

	public void setEnSuiteBathroom(int enSuiteBathroom)
	{
		this.enSuiteBathroom = enSuiteBathroom;
	}

	public void setAttributesChanged(boolean attributesChanged)
	{
		this.attributesChanged = attributesChanged;
	}
	

}