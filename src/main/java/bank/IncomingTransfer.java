package bank;

import bank.exceptions.TransactionAttributeException;

import java.util.Objects;

/**
 * Abstract class that manages basic attributes and methods of the classes Payment and Transfer.
 * Implements the CalculateBill interface.
 * @author tarekvonseckendorff
 * @see bank.Transfer
 */
public class IncomingTransfer extends Transfer{

    
    public IncomingTransfer (String date, double amount, String description, String sender, String recipient) throws TransactionAttributeException {
        super(date, amount, description, sender, recipient);
    }
    public IncomingTransfer (String date, double amount, String description) throws TransactionAttributeException {
        super(date, amount, description);
    }
    public IncomingTransfer(Transfer x) throws TransactionAttributeException{
        super (x.getDate(), x.getAmount(), x.getDescription(), x.getSender(), x.getRecipient());
    }
    @Override
    public String toString() {
        return "Es handelt sich um ein IncomingTransfer. " + super.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if (obj.getClass()!=this.getClass())
            return false;
        IncomingTransfer T= (IncomingTransfer) obj;

        return (super.equals(obj) && Objects.equals(getSender(),T.getSender()) && Objects.equals(getRecipient(),T.getRecipient()));
    }
}
