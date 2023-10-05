package bank;

import bank.exceptions.TransactionAttributeException;

import java.util.Objects;

/**Abstrakte Klasse welche Grundattribute und Methoden der Klassen Payment und Transfer
 * verwaltet/beinhaltet. Implementiert Interface CalculateBill
 * @author tarekvonseckendorff
 * @see bank.CalculateBill
 * @version 1.0
 *
 */
public abstract class Transaction implements CalculateBill {



    protected String date; //DD.MM.YY einer Ein/Auszahlung

    protected double amount; //Höhe der Ein/Auszahlung

    protected String description; // Beschreibung der Ein/Auszahlung

    /** Nicht vollständiger Konstruktor der alle Werte über die Setter Methoden der jeweiligen Klassen
     * initialisiert. (Eventuell geteilte Methoden)
     *
     * @param date Datum im Format DD.MM.YY
     * @param amount Betrag des Transfers oder Ein/Auszahlung.
     * @param description Beschreibung des Transfers oder Ein/Auszahlung
     */
    Transaction (String date,double amount, String description) throws TransactionAttributeException{
        setAmount(amount); //Methode einer noch nicht instanziierten sub-class wird aufgerufen, ist das gut?
        setDate(date);
        setDescription(description);
    }

    /**
     *Getter Methode für das Datum des Transfers oder Ein/Auszahlung
     * @return Gibt das Datum des Transfers oder Ein/Auszahlung zurück
     */
    public String getDate (){
        return date;
    }

    /**
     * Getter für Amount des Transfers oder Ein/Auszahlung
     * @return Amount des Transfers oder Ein/Auszahlung
     */
    public double getAmount (){
        return amount;
    }

    /**
     * Getter für Description des Transfers oder Ein/Auszahlung
     * @return Beschreibung des Transfers oder Ein/Auszahlung
     */
    public String getDescription (){
        return description;
    }

    /**
     * Abstrakte Set-Methode für Amount welche von den Subklassen definiert wird
     * @param amount Amount des Transfers oder Ein/Auszahlung
     */
    protected void setAmount(double amount) throws TransactionAttributeException{
        this.amount=amount;
    };

    /**
     * Set-Methode für das Datum des Transfers oder Ein/Auszahlung
     * @param Date Datum des Transfers oder Ein/Auszahlung
     */
    protected void setDate(String Date){ date=Date; }     //ausgelagerte setter

    /**
     * Set-Methode für die Beschreibungs des Transfers oder Ein/Auszahlung
     * @param Description Beschreibungs des Transfers oder Ein/Auszahlung
     */
    protected void setDescription(String Description){ description= Description; } //für alle Funktionen

    /**
     * Überschriebene toString Methode der Klasse Object.
     * @return Ein String der eine Basis Beschreibung darstellt, welche von den Subklassen weiter ausgeführt wird
     */
    @Override
    public String toString() {
        return "Beschreibung: " + description + ". Transaktion in Höhe von " + calculate() + ", getätigt am " + date ;
    }

    /**
     * Überschriebene equals Methode der Klasse Object.
     * Vergleicht jede Eigenschaft des aufrufenden Objekts mit dem übergebenem Objekt.
     * Verwende bei String vergleichen die equals Methode, da String ein Objekt der Klasse String ist, und ansonsten nur die Referenzen verglichen werden
     * @param obj Ein Objekt der Klasse Object
     * @return True wenn Attribute der zwei Objekte identisch, ansonsten False
     */
    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if (obj.getClass()!=this.getClass())
                return false;

        Transaction T= (Transaction) obj;
        //Objects.equals prüft auf NULL bevor object.equals gerufen wird
        //Strings sind Objekte in Java?
        return (Objects.equals(date, T.getDate()) && amount==T.getAmount() && Objects.equals(description, T.getDescription()));
    }
}


