package ui;

import bank.PrivateBank;
import bank.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Collections;


/**
 * Controller for the modal dialog used to add transactions.
 * <p>
 * This controller provides interfaces for transferring and making payments. 
 * The user can choose to either transfer funds or make a payment. 
 * Each action redirects the user to the appropriate view.
 * </p>
 */
public class ModalTransacAddController extends TransactionsController {


    /**
     * Handles the transfer action by redirecting the user to the transfer view.
     * 
     * @throws Exception If there's an issue loading the 'Transfer.fxml' or initializing the TransferController.
     */
    @FXML
    public void transfer (){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Transfer.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            TransferController controller= loader.getController();
            controller.initialize(stage,account,null);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
    
    /**
     * Handles the payment action by redirecting the user to the payment view.
     * 
     * @throws Exception If there's an issue loading the 'Payment.fxml' or initializing the PaymentController.
     */
    @FXML
    public void payment(){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Payment.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            PaymentController controller= loader.getController();
            controller.initialize(stage,account,transaction);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }



}

