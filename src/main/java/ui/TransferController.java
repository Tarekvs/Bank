package ui;

import bank.IncomingTransfer;
import bank.OutgoingTransfer;
import bank.Payment;
import bank.PrivateBank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

/**
 * Controller for handling transfer-related actions.
 * 
 * <p>
 * This controller extends the {@link TransactionsController} and is specific to transfer operations.
 * It facilitates the addition of incoming and outgoing transfers.
 * </p>
 */
public class TransferController extends TransactionsController{

    @FXML
    TextField date;
    @FXML
    TextField amount;
    @FXML
    TextField description;
    @FXML
    TextField sender;
    @FXML
    TextField recipient;


    public void initialize(){
        System.out.println("Initializing...");
        System.out.println("Sender TextField: " + sender);
        System.out.println("Account: " + account);
        sender.setText("Rastla");
    }

    /**
     * Adds a transfer transaction, either outgoing or incoming based on the account
     * being the sender or recipient.
     *
     * <p>
     * This method creates an instance of {@link OutgoingTransfer} if the account matches the sender.
     * Otherwise, if the account matches the recipient, it creates an instance of {@link IncomingTransfer}.
     * </p>
     */
    @FXML
    public void addtransfer (){

        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            System.out.println(account);
            System.out.println(sender.getText());
            if (Objects.equals(account, sender.getText())) {
                OutgoingTransfer O = new OutgoingTransfer(date.getText(),Double.parseDouble(amount.getText()),description.getText(),sender.getText(),recipient.getText());
                PB.addTransaction(account,O);

            }
            else if (Objects.equals(account, recipient.getText())){
                IncomingTransfer I = new IncomingTransfer(date.getText(),Double.parseDouble(amount.getText()),description.getText(),sender.getText(),recipient.getText());
                PB.addTransaction(account,I);

            }
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
