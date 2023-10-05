package bank;

import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionAttributeException;

/**
 * Payment Klasse - Subklasse von Transaction
 */
public class Payment extends Transaction  {

    private double incominginterest; //Zinsen bei Einzahlung
    private double outgoinginterest; //Zinsen bei Auszahlung

    /**
     * Unvollständiger Konstruktor der den Super Konstruktor nutzt
     * @param date Datum der Ein/Auszahlung
     * @param amount Amount der Ein/Auszahlung
     * @param description Beschreibung der Ein/Auszahlung
     */
    public Payment(String date, double amount, String description) throws TransactionAttributeException { //Konstruktor für die Klasse Payment, bei dem nur 3/5 Attribute initialisiert werden
        super(date,amount, description);

    }

    /**
     * Vollständiger Konstruktor der den Super Konstruktor für die basis Attribute aufruft, und für die Zinsen die Setter der Payment Klasse
     * @param date Datum der Ein/Auszahlung
     * @param amount Amount der Ein/Auszahlung
     * @param description der Ein/Auszahlung
     * @param incominginterest Eingehende Zinsen der Ein/Auszahlung
     * @param outgoinginterest Ausgehende Zinsender Ein/Auszahlung
     */
    public Payment(String date, double amount, String description, double incominginterest, double outgoinginterest) throws TransactionAttributeException { //Konstruktor bei dem alle Werte initialisiert werden
        super(date,amount, description);
        this.incominginterest=incominginterest;
        this.outgoinginterest=outgoinginterest;
        //setOutgoinginterest(outgoinginterest);
        //setIncominginterest(incominginterest);
    }

    /**
     * Copy-Konstruktor
     * @param x Zu kopierendes Objekt X
     */
    public Payment(Payment x) throws TransactionAttributeException{ //Copy-Konstruktor bei dem Objekt dem zu kopierendes Objekt übergeben wird
        this(x.getDate(), x.getAmount(), x.getDescription(),x.getIncominginterest(),x.getOutgoinginterest());
    }

    /**
     * Getter für eingehende Zinsen
     * @return eingehende Zinsen
     */
    public double getIncominginterest (){                       //Objekts
        return incominginterest;
    }

    /**
     * Getter für ausgehende Zinsen
     * @return ausgehende Zinsen
     */
    public double getOutgoinginterest (){                       //die Werte zurückgeben
        return outgoinginterest;
    }


/*
    /**
     * Setter für Amount. Setzt den Amount ohne weitere Prüfung
     * @param Amount Amount der Ein/Auszahlung

    @Override
    protected void setAmount(double Amount) {
        amount=Amount;
    }
*/
    /**
     * Setter für eingehende Zinsen.
     * Falls Zinsen größer 1, oder kleiner 0, setzte kein Zins und gebe
     * stattdessen einen Fehler auf der Konsole aus
     * @param Incominginterest eingehende Zinsen
     */
    public void setIncominginterest(double Incominginterest) throws TransactionAttributeException { //Zins setzen
        if (Incominginterest>1){ //Falls Zins >1 gebe Fehler aus
            throw new TransactionAttributeException("Fehler: Zins ist zu hoch");

        }
        else if (Incominginterest<0){ //Falls Zins <0 gebe Fehler aus
            throw new TransactionAttributeException("Fehler: Zins ist negativ");

        }
        incominginterest=Incominginterest;
    }

    /**
     * Setter für ausgehende Zinsen. Falls Zinsen größer 1, oder kleiner 0, setzte kein Zins und gebe stattdessen einen Fehler auf der Konsole aus
     * @param Outgoinginterest ausgehende Zinsen
     */
    public void setOutgoinginterest(double Outgoinginterest) throws TransactionAttributeException{

        if (Outgoinginterest>1){ //Falls Zins >1 gebe Fehler aus
            throw new TransactionAttributeException("Fehler: Zins ist zu hoch");
        }
        else if (Outgoinginterest<0) { //Falls Zins <0 gebe Fehler aus
            throw new TransactionAttributeException("Fehler: Zins ist negativ");
        }
        outgoinginterest=Outgoinginterest;
    }

    /**
     * Überschriebene / Definierte Methode des Interfaces CalculateBill.
     * Subtrahiere in beiden Fällen die Zinsen
     * @return Amount mit den entsprechenden Zinsen verrechnet
     */
    @Override
    public double calculate() {
        if (amount > 0)
            return (amount* (1-incominginterest));
        else
            return (amount * (1+outgoinginterest));
    }

    /**Überschriebene toString der SuperKlasse Transaction.
     * Füge zum basis String Payment spezifische Informationen hinzu
     * @return Beschreibung der Klasse in Form eines String
     */
    @Override
    public String toString() {
        if (amount>0)
            return super.toString() + "\n" + "Die eingehenden Zinsen in höhe von " + incominginterest + " wurden bereits miteinberechnet. (Amount= " + amount + ")";
        else
            return super.toString() + "\n" + "Die ausgehenden Zinsen in höhe von " + outgoinginterest + " wurden bereits miteinberechnet. (Amount= " + amount + ")";

    }

    /**
     * Überschriebene equals Methode der Super-Klasse Transaction. Vergleiche Paymentspezifische Eigenschaften
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

        Payment P= (Payment) obj;
        return (super.equals(obj) && incominginterest==P.getIncominginterest() && outgoinginterest==P.getOutgoinginterest() );
    }
}

