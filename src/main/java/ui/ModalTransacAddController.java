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

public class ModalTransacAddController extends TransactionsController {


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

