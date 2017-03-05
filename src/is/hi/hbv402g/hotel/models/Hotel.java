package is.hi.hbv402g.hotel.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import is.hi.hbv402g.hotel.db.Database;

public class Hotel {
	
	private String[] images;
	private String[] amenities;
	private String name;
	private String streetAddress;
	private String city;
	private String postalCode;
	private String country;
	private int starCount;
	private Review[] reviews;
	private Room[] rooms;
	
	public static Hotel getHotelById(int id) {
		try
		{
			Connection connection = Database.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT name, streetAddress, city, postalCode, country, starCount FROM hotel WHERE id = ?");
			statement.setInt(1, id);

			ResultSet results = statement.executeQuery();

			Hotel hotel = new Hotel();
			if (results.next()) {
				hotel.setName(results.getString("name"));
				hotel.setStreetAddress(results.getString("streetAddress"));
				hotel.setCity(results.getString("city"));
				hotel.setPostalCode(results.getString("postalCode"));
				hotel.setCountry(results.getString("country"));
				hotel.setStarCount(results.getInt("starCount"));
			}

			return hotel;
		}
		catch (SQLException exc)
		{
			System.out.println(exc.getMessage());
			return null;
		}
	}
	
	public String[] getImages() {
		return images;
	}
	
	public void setImages(String[] images) {
		this.images = images;
	}
	
	public String[] getAmenities() {
		return amenities;
	}
	
	public void setAmenities(String[] amenities) {
		this.amenities = amenities;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getStarCount() {
		return starCount;
	}
	
	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}
	
	public Review[] getReviews() {
		return reviews;
	}
	
	public void setReviews(Review[] reviews) {
		this.reviews = reviews;
	}
	
	public Room[] getRooms() {
		return rooms;
	}
	
	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
	
	public String toString() {
		return "[Hotel name=" + this.getName() + 
				",streetAddress=" + this.getStreetAddress() +
				",city=" + this.getCity() +
				",postalCode=" + this.getPostalCode() +
				",country=" + this.getCountry() +
				",starCount=" + this.getStarCount() +
				"]";
	}
}
