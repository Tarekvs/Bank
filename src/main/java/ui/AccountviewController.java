package ui;

import bank.PrivateBank;
import bank.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;


public class AccountviewController {

    @FXML
    ListView listview;
    @FXML
    Label accountname;
    @FXML
    TextField balance;
    Stage stage;
    String account;
    public void setAccount(String account) {
        this.account=account;
    }
    public void setLabel (String account){
        accountname.setText(account);
    }

    public void setStage(Stage Stages){
        this.stage=Stages;
    }
  

    public void initialize(Stage stage,String account){
        setAccount(account);
        setLabel(account);
        setStage(stage);
        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            List<Transaction> transactions = PB.getTransactions(account);
            ObservableList<Transaction> items = FXCollections.observableArrayList(transactions);
            listview.setItems(items);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        balance.setText(Double.toString(PB.getAccountBalance(account)));
    }
    @FXML
    public void deleteTransacDialog(){

        Transaction transaction= (Transaction) listview.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModalTransacDelete.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            ModalTransacDeleteController controller= loader.getController();
            controller.initialize(stage,account,transaction);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
    @FXML
    public void addTransacDialog (){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModalTransacAdd.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            ModalTransacAddController controller= loader.getController();
            controller.initialize(stage,account,null);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
    @FXML
    public void sortedpositive(){
        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            List<Transaction> transactions = PB.getTransactionsSorted(account,true);
            ObservableList<Transaction> items = FXCollections.observableArrayList(transactions);
            listview.setItems(items);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void sortednegative(){
        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            List<Transaction> transactions = PB.getTransactionsSorted(account,false);
            ObservableList<Transaction> items = FXCollections.observableArrayList(transactions);

            listview.setItems(items);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void positivetransactions(){
        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            List<Transaction> transactions = PB.getTransactionsByType(account,true);
            ObservableList<Transaction> items = FXCollections.observableArrayList(transactions);
            listview.setItems(items);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void negativetransactions(){
        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            List<Transaction> transactions = PB.getTransactionsByType(account,false);
            ObservableList<Transaction> items = FXCollections.observableArrayList(transactions);
            listview.setItems(items);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void back(){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mainview.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            MainviewController controller= loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }

}

