package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.ExternalTransportSR;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ExternalTransportController implements IController {

    @FXML TextField SenderTxt;
    @FXML TextField PickupLocTxt;
    @FXML TextField DestinationTxt;
    @FXML TextField InfoTxt;
    @FXML Button SubmitB;
    @FXML Button HelpB;
    @FXML DatePicker DateCal;
    @FXML ChoiceBox FormOfTransport;
    @FXML Button HomeB;
    boolean isDone;
    String assignedP;

    @FXML
    private void initialize() {
        String st[] = {"Car", "Helicopter", "Ambulance", "Wheelchair", "Plane", "Boat", "Spaceship"};
        FormOfTransport.setItems(FXCollections.observableArrayList(st));
    }

    @FXML
    private void returnHomeScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void submit() {
        ExternalTransportSR request =
                new ExternalTransportSR(
                        "11112",
                        PickupLocTxt.getText(),
                        DestinationTxt.getText(),
                        "WAITING",
                        InfoTxt.getText(),
                        DateCal.getAccessibleText(),
                        FormOfTransport.getAccessibleText());
        System.out.println(request.toString());
        clear();
    }

    @Override
    public void clear() {
        SenderTxt.clear();
        PickupLocTxt.clear();
        DestinationTxt.clear();
        InfoTxt.clear();
        DateCal.getEditor().clear();
        FormOfTransport.setAccessibleText("");
        FormOfTransport.valueProperty().set(null);
    }
}
