package ui;

import javafx.stage.Stage;

/**
 * This class is the controller of the account page.
 * It is used to set the account and stage.
 * It is used to initialize the account page.
 */
public class AccountController {
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

}
