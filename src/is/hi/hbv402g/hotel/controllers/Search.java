package is.hi.hbv402g.hotel.controllers;

import java.util.ArrayList;
import java.util.Date;

import is.hi.hbv402g.hotel.db.IDataManager;
import is.hi.hbv402g.hotel.models.Room;

public class Search
{
	private IDataManager db;
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
	
	public Search(IDataManager db)
	{
		this.db = db;
	}
	
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
		if (this.amenities == null)
			this.amenities = new ArrayList<>();
		this.amenities.add(amenity);
	}
	
	public void removeAmenity(String amenity)
	{
		if (this.amenities == null)
			this.amenities = new ArrayList<>();
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
	
	public ArrayList<Room> find(String hotelName, String location, Date availabilityFrom, Date availabilityTo)
	{
		this.availableRooms = db.findHotelRooms(hotelName, location, availabilityFrom, availabilityTo); 
		return availableRooms;
	}
	
	public ArrayList<Room> filter()
	{
		ArrayList<Room> rooms = this.availableRooms;
		
		rooms = filterAmenities(rooms);
		
		this.filteredRooms = rooms;
		return rooms;
	}
	
	private ArrayList<Room> filterAmenities(ArrayList<Room> rooms)
	{
		if (this.amenities.size() == 0 && this.amenities == null)
			return rooms;
		
		ArrayList<Room> filteredRooms = new ArrayList<>();
		
		for (Room r : rooms)
		{
			if (r.getHotel().getAmenities().containsAll(this.amenities))
			{
				filteredRooms.add(r);
			}
		}
		
		return filteredRooms;
	}
}