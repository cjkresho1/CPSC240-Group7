import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

/**
 * Provides the main control structure for the project. Contains the main Warehouse
 * and a list of Van Warehouses, and handles all user I/O.
 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work.
 * @author Charles, Libbie, Anna Totten
 *
 */
public class Main
{
	private static final String MAIN_NAME = "mainWarehouse";
    private static final String DB_FILE_NAME = "mainWarehouse.warehouse.txt";
    private static final String FILE_MODIFIER = ".warehouse.txt";
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
    	System.out.println("Enter file name: ");
        String fileName = userIn.next();
        File file = new File(fileName);
        
        //If the text file doesn't exist, print error message and exist method
        try
        {
            Scanner read = new Scanner(file);
            
            //Iterate over the text file's lines
            while (read.hasNext())
            {
                String line = read.next();
                String[] pv = line.split(",");
                //Create new bike part from the ',' delimited line
                BikePart bp = new BikePart(pv[0], Integer.parseInt(pv[1]), Double.parseDouble(pv[2]),
                        Double.parseDouble(pv[3]), Boolean.parseBoolean(pv[4]), Integer.parseInt(pv[5]));
                
                //Add the BikePart to the main warehouse
                mainWarehouse.add(bp);
            }
            
            read.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
    }

    /**
     * Prompts the user to enter the information for a BikePart, then adds it to the warehouse.
     */
    private void add()
    {
    	
    	String partName, partNumberStr, partPriceStr, partSalePriceStr, partOnSaleStr,
    		partQuantityStr = null;
    	int partNumber = -1, partQuantity = -1;
    	double partPrice = -1, partSalePrice = -1;
    	boolean partOnSale = false, dataEntered = false;
    	
    	
    	System.out.print("Enter the bike part name: ");
    	partName = userIn.next();
    	
    	while(!dataEntered)
    	{
    		dataEntered = true;
	    	System.out.print("Enter the bike part number: ");
	    	partNumberStr = userIn.next();
	    	try
	    	{
	    		partNumber = Integer.parseInt(partNumberStr);
	    	}
	    	catch (IllegalArgumentException e)
	        {
	            System.out.println("The part number entered wasn't a number. Please try again.");
	            dataEntered = false;
	        }
	        catch(Exception e)
	        {
	            System.out.println("Incorrect data entered.");
	            dataEntered = false;
	        }
    	}
    	dataEntered = false;
    	
    	while(!dataEntered)
    	{
    		dataEntered = true;
	    	System.out.print("Enter the bike part price: ");
	    	partPriceStr = userIn.next();
	    	try
	    	{
	    		partPrice = Double.parseDouble(partPriceStr);
	    	}
	    	catch (IllegalArgumentException e)
	        {
	            System.out.println("The part price entered wasn't a number. Please try again.");
	            dataEntered = false;
	        }
	        catch(Exception e)
	        {
	            System.out.println("Incorrect data entered.");
	            dataEntered = false;
	        }
    	}
    	dataEntered = false;
    	
    	while(!dataEntered)
    	{
    		dataEntered = true;
	    	System.out.print("Enter the bike part sale price: ");
	    	partSalePriceStr = userIn.next();
	    	try
	    	{
	    		partSalePrice = Double.parseDouble(partSalePriceStr);
	    	}
	    	catch (IllegalArgumentException e)
	        {
	            System.out.println("The part sale price entered wasn't a number. Please try again.");
	            dataEntered = false;
	        }
	        catch(Exception e)
	        {
	            System.out.println("Incorrect data entered.");
	            dataEntered = false;
	        }
    	}
    	dataEntered = false;
    	
    	while(!dataEntered)
    	{
    		System.out.print("Enter the bike part's sale status [\"true\" or \"false\"]: ");
			partOnSaleStr = userIn.next();
    		if (partOnSaleStr.equalsIgnoreCase("true") || partOnSaleStr.equalsIgnoreCase("false"))
    		{
    			dataEntered = true;
    			partOnSale = Boolean.parseBoolean(partOnSaleStr);
    		}
    		else
    		{
    			System.out.println("The part sale status entered wasn't true/false. Please try again.");
    		}
    	}
    	dataEntered = false;
    	
    	while(!dataEntered)
    	{
    		dataEntered = true;
	    	System.out.print("Enter the bike part quantity: ");
	    	partQuantityStr = userIn.next();
	    	try
	    	{
	    		partQuantity = Integer.parseInt(partQuantityStr);
	    	}
	    	catch (IllegalArgumentException e)
	        {
	            System.out.println("The part quantity entered wasn't a number. Please try again.");
	            dataEntered = false;
	        }
	        catch(Exception e)
	        {
	            System.out.println("Incorrect data entered.");
	            dataEntered = false;
	        }
    	}
    	
        BikePart bp = new BikePart(partName, partNumber, partPrice, partSalePrice, 
        		partOnSale, partQuantity);
        
        //Get the warehouse the user wants to add the part to
        boolean partEntered = false;
        System.out.println("Enter the warehouse you want to add the part to. "
    			+ "Enter \"" + MAIN_NAME + "\" for the main warehouse, or the name of a van for a van.");
    	
    	String wareName = userIn.next();
    	
    	if(wareName.equals(MAIN_NAME))
    	{
    		mainWarehouse.add(bp);
    		partEntered = true;
    	}
    	else
    	{
    		for (Warehouse van : vans)
    		{
    			if (wareName.equals(van.getName()))
    			{
    				van.add(bp);
    				partEntered = true;
    			}
    		}
    	}
    	
        while(!partEntered)
        {
        	System.out.println("Invalid warehouse name. Please try again.");
        	String warehouseNames = "mainWarehouse";
        	for (Warehouse van : vans)
        	{
        		warehouseNames = warehouseNames + ", " + van.getName();
        	}
        	
        	System.out.println("Valid names include: " + warehouseNames);
        	System.out.println("Enter the warehouse you want to add the part to. "
        			+ "Enter \"" + MAIN_NAME + "\" for the main warehouse, or the name of a van for a van.");
        	
        	wareName = userIn.next();
        	
        	if(wareName.equals(MAIN_NAME))
        	{
        		mainWarehouse.add(bp);
        		partEntered = true;
        	}
        	else
        	{
        		for (Warehouse van : vans)
        		{
        			if (wareName.equals(van.getName()))
        			{
        				van.add(bp);
        				partEntered = true;
        			}
        		}
        	}
        }
    }

