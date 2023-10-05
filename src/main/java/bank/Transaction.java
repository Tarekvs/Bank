package bank;

import bank.exceptions.TransactionAttributeException;

import java.util.Objects;

/**
 * Abstract class that manages basic attributes and methods of the classes Payment and Transfer.
 * Implements the CalculateBill interface.
 * @author tarekvonseckendorff
 * @see bank.CalculateBill
 */

public abstract class Transaction implements CalculateBill {

    protected String date; // Date (DD.MM.YY) of a deposit/withdrawal
    protected double amount; // Amount of the deposit/withdrawal
    protected String description; // Description of the deposit/withdrawal

    /** 
     * Incomplete constructor that initializes all values using the setter methods 
     * of their respective classes (possibly shared methods).
     * @param date Date in the format DD.MM.YY
     * @param amount Amount of the transfer or deposit/withdrawal.
     * @param description Description of the transfer or deposit/withdrawal
     */
    Transaction (String date, double amount, String description) throws TransactionAttributeException {
        setAmount(amount); 
        setDate(date);
        setDescription(description);
    }

    /**
     * Getter method for the date of the transfer or deposit/withdrawal
     * @return Returns the date of the transfer or deposit/withdrawal
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for the amount of the transfer or deposit/withdrawal
     * @return Amount of the transfer or deposit/withdrawal
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Getter for the description of the transfer or deposit/withdrawal
     * @return Description of the transfer or deposit/withdrawal
     */
    public String getDescription() {
        return description;
    }

    /**
     * Abstract set method for amount which will be defined by the subclasses
     * @param amount Amount of the transfer or deposit/withdrawal
     */
    protected void setAmount(double amount) throws TransactionAttributeException {
        this.amount = amount;
    };

    /**
     * Set method for the date of the transfer or deposit/withdrawal
     * @param Date Date of the transfer or deposit/withdrawal
     */
    protected void setDate(String Date) {
        date = Date; 
    }

    /**
     * Set method for the description of the transfer or deposit/withdrawal
     * @param Description Description of the transfer or deposit/withdrawal
     */
    protected void setDescription(String Description) {
        description = Description; 
    }

    /**
     * Overridden toString method from the Object class.
     * @return A string representing a basic description, further elaborated by the subclasses
     */
    @Override
    public String toString() {
        return "Description: " + description + ". Transaction amounting to " + calculate() + ", made on " + date;
    }

    /**
     * Overridden equals method from the Object class.
     * Compares every property of the calling object with the passed object.
     * For string comparisons, use the equals method because String is an object of the String class, and otherwise only references are compared
     * @param obj An object of the Object class
     * @return True if attributes of the two objects are identical, otherwise False
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;

        Transaction T = (Transaction) obj;
        // Objects.equals checks for NULL before calling object.equals
        return (Objects.equals(date, T.getDate()) && amount == T.getAmount() && Objects.equals(description, T.getDescription()));
    }
}
