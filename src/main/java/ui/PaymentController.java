package ui;

import bank.Payment;
import bank.PrivateBank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * Controller for handling the addition of payments.
 * <p>
 * This controller is responsible for capturing payment details from the user and adding
 * them as transactions to a given account. Once a payment is added, the user is redirected
 * back to the account view.
 * </p>
 */
public class PaymentController extends TransactionsController{

    @FXML
    TextField date;
    @FXML
    TextField amount;
    @FXML
    TextField description;
    @FXML
    TextField incominginterest;
    @FXML
    TextField outgoinginterest;


    /**
     * Adds a payment as a transaction to the given account.
     * <p>
     * After successfully adding a payment, the user is redirected to the 'Accountview.fxml'.
     * If an error occurs, it presents an alert dialog with the error message.
     * </p>
     * 
     * @throws Exception If there's an issue with the payment addition or loading 'Accountview.fxml'.
     */
    @FXML
    public void addpayment (){

        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            Payment P = new Payment(date.getText(),Double.parseDouble(amount.getText()),description.getText(),Double.parseDouble(incominginterest.getText()), Double.parseDouble(outgoinginterest.getText()));
            PB.addTransaction(account,P);
            PB.writeAccount(account);
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error has occurred");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
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
}