    /**
     * Prompt the user for a part number, and attempt to sell a part matching that number.
     */
    private void sell()
    {
    	//Get number of a BikePart from the user
        int partNumber = Integer.MIN_VALUE;
    	System.out.println("Please enter the number of the part you want to sell:");
    	String partNumberStr = userIn.next();
        try
        {
            partNumber = Integer.parseInt(partNumberStr);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("The part number entered wasn't a number.");
            return;
        }
        catch(Exception e)
        {
            System.out.println("Incorrect data entered.");
            return;
        }
        
    	//Get warehouse that the user wants to sell from. 
        boolean partSold = false;
        System.out.println("Enter the warehouse you want to sell the part from. "
                + "Enter \"" + MAIN_NAME + "\" for the main warehouse, or the name of a van for a van.");
        
        String wareName = userIn.next();
        BikePart soldPart = null;
        
        if(wareName.equals(MAIN_NAME))
        {
            soldPart = mainWarehouse.sell(partNumber);
            partSold = true;
        }
        else
        {
            for (Warehouse van : vans)
            {
                if (wareName.equals(van.getName()))
                {
                    soldPart = van.sell(partNumber);
                    partSold = true;
                }
            }
        }
        
        while(!partSold)
        {
            System.out.println("Invalid warehouse name. Please try again.");
            String warehouseNames = "mainWarehouse";
            for (Warehouse van : vans)
            {
                warehouseNames = warehouseNames + ", " + van.getName();
            }
            
            System.out.println("Valid names include: " + warehouseNames);
            System.out.println("Enter the warehouse you want to add the part to. "
                    + "Enter \"" + MAIN_NAME + "\" for the main warehouse, or the name of a van for a van.");
            
            wareName = userIn.next();
            
            if(wareName.equals(MAIN_NAME))
            {
                soldPart = mainWarehouse.sell(partNumber);
                partSold = true;
            }
            else
            {
                for (Warehouse van : vans)
                {
                    if (wareName.equals(van.getName()))
                    {
                        soldPart = van.sell(partNumber);
                        partSold = true;
                    }
                }
            }
        }
        
    	/*
    	 * If the BikePart exists in the warehouse, sell one and display the part
    	 * name, part cost, and the time the part was sold CLEANLY. Otherwise, print 
    	 * error message and exit method.
    	 */
        
        if (soldPart == null)
        {
            System.out.println("A part with that number couldn't be found.");
            return;
        }
        
        Date date = new Date();
        String retValue = "Part Name: " + soldPart.getName() + "\nPart price: " + soldPart.getPrice();
        if (soldPart.getSale())
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

    /**
     * Prompt the user for a part name, then print the name and current price for the part.
     */
    private void display()
    {    	
        System.out.println("Please enter the name of the part you want to display:");
        String partName = userIn.next();
        
        //Get warehouse that the user wants to display from. 
        boolean partFound = false;
        System.out.println("Enter the warehouse you want to display the part from. "
                + "Enter \"" + MAIN_NAME + "\" for the main warehouse, or the name of a van for a van.");
        
        String wareName = userIn.next();
        BikePart foundPart = null;
        
        if(wareName.equals(MAIN_NAME))
        {
            foundPart = mainWarehouse.findPart(partName);
            partFound = true;
        }
        else
        {
            for (Warehouse van : vans)
            {
                if (wareName.equals(van.getName()))
                {
                    foundPart = van.findPart(partName);
                    partFound = true;
                }
            }
        }
        
        while(!partFound)
        {
            System.out.println("Invalid warehouse name. Please try again.");
            String warehouseNames = "mainWarehouse";
            for (Warehouse van : vans)
            {
                warehouseNames = warehouseNames + ", " + van.getName();
            }
            
            System.out.println("Valid names include: " + warehouseNames);
            System.out.println("Enter the warehouse you want to add the part to. "
                    + "Enter \"" + MAIN_NAME + "\" for the main warehouse, or the name of a van for a van.");
            
            wareName = userIn.next();
            
            if(wareName.equals(MAIN_NAME))
            {
                foundPart = mainWarehouse.findPart(partName);
                partFound = true;
            }
            else
            {
                for (Warehouse van : vans)
                {
                    if (wareName.equals(van.getName()))
                    {
                        foundPart = van.findPart(partName);
                        partFound = true;
                    }
                }
            }
        }
        
        if (foundPart == null)
        {
            System.out.println("Part couldn't be found in that warehouse.");
            return;
        }
        
        System.out.println("Part Name: " + foundPart.getName());
        System.out.println("Number: " + foundPart.getNumber());
        System.out.println("List Price: " + foundPart.getListPrice());
        System.out.println("Sale Price: " + foundPart.getSalesPrice());
        if (foundPart.getSale())
        {
            System.out.println("This part is on sale!");
        }
        else
        {
            System.out.println("This part is not on sale.");
        }
        System.out.println("Quantity: " + foundPart.getQuantity());
    }

    /**
     * Sorts the warehouse by part name, then prints the contents
     */
    private void sortName()
    {
    	System.out.println("Would you like to sort:\n1) The main warehouse\n2) A van\n3) All of the above\nEnter the number of your choice:");
    	
    	String choice = userIn.next();
    	ArrayList<BikePart> toSort = null;
    	if (choice.equals("1"))
    	{
    	    System.out.println("Sorting the main warehouse...");
    	    toSort = mainWarehouse.getInventory();
    	}
    	else if (choice.equals("2"))
    	{
    	    if (vans.size() == 0)
    	    {
    	        System.out.println("There are no vans to sort.");
    	        return;
    	    }
    	    
    	    System.out.println("Enter the name of the van you want to sort: ");
            
            String wareName = userIn.next();
            Warehouse foundWarehouse = null;
            
            for (Warehouse van : vans)
            {
                if (wareName.equals(van.getName()))
                {
                    foundWarehouse = van;
                }
            }
            
            while(foundWarehouse == null)
            {
                System.out.println("Invalid warehouse name. Please try again.");
                String warehouseNames = "";
                for (Warehouse van : vans)
                {
                    warehouseNames = warehouseNames + ", " + van.getName();
                }
                
                System.out.println("Valid names include: " + warehouseNames.substring(2));
                System.out.println("Enter the warehouse you want to add the part to. "
                        + "Enter \"" + MAIN_NAME + "\" for the main warehouse, or the name of a van for a van.");
                
                wareName = userIn.next();
                

                for (Warehouse van : vans)
                {
                    if (wareName.equals(van.getName()))
                    {
                        foundWarehouse = van;
                    }
                }
            }
            
            toSort = foundWarehouse.getInventory();
    	}
    	else if (choice.equals("3"))
    	{
    	    toSort = mainWarehouse.getInventory();
    	    
    	    for (Warehouse van : vans)
    	    {
    	        toSort.addAll(van.getInventory());
    	    }
    	    
    	}
    	else
    	{
    	    System.out.println("Choice not recognized.");
    	    return;
    	}
    	
    	Collections.sort(toSort, new StringComparator());
        printWarehouse(toSort);
    }
    
    /**
     * Sorts the warehouse by part number, then prints the contents
     */
    private void sortNumber()
    {
System.out.println("Would you like to sort:\n1) The main warehouse\n2) A van\n3) All of the above\nEnter the number of your choice:");
        
        String choice = userIn.next();
        ArrayList<BikePart> toSort = null;
        if (choice.equals("1"))
        {
            System.out.println("Sorting the main warehouse...");
            toSort = mainWarehouse.getInventory();
        }
        else if (choice.equals("2"))
        {
            if (vans.size() == 0)
            {
                System.out.println("There are no vans to sort.");
                return;
            }
            
            System.out.println("Enter the name of the van you want to sort: ");
            
            String wareName = userIn.next();
            Warehouse foundWarehouse = null;
            
            for (Warehouse van : vans)
            {
                if (wareName.equals(van.getName()))
                {
                    foundWarehouse = van;
                }
            }
            
            while(foundWarehouse == null)
            {
                System.out.println("Invalid warehouse name. Please try again.");
                String warehouseNames = "";
                for (Warehouse van : vans)
                {
                    warehouseNames = warehouseNames + ", " + van.getName();
                }
                
                System.out.println("Valid names include: " + warehouseNames.substring(2));
                System.out.println("Enter the warehouse you want to add the part to. "
                        + "Enter \"" + MAIN_NAME + "\" for the main warehouse, or the name of a van for a van.");
                
                wareName = userIn.next();
                

                for (Warehouse van : vans)
                {
                    if (wareName.equals(van.getName()))
                    {
                        foundWarehouse = van;
                    }
                }
            }
            
            toSort = foundWarehouse.getInventory();
        }
        else if (choice.equals("3"))
        {
            toSort = mainWarehouse.getInventory();
            
            for (Warehouse van : vans)
            {
                toSort.addAll(van.getInventory());
            }
            
        }
        else
        {
            System.out.println("Choice not recognized.");
            return;
        }
        
        Collections.sort(toSort, new IntegerComparator());
        printWarehouse(toSort);
    }
    
    /**
     * Add a new van to the fleet. Get the van name, then create a new empty van.
     */
    private void addVan()
    {
    	//Get the van name from the user.
    	System.out.println("Please enter a name for the van: ");
    	String vanName = userIn.next();
    	boolean uniqueVan = true;
    	for (Warehouse van : vans)
    	{
    		if (van.getName().equals(vanName))
    		{
    			uniqueVan = false;
    		}
    	}
    	
    	while(!uniqueVan)
    	{
    		System.out.println("Van already exists, please try again.");
        	System.out.println("Please enter a name for the van: ");
    		vanName = userIn.next();
        	uniqueVan = true;
        	for (Warehouse van : vans)
        	{
        		if (van.getName().equals(vanName))
        		{
        			uniqueVan = false;
        		}
        	}
    	}
    	
    	vans.add(new Warehouse(vanName));
    	
    	
    	//Create a new Warehouse with the name, the add it to vans
    }
    
    /**
     * Transfer inventory between the warehouses and sales vans. Should use the 
     * helper methods transferFromMain() and transferFromVan().
     */
    private void transfer()
    {
        System.out.println("Enter file name: ");
        String fileName = userIn.next();
        File file = new File(fileName);
        
        //If the text file doesn't exist, print error message and exist method
        try
        {
            Scanner read = new Scanner(file);
            
            String firstLine = read.next();
            
            read.close();
            
            String[] firstLineArray= firstLine.split(",");
            if (firstLineArray[0].equals(MAIN_NAME))
            {
                transferFromMain(file);
            }
            else
            {
                transferFromVan(file);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
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
     * 
     * @param transferFile File used for transfer
     */
    private void transferFromMain(File transferFile)
    {
    	//read destination van, and requested BikeParts
        try
        {
            Scanner read = new Scanner(transferFile);
            
            String firstLine = read.next();
            
            String[] firstLineArray= firstLine.split(",");
            Warehouse source = mainWarehouse, destination = null;
            
            for (Warehouse cur : vans)
            {
                if (cur.getName().equals(firstLineArray[1]))
                {
                    destination = cur;
                }
            }
            
            if (destination == null)
            {
                System.out.println("Destination van not found!");
                read.close();
                return;
            }
            
            //Transfer BikeParts
            ArrayList<BikePart> requestedParts = new ArrayList<BikePart>();
            while(read.hasNext())
            {
                String part = read.next();
                String[] partArray = part.split(",");
                requestedParts.add(new BikePart(partArray[0], -1, -1, -1, false, Integer.parseInt(partArray[1])));
            }
            
            ArrayList<BikePart> foundParts = source.remove(requestedParts);
            
            for (int i = 0; i < foundParts.size(); i++)
            {
                BikePart curPart = foundParts.get(i);
                if (curPart.getQuantity() == 0)
                {
                    System.out.println("Part " + curPart.getName() + "couldn't be found in the source warehouse");
                }
                else if (curPart.getQuantity() < requestedParts.get(i).getQuantity())
                {
                    System.out.println("Only " + curPart.getQuantity() + " parts of " + curPart.getName() + " could be transfered.");
                }
            }
            
            destination.addParts(foundParts);
            
            System.out.println("Parts transfered successfully!");
            read.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong with reading the bike parts!");
            e.printStackTrace();
        }
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
     * 
     * @param transferFile File used for transfer
     */
    private void transferFromVan(File transferFile)
    {
      //read destination van, and requested BikeParts
        try
        {
            Scanner read = new Scanner(transferFile);
            
            String firstLine = read.next();
            
            String[] firstLineArray= firstLine.split(",");
            Warehouse source = null, destination = null;
            
            for (Warehouse cur : vans)
            {
                if (cur.getName().equals(firstLineArray[0]))
                {
                    destination = cur;
                }
                if (cur.getName().equals(firstLineArray[1]))
                {
                    source = cur;
                }
            }
            
            if (destination == null)
            {
                System.out.println("Destination van not found!");
                read.close();
                return;
            }

            if (source == null)
            {
                System.out.println("Source van not found!");
                read.close();
                return;
            }
            
            if (destination == source)
            {
                System.out.println("The destination is the same as the source.");
                read.close();
                return;
            }
            
            //Transfer BikeParts
            ArrayList<BikePart> requestedParts = new ArrayList<BikePart>();
            while(read.hasNext())
            {
                String part = read.next();
                String[] partArray = part.split(",");
                requestedParts.add(new BikePart(partArray[0], -1, -1, -1, false, Integer.parseInt(partArray[1])));
            }
            
            ArrayList<BikePart> foundParts = source.remove(requestedParts);
            
            for (int i = 0; i < foundParts.size(); i++)
            {
                BikePart curPart = foundParts.get(i);
                if (curPart.getQuantity() == 0)
                {
                    System.out.println("Part " + curPart.getName() + "couldn't be found in the source warehouse");
                }
                else if (curPart.getQuantity() < requestedParts.get(i).getQuantity())
                {
                    System.out.println("Only " + curPart.getQuantity() + " parts of " + curPart.getName() + " could be transfered.");
                }
            }
            
            destination.addParts(foundParts);
            
            System.out.println("Parts transfered successfully!");
            read.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong with reading the bike parts!");
            e.printStackTrace();
        }
    }
    
    /**
     * Prints the contents of the passed "warehouse" in a neat format to 
     * System.out.
     * @param warehouse Warehouse to be printed
     */
    private void printWarehouse(ArrayList<BikePart> warehouse)
    {
    	System.out.printf("%-30s%-15s%-15s%-15s%-15s%-15s%n", "Name", "Number", "List Price", "Sale Price", "On Sale", "Quantity");
    	
    	for (BikePart cur : warehouse)
    	{
    	    System.out.printf("%-30s%-15d%-15.2f%-15.2f%-15s%-15d%n", cur.getName(), cur.getNumber(), cur.getListPrice(), 
    	            cur.getSalesPrice(), cur.getSale() ? "On Sale" : "Not on Sale", cur.getQuantity());
    	}
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