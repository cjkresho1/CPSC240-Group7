/**
 * @author Charles Kresho, Anna Totten
 * Section number: 16221, 11am MW
 * 
 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help with this assignment.
 */

public class BikePart 
{
	String partName;
	int partNum;
	double price;
	double salePrice;
	boolean onSale;
	
	/**
	 * 
	 * @param name The name of the part
	 * @param num The number of the part
	 * @param _price The price of the part
	 * @param _salePrice The price of the part when onSale is true
	 * @param sale True if the part is on sale, false otherwise
	 */
	public BikePart(String name, int num, double _price, double _salePrice, boolean sale)
	{
		partName = name;
		partNum = num;
		price = _price;
		salePrice = _salePrice;
		onSale = sale;
	}
	
	
	/**
	 * @return the partName
	 */
	public String getPartName() 
	{
		return partName;
	}

	/**
	 * @param partName the partName to set
	 */
	public void setPartName(String partName) 
	{
		this.partName = partName;
	}

	/**
	 * @return the partNum
	 */
	public int getPartNum() 
	{
		return partNum;
	}

	/**
	 * @param partNum the partNum to set
	 */
	public void setPartNum(int partNum) 
	{
		this.partNum = partNum;
	}

	/**
	 * @return the price
	 */
	public double getPrice() 
	{
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) 
	{
		this.price = price;
	}

	/**
	 * @return the salePrice
	 */
	public double getSalePrice() 
	{
		return salePrice;
	}

	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(double salePrice) 
	{
		this.salePrice = salePrice;
	}

	/**
	 * @return the onSale
	 */
	public boolean isOnSale() 
	{
		return onSale;
	}

	/**
	 * @param onSale the onSale to set
	 */
	public void setOnSale(boolean onSale) 
	{
		this.onSale = onSale;
	}
	
	/**
	 * Prints a String representation of the BikePart in the following format:
	 * partName,partNumber,price,salesPrice,onSale
	 * @return A string representation of the BikePart
	 */
	public String toString()
	{
		String val = String.format("%s,%d,%.2f,%.2f,", partName, partNum, price, salePrice);
		
		if (onSale)
		{
			val = val + "true";
		}
		else
		{
			val = val + "false";
		}
		return val;
	}
}
