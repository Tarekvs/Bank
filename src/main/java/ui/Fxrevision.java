package ui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

class myHandler implements EventHandler <ActionEvent> {
    @Override
    public void handle (ActionEvent ereignis){
        System.out.println("swag");
    }
}
public class Fxrevision extends Application {

    @Override
    public void start(Stage stage){

        FlowPane flow = new FlowPane();
        FlowPane flow2 = new FlowPane();
        FlowPane flow3 = new FlowPane();

        Label Kontostand = new Label("Kontostand: ");
        Label Betrag = new Label("Betrag: ");
        TextField Kontostandt = new TextField ();
        TextField Betragt = new TextField ();
        flow.getChildren().addAll(Kontostand,Kontostandt);
        flow2.getChildren().addAll(Betrag,Betragt);
        Button einzahlen = new Button("einzahlen");
        Button abheben = new Button("abheben");
        einzahlen.setOnAction(new myHandler());
        abheben.setOnAction(new EventHandler <ActionEvent> (){
            @Override
            public void handle (ActionEvent ereignis){
                System.out.println("lassmich");
            }
                            });
        flow3.getChildren().addAll(einzahlen,abheben);
        GridPane grid = new GridPane();
        grid.add(flow,0,0);
        grid.add(flow2,0,1);
        grid.add(flow3,0,2);
        Scene scene = new Scene(grid);
        stage.setTitle("swag");
        stage.setScene(scene);
        stage.show();
    }
    public static void main (String [] args){

            launch(args);
    }
}
