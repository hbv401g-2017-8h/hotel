package is.hi.hbv402g.hotel.models;

import java.util.ArrayList;

public class Hotel
{
	private int id;
	private String[] images;
	private String[] amenities;
	private String name;
	private String streetAddress;
	private String city;
	private String postalCode;
	private String country;
	private int starCount;
	private ArrayList<Review> reviews;
	private ArrayList<Room> rooms;
	
	public Hotel()
	{
		
	}
	
	public Hotel(int id)
	{
		this.id = id;
	}

	public String[] getImages()
	{
		return images;
	}

	public void setImages(String[] images)
	{
		this.images = images;
	}

	public String[] getAmenities()
	{
		return amenities;
	}

	public void setAmenities(String[] amenities)
	{
		this.amenities = amenities;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStreetAddress()
	{
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress)
	{
		this.streetAddress = streetAddress;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public int getStarCount()
	{
		return starCount;
	}

	public void setStarCount(int starCount)
	{
		this.starCount = starCount;
	}

	public ArrayList<Review> getReviews()
	{
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews)
	{
		this.reviews = reviews;
	}

	public ArrayList<Room> getRooms()
	{
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms)
	{
		this.rooms = rooms;
	}

	public String toString()
	{
		return "[Hotel id=" + this.id + 
				", name=" + this.getName() + 
				", streetAddress=" + this.getStreetAddress() + 
				", city=" + this.getCity() + 
				", postalCode=" + this.getPostalCode() + 
				", country=" + this.getCountry() + 
				", starCount=" + this.getStarCount() + "]";
	}

	public int getId()
	{
		return id;
	}

}
