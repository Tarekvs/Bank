package ui;


import bank.PrivateBank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Controller for the modal dialog used to delete accounts.
 * <p>
 * This controller allows users to delete an account
 * from the bank system. In case of errors, appropriate alerts are shown.
 * </p>
 */
public class ModalAccDeleteController {

    Stage stage;

    String account;
    public void setAccount(String account) {
        this.account=account;
    }

    public void setStage(Stage Stages){
        this.stage=Stages;
    }

    public void initialize(String account){
        setAccount(account);
    }

    /**
     * Delete an account from the bank system.
     * If successful, the account data is deleted from the JSON file and the user is redirected to the main view.
     * If an error occurs during account creation or writing, an error alert is displayed.
     * 
     * @throws Exception If there's an issue deleting the account.
     */
    @FXML
    public void delete(){

        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            PB.deleteAccount(account);
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error has occurred");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


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

    //Returns to Mainview
    @FXML
    public void deletereturn(){
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



