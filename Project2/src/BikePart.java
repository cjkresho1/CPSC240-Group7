/**
 * Holds the information important to a Bike Part
 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work.
 * @author Group7
 */
public class BikePart
{
    
    private String name;
    private int number;
    private double listPrice;
    private double salesPrice;
    private boolean sale;
    private int quantity;

    /**
     * Constructs a BikePart object
     *
     * @param name       the name of the bike part
     * @param number     the serial number of the bike part
     * @param listPrice  the list price of the bike part
     * @param salesPrice the price of the bike part if it is on sale
     * @param sale       whether the bike part is on sale or not
     * @param quantity   the quantity of bike parts
     */
    public BikePart(String name, int number, double listPrice, double salesPrice, boolean sale, int quantity)
    {
        this.name = name;
        this.number = number;
        this.listPrice = listPrice;
        this.salesPrice = salesPrice;
        this.sale = sale;
        this.quantity = quantity;
    }

    /**
     * Return the name of the bike
     * @return the name of the bike
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Set the name
     * @param newName name to be set
     */
    public void setName(String newName)
    {
        this.name = newName;
    }

    /**
     * Get the bike number
     * @return the serial number of the bike
     */
    public int getNumber()
    {
        return this.number;
    }

    /**
     * Set the bike number
     * @param newNumber number to be set
     */
    public void setNumber(int newNumber)
    {
        this.number = newNumber;
    }

    /**
     * Get the list price
     * @return the list price of the bike
     */
    public double getListPrice()
    {
        return this.listPrice;
    }

    /**
     * Set the list price
     * @param newListPrice list price to be set
     */
    public void setListPrice(double newListPrice)
    {
        this.listPrice = newListPrice;
    }

    /**
     * Get the sale price of the bike
     * @return the price of the bike if it is on sale
     */
    public double getSalesPrice()
    {
        return this.salesPrice;
    }

    /**
     * Set the sale price of the bike
     * @param newSalesPrice Sales Price to be set
     */
    public void setSalesPrice(double newSalesPrice)
    {
        this.salesPrice = newSalesPrice;
    }

    /**
     * Get if the bike is on sale
     * @return whether the bike is on sale or not
     */
    public boolean getSale()
    {
        return this.sale;
    }

    /**
     * Set if the bike is on sale
     * @param newSale the sale status to be set
     */
    public void setSale(boolean newSale)
    {
        this.sale = newSale;
    }

    /**
     * Get the quantity of bikes
     * @return The quantity of bikes
     */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * Set the quantity of bikes
     * @param newQuantity The quantity of bikes to be set
     */
    public void setQuantity(int newQuantity)
    {
        this.quantity = newQuantity;
    }
    
    /**
     * Get the current price of the bike part, based on the sale status
     * @return The current price of the bike.
     */
    public double getPrice()
    {
        if (sale)
        {
            return salesPrice;
        }
        
        return listPrice;
    }
    
    /**
     * Prints a String representation of the BikePart in the following format:
     * name,number,listPrice,salesPrice,sale,quantity
     * @return A string representation of the BikePart
     */
    public String toString()
    {
        String val = String.format("%s,%d,%.2f,%.2f,", name, number, listPrice, salesPrice);
        
        if (sale)
        {
            val = val + "true";
        }
        else
        {
            val = val + "false";
        }
        
        val = val + "," + quantity;
        return val;
    }
}