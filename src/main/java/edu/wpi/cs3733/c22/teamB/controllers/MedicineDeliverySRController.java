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

public class MedicineDeliverySRController implements IController, Initializable {
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField patientID;
    @FXML private TextField DOB;
    @FXML private TextField email;
    //    TODO add allergies
    @FXML private TextField dosage;
    @FXML private TextField medicineName;
    @FXML private TextField dispenseAmount;
    @FXML private TextField frequency;
    @FXML private TextField form;
    @FXML private TextField mgPerDose;
    @FXML private JFXComboBox<String> roomNumber;
    @FXML private TextField idField;
    @FXML private JFXComboBox<String> statusField;
    @FXML private JFXComboBox<String> assignedEmployeeField;
    @FXML private TextField medicineID;

    private List<Location> locList;
    private Map<String, Location> locMap;
    private List<MedicalEquipment> medEqpList;
    private Map<String, MedicalEquipment> medEqpMap;
    private List<Employee> employeeList;
    private Map<String, Employee> employeeMap;

    private MedicineDeliverySRDBI medicineDeliverySRDBI = new MedicineDeliverySRDBI();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocationDBI locationDBI = new LocationDBI();

        locList = locationDBI.getAllNodes();
        locMap =
                IntStream.range(0, locList.size())
                        .boxed()
                        .collect(
                                Collectors.toMap(
                                        i -> (locList.get(i).getNodeID() + ' ' + locList.get(i).getLongName()),
                                        i -> locList.get(i)));

        EmployeeDBI employeeDBI = new EmployeeDBI();

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

        roomNumber.getItems().addAll(locMap.keySet());

        assignedEmployeeField.getItems().addAll(employeeMap.keySet());
    }

    public void submit() {
        //        medicineDeliverySRDBI.initConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        //        medicineDeliverySRDBI.closeConnection();
        MedicineDeliverySR medicineDeliverySR =
                new MedicineDeliverySR(
                        idField.getText(),
                        statusField.getValue(),
                        locMap.get(roomNumber.getValue()),
                        medicineID.getText(),
                        employeeMap.get(assignedEmployeeField.getValue()),
                        firstName.getText(),
                        lastName.getText(),
                        patientID.getText(),
                        DOB.getText(),
                        email.getText(),
                        dosage.getText(),
                        medicineName.getText(),
                        dispenseAmount.getText(),
                        frequency.getText(),
                        form.getText(),
                        mgPerDose.getText());
        //        System.out.println(medicineDeliverySR.toString());

        // roomNumber.getValue(),

        MedicineDeliverySRDBI medicineDeliverySRDBI = new MedicineDeliverySRDBI();
        medicineDeliverySRDBI.insertNode(medicineDeliverySR);
        clear();
    }

    @Override
    public void clear() {
        firstName.clear();
        lastName.clear();
        patientID.clear();
        DOB.clear();
        email.clear();
        dosage.clear();
        medicineName.clear();
        ;
        dispenseAmount.clear();
        frequency.clear();
        form.clear();
        mgPerDose.clear();
        idField.clear();
        roomNumber.setValue("");
        assignedEmployeeField.setValue("");
        statusField.setValue("");
        roomNumber.setValue("");
    }
}
