package bank;

import bank.exceptions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PrivateBank implements Bank {

     private String name;

     private double incominginterest;

     private double outgoinginterest;

     public String directoryName;

     public String getDirectoryName() {
          return directoryName;
     }

     // Public as there are no getter / setter methods
     public Map<String, List<Transaction>> accountsToTransactions = new HashMap<String, List<Transaction>>();

     /**
      * Constructor that calls the readAccount() method after creation
      *
      * @param name             Name of the bank
      * @param incominginterest Incoming interest rate
      * @param outgoinginterest Outgoing interest rate
      * @param directoryname    Name of the directory
      */
     public PrivateBank(String name, double incominginterest, double outgoinginterest, String directoryname) {
          setName(name);
          directoryName = directoryname;
          try {
               setIncominginterest(incominginterest);
               setOutgoinginterest(outgoinginterest);
          } catch (TransactionAttributeException e) {
               System.out.println(e.getMessage());
          }
          try {
               this.readAccount();}
          catch (IOException e){
               e.printStackTrace();
          }
     }

     /**
      * Copy Constructor
      *
      * @param x Object of type PrivateBank to be copied
      */
     public PrivateBank(PrivateBank x) {
          this(x.name, x.incominginterest, x.outgoinginterest, x.directoryName);
     }


     /**
      * Getter for incoming interest rate
      *
      * @return Incoming interest rate
      */
     public double getIncominginterest() {                       
          return incominginterest;
     }

     /**
      * Getter for outgoing interest rate
      *
      * @return Outgoing interest rate
      */
     public String getName() {
          return name;
     }

     /**
      * Getter for outgoing interest rate
      *
      * @return Outgoing interest rate
      */
     public double getOutgoinginterest() {                       
          return outgoinginterest;
     }


     /**
      * Setter for the name of the bank
      *
      * @param Name Name of the bank
      */
     public void setName(String Name) {
          name = Name;
     }

     /**
      * Setter for incoming interest rate.
      * If the interest rate is greater than 1 or less than 0, do not set the interest and instead display an error in the console
      *
      * @param Incominginterest Incoming interest rate
      */
     protected void setIncominginterest(double Incominginterest) throws TransactionAttributeException { //Zins setzen
          if (Incominginterest > 1) { //Falls Zins >1 gebe Fehler aus
               throw new TransactionAttributeException("Fehler: Zins in Höhe von " + Incominginterest + " ist zu hoch");

          } else if (Incominginterest < 0) { //Falls Zins <0 gebe Fehler aus
               throw new TransactionAttributeException("Fehler: Zins in Höhe von " + Incominginterest + " ist zu niederig");

          }
          incominginterest = Incominginterest;
     }


     /**
      * Setter for outgoing interest rate. If the interest rate is greater than 1 or less than 0, do not set the interest and instead display an error in the console
      *
      * @param Outgoinginterest Outgoing interest rate
      */
     protected void setOutgoinginterest(double Outgoinginterest) throws TransactionAttributeException {

          if (Outgoinginterest > 1) { //Falls Zins >1 gebe Fehler aus
               throw new TransactionAttributeException("Fehler: Zins in Höhe von " + outgoinginterest + " ist zu hoch");
          } else if (Outgoinginterest < 0) { //Falls Zins <0 gebe Fehler aus
               throw new TransactionAttributeException("Fehler: Zins in Höhe von " + outgoinginterest + " ist zu gering");
          }
          outgoinginterest = Outgoinginterest;
     }

     /**
      * Overridden equals method from the Object class.
      * Compares every property of the calling object with the given object.
      * Use the equals method when comparing Strings, since String is an object of the String class, and otherwise, only references will be compared
      *
      * @param obj An object of the Object class
      * @return Returns true if the attributes of the two objects are identical, otherwise false
      */
     @Override
     public boolean equals(Object obj) {
          if (obj == null)
               return false;
          if (obj.getClass() != this.getClass())
               return false;
          PrivateBank T = (PrivateBank) obj;
          return (Objects.equals(this.name, T.getName()) && incominginterest == T.getIncominginterest() && outgoinginterest == T.getOutgoinginterest() && accountsToTransactions.equals(T.accountsToTransactions));
     }

    /**
      * Overridden toString method from the Object class.
      *
      * @return A string that describes all attributes
      */
     @Override
     public String toString() {
          return "Der Name der Bank lautet: " + name + "\n" + "Ausgehende Zinsen in Höhe von: " + outgoinginterest + "\n" + "Eingehende Zinsen in Höhe von: " + incominginterest;
     }


     @Override
     public void createAccount(String account) throws AccountAlreadyExistsException {
          if (accountsToTransactions.containsKey(account))
               throw new AccountAlreadyExistsException("Account existiert bereits");
          accountsToTransactions.put(account, new ArrayList<>());
     }

     /**
      * Do not use the Contains method from List here since the list must contain the transaction twice, hence use a counter
      *
      * @param account      The account to be added
      * @param transactions A list of already existing transactions that should be added to the newly created account
      * @throws AccountAlreadyExistsException
      * @throws TransactionAlreadyExistException
      * @throws TransactionAttributeException
      */
     @Override
     public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException, TransactionAlreadyExistException, TransactionAttributeException {
          //Use old Create Account
          if (accountsToTransactions.containsKey(account))
               throw new AccountAlreadyExistsException("Account existiert bereits");
          //Use Add Transaction instead
          for (Transaction T : transactions) {
               int zähler = 0;
               for (Transaction P : transactions) {
                    if (T.equals(P))
                         zähler++;
                    if (zähler > 1)
                         throw new TransactionAlreadyExistException("Mindestens eine Transaktion in der übergebenen Liste ist doppelt");
               }
          }
          for (Transaction transaction : transactions) {
               if (!pruefeTransaction(transaction))
                    throw new TransactionAttributeException("Validation Check für bestimmte Attributes der bestimmte Objekte der übergebenen Liste von Transaktionen ist fehlgeschlagen");
               /*try {
               this.addTransaction(account,transaction);}
               catch (Exception e){e.printStackTrace();}*/
          }

          accountsToTransactions.put(account, transactions);
          try {
               this.writeAccount(account);}
          catch (IOException e){
               e.printStackTrace();
          }
     }

     @Override
     public void addTransaction(String account, Transaction transaction) throws TransactionAlreadyExistException, AccountDoesNotExistException, TransactionAttributeException {

          if (!accountsToTransactions.containsKey(account))
               throw new AccountDoesNotExistException("Es existiert kein Konto mit dem Namen " + account);
          if (accountsToTransactions.get(account).contains(transaction))
               throw new TransactionAlreadyExistException("Account besitzt bereits diese Transaction");

          if (!pruefeTransaction(transaction))
               throw new TransactionAttributeException("Validation Check für bestimmte Attributes ist fehlgeschlagen");

          if (transaction instanceof Payment) {
               Payment P = new Payment (transaction.getDate(), transaction.getAmount(), transaction.getDescription(),this.getIncominginterest(),this.getOutgoinginterest());
               try {
               accountsToTransactions.get(account).add(P);} catch (Exception e) {e.printStackTrace();}

          }
          else if (transaction instanceof Transfer) {
               accountsToTransactions.get(account).add(transaction);}
          try {
               this.writeAccount(account);}
          catch (IOException e){
               e.printStackTrace();
          }
     }

     @Override
     public void removeTransaction(String account, Transaction transaction) throws AccountDoesNotExistException, TransactionDoesNotExistException {

          if (!accountsToTransactions.containsKey(account))
               throw new AccountDoesNotExistException("Es existiert kein Konto mit dem Namen " + account);
          if (!accountsToTransactions.get(account).contains(transaction))
               throw new TransactionDoesNotExistException("Account besitzt nicht diese Transaction");


          accountsToTransactions.get(account).remove(transaction);
          try {
          writeAccount(account);}
          catch (Exception e){
               e.printStackTrace();
          }
     }

     @Override
     public boolean containsTransaction(String account, Transaction transaction) {

          if (accountsToTransactions.get(account).contains(transaction))
               return true;

          return false;
     }

     @Override
     public double getAccountBalance(String account) {
          int balance = 0;
          for (Transaction T : accountsToTransactions.get(account)) {
               balance += T.calculate();
          }
          return balance;
     }

     @Override
     public List<Transaction> getTransactions(String account) {

          return accountsToTransactions.get(account);

     }

     @Override
     public List<Transaction> getTransactionsSorted(String account, boolean asc) {
          int size = accountsToTransactions.get(account).size();
          List<Double> A = new ArrayList<>();
          List<Transaction> Copy = accountsToTransactions.get(account);
          List<Transaction> Sorted = new ArrayList<>();
          for (Transaction T : Copy) {
               A.add(T.calculate());
          }
          //Bubble Sort
          int n = A.size();
          double temp = 0;
          for (int i = 0; i < n; i++) {
               for (int j = 1; j < (n - i); j++) {
                    if (A.get(j - 1) > A.get(j)) {
                         //swap elements
                         temp = A.get(j - 1);
                         A.set(j - 1, A.get(j));
                         A.set(j, temp);
                    }
               }
          }
          int zaehler;
          if (asc)
               zaehler = 0;
          else zaehler = A.size() - 1;

          for (Transaction X : Copy) {
               for (Transaction T : Copy) {
                    if (A.get(zaehler) == T.calculate()) {
                         Sorted.add(T);
                    }
               }
               if (asc)
                    zaehler++;
               else zaehler--;
          }
          return Sorted;
     }

     @Override
     public List<Transaction> getTransactionsByType(String account, boolean positive) {
          List<Transaction> Copy = new ArrayList<>();
          for (Transaction T : accountsToTransactions.get(account)) {
               if (positive) {
                    if (T.calculate() >= 0)
                         Copy.add(T);
               } else if (T.calculate() < 0)
                    Copy.add(T);
          }
          return Copy;
     }


     /**
      * Function that determines whether to throw a TransactionAttributeException or not.
      * Necessary because the interface 'Bank' methods throw exceptions, but I've already checked for exceptions in the setter methods.
      *
      * @param x Object of type Transaction
      * @return False if the "validation for certain attributes fails", otherwise returns True
      */

     private boolean pruefeTransaction(Transaction x) {
          if (x.getDescription() == null || x.getDate() == null || x.getAmount() == 0)
               return false;
          if (x instanceof Transfer) {
               if (((Transfer) x).getSender() == null || ((Transfer) x).getRecipient() == null)
                    return false;
          }
          return true;
     }

     public void readAccount() throws IOException{

          File directory = new File(this.directoryName);
          File[] files = Objects.requireNonNull(directory.listFiles());

          Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Transaction.class, new CustomLizer()).create();
          for (File file: files) {

               String tmp = file.getName().replace(".json","");
               String accountName = tmp.replace(directoryName + "Konten", "");
               if(!accountsToTransactions.containsKey(accountName))
                    try {
                         this.createAccount(accountName);
                    } catch (AccountAlreadyExistsException e) {
                         e.getMessage();
                    }


               try {
                    Reader reader = new FileReader(this.directoryName + file.getName());
                    List<Transaction> TransactionList = gson.fromJson(reader, new TypeToken<List<Transaction>>() {}.getType());
                    if (TransactionList.size() == 0)
                         continue;
                    for (Transaction T : TransactionList) {
                         this.addTransaction(accountName, T);
                    }

               }
                    catch(IOException e){
                    e.printStackTrace();
               } catch (TransactionAlreadyExistException e) {
                    e.printStackTrace();
               } catch (AccountDoesNotExistException e) {
                    e.printStackTrace();
               } catch (TransactionAttributeException e) {
                    e.printStackTrace();
               }
          }
     }

     /**
      *
      * @param account
      * @throws IOException
      */
     public void writeAccount(String account) throws IOException {

          FileWriter fw = new FileWriter(directoryName + account +".json" );
          BufferedWriter bw = new BufferedWriter(fw);

          GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
          builder.registerTypeHierarchyAdapter(Transaction.class, new CustomLizer());
          Gson gson = builder.create();
          bw.write(gson.toJson(accountsToTransactions.get(account)));

          bw.close();
          fw.close();

     }

     /**
      * Deletes an account and deletes the associated JSON file if it exists.
      * @param account The account to be deleted
      * @throws AccountDoesNotExistException if the specified account does not exist
      * @throws IOException if an I/O error occurs
      */

     public void deleteAccount(String account) throws AccountDoesNotExistException, IOException {
          if (!accountsToTransactions.containsKey(account))
               throw new AccountDoesNotExistException("Account existiert nicht");
          accountsToTransactions.remove(account);
          String file =directoryName + account + ".json";
          Files.deleteIfExists(Path.of(file));
     }

     /**
      * List of accounts
      * @return String list of accounts
      */
     public List<String> getAllAccounts(){
          Set<String> accountSet= accountsToTransactions.keySet();
          List <String> accounts = new ArrayList<>();
          for (String account : accountSet)
               accounts.add(account);
          return accounts;
     }
}