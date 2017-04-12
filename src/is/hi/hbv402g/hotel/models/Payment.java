package is.hi.hbv402g.hotel.models;

public class Payment
{
	private int amount;
	private boolean completed;
	private boolean canceled;
	
	Payment()
	{
		
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public boolean isCompleted()
	{
		return completed;
	}

	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}

	public boolean isCanceled()
	{
		return canceled;
	}

	public void setCanceled(boolean canceled)
	{
		this.canceled = canceled;
	}
	
	public void cancel()
	{
		
	}

}
