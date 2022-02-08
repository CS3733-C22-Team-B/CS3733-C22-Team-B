package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.Employee;
import edu.wpi.cs3733.c22.teamB.entity.EmployeeDBI;
import edu.wpi.cs3733.c22.teamB.entity.ExternalTransportSR;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    @FXML ChoiceBox EmployeeAssignment;

    boolean isDone;
    String assignedP;
    private List<Employee> employeeList;
    private Map<String, Employee> employeeMap;

    @FXML
    private void initialize() {
        String st[] = {"Car", "Helicopter", "Ambulance", "Wheelchair", "Plane", "Boat", "Spaceship"};
        FormOfTransport.setItems(FXCollections.observableArrayList(st));
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



        EmployeeAssignment.getItems().addAll(employeeMap.keySet());
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
                        SenderTxt.getText(),
                        PickupLocTxt.getText(),
                        DestinationTxt.getText(),
                        "WAITING",
                        InfoTxt.getText(),
                        DateCal.getAccessibleText(),
                        FormOfTransport.getAccessibleText(),
                        employeeMap.get(EmployeeAssignment.getValue()));
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
        EmployeeAssignment.valueProperty().set(null);
    }
}
