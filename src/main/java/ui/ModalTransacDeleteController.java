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


/**
 * Controller for the modal dialog used to handle transaction deletion.
 * <p>
 * This controller facilitates the deletion of a specified transaction and provides
 * methods for navigating back to the account view.
 * </p>
 */
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

    /**
     * Initializes the controller with the given parameters.
     * 
     * @param stage the stage to be used.
     * @param account the account to be used.
     * @param transaction the transaction to be used.
     */
    public void initialize(Stage stage, String account, Transaction transaction){
        setAccount(account);
        setTransaction(transaction);
        setStage(stage);
    }

    /**
     * Handles the delete action by removing the selected transaction and updating the account.
     * Redirects the user to the 'Accountview.fxml'.
     * 
     * @throws Exception If there's an issue with the operation or loading 'Accountview.fxml'.
     */
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


    /**
     * Handles the return action by redirecting the user to the 'Accountview.fxml'.
     * 
     * @throws Exception If there's an issue loading 'Accountview.fxml'.
     */
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



