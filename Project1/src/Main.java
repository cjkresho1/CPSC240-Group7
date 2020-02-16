import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Date;

/**
 * Provides the main control structure for the project. Contains an ArrayList that acts as the warehouse for bike parts.
 * @author Charles, Libbie, Anna Totten
 *
 */
public class Main
{
    ArrayList<BikePart> Warehouse = new ArrayList<>();

    public void run()
    {
        while (true)
            displayUI();
    }

    /**
     * Gets a filename and creates BikeParts from the lines in the text file, then
     * adds them to the array list. If the BikePart already exists, update quantity.
     * If the textfile doesn't exist, print that and exit the method.
     */
    private void read()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String fileName = in.next();
        File file = new File(fileName);
        
        //If the text file doesn't exist, print error message and exist method
        try
        {
            boolean existingFile = false;
            Scanner read = new Scanner(file);
            
            //Iterate over the text file's lines
            while (read.hasNext())
            {
                String line = read.next();
                String[] pv = line.split(",");
                //Create new bike part from the ',' delimited line
                BikePart bp = new BikePart(pv[0], Integer.parseInt(pv[1]), Double.parseDouble(pv[2]),
                        Double.parseDouble(pv[3]), Boolean.parseBoolean(pv[4]), Integer.parseInt(pv[5]));
                for (BikePart x : Warehouse)
                {
                    //If a part with the same name already exists, update all the information about the part from the 
                    //new part.
                    if (x.getName() == bp.getName())
                    {
                        existingFile = true;
                        x.setNumber(bp.getNumber());
                        x.setListPrice(bp.getListPrice());
                        x.setSalesPrice(bp.getSalesPrice());
                        x.setSale(bp.getSale());
                        x.setQuantity((x.getQuantity() + bp.getQuantity()));
                    }
                }
                
                //If the part didn't exist, add it to the warehouse.
                if (existingFile == false)
                {
                    Warehouse.add(bp);
                }
                
                existingFile = false;
            }
            
            read.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        
        in.close();
    }

    /**
     * Prompts the user to enter the information for a BikePart, then adds it to the warehouse.
     */
    private void add()
    {
        Scanner x = new Scanner(System.in);
        System.out.println(
                "Enter your bike part name, list price, sale price, whether it is on sale or not, and the quantity of "
                + "bike parts you want to enter: ");
        try
        {
            String newBikePart = x.next();
            String[] pv = newBikePart.split(",");
            BikePart bp = new BikePart(pv[0], Integer.parseInt(pv[1]), Double.parseDouble(pv[2]),
                    Double.parseDouble(pv[3]), Boolean.parseBoolean(pv[4]), Integer.parseInt(pv[5]));
            boolean existingPart = false;
            for (BikePart y : Warehouse)
            {
                if (y.getName() == bp.getName())
                {
                    existingPart = true;
                    y.setNumber(bp.getNumber());
                    y.setListPrice(bp.getListPrice());
                    y.setSalesPrice(bp.getSalesPrice());
                    y.setSale(bp.getSale());
                    y.setQuantity((y.getQuantity() + bp.getQuantity()));
                }
            }
            if (existingPart == false)
            {
                Warehouse.add(bp);
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Inadequate info!");
        }
        catch(Exception e)
        {
            System.out.println("Incorrect data entered.");
        }
        
        x.close();
    }

    /**
     * Prompt the user for a part number, and attempt to sell a part matching that number.
     */
    private void sell()
    {
        Scanner x = new Scanner(System.in);
        System.out.println("Enter bike part number: ");
        try
        {
            int number = x.nextInt();
            BikePart reference = null;
            for (BikePart y : Warehouse)
            {
                if (y.getNumber() == number)
                {
                    //If the BikePart exists, but has no quantity in stock, print message then exit method.
                    if (y.getQuantity() == 0)
                    {
                        System.out.println("That bike part isn't in stock!");
                        x.close();
                        return;
                    }
                    y.setQuantity((y.getQuantity() - 1));
                    reference = y;
                }
            }
            //If the bike part trying to be sold doesn't exist, print message then exit method.
            if (reference == null)
            {
                System.out.println("That bike part does not exist!");
                x.close();
                return;
            }
            
            //The bike part exists, so print the sales message
            double price = reference.getPrice();
            Date date = new Date();
            String retValue = "Part Name: " + reference.getName() + "\nPart price: " + price;
            if (reference.getSale())
            {
                retValue = retValue + "\nPart was on sale!";
            }
            else
            {
                retValue = retValue + "\nPart was not on sale.";
            }
            retValue = retValue + "\nSold at: " + date.toString();
            System.out.println(retValue);
        }
        catch (Exception e)
        {
            System.out.println("Incorrect data entered.");
        }
        
        x.close();
    }

    public void Display()
    {
        Scanner x = new Scanner(System.in);
        System.out.println("Enter bike part name: ");
        String name = x.nextLine();
        BikePart reference = null;
        double price = 0.0;
        for (BikePart y : Warehouse)
        {
            if (y.getName() == name)
            {
                reference = y;
                if (y.getSale() == true)
                {
                    price = y.getSalesPrice();
                }
                else if (y.getSale() == false)
                {
                    price = y.getListPrice();
                }
                System.out.println(y.getName() + ", " + price);
            }
        }
        if (reference == null)
        {
            System.out.println("That bike part does not exist!");
        }
    }

    private void quit()
    {
        // Output data to text file

        System.exit(0);
    }

    private void displayUI()
    {
        // Print out user options

        // Get user input

        // Call respective method

        // exit
    }
}