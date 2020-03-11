import java.util.ArrayList;

public class Warehouse 
{
	private ArrayList<BikePart> inventory;
	private String name;
	
	
	public Warehouse(String name)
	{
		inventory = new ArrayList<BikePart>();
		this.name = name;
	}
	
	public void add(BikePart part)
	{
		//XXX Create flow
	}
	
	public String sell(BikePart part)
	{
		//XXX Create flow
	    
	    return null;
	}
	
	public ArrayList<BikePart> remove(ArrayList<BikePart> requestedParts)
	{
	    return null;
	}
	
	public void addParts(ArrayList<BikePart> parts)
	{
	    
	}
	
	public ArrayList<BikePart> getInventory()
	{
	    return new ArrayList<BikePart>(inventory);
	}
	
	public String getName()
	{
	    return name;
	}
}
