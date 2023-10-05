package bank;

import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionAttributeException;

/**
 * Abstract class that manages basic attributes and methods of the classes Payment and Transfer.
 * Implements the CalculateBill interface.
 * @author tarekvonseckendorff
 * @see bank.Transaction
 * @version 1.0
 */
public class Payment extends Transaction {

    private double incominginterest; 
    private double outgoinginterest;

    /**
     * Partial constructor that utilizes the super constructor.
     *
     * @param date The date of the deposit/withdrawal.
     * @param amount The amount of the deposit/withdrawal.
     * @param description Description of the deposit/withdrawal.
     */
    public Payment(String date, double amount, String description) throws TransactionAttributeException {
        super(date, amount, description);
    }

    /**
     * Complete constructor that invokes the super constructor for basic attributes 
     * and uses the setters of the Payment class for the interests.
     *
     * @param date Date of the deposit/withdrawal.
     * @param amount Amount of the deposit/withdrawal.
     * @param description Description of the deposit/withdrawal.
     * @param incominginterest Incoming interest of the deposit/withdrawal.
     * @param outgoinginterest Outgoing interest of the deposit/withdrawal.
     */
    public Payment(String date, double amount, String description, double incominginterest, double outgoinginterest) throws TransactionAttributeException {
        super(date, amount, description);
        this.incominginterest = incominginterest;
        this.outgoinginterest = outgoinginterest;
    }

    /**
     * Copy constructor.
     *
     * @param x The object X to be copied.
     */
    public Payment(Payment x) throws TransactionAttributeException {
        this(x.getDate(), x.getAmount(), x.getDescription(), x.getIncominginterest(), x.getOutgoinginterest());
    }

    /**
     * Getter for incoming interest.
     *
     * @return Incoming interest.
     */
    public double getIncominginterest() {
        return incominginterest;
    }

    /**
     * Getter for outgoing interest.
     *
     * @return Outgoing interest.
     */
    public double getOutgoinginterest() {
        return outgoinginterest;
    }

    /**
     * Setter for incoming interest. If the interest is greater than 1 or less than 0, 
     * no interest is set and instead an error is displayed on the console.
     *
     * @param Incominginterest Incoming interest.
     */
    public void setIncominginterest(double Incominginterest) throws TransactionAttributeException {
        if (Incominginterest > 1) {
            throw new TransactionAttributeException("Error: Interest is too high");
        } else if (Incominginterest < 0) {
            throw new TransactionAttributeException("Error: Interest is negative");
        }
        incominginterest = Incominginterest;
    }

    /**
     * Setter for outgoing interest. If the interest is greater than 1 or less than 0,
     * no interest is set and instead an error is displayed on the console.
     *
     * @param Outgoinginterest Outgoing interest.
     */
    public void setOutgoinginterest(double Outgoinginterest) throws TransactionAttributeException {
        if (Outgoinginterest > 1) {
            throw new TransactionAttributeException("Error: Interest is too high");
        } else if (Outgoinginterest < 0) {
            throw new TransactionAttributeException("Error: Interest is negative");
        }
        outgoinginterest = Outgoinginterest;
    }

    /**
     * Overridden method of the CalculateBill interface.
     * Subtract interests in both cases.
     *
     * @return Amount adjusted for the corresponding interest.
     */
    @Override
    public double calculate() {
        if (amount > 0)
            return (amount * (1 - incominginterest));
        else
            return (amount * (1 + outgoinginterest));
    }

    /**
     * Overridden toString method of the Transaction superclass.
     * Add Payment-specific information to the basic string.
     *
     * @return Description of the class in the form of a string.
     */
    @Override
    public String toString() {
        if (amount > 0)
            return super.toString() + "\n" + "The incoming interest amounting to " + incominginterest + " has already been taken into account. (Amount = " + amount + ")";
        else
            return super.toString() + "\n" + "The outgoing interest amounting to " + outgoinginterest + " has already been taken into account. (Amount = " + amount + ")";
    }

    /**
     * Overridden equals method of the Transaction superclass. Compare Payment-specific properties.
     * Use the equals method to compare strings because String is an object of the String class, and otherwise only references are compared.
     *
     * @param obj An object of the Object class.
     * @return True if Payment objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;

        Payment P = (Payment) obj;
        return (super.equals(obj) && incominginterest == P.getIncominginterest() && outgoinginterest == P.getOutgoinginterest());
   
    }
}