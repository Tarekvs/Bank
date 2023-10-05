package ui;


import bank.PrivateBank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalAccDeleteController {

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

    @FXML
    public void delete(){

        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        try {
            PB.deleteAccount(account);
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

    @FXML
    public void deletereturn(){
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



