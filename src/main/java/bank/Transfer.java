package bank;

import bank.exceptions.TransactionAttributeException;

import java.util.Objects;

/**
 * Abstract class that manages basic attributes and methods of the classes Payment and Transfer.
 * Implements the CalculateBill interface.
 * @author tarekvonseckendorff
 * @see bank.Transaction
 */
public class Transfer extends Transaction {
    private String sender;      // Actor sending the money amount
    private String recipient;   // Actor receiving the money amount

    /**
     * Incomplete constructor utilizing the superclass constructor
     * @param date Date of the transfer
     * @param amount Amount of the transfer
     * @param description Description of the transfer
     */
    public Transfer(String date, double amount, String description) throws TransactionAttributeException { // Constructor for Transfer class initializing only 3 out of 5 attributes
        super(date, amount, description);
    }

    /**
     * Incomplete constructor utilizing the superclass constructor
     * @param date Date of the transfer
     * @param amount Amount of the transfer
     * @param description Description of the transfer
     * @param sender Sender of the transfer
     * @param recipient Recipient of the transfer
     */
    public Transfer(String date, double amount, String description, String sender, String recipient) throws TransactionAttributeException { // Constructor initializing all attributes
        super(date, amount, description);
        setSender(sender);
        setRecipient(recipient);
    }

    /**
     * Copy Constructor
     * @param x Object to be copied
     */
    public Transfer(Transfer x) throws TransactionAttributeException {
        this(x.getDate(), x.getAmount(), x.getDescription(), x.getSender(), x.getRecipient());
    }

    /**
     * Getter for the transfer's sender
     * @return Sender of the transfer
     */
    public String getSender() {
        return sender;
    }

    /**
     * Getter for the transfer's recipient
     * @return Recipient of the transfer
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Overridden Amount method from the superclass Transaction.
     * If Amount is less than zero, produce an error.
     * Otherwise, set the Amount
     * @param Amount Amount of the transfer
     */
    @Override
    protected void setAmount(double Amount) throws TransactionAttributeException {
        if (Amount <= 0) { // If Amount ≤0 produce an error
            throw new TransactionAttributeException("Error: Transfer cannot be negative or 0");
        }
        amount = Amount;
    }

    /**
     * Setter method for the transfer's sender
     * @param Sender Sender of the transfer
     */
    private void setSender(String Sender) {
        sender = Sender;
    }

    /**
     * Setter for the transfer's recipient
     * @param Recipient Recipient of the transfer
     */
    private void setRecipient(String Recipient) {
        recipient = Recipient;
    }

    /**
     * Overridden / Defined method from the CalculateBill interface.
     * Return amount since there are no interests incurred in transfers
     * @return Amount
     */
    @Override
    public double calculate() {
        return amount;
    }

    /**
     * Overridden toString from the superclass Transaction.
     * Append transfer-specific information to the base string
     * @return Class description in the form of a string
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + "Sender: " + sender + "\n" + "Recipient: " + recipient;
    }

    /**
     * Overridden equals method from the superclass Transaction. Compare transfer-specific properties.
     * For string comparison, use the equals method since String is an object of the String class, and otherwise only references will be compared
     * @param obj An object of the Object class
     * @return True if Transfer objects are identical
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;

        Transfer T = (Transfer) obj;
        return (super.equals(obj) && Objects.equals(sender, T.getSender()) && Objects.equals(recipient, T.getRecipient()));
    }
}

