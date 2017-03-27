package is.hi.hbv402g.hotel.models;

public class Room
{
	private int id;
	private int numberOfSingleBeds;
	private int numberOfDoubleBeds;
	private boolean enSuiteBathroom;
	private Hotel hotel;
	private int hotelId;
	private int costPerNight;
	
	public Room()
	{
		
	}
	
	public Room(int id)
	{
		this.id = id;
	}

	public int getNumberOfSingleBeds()
	{
		return numberOfSingleBeds;
	}

	public void setNumberOfSingleBeds(int numberOfSingleBeds)
	{
		this.numberOfSingleBeds = numberOfSingleBeds;
	}

	public int getNumberOfDoubleBeds()
	{
		return numberOfDoubleBeds;
	}

	public void setNumberOfDoubleBeds(int numberOfDoubleBeds)
	{
		this.numberOfDoubleBeds = numberOfDoubleBeds;
	}

	public boolean getEnSuiteBathroom()
	{
		return enSuiteBathroom;
	}

	public void setEnSuiteBathroom(boolean enSuiteBathroom)
	{
		this.enSuiteBathroom = enSuiteBathroom;
	}

	public Hotel getHotel()
	{
		return hotel;
	}

	public void setHotel(Hotel hotel)
	{
		this.hotel = hotel;
		this.hotelId = hotel.getId();
	}

	public int getCostPerNight()
	{
		return costPerNight;
	}

	public void setCostPerNight(int costPerNight)
	{
		this.costPerNight = costPerNight;
	}

	public int getId()
	{
		return id;
	}

	public String toString()
	{
		return "[Room id=" + this.id +
				", hotel=" + this.hotelId + 
				", costPerNight=" + this.getCostPerNight() +
				", enSuiteBathroom=" + this.getEnSuiteBathroom() + 
				", numberOfSingleBeds=" + this.getNumberOfSingleBeds() +
				", numberOfDoubleBeds=" + this.getNumberOfDoubleBeds() + "]";
	}

}
