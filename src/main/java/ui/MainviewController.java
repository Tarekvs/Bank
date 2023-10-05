package ui;

import bank.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the main view of the application.
 * Provides functionalities such as displaying accounts, adding accounts,
 * deleting accounts, and transitioning to specific account views.
 */
public class MainviewController {

    private Stage stage;

    @FXML
    private MenuItem menuaccountview;
    @FXML
    private MenuItem menudelete;
    @FXML
    private Button addbutton;
    @FXML
    private ListView<String> listview;

    /**
     * Sets the main stage for this controller.
     * 
     * @param stage The stage to be set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initializes the main view by loading all accounts from the bank
     * and displaying them in the list view.
     */
    @FXML
    public void initialize() {
        PrivateBank PB = new PrivateBank("Test", 0.5, 0.5, System.getProperty("user.dir") + "/src/main/java/bank/Konten/");
        List<String> strings = PB.getAllAccounts();
        ObservableList<String> items = FXCollections.observableArrayList(strings);
        listview.setItems(items);
    }

    /**
     * Displays a dialog for deleting a selected account.
     * It retrieves the account selected in the list view and transitions
     * to a modal for account deletion.
     */
    @FXML
    public void deleteDialog() {
        String account = listview.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModalAccDelete.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            ModalAccDeleteController controller = loader.getController();
            controller.initialize(account);
            controller.setStage(stage);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }

    /**
     * Displays a dialog for adding a new account.
     * Transitions to a modal for account addition.
     */
    @FXML
    public void addDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModalAccAdd.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            ModalAccAddController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }

    /**
     * Loads the view for a specific account.
     * It retrieves the account selected in the list view and transitions to
     * the detailed view for that account.
     */
    @FXML
    public void loadAccountview() {
        String account = listview.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Accountview.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            AccountviewController controller = loader.getController();
            controller.initialize(stage, account);
            controller.setStage(stage);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
