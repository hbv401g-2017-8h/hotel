package is.hi.hbv402g.hotel.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import is.hi.hbv402g.hotel.db.IDataManager;
import is.hi.hbv402g.hotel.models.Room;

public class Search
{
	private IDataManager db;
	private ArrayList<Room> availableRooms;
	private ArrayList<Room> filteredRooms;
	private ArrayList<String> amenities  = new ArrayList<>();
	private Integer minimumSingleBeds;
	private Integer maximumSingleBeds;
	private Integer minimumDoubleBeds;
	private Integer maximumDoubleBeds;
	private Integer minimumPrice;
	private Integer maximumPrice;
	private Integer minimumStarCount;
	private Integer maximumStarCount;
	private boolean enSuiteBathroom;

	public enum SortBy
	{
		PRICE_DESCENDING,
		PRICE_ASCENDING,
		STARCOUNT_DESCENDING,
		STARCOUNT_ASCENDING,
		HOTEL_NAME_AZ,
		HOTEL_NAME_ZA,
		NONE
	}
	
	public Search(IDataManager db)
	{
		this.db = db;
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
		this.amenities.remove(amenity); // alert if not completed?
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
		if ((minimum != null && minimum < 0) || (maximum != null && maximum < 0))
		{
			throw new IllegalArgumentException("Error: price range must be positive.");
		}
		else
		{
			this.minimumPrice = minimum;
			this.maximumPrice = maximum;
		}
	}

	public void setStarCount(Integer minimum, Integer maximum)
	{
		if (minimum != null && minimum < 1 || maximum != null && maximum < 1)
		{
			throw new IllegalArgumentException("Error: Both minimum and maximum star count must be positive integers.");
		}
		else if (minimum != null && minimum > 5 || maximum != null && maximum > 5)
		{
			throw new IllegalArgumentException("Error: Both minimum and maximum star count must be less than 5.");
		}
		else
		{
			this.minimumStarCount = minimum;
			this.maximumStarCount = maximum;
		}
	}

	public void setEnSuiteBathroom(boolean enSuiteBathroom)
	{
		this.enSuiteBathroom = enSuiteBathroom;
	}

	public ArrayList<Room> getSearchResults()
	{
		return this.filteredRooms;
	}

	public ArrayList<Room> find(String hotelName, String location, Date availabilityFrom, Date availabilityTo)
	{
		this.availableRooms = db.findHotelRooms(hotelName, location, availabilityFrom, availabilityTo);
		this.filteredRooms = this.availableRooms;
		return this.availableRooms;
	}

	public ArrayList<Room> sort(SortBy sb)
	{
		if (sb == SortBy.HOTEL_NAME_AZ)
			Collections.sort(this.filteredRooms, new Comparator<Room>() {
				@Override
				public int compare(Room a, Room b)
				{
					return a.getHotel().getName().compareTo(b.getHotel().getName());
				}
			});
		else if (sb == SortBy.HOTEL_NAME_ZA)
			Collections.sort(this.filteredRooms, new Comparator<Room>() {
				@Override
				public int compare(Room a, Room b)
				{
					return b.getHotel().getName().compareTo(a.getHotel().getName());
				}
			});
		else if (sb == SortBy.PRICE_ASCENDING)
			Collections.sort(this.filteredRooms, new Comparator<Room>() {
				@Override
				public int compare(Room a, Room b)
				{
					return Integer.compare(a.getCostPerNight(), b.getCostPerNight());
				}
			});
		else if (sb == SortBy.PRICE_DESCENDING)
			Collections.sort(this.filteredRooms, new Comparator<Room>() {
				@Override
				public int compare(Room a, Room b)
				{
					return Integer.compare(b.getCostPerNight(), a.getCostPerNight());
				}
			});
		else if (sb == SortBy.STARCOUNT_ASCENDING)
			Collections.sort(this.filteredRooms, new Comparator<Room>() {
				@Override
				public int compare(Room a, Room b)
				{
					return Integer.compare(a.getHotel().getStarCount(), b.getHotel().getStarCount());
				}
			});
		else if (sb == SortBy.STARCOUNT_DESCENDING)
			Collections.sort(this.filteredRooms, new Comparator<Room>() {
				@Override
				public int compare(Room a, Room b)
				{
					return Integer.compare(b.getHotel().getStarCount(), a.getHotel().getStarCount());
				}
			});
		
		return filteredRooms;
	}
	
	public ArrayList<Room> filter()
	{
		ArrayList<Room> rooms = this.availableRooms;

		rooms = filterAmenities(rooms);
		rooms = filterNumberOfSingleBeds(rooms);
		rooms = filterNumberOfDoubleBeds(rooms);
		rooms = filterByPrice(rooms);
		rooms = filterByStarCount(rooms);
		rooms = filterByEnSuiteBathroom(rooms);

		this.filteredRooms = rooms;
		return this.filteredRooms;
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
			if ((this.minimumSingleBeds == null || r.getNumberOfSingleBeds() >= this.minimumSingleBeds)
					&& (this.maximumSingleBeds == null || r.getNumberOfSingleBeds() <= this.maximumSingleBeds))
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
			if ((this.minimumDoubleBeds == null || r.getNumberOfDoubleBeds() >= this.minimumDoubleBeds)
					&& (this.maximumDoubleBeds == null || r.getNumberOfDoubleBeds() <= this.maximumDoubleBeds))
			{
				filteredRooms.add(r);
			}
		}

		return filteredRooms;
	}

	private ArrayList<Room> filterByPrice(ArrayList<Room> rooms)
	{
		if (this.minimumPrice == null && this.maximumPrice == null)
			return rooms;

		ArrayList<Room> filteredRooms = new ArrayList<>();

		for (Room r : rooms)
		{
			if ((this.minimumPrice == null || r.getCostPerNight() >= this.minimumPrice)
					&& (this.maximumPrice == null || r.getCostPerNight() <= this.maximumPrice))
			{
				filteredRooms.add(r);
			}
		}
		return filteredRooms;
	}

	private ArrayList<Room> filterByStarCount(ArrayList<Room> rooms)
	{
		if (this.minimumStarCount == null && this.maximumStarCount == null)
			return rooms;
		ArrayList<Room> filteredRooms = new ArrayList<>();

		for (Room r : rooms)
		{
			if ((this.minimumStarCount == null || r.getHotel().getStarCount() >= this.minimumStarCount)
					&& (this.maximumStarCount == null || r.getHotel().getStarCount() <= this.maximumStarCount))
			{
				filteredRooms.add(r);
			}
		}
		return filteredRooms;
	}

	private ArrayList<Room> filterByEnSuiteBathroom(ArrayList<Room> rooms)
	{
		if (this.enSuiteBathroom == false)
			return rooms;

		ArrayList<Room> filteredRooms = new ArrayList<>();
		for (Room r : rooms)
		{
			if (r.getEnSuiteBathroom())
			{
				filteredRooms.add(r);
			}
		}

		return filteredRooms;
	}
	
	public ArrayList<String> getListOfAmenities()
	{
		return db.getListOfAmenities();
	}
}



















