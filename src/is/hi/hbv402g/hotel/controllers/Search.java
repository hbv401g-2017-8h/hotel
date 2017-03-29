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
	private Integer minimumSingleBeds;
	private Integer maximumSingleBeds;
	private Integer minimumDoubleBeds;
	private Integer maximumDoubleBeds;
	private Integer minimumPrice;
	private Integer maximumPrice;
	private Integer minimumStarCount;
	private Integer maximumStarCount;
	private Integer enSuiteBathroom;
	private Boolean attributesChanged;
	
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

	public void setNumberOfSingleBeds(Integer minimum, Integer maximum)
	{
		this.minimumSingleBeds = minimum;
		this.maximumSingleBeds = maximum;
	}

	public void setNumberOfDoubleBeds(Integer minimum, Integer maximum)
	{
		this.minimumDoubleBeds = minimum;
		this.maximumDoubleBeds = maximum;
	}

	public void setPriceRange(Integer minimum, Integer maximum)
	{
		if(minimum < 0 || maximum < 0){
			throw new IllegalArgumentException("Error: price range must be positive.");
		}
		else {
			this.minimumPrice = minimum;
			this.maximumPrice = maximum;
		}
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
		
		rooms = filterNumberOfSingleBeds(rooms);
		rooms = filterNumberOfDoubleBeds(rooms);
		rooms = filterByPrice(rooms);
		
		this.filteredRooms = rooms;
		return rooms;
	}
	
	private ArrayList<Room> filterAmenities(ArrayList<Room> rooms)
	{
		if (this.amenities == null || this.amenities.size() == 0)
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
	
	private ArrayList<Room> filterNumberOfSingleBeds(ArrayList<Room> rooms)
	{
		if (this.minimumSingleBeds == null && this.maximumSingleBeds == null)
			return rooms;
		
		ArrayList<Room> filteredRooms = new ArrayList<>();
		
		for (Room r : rooms)
		{
			if ((this.minimumSingleBeds == null || r.getNumberOfSingleBeds() >= this.minimumSingleBeds) &&
				(this.maximumSingleBeds == null || r.getNumberOfSingleBeds() <= this.maximumSingleBeds))
			{
				filteredRooms.add(r);
			}
		}
		
		return filteredRooms;
	}
	
	private ArrayList<Room> filterNumberOfDoubleBeds(ArrayList<Room> rooms)
	{
		if (this.minimumDoubleBeds == null && this.maximumDoubleBeds == null)
			return rooms;
		
		ArrayList<Room> filteredRooms = new ArrayList<>();
		
		for (Room r : rooms)
		{
			if ((this.minimumDoubleBeds == null || r.getNumberOfDoubleBeds() >= this.minimumDoubleBeds) &&
				(this.maximumDoubleBeds == null || r.getNumberOfDoubleBeds() <= this.maximumDoubleBeds))
			{
				filteredRooms.add(r);
			}
		}
		
		return filteredRooms;
	}
	
	private ArrayList<Room> filterByPrice(ArrayList<Room> rooms)
	{
		if(this.minimumPrice == null || this.maximumPrice == null)
			return rooms;
		ArrayList<Room> filteredRooms = new ArrayList<>();
		
		for(Room r : rooms)
		{
			if((this.minimumPrice == null || r.getCostPerNight() >= this.minimumPrice) &&
				(this.maximumPrice == null || r.getCostPerNight() <= this.maximumPrice))
			{
				filteredRooms.add(r);
			}
		}
		return filteredRooms;
	}
}