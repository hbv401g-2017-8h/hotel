package is.hi.hbv402g.hotel.models;

public class Guest
{
	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private int numberOfAdults;
	private int numberOfChildren;
	
	public Guest()
	{
		
	}
	
	public Guest(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public int getNumberOfAdults()
	{
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults)
	{
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren()
	{
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren)
	{
		this.numberOfChildren = numberOfChildren;
	}

	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}

	public String toString()
	{
		return "[Guest id=" + this.id + 
				", name=" + this.getName() + 
				", email=" + this.getEmail() + 
				", phoneNumber=" + this.getPhoneNumber() + 
				", numberOfAdults=" + this.getNumberOfAdults() + 
				", numberOfChildren=" + this.getNumberOfChildren() + "]";
	}
}
