package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
    @FXML private GiftFloralSRDBI giftfloralDatabase = new GiftFloralSRDBI();
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

            giftOptions.getItems().add("Teddy Bears");
            giftOptions.getItems().add("Rose Bouquet");
            giftOptions.getItems().add("Floral Wreath");
            giftOptions.getItems().add("Tulip Bouquet");
        }

    @Override
    public void submit() {
        List<GiftType> listOfGifts = new ArrayList<>();
        String test = " ";




        confirmLabel.setText("Order confirmed for gifts " + test + "." );


        giftfloralDatabase.insertNode(
                new GiftFloralSR(
                        "id23",
                        "WAITING",
                        listOfGifts,
                        "01012020",
                        roomID.getValue()));
    }

    @Override
    public void clear() {
        confirmLabel.setText(" ");
        statusField.setValue("BLANK");
        roomID.setValue(locList.get(0).getNodeID() + ' ' + locList.get(0).getLongName());
        idField.clear();
        giftOptions.setValue(" ");
        assignedEmployeeField.setValue(employeeList.get(0).getEmployeeID() + ' ' + employeeList.get(0).getName());
    }
}
