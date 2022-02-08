package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXListView;
import edu.wpi.cs3733.c22.teamB.entity.LaundrySRDBI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class LaundrySRController implements IController, Initializable {

    private LaundrySRDBI laundryDBI = new LaundrySRDBI();

    @FXML private TextField roomNumberTextField;

    @FXML private JFXListView<String> roomsWithRequest;

    @FXML
    private void refresh(ActionEvent event) {
        updateRoomsWithRequest();
    }

    @FXML
    private void initialize() {
        updateRoomsWithRequest();
    }

    private void updateRoomsWithRequest() {
        roomsWithRequest.setItems(laundryDBI.getRooms());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void submit() {
        String roomNumber = roomNumberTextField.getText();
        if (!roomNumber.equals("")) {
            laundryDBI.add(roomNumber, "Facilities", "WAITING");
            roomNumberTextField.clear();
            updateRoomsWithRequest();
        }
    }

    @Override
    public void clear() {
        roomNumberTextField.clear();
    }
}
