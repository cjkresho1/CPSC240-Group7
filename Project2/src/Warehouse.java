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
	
	/**
	 * Adds a part to the Warehouse's inventory. If a part with the same name exists,
	 * it will update the number, prices, sale status, and quantity.
	 * @param part Part to be added
	 */
	public void add(BikePart part)
	{
	    boolean existingPart = false;
		for (BikePart foundPart : inventory)
		{
		    if(foundPart.getName().equals(part.getName()))
		    {
		        existingPart = true;
		        foundPart.setNumber(part.getNumber());
		        foundPart.setListPrice(part.getListPrice());
		        foundPart.setSalesPrice(part.getSalesPrice());
		        foundPart.setSale(part.getSale());
		        foundPart.setQuantity(foundPart.getQuantity() + part.getQuantity());
		    }
		}
		
		if (!existingPart)
		{
		    inventory.add(part);
		}
	}
	
	/**
	 * Attempt to sell a part matching the part number. Returns null if the part 
	 * is not in the Warehouse. Otherwise, return a bike part with data matching 
	 * the sold part. 
	 * @param partNum Number of the BikePart to be sold
	 * @return The BikePart sold. Null if the part cannot be sold.
	 */
	public BikePart sell(int partNum)
	{
		BikePart reference = null;
        for (BikePart y : inventory)
        {
            if (y.getNumber() == partNum)
            {
                //If the BikePart exists, but has no quantity in stock, print message then exit method.
                if (y.getQuantity() == 0)
                {
                    return null;
                }
                y.setQuantity((y.getQuantity() - 1));
                reference = y;
            }
        }
        
        return reference;
	}
	
	/**
	 * Remove a set of BikeParts from the inventory. Will return a list matching the 
	 * passed list, but with quantities that were removed. Currently, the method will
	 * remove any possible parts, even if the Warehouse doesn't contain enough to fill
	 * the full order. A quantity of -1 indicates the part couldn't be found.
	 * @param requestedParts A list of parts requested from the Warehouse
	 * @return A list of the parts that were removed from the Warehouse
	 */
	public ArrayList<BikePart> remove(ArrayList<BikePart> requestedParts)
	{
		ArrayList<BikePart> removedParts = new ArrayList<BikePart>();
		
		for (BikePart curPart : requestedParts)
		{
			BikePart partToAdd = new BikePart(curPart.getName(), curPart.getNumber(),
					curPart.getListPrice(), curPart.getSalesPrice(), curPart.getSale(), -1);
			
			for (BikePart matchedPart : inventory)
			{
				if (curPart.getName().equals(matchedPart.getName()))
				{
					//TODO find out what we should do if there are not enough parts
					//to meet the request
					
					partToAdd.setQuantity(Math.min(curPart.getQuantity(), matchedPart.getQuantity()));
				}
			}
			
			removedParts.add(partToAdd);
		}
		
		return removedParts;
	}
	
	/**
	 * Add a list of parts to the Warehouse using Warehouse.add(BikePart). Ignores 
	 * parts that have a quantity of less than 1.
	 * @param parts List of parts to be added to the warehouse.
	 */
	public void addParts(ArrayList<BikePart> parts)
	{
	    for (BikePart curPart : parts)
	    {
	    	if (curPart.getQuantity() > 0)
	    	{
	    		this.add(curPart);
	    	}
	    }
	}
	
	/**
	 * Returns a deep copy of the Warehouse's inventory.
	 * @return A deep copy of the Warehouse's inventory.
	 */
	public ArrayList<BikePart> getInventory()
	{
	    ArrayList<BikePart> returnParts =  new ArrayList<BikePart>();
	    
	    for (BikePart cur : inventory)
	    {
	    	BikePart tempPart = new BikePart(cur.getName(), cur.getNumber(), 
	    			cur.getListPrice(), cur.getSalesPrice(), cur.getSale(), cur.getQuantity());
	    	
	    	returnParts.add(tempPart);
	    }
	    
	    return returnParts;
	}
	
	/**
	 * Returns the name of the warehouse.
	 * @return Name of the warehouse.
	 */
	public String getName()
	{
	    return name;
	}
}
