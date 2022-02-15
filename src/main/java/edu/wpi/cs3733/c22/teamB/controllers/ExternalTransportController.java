package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.ExternalTransportSR;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExternalTransportController implements IController {

    @FXML TextField patientID;
    @FXML TextField DestinationTxt;
    @FXML JFXComboBox<String> FormOfTransport;


    private ExternalTransportSR sr = null;

    public ExternalTransportController() {}

    public ExternalTransportController(ExternalTransportSR sr) {this.sr = sr;}



    @FXML
    private void initialize() {
        String st[] = {"Car", "Helicopter", "Ambulance", "Wheelchair", "Plane", "Boat", "Spaceship"};
        FormOfTransport.setItems(FXCollections.observableArrayList(st));
        DatabaseWrapper dw = new DatabaseWrapper();

    }


    @Override
    public void submit() {

    }

    @Override
    public void submit(AbstractSR sr) {
    DatabaseWrapper dw = new DatabaseWrapper();
    dw.addSR(new ExternalTransportSR(sr,patientID.getText(), DestinationTxt.getText(), FormOfTransport.getValue()));
    }

    @Override
    public void clear() {
        patientID.clear();
        DestinationTxt.clear();
        FormOfTransport.setAccessibleText("");
        FormOfTransport.valueProperty().set(null);
    }
}
