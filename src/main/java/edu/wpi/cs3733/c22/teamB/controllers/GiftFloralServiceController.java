package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.GiftType;
import edu.wpi.cs3733.c22.teamB.entity.GiftFloralSR;
import edu.wpi.cs3733.c22.teamB.entity.GiftFloralSRDBI;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class GiftFloralServiceController implements IController {
    @FXML private JFXCheckBox teddy1;
    @FXML private JFXCheckBox teddy2;
    @FXML private JFXCheckBox teddy3;
    @FXML private JFXCheckBox teddy4;
    @FXML private JFXCheckBox rose1;
    @FXML private JFXCheckBox rose2;
    @FXML private JFXCheckBox wreath1;
    @FXML private JFXCheckBox wreath2;
    @FXML private JFXComboBox<String> floorID;
    @FXML private JFXComboBox<String> roomID;
    @FXML private DatePicker dateID;
    @FXML private GiftFloralSRDBI giftfloralDatabase = new GiftFloralSRDBI();

    @FXML private Label confirmLabel;

    @FXML private Label reminderText;
    @FXML private Label whatGifts;
    // scroll area
    // checkboxes
    @FXML private Label whenGifts;
    // calendar
    @FXML private Label whatFloor;
    // dropdown floor
    @FXML private Label whatRoom;
    // dropdown room

    private boolean requestCompleted = false;
    private String assignedToRequest;

    private void requestAssigned(String name) {
        assignedToRequest = name;
    }

    @Override
    public void submit() {
        List<GiftType> listOfGifts = new ArrayList<>();
        String test = " ";
        if (teddy1.isSelected()) {
            listOfGifts.add(new GiftType(teddy1.getText(), false));
            test += "teddy1 ";
        }
        if (teddy2.isSelected()) {
            listOfGifts.add(new GiftType(teddy2.getText(), false));
            test += "teddy2 ";
        }
        if (teddy3.isSelected()) {
            listOfGifts.add(new GiftType(teddy3.getText(), false));
            test += "teddy3 ";
        }
        if (teddy4.isSelected()) {
            listOfGifts.add(new GiftType(teddy4.getText(), false));
            test += "teddy4 ";
        }
        if (rose1.isSelected()) {
            listOfGifts.add(new GiftType(rose1.getText(), true));
            test += "rose1 ";
        }
        if (rose2.isSelected()) {
            listOfGifts.add(new GiftType(rose2.getText(), true));
            test += "rose2 ";
        }
        if (wreath1.isSelected()) {
            listOfGifts.add(new GiftType(wreath1.getText(), false));
            test += "wreath1 ";
        }
        if (wreath2.isSelected()) {
            listOfGifts.add(new GiftType(wreath2.getText(), false));
            test += "wreath2 ";
        }




        confirmLabel.setText("Order confirmed for gifts " + test + "." );


        giftfloralDatabase.insertNode(
                new GiftFloralSR(
                        "id23",
                        "WAITING",
                        listOfGifts,
                        dateID.getValue(),
                        floorID.getValue(),
                        roomID.getValue()));
    }

    @Override
    public void clear() {
        teddy1.setSelected(false);
        teddy2.setSelected(false);
        teddy3.setSelected(false);
        teddy4.setSelected(false);
        rose1.setSelected(false);
        rose2.setSelected(false);
        wreath1.setSelected(false);
        wreath2.setSelected(false);
        floorID.setValue("");
        roomID.setValue("");
        confirmLabel.setText(" ");
    }
}
