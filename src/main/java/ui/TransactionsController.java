package ui;

import bank.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Controller for handling transaction-related actions.
 * 
 * <p>
 * This controller serves as a base for transaction-based operations. It offers
 * functionalities to set and get basic transaction details.
 * </p>
 */
public class TransactionsController {
    Stage stage;

    public void setStage(Stage Stages){
        this.stage=Stages;
    }

    @FXML
    String account;
    public void setAccount(String account) {
        this.account = account;
    }
    Transaction transaction;
    public void setTransaction(Transaction transaction){
        this.transaction=transaction;
    }


    /**
     * Initializes the controller with the provided details.
     *
     * @param stage The stage associated with this controller.
     * @param account The account associated with the transaction.
     * @param transaction The transaction to be processed.
     */
    public void initialize(Stage stage,String account, Transaction transaction){
        setAccount(account);
        setTransaction(transaction);
        setStage(stage);
    }
}
