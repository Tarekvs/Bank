package ui;

import bank.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    public void initialize(Stage stage,String account, Transaction transaction){
        setAccount(account);
        setTransaction(transaction);
        setStage(stage);
    }
}
