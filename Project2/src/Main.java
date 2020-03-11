import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Date;

/**
 * Provides the main control structure for the project. Contains an ArrayList that acts as the warehouse for bike parts.
 * @author Charles, Libbie, Anna Totten
 *
 */
public class Main
{
	private static final String MAIN_NAME = "mainWarehouse";
    private static final String DB_FILE_NAME = "warehouseDB.txt";
    private Warehouse mainWarehouse;
    private ArrayList<Warehouse> vans;
    private Scanner userIn;
    private boolean run;
    private static final String CHOICES[] = {"read",
                "enter",
                "sell",
                "display",
                "sortname",
                "sortnumber",
                "quit"};
    
    /**
     * Call to start the entire program, including initializing the warehouse, and starting the user interface
     */
    public void run()
    {
        //TODO finish run()
    	
    	/*
    	 * Import existing Warehouse and Vans from files.
    	 * Warehouse file to be: "warehouseDB.txt", specified by DB_FILE_NAME
    	 * Van files to be: "Van" + [van number] ex: Van1, Van2, Van3...Van[n] for n vans
    	 * Delete files after import.
    	 */
    	
    	//Enter control loop, until user chooses to exit
    	
    }

    /**
     * Gets a filename and creates BikeParts from the lines in the text file, then
     * adds them to the array list. If the BikePart already exists, update quantity.
     * If the textfile doesn't exist, print that and exit the method.
     */
    private void read()
    {
        //TODO finish read()
    	
    	//Get delivery filename, ensure the file exists. Exit if file doesn't exist
    	
    	//Read line by line to create new BikePart. Add bike parts to mainWarehouse
    }

    /**
     * Prompts the user to enter the information for a BikePart, then adds it to the warehouse.
     */
    private void add()
    {
    	//TODO finish add()
    	
    	//Interactively (field by field) prompt the user for each BikePart field
    	
    	//Create new BikePart from information and add it to the mainWarehouse
    }

    /**
     * Prompt the user for a part number, and attempt to sell a part matching that number.
     */
    private void sell()
    {
    	//TODO finish sell()
    	
    	//Get number of a BikePart from the user
    	
    	//Get warehouse that the user wants to sell from. 
    	
    	/*
    	 * If the BikePart exists in the warehouse, sell one and display the part
    	 * name, part cost, and the time the part was sold CLEANLY. Otherwise, print 
    	 * error message and exit method.
    	 */
    }

    /**
     * Prompt the user for a part name, then print the name and current price for the part.
     */
    private void display()
    {
    	//TODO finish display()
    	
    	//Get name of a BikePart from the user
    	
    	//If the part exists, print the part information CLEANLY. Otherwise print an error message and exit method
    }

    /**
     * Sorts the warehouse by part name, then prints the contents
     */
    private void sortName()
    {
    	//TODO sortName()
    	
    	/*
    	 * Get whether the user wants to display the mainWarehouse, a van, or all of
    	 * the existing inventory. 
    	 */
    	
    	//Create temporary Warehouse object. Add all specified warehouses to it
    	
    	//Sort the temporary Warehouse, and print the parts CLEANLY
    }
    
    /**
     * Sorts the warehouse by part number, then prints the contents
     */
    private void sortNumber()
    {
    	//TODO sortNumber()
    	
    	/*
    	 * Get whether the user wants to display the mainWarehouse, a van, or all of
    	 * the existing inventory. 
    	 */
    	
    	//Create temporary Warehouse object. Add all specified warehouses to it
    	
    	//Sort the temporary Warehouse, and print the parts CLEANLY
    }
    
    /**
     * Add a new van to the fleet. Get the van name, then create a new empty van.
     */
    private void addVan()
    {
    	//TODO finish addVan()
    	
    	//Get the van name from the user.
    	
    	//Create a new Warehouse with the name, the add it to vans
    }
    
    /**
     * Transfer parts between the mainWarehouse to a Van. Uses a text file.
     * 
     * First line of the transfer file should be:
     * 		[mainWarehouseName],[destinationVanName]
     * Other lines should be: 
     * 		[bikePartName],[quantity]
     * 
     * Attempt to transfer the specified quantity of parts. If the quantity isn't
     * available, //XXX SHOULD WE TRANSFER WHAT'S AVAILABLE, OR NOT TRANSFER ANY?
     */
    private void transferFromMain()
    {
    	//TODO finish transferFromMain()
    	
    	//get filename
    	
    	//read destination van, and requested BikeParts
    	
    	//Transfer BikeParts
    }
    
    /**
     * Transfer parts between the mainWarehouse to a Van. Uses a text file.
     * 
     * First line of the transfer file should be:
     * 		[destinationVan],[sourceVan]
     * Other lines should be: 
     * 		[bikePartName],[quantity]
     * 
     * Attempt to transfer the specified quantity of parts. If the quantity isn't
     * available, //XXX SHOULD WE TRANSFER WHAT'S AVAILABLE, OR NOT TRANSFER ANY?
     */
    private void transferFromVan()
    {
    	//TODO finish transferFromVan()
    	
    	//get filename
    	
    	//read source van, destination van, and requested BikeParts
    	
    	//Transfer BikeParts
    }
    
    /**
     * Prints the contents of the warehouse to System.out in the following format:
     * name,number,listPrice,salesPrice,sale,quantity
     */
    private void printWarehouse(Warehouse house)
    {
    	//TODO finish printWarehouse()
    	
    	//Print the Warehouse inventory as is in a table format
    }
    
    /*
     * Exits the program. Writes the existing warehouse to the database file
     */
    private void quit()
    {
    	//TODO finish quit()
    	
    	/*
    	 * On exit, write existing Warehouse and Vans to files.
    	 * Warehouse file to be: "warehouseDB.txt", specified by DB_FILE_NAME
    	 * Van files to be: "Van" + [van number] EX: Van1, Van2, Van3...Van[n] for n vans
    	 */
    }

    /**
     * Prints out the UI menu options, and processes the input.
     */
    private void displayUI()
    {
    	//TODO finish displayUI()
    	
    	//Print options menu, and get user input
    	
    	/*
    	 * On user input, switch on the specified option, calling the proper method,
    	 * then exit this method
    	 */
    }
}