import bank.*;
import bank.exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*List<Transaction> Transactions=new ArrayList<>();
        //Transactions via Constructor erstellen (Exceptions möglich)
        //Transactions werden ohne zu PrivateBank ohne PrivateBank Methoden hinzugefügt (Keine Exceptions)
        try {
            Transfer T1 = new OutgoingTransfer("10.10.1997", 200, "Transfer 1", "Account 1", "Account 2");
            Transactions.add(T1);
        }
        catch (TransactionAttributeException T){
            System.out.println(T.getMessage());
        }
        try {
            Payment P1 = new Payment("10.10.1997", 500, "Payment 1 Positiv", 0.1, 1);
            Transactions.add(P1);
        }
        catch (TransactionAttributeException T) {
            System.out.println(T.getMessage());
        }
        try {
            Payment P2 = new Payment("10.10.1997", -500, "Payment 2 Negativ!", 0.1, 1);
            Transactions.add(P2);
        }
        catch (TransactionAttributeException T){
            System.out.println(T.getMessage());
        }
        //PrivateBank Objekt erstellen ohne PrivateBank Methods
        PrivateBank PB1 = new PrivateBank("Bank 1", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        PB1.accountsToTransactions.put("Account 1",Transactions);

        try {
            PB1.createAccount("Account 2");
            Payment P= new Payment("String",0.3,"Hallo",0.3,0.2);
            Payment P2=new Payment(P);
            PB1.addTransaction("Account 2", P);
            //PB1.addTransaction("Account 2", P);
            PB1.addTransaction("Account 2", P2);
        }
        catch (AccountAlreadyExistsException A){
            System.out.println(A.getMessage());
        }
        catch (TransactionAttributeException b){
            System.out.println(b.getMessage());
        } catch (TransactionAlreadyExistException e) {
            throw new RuntimeException(e);
        } catch (AccountDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        try {
        PB1.writeAccount("Account 1");}
        catch (IOException e){
            e.printStackTrace();
        }
        try {
            PrivateBank PBTest = new PrivateBank("Bank Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
            PBTest.writeAccount("Account 1");}
        catch (IOException e){
            e.printStackTrace();
        }
        List <Transaction> transactions = PB1.getTransactionsSorted("Account 1",false);
        for(Transaction T:transactions)
            System.out.println(T);*/
    PrivateBank PB = new PrivateBank("Test",0.2,0.3,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
    List<Transaction> Transactions=new ArrayList<>();
        //Transactions via Constructor erstellen (Exceptions möglich)
        //Transactions werden ohne zu PrivateBank ohne PrivateBank Methoden hinzugefügt (Keine Exceptions)
        try {
            Transfer T1 = new OutgoingTransfer("10.10.1997", 200, "Transfer 1", "Account 1", "Account 2");
            Transactions.add(T1);
        }
        catch (TransactionAttributeException T){
            System.out.println(T.getMessage());
        }
        try {
            Payment P1 = new Payment("10.10.1997", 500, "Payment 1 Positiv", 0.5, 0.5);
            Transactions.add(P1);
        }
        catch (TransactionAttributeException T) {
            System.out.println(T.getMessage());
        }
        try {
            Payment P2 = new Payment("10.10.1997", -500, "Payment 2 Negativ!", 0.6, 0.7);
            Transactions.add(P2);
        }
        catch (TransactionAttributeException T){
            System.out.println(T.getMessage());
        }
        try {
            PB.createAccount("Test");
            for (Transaction T:Transactions)
            PB.addTransaction("Test", T);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            for (Transaction T:Transactions)
                PB.accountsToTransactions.get("Test").remove(T);
            //PB.removeTransaction("Test", T);
                PB.writeAccount("Test");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}