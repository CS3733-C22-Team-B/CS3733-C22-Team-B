package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class MedicalEquipmentSRController implements IController, Initializable {
    @FXML private TextField idField;
    @FXML private TextField requesterNameField;
    @FXML private JFXComboBox<String> assignedEmployeeField;
    @FXML private JFXComboBox<String> statusField;
    @FXML private JFXComboBox<String> destinationField;
    @FXML private JFXComboBox<String> equipmentNameField;

    private List<Location> locList;
    private Map<String, Location> locMap;
    private List<MedicalEquipment> medEqpList;
    private Map<String, MedicalEquipment> medEqpMap;
    private List<Employee> employeeList;
    private Map<String, Employee> employeeMap;
    //    Connection conn = DBConnection.getConnection();

    private MedicalEquipmentSRDBI medSRDB = new MedicalEquipmentSRDBI();

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

        MedicalEquipmentDBI medicalEquipmentDBI = new MedicalEquipmentDBI();
        //        medicalEquipmentDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        medEqpList = medicalEquipmentDBI.getAllNodes();
        medEqpMap =
                IntStream.range(0, medEqpList.size())
                        .boxed()
                        .collect(
                                Collectors.toMap(
                                        i ->
                                                (medEqpList.get(i).getEquipmentID()
                                                        + ' '
                                                        + medEqpList.get(i).getEquipmentName()),
                                        i -> medEqpList.get(i)));
        //        medicalEquipmentDBI.closeConnection();

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

        destinationField.getItems().addAll(locMap.keySet());

        equipmentNameField.getItems().addAll(medEqpMap.keySet());

        assignedEmployeeField.getItems().addAll(employeeMap.keySet());
    }

    @Override
    public void submit() {
        //        Location destination = new Location();

        //        medSRDB.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");

        medSRDB.insertNode(
                new MedicalEquipmentSR(
                        idField.getText(),
                        statusField.getValue(),
                        locMap.get(destinationField.getValue()),
                        medEqpMap.get(equipmentNameField.getValue()),
                        employeeMap.get(assignedEmployeeField.getValue())));

        //        medSRDB.closeConnection();
    }

    @Override
    public void clear() {
        idField.clear();
        requesterNameField.clear();
        assignedEmployeeField.setValue(
                employeeList.get(0).getEmployeeID() + ' ' + employeeList.get(0).getName());
        statusField.setValue("BLANK");
        destinationField.setValue(locList.get(0).getNodeID() + ' ' + locList.get(0).getLongName());
        equipmentNameField.setValue(
                medEqpList.get(0).getEquipmentID() + ' ' + medEqpList.get(0).getEquipmentName());
    }
}
