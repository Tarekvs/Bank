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

public class MainviewController {

    Stage stage;
    @FXML
    MenuItem menuaccountview;
    @FXML
    MenuItem menudelete;
    @FXML
    Button addbutton;

    @FXML
    ListView listview;


    public void setStage(Stage Stages){
        this.stage=Stages;
    }
    @FXML
    public void initialize(){
        PrivateBank PB = new PrivateBank("Test", 0.5,0.5,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");

        List<String> strings = PB.getAllAccounts();
        ObservableList<String> items = FXCollections.observableArrayList(strings);
        listview.setItems(items);
    }
    @FXML
    public void deleteDialog (){

        String account= (String) listview.getSelectionModel().getSelectedItem();
        System.out.println(account);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModalAccDelete.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            ModalAccDeleteController controller= loader.getController();
            controller.initialize(account);
            controller.setStage(stage);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }

    @FXML
    public void addDialog () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModalAccAdd.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            stage.setScene(scene);
            ModalAccAddController controller= loader.getController();
            controller.setStage(stage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
    @FXML
    public void loadAccountview (){
        String account= (String) listview.getSelectionModel().getSelectedItem();
        System.out.println(account);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Accountview.fxml"));
        try {
            Parent anwendung = loader.load();
            Scene scene = new Scene(anwendung);
            AccountviewController controller= loader.getController();
            controller.initialize(stage,account);
            controller.setStage(stage);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.show();
    }



}
