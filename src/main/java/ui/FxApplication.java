package ui;

import bank.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * This is the main application class for the JavaFX UI.
 * It initializes and displays the primary stage with content loaded from "Mainview.fxml".
 * <p>
 * The class sets up the main view and connects it with its corresponding controller.
 * </p>
 * <p>
 * Note: The title of the primary stage is "Welcome to Tarek's bank".
 * </p>
 */
public class FxApplication extends javafx.application.Application {

    /**
     * Initializes the primary stage with the main view loaded from FXML.
     * This method is overridden from the JavaFX Application class.
     * 
     * @param stage The primary stage of the application.
     * @throws Exception if there's an issue loading the FXML or initializing the stage.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mainview.fxml"));
        Parent anwendung = loader.load();
        Scene scene = new Scene(anwendung);
        MainviewController controller = loader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
        stage.setTitle("Welcome to Tarek's bank");
        stage.show();
        controller.setStage(stage);
    }

    /**
     * The main entry point of the application.
     * <p>
     * Launches the JavaFX application. If there's any exception during the launch,
     * it prints the stack trace.
     * </p>
     * 
     * @param args Command line arguments. (Not used in this application)
     */
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
