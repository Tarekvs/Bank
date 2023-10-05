package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;


public class Fxtests extends javafx.application.Application{
    private TextField kontostandFeld = new TextField();
    private Button einzahlKnopf = new Button();
    private Button abhebeKnopf = new Button();
    private TextField betragFeld = new TextField();
    @Override
    public void start(Stage stage) throws Exception {

        FlowPane kontostandScheibe = new FlowPane();
        Label kontostandEtikett = new Label("Kontostand: ");
        kontostandScheibe.getChildren().addAll(kontostandEtikett,kontostandFeld);
        FlowPane betragScheibe = new FlowPane();
        Label betragEtikett = new Label("Betrag: ");
        betragScheibe.getChildren().addAll(betragEtikett,betragFeld);
        GridPane buttons = new GridPane();
        Button einzahlenButton = new Button();
        Button abhebenButton = new Button();
        einzahlenButton.setText("einzahlen");
        abhebenButton.setText("abheben");
        myHandler handlingButton = new myHandler();
        einzahlenButton.setOnAction(handlingButton);
        abhebenButton.setOnAction(handlingButton);
        buttons.add(einzahlenButton, 0,0);
        buttons.add(abhebenButton, 0,1);
        GridPane fensterInhalt = new GridPane();
        fensterInhalt.add(kontostandScheibe,0,0);
        fensterInhalt.add(betragScheibe,0,1);
        fensterInhalt.add(buttons,0,2);
        Scene scene = new Scene(fensterInhalt);
        stage.setScene(scene);
        stage.setTitle("Bankkonto");
        stage.show();
    }

    public class myHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent ereignis) {
            System.out.println("Es wurde " + ereignis.getSource() + " gedrueckt.");
        }
    }
    public static void main (String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


