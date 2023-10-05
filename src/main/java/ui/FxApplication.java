package ui;
import bank.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class FxApplication extends javafx.application.Application{

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



    public static void main (String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


