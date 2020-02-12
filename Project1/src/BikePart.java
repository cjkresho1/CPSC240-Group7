import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BikePart {
    /**
     * Constructs a BikePart object
     *
     * @param name the name of the bike
     * @param number the serial number of the bike
     * @param listPrice the list price of the bike
     * @param salesPrice the price of the bike if it is on sale
     * @param sale whether the bike is on sale or not
     * @author Anna Totten
     */
    String name;
    int number;
    double listPrice;
    double salesPrice;
    boolean sale;
    int quantity;

    public BikePart(String name, int number, double listPrice, double salesPrice, boolean sale, int quantity) {
        this.name = name;
        this.number = number;
        this.listPrice = listPrice;
        this.salesPrice = salesPrice;
        this.sale = sale;
        this.quantity = quantity;
    }

    /**
     *
     * @return the name of the bike
     */
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    /**
     *
     * @return the serial number of the bike
     */
    public int getNumber() {
        return this.number;
    }

    public void setNumber(int newNumber) {
        this.number = newNumber;
    }

    /**
     *
     * @return the list price of the bike
     */
    public double getListPrice() {
        return this.listPrice;
    }

    public void setListPrice(double newListPrice) {
        this.listPrice = newListPrice;
    }

    /**
     *
     * @return the price of the bike if it is on sale
     */
    public double getSalesPrice() {
        return this.salesPrice;
    }

    public void setSalesPrice(double newSalesPrice) {
        this.salesPrice = newSalesPrice;
    }

    /**
     *
     * @return whether the bike is on sale or not
     */
    public boolean getSale() {
        return this.sale;
    }

    public void setSale(boolean newSale) {
        this.sale = newSale;
    }

    public int getQuantity() { return this.quantity; }

    public void setQuantity(int newQuantity) { this.quantity = newQuantity; }
}