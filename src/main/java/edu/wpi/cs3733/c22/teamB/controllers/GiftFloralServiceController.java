package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GiftFloralServiceController implements IController, Initializable {


    @FXML private JFXComboBox<String> roomID;
    @FXML private DatePicker dateID;
    private GiftFloralSRDBI giftfloralDatabase = new GiftFloralSRDBI();
    @FXML private TextField idField;
    @FXML private JFXComboBox<String> assignedEmployeeField;
    @FXML private JFXComboBox<String> statusField;
    @FXML private Label confirmLabel;
    @FXML private JFXComboBox<String> giftOptions;

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

    private List<Employee> employeeList;
    private Map<String, Employee> employeeMap;
    private List<Location> locList;
    private Map<String, Location> locMap;

    private void requestAssigned(String name) {
        assignedToRequest = name;
    }

    @Override
        public void initialize(URL location, ResourceBundle resources) {
            LocationDBI locationDBI = new LocationDBI();
//        locationDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
            locList = locationDBI.getAllNodes();
            locMap =
                    IntStream.range(0, locList.size())
                            .boxed()
                            .collect(
                                    Collectors.toMap(
                                            i -> (locList.get(i).getNodeID() + ' ' + locList.get(i).getLongName()),
                                            i -> locList.get(i)));
//        locationDBI.closeConnection();


            EmployeeDBI employeeDBI = new EmployeeDBI();
//        employeeDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
            employeeList = employeeDBI.getAllNodes();
            employeeMap =
                    IntStream.range(0, employeeList.size())
                            .boxed()
                            .collect(
                                    Collectors.toMap(
                                            i ->
                                                    (employeeList.get(i).getEmployeeID() + ' ' + employeeList.get(i).getName()),
                                            i -> employeeList.get(i)));
//        employeeDBI.closeConnection();

        statusField.getItems().addAll(AbstractSR.StringToSRStatus.keySet());
        statusField.setValue("BLANK");

        roomID.getItems().addAll(locMap.keySet());

        assignedEmployeeField.getItems().addAll(employeeMap.keySet());

            // Implement it so that when you press "gift" or "floral", it only shows
            // the drop down for one or the other

            // regular gift options
            giftOptions.getItems().add("Blanket");
            giftOptions.getItems().add("Book");
            giftOptions.getItems().add("Board Game");
            giftOptions.getItems().add("Chocolate");
            giftOptions.getItems().add("Cotton Pajamas");
            giftOptions.getItems().add("Coloring Books");
            giftOptions.getItems().add("Journal");
            giftOptions.getItems().add("Socks");
            giftOptions.getItems().add("Slippers");
            giftOptions.getItems().add("Sleep Mask");
            giftOptions.getItems().add("Teddy Bear");

            // Floral options
            giftOptions.getItems().add("Romantic Bouquet");
            giftOptions.getItems().add("Red Bouquet");
            giftOptions.getItems().add("Orange Bouquet");
            giftOptions.getItems().add("Yellow Bouquet");
            giftOptions.getItems().add("Blue Bouquet");
            giftOptions.getItems().add("Purple Bouquet");
            giftOptions.getItems().add("Pink Bouquet");
            giftOptions.getItems().add("White Bouquet");
            giftOptions.getItems().add("Floral Wreath");
        }

    @Override
    public void submit() {

      GiftFloralSR request = new GiftFloralSR(idField.getText(), statusField.getValue(), giftOptions.getValue(), dateID.getValue().toString(), roomID.getValue());
      System.out.println(request.toString());
      giftfloralDatabase.insertNode(request);
      clear();
    }

    @Override
    public void clear() {
        confirmLabel.setText(" ");
        statusField.setValue("");
        roomID.setValue("");
        idField.clear();
        giftOptions.setValue("");
        assignedEmployeeField.setValue("");
        dateID.getEditor().clear();
    }
}
