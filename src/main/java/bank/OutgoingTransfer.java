package bank;

import bank.exceptions.TransactionAttributeException;

import java.util.Objects;

public class OutgoingTransfer extends Transfer{


    public OutgoingTransfer (String date, double amount, String description, String sender, String recipient) throws TransactionAttributeException {
        super(date, amount, description, sender, recipient);
    }
    public OutgoingTransfer (String date, double amount, String description) throws TransactionAttributeException {
        super(date, amount, description);
    }
    public OutgoingTransfer(Transfer x) throws TransactionAttributeException{
        super (x.getDate(), x.getAmount(), x.getDescription(), x.getSender(), x.getRecipient());
    }
    @Override
    public double calculate() {
        return -1*amount;
    }
    @Override
    public String toString() {
        return "Es handelt sich um ein OutgoingTransfer. " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if (obj.getClass()!=this.getClass())
            return false;
        OutgoingTransfer T= (OutgoingTransfer) obj;

        return (super.equals(obj) && Objects.equals(getSender(),T.getSender()) && Objects.equals(getRecipient(),T.getRecipient()));
    }
}

