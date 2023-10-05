package ui;


import bank.PrivateBank;
import bank.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ModalTransacDeleteController {


    Stage stage;
    public void setStage(Stage Stages){
        this.stage=Stages;
    }

    String account;
    public void setAccount(String account){
        this.account=account;
    }
    Transaction transaction;
    public void setTransaction(Transaction transaction){
        this.transaction=transaction;
    }

    public void initialize(Stage stage, String account, Transaction transaction){
        setAccount(account);
        setTransaction(transaction);
        setStage(stage);
    }
    @FXML
    public void delete(){

        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            PB.removeTransaction(account,transaction);
            PB.writeAccount(account);
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error has occurred");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Accountview.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            AccountviewController controller= loader.getController();
            controller.initialize(stage,account);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }

    @FXML
    public void deletereturn(){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Accountview.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            AccountviewController controller= loader.getController();
            controller.initialize(stage,account);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
}



