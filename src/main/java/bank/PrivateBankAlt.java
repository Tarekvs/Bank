package bank;

import bank.exceptions.*;

import java.util.*;

public class PrivateBankAlt implements Bank{

    protected String name;

    protected double incominginterest;

    protected double outgoinginterest;


    /**
     * Public da keine Getter / Setter Methoden
     */
    public Map<String, List<Transaction>> accountsToTransactions= new HashMap<String, List<Transaction>>();

    /**
     * Konstruktor
     * @param name Name der Bank
     * @param incominginterest Eingehende Zinsen
     * @param outgoinginterest Ausgehende Zinsen
     */
    public PrivateBankAlt (String name, double incominginterest, double outgoinginterest){
        setName(name);
        try {
            setIncominginterest(incominginterest);
            setOutgoinginterest(outgoinginterest);}
        catch(TransactionAttributeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Copy Constructor
     * @param x Zu kopierendes Objekt vom Typ PrivateBank
     */
    public PrivateBankAlt (PrivateBankAlt x){
        this(x.name,x.incominginterest,x.outgoinginterest);
    }


    /**
     * Getter für eingehende Zinsen
     * @return eingehende Zinsen
     */
    public double getIncominginterest (){                       //Objekts
        return incominginterest;
    }

    /**
     * Getter für Name der Bank
     * @return Name der Bank
     */
    public String getName(){
        return name;
    }

    /**
     * Getter für ausgehende Zinsen
     * @return ausgehende Zinsen
     */
    public double getOutgoinginterest (){                       //die Werte zurückgeben
        return outgoinginterest;
    }


    /**
     * Setter für Name der Bank
     * @param Name Name der Bank
     */

    public void setName(String Name){ name=Name;}

    /**
     * Setter für eingehende Zinsen.
     * Falls Zinsen größer 1, oder kleiner 0, setzte kein Zins und gebe
     * stattdessen einen Fehler auf der Konsole aus
     * @param Incominginterest eingehende Zinsen
     */
    protected void setIncominginterest(double Incominginterest) throws TransactionAttributeException { //Zins setzen
        if (Incominginterest>1){ //Falls Zins >1 gebe Fehler aus
            throw new TransactionAttributeException("Fehler: Zins in Höhe von " + Incominginterest + " ist zu hoch");

        }
        else if (Incominginterest<0){ //Falls Zins <0 gebe Fehler aus
            throw new TransactionAttributeException("Fehler: Zins in Höhe von " + Incominginterest + " ist zu niederig");

        }
        incominginterest=Incominginterest;
    }

    /**
     * Setter für ausgehende Zinsen. Falls Zinsen größer 1, oder kleiner 0, setzte kein Zins und gebe stattdessen einen Fehler auf der Konsole aus
     * @param Outgoinginterest ausgehende Zinsen
     */
    protected void setOutgoinginterest(double Outgoinginterest) throws TransactionAttributeException{

        if (Outgoinginterest>1){ //Falls Zins >1 gebe Fehler aus
            throw new TransactionAttributeException("Fehler: Zins in Höhe von " + outgoinginterest + " ist zu hoch");
        }
        else if (Outgoinginterest<0) { //Falls Zins <0 gebe Fehler aus
            throw new TransactionAttributeException("Fehler: Zins in Höhe von " + outgoinginterest + " ist zu gering");
        }
        outgoinginterest=Outgoinginterest;
    }
    /**
     * Überschriebene equals Methode der Klasse Object.
     * Vergleicht jede Eigenschaft des aufrufenden Objekts mit dem übergebenem Objekt.
     * Verwende bei String vergleichen die equals Methode, da String ein Objekt der Klasse String ist, und ansonsten nur die Referenzen verglichen werden
     * @param obj Ein Objekt der Klasse Object
     * @return gebe True zurück wenn Attribute der zwei Objekte identisch, ansonsten False
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        PrivateBankAlt T = (PrivateBankAlt) obj;
        return (Objects.equals(this.name, T.getName()) && incominginterest == T.getIncominginterest() && Objects.equals(outgoinginterest, T.getOutgoinginterest()) && Map.of().equals(accountsToTransactions == T.accountsToTransactions));
    }

    /**
     * Überschriebene toString Methode der Klasse Object.
     * @return Ein String der eine Beschreibung aller Attribute darstellt
     */
    @Override
    public String toString() {
        return "Der Name der Bank lautet: " + name + "\n" + "Ausgehende Zinsen in Höhe von: " + outgoinginterest + "\n" + "Eingehende Zinsen in Höhe von: " + incominginterest;
    }


    @Override
    public void createAccount(String account) throws AccountAlreadyExistsException {
        if (accountsToTransactions.containsKey(account))
            throw new AccountAlreadyExistsException("Account existiert bereits");
        accountsToTransactions.put(account,null);
    }

    /**
     * Hier nicht Contains Methode von List verwenden da Liste die Transaction zweimal beinhalten muss, deswegen über Zähler
     * @param account      the account to be added
     * @param transactions a list of already existing transactions which should be added to the newly created account
     * @throws AccountAlreadyExistsException
     * @throws TransactionAlreadyExistException
     * @throws TransactionAttributeException
     */
    @Override
    public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException, TransactionAlreadyExistException, TransactionAttributeException {

        if (accountsToTransactions.containsKey(account))
            throw new AccountAlreadyExistsException("Account existiert bereits");

        for (Transaction T : transactions){
            int zähler=0;
            for (Transaction P: transactions){
                if (T.equals(P))
                    zähler++;
                if (zähler>1)
                    throw new TransactionAlreadyExistException("Mindestens eine Transaktion in der übergebenen Liste ist doppelt");
            }
        }
        for (Transaction transaction : transactions) {
            if (!pruefeTransaction(transaction))
                throw new TransactionAttributeException("Validation Check für bestimmte Attributes der bestimmte Objekte der übergebenen Liste von Transaktionen ist fehlgeschlagen");
        }

        accountsToTransactions.put(account, transactions);
    }

    @Override
    public void addTransaction(String account, Transaction transaction) throws TransactionAlreadyExistException, AccountDoesNotExistException, TransactionAttributeException {

        if (!accountsToTransactions.containsKey(account))
            throw new AccountDoesNotExistException("Es existiert kein Konto mit dem Namen " + account);
        if (accountsToTransactions.get(account).contains(transaction))
            throw new TransactionAlreadyExistException("Account besitzt bereits diese Transaction");

        if (!pruefeTransaction(transaction))
            throw new TransactionAttributeException("Validation Check für bestimmte Attributes ist fehlgeschlagen");

        if (transaction instanceof Payment){
            ((Payment)transaction).setIncominginterest(incominginterest);
            ((Payment)transaction).setOutgoinginterest(outgoinginterest);
        }

        accountsToTransactions.get(account).add(transaction);
    }

    @Override
    public void removeTransaction(String account, Transaction transaction) throws AccountDoesNotExistException, TransactionDoesNotExistException {

        if (!accountsToTransactions.containsKey(account))
            throw new AccountDoesNotExistException("Es existiert kein Konto mit dem Namen " + account);
        if (!accountsToTransactions.get(account).contains(transaction))
            throw new TransactionDoesNotExistException("Account besitzt nicht diese Transaction");
        if (!accountsToTransactions.containsValue(transaction))
            throw new TransactionDoesNotExistException("Account besitzt bereits diese Transaction");

        accountsToTransactions.get(account).remove(transaction);
    }

    @Override
    public boolean containsTransaction(String account, Transaction transaction) {

        if (accountsToTransactions.get(account).contains(transaction))
            return true;

        return false;
    }

    @Override
    public double getAccountBalance(String account){
        int balance=0;
        for (Transaction T:accountsToTransactions.get(account)){
            if (T instanceof Transfer)
                if (Objects.equals(((Transfer) T).getRecipient(), account))
                    balance+=T.calculate();
                else
                    balance -=T.calculate();
            else
                balance+=T.calculate();
        }
        return balance;
    }


    @Override
    public List<Transaction> getTransactions(String account) {

        return accountsToTransactions.get(account);

    }

    @Override
    public List<Transaction> getTransactionsSorted(String account, boolean asc) {
        int size=accountsToTransactions.get(account).size();
        List<Double> A= new ArrayList<>();
        Map<String, List<Transaction>> Copy=accountsToTransactions;
        //accountsToTransactions.get(account).clear(); Falls accountsToTransaction bearbeitet werden soll?
        Map<String, List<Transaction>> Sorted= new HashMap<>();
        for (Transaction T: accountsToTransactions.get(account)){
            A.add(T.calculate());
        }
        //Bubble Sort
        int n = A.size();
        double temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(A.get(j-1) > A.get(j)){
                    //swap elements
                    temp = A.get(j-1);
                    A.set(j-1,A.get(j));
                    A.set(j,temp);
                }
            }
        }
        int zähler;
        if (asc)
            zähler=0;
        else zähler=A.size()-1;

        for (Transaction T:Copy.get(account)){
            if (A.get(zähler)==T.calculate()){
                Sorted.get(account).add(T);
                //Copy.get(account).remove(T);
                if (asc)
                    zähler++;
                else zähler--;
            }
        }
        return Sorted.get(account);
    }

    @Override
    public List<Transaction> getTransactionsByType(String account, boolean positive) {
        List<Transaction> Copy=new ArrayList<>();
        for (Transaction T:accountsToTransactions.get(account)){
            if (positive) {
                if (T.calculate() >= 0)
                    Copy.add(T);
            }
            else
            if (T.calculate()<0)
                Copy.add(T);
        }
        return Copy;
    }

    /**
     * Function welche bestimmt ob TransactionAttributeException geworfen wird oder nicht
     * Nötig weil interface Bank Methoden Exception throwen aber ich prüfe bereits bei Setter die Exception?
     * @param x Objekt der Klasse Transaction
     * @return False falls "validation for certain Attributes check fail" ansonsten True
     */
    private boolean pruefeTransaction(Transaction x){
        if (x.getDescription()==null || x.getDate()==null || x.getAmount()==0)
            return false;
        if (x instanceof Transfer){
            if (((Transfer) x).getSender()==null || ((Transfer) x).getRecipient()==null)
                return false;
        }
        return true;
    }
};