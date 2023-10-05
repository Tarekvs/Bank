package bank;

import bank.exceptions.TransactionAttributeException;

import java.util.Objects;

/**
 * Transfer Klasse - Subklasse von Transaction
 */
public class Transfer extends Transaction  {
    private String sender; //Akteur der Geldemenge schickt
    private String recipient; //Aktuer der Geldmenge bekommt

    /**
     * Unvollständiger Konstruktor der den Super Konstruktor nutzt
     * @param date Datum des Transfers
     * @param amount Amount des Transfers
     * @param description Beschreibung des Transfers
     */
    public Transfer(String date, double amount, String description) throws TransactionAttributeException{//Konstruktor für die Klasse Transfer, bei dem nur 3/5 Attribute initialisiert werden
        super(date,amount, description);

    }

    /**
     * Unvollständiger Konstruktor der den Super Konstruktor nutzt
     * @param date Datum des Transfers
     * @param amount Amount des Transfers
     * @param description Beschreibung des Transfers
     * @param sender Sender des Transfers
     * @param recipient Empfänger des Transfers
     */
    public Transfer(String date, double amount, String description, String sender, String recipient)throws TransactionAttributeException {   //Konstruktor bei dem alle Werte initialisiert werden
        super(date,amount, description);
        setSender(sender);
        setRecipient(recipient);
    }
    /**
     * Copy-Konstruktor
     * @param x Zu kopierendes Objekt X
     */
    public Transfer(Transfer x) throws TransactionAttributeException{
        this (x.getDate(), x.getAmount(), x.getDescription(), x.getSender(), x.getRecipient());
    }


    /**
     * Getter für Sender des Transfers
     * @return Sender des Transfers
     */
    public String getSender (){
        return sender;
    }

    /**
     * getter für Empfänger des Transfers
     * @return Empfänger des Transfers
     */
    public String getRecipient (){
        return recipient;
    }           //des Objekts


    /**
     * Überschriebene Amount Methode der Super-Klasse Transaction.
     * Falls Amount kleiner als Null, gebe Fehler aus.
     * Ansonsten setze Amount
     * @param Amount Amount des Transfers
     */
     @Override
     protected void setAmount(double Amount) throws TransactionAttributeException {
         if (Amount<=0){ //Falls Amount ≤0 gebe Fehler aus
             throw new TransactionAttributeException("Fehler: Transfer kann nicht negativ oder 0 sein");
         }
         amount=Amount;
     }

    /**
     * Setter Methode für Sender des Transfers
     * @param Sender Sender des Transfers
     */
    private void setSender(String Sender){
        sender=Sender;
    }

    /**
     * Setter für Empfänger des Transfers
     * @param Recipient Empfänger des Transfers
     */
    private void setRecipient(String Recipient){
        recipient=Recipient;
    }               //des Objekts

    /**
     * Überschriebene / Definierte Methode des Interfaces CalculateBill.
     * Gebe amount zurück da keine anfallenden Zinsen bei Transfers
     * @return Amount
     */
    @Override
    public double calculate() {
        return amount;
        }
    /**Überschriebene toString der SuperKlasse Transaction.
     * Füge zum basis String Transfer spezifische Informationen hinzu
     * @return Beschreibung der Klasse in Form eines String
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + "Sender: " + sender + "\n" + "Empfänger: " + recipient;
    }
    /**
     * Überschriebene equals Methode der Super-Klasse Transaction. Vergleiche Transferspezifische Eigenschaften
     * Verwende bei String vergleichen die equals Methode, da String ein Objekt der Klasse String ist, und ansonsten nur die Referenzen verglichen werden
     * @param obj Ein Objekt der Klasse Object
     * @return True Falls Payment Objekte gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if (obj.getClass()!=this.getClass())
            return false;

        Transfer T=(Transfer) obj;
        return (super.equals(obj) && Objects.equals(sender, T.getSender()) && Objects.equals(recipient, T.getRecipient()));
    }
}





