import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides the main control structure for the project. Contains the main Warehouse
 * and a list of Van Warehouses, and handles all user I/O.
 * @author Charles, Libbie, Anna Totten
 *
 */
public class Main
{
	private static final String MAIN_NAME = "mainWarehouse";
    private static final String DB_FILE_NAME = "mainWarehouse.warehouse.txt";
    private static final String FILE_MODIFIER = "warehouse.txt";
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
                "addvan",
                "transfer",
                "quit"};
    
    /**
     * Call to start the entire program, including initializing the warehouse, and starting the user interface
     */
    public void run()
    {
    	mainWarehouse = new Warehouse(MAIN_NAME);
    	vans = new ArrayList<Warehouse>();
    	userIn = new Scanner(System.in);
    	run = true;
    	
    	/*
    	 * Import existing Warehouse and Vans from files.
    	 * Warehouse file to be: "warehouseDB.txt", specified by DB_FILE_NAME
    	 * Van files to be: "Van" + [van number] ex: Van1, Van2, Van3...Van[n] for n vans
    	 * Delete files after import.
    	 */
    	
    	importData();
    	
    	//Enter control loop, until user chooses to exit
    	while(run)
    	{
    		displayUI();
    		System.out.print("\n");
    	}
    	
    	userIn.close();
    }
    
    /**
     * Helper method to run(). Imports existing data from text files before run.
     */
    private void importData()
    {
    	/*
    	 * IMPORTANT: This string contains the absolute file path to the directory
    	 * the application was run in. Use it to walk the existing files in the 
    	 * directory.
    	 * 
    	 * The following code creates a directory at the path, and gets a list of all
    	 * files/directories in it. 
    	 */
    	String absolutePath = System.getProperty("user.dir");
    	
    	File curDirectory = new File(absolutePath);
    	File[] files = null;
    	
    	if (curDirectory.isDirectory())
    	{
    		files = curDirectory.listFiles();
    	}
    	
    	if (files == null)
    	{
    		System.err.println("Something went wrong reading the file structure.");
    		System.exit(1);
    	}
    	
    	//Get all .warehouse.txt files from the current directory, then create
    	//Warehouse objects from them
    	
    	for (File curFile : files)
    	{
    		if (curFile.exists())
    		{
    			if (curFile.getName().contains(FILE_MODIFIER))
    			{
    				//File is a warehouse. Let's get that data
    				
    				/*
    				 * This gets the name of the Warehouse. How does this work?
    				 * A .warehouse.txt file has the following format dictated by the
    				 * quit() method: "[warehouseName].warehouse.txt"
    				 * This means that we can take a substring of the file name to 
    				 * remove the trailing 14 characters, which will be the 
    				 * ".warehouse.txt", leaving only [warehouesName].
    				 */
    				
    				String fileName = curFile.getName();
    				fileName = fileName.substring(0, fileName.length() - 14);
    				Warehouse curWarehouse = new Warehouse(fileName);
    				
    				try
    				{
    					Scanner curScan = new Scanner(curFile);
    					while(curScan.hasNext())
    					{
    						String line = curScan.next();
    						String[] pv = line.split(",");
    						//Create new bike part from the ',' delimited line
    		                BikePart bp = new BikePart(pv[0], Integer.parseInt(pv[1]), Double.parseDouble(pv[2]),
    		                        Double.parseDouble(pv[3]), Boolean.parseBoolean(pv[4]), Integer.parseInt(pv[5]));
    		                
    		                curWarehouse.add(bp);
    					}
    					
    					curScan.close();
    				}
    				catch (FileNotFoundException e)
    				{
    					System.err.println("Somehow, the DB file that just existed "
    							+ "doesn't exist anymore.");
    					e.printStackTrace();
    				}
    				
    				if (curWarehouse.getName().equals(MAIN_NAME))
    				{
    					mainWarehouse = curWarehouse;
    				}
    				else
    				{
    					vans.add(curWarehouse);
    				}
    				
    				curFile.delete();
    			}
    		}
    	}
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
     * Transfer inventory between the warehouses and sales vans. Should use the 
     * helper methods transferFromMain() and transferFromVan().
     */
    private void transfer()
    {
    	//TODO finish transfer()
    	
    	//Get transfer filename
    	
    	//read first line, determine if it's a transfer from the mainWarehouse or a 
    	//sales van. Call appropriate helper method.
    	
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
    	/*
    	 * On exit, write existing Warehouse and Vans to files.
    	 * Warehouse file to be: "warehouseDB.txt", specified by DB_FILE_NAME
    	 * Van files to be: "Van" + [van number] EX: Van1, Van2, Van3...Van[n] for n vans
    	 */
    	
    	//Create database file for the mainWarehouse
    	File mainWarehouseFile = new File(DB_FILE_NAME);
    	try
        {
            if (mainWarehouseFile.exists())
            {
            	mainWarehouseFile.delete();
            }
            
            mainWarehouseFile.createNewFile();
            FileWriter dbWriter = new FileWriter(mainWarehouseFile);
            
            for(BikePart part : mainWarehouse.getInventory())
            {
                dbWriter.write(part.toString() + "\n");
            }
            
            dbWriter.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Warehouse database file couldn't be created! "
            		+ "Data will be lost!");
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.err.println("Couldn't write to the warehouse database file, "
            		+ "data will be lost!");
            e.printStackTrace();
        }
    	
    	//Create database files for the vans. ENSURE NO 2 VANS HAVE THE SAME NAME
    	for (int i = 0; i < vans.size(); i++)
    	{
    		Warehouse curVan = vans.get(i);
    		File vanFile = new File(curVan.getName() + FILE_MODIFIER);
        	try
            {
                if (vanFile.exists())
                {
                	vanFile.delete();
                }
                
                vanFile.createNewFile();
                FileWriter vanWriter = new FileWriter(vanFile);
                
                for(BikePart part : curVan.getInventory())
                {
                	vanWriter.write(part.toString() + "\n");
                }
                
                vanWriter.close();
            }
            catch(FileNotFoundException e)
            {
                System.err.println("Van " + curVan.getName() +" database file "
                		+ "couldn't be created! Data will be lost!");
                e.printStackTrace();
            }
            catch(IOException e)
            {
                System.err.println("Couldn't write to van " + curVan.getName() + 
                		" database file, data will be lost!");
                e.printStackTrace();
            }
    	}
    	
    	run = false;
    }

    /**
     * Prints out the UI menu options, and processes the input.
     */
    private void displayUI()
    {
    	// Print out user options
        System.out.println(
                "Please select your option from the following menu:\n" + 
                "Read: Read an inventory delivery file\n" + 
                "Enter: Enter a part\n" + 
                "Sell: Sell a part\n" + 
                "Display: Display a part\n" + 
                "SortName: Sort parts by part name\n" + 
                "SortNumber: Sort parts by part number\n" + 
                "AddVan: Add a new sales van to the fleet\n" +
                "Transfer: Read a inventory transfer file\n" +
                "Quit: Close the program\n" + 
                "Enter your choice:"
                );
        // Get user input
        String input = userIn.next();
        int choice = -1;
        for (int i = 0; i < CHOICES.length; i++)
        {
            if (CHOICES[i].equalsIgnoreCase(input))
            {
                choice = i;
                break;
            }
        }
        System.out.print("\n");
        // Call respective method
        switch(choice)
        {
        case 0:
            read();
            break;
        case 1: 
            add();
            break;
        case 2:
            sell();
            break;
        case 3: 
            display();
            break;
        case 4:
            sortName();
            break;
        case 5:
            sortNumber();
            break;
        case 6:
            addVan();
            break;
        case 7: 
        	transfer();
        	break;
        case 8:
        	quit();
        	break;
        default:
            System.out.println("That didn't match any option, please try again.");
            break;
        }
    }
}