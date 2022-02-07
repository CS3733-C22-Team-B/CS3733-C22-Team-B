package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXListView;
import edu.wpi.cs3733.c22.teamB.entity.LaundrySRDBI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LaundrySRController implements IController {

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
