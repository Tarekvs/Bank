package ui;

import bank.PrivateBank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Collections;

public class ModalAccAddController {
    Stage stage;

    @FXML
    TextField accountname;
    public void setStage(Stage Stages){
        this.stage=Stages;
    }

    public void addaccount(){
        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            PB.createAccount(accountname.getText());
            PB.writeAccount(accountname.getText());
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error has occurred");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Mainview.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            MainviewController controller= loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }

}

