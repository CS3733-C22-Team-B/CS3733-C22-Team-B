package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.oldEntity.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class FoodDeliverySRController implements IController, Initializable {
    @FXML private TextField AssigneeName;
    @FXML private JFXComboBox<String> ComboMeals;
    @FXML private JFXComboBox<String> assignedEmployeeField;
    @FXML private JFXComboBox<String> statusField;
    @FXML private JFXComboBox<String> destinationField;
    @FXML private TextField idField;

    List<FoodDeliverySR> srList = new ArrayList<>();
    private List<Employee> employeeList;
    private Map<String, Employee> employeeMap;
    private List<Location> locList;
    private Map<String, Location> locMap;

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

        destinationField.getItems().addAll(locMap.keySet());


        assignedEmployeeField.getItems().addAll(employeeMap.keySet());

        ComboMeals.getItems().add("Chicken and Potatoes");
        ComboMeals.getItems().add("Salad and Fruits");
        ComboMeals.getItems().add("Soup of The Day");
    }

    @Override
    public void submit() {
        System.out.println("Submit: ");
        String foodName = ComboMeals.getValue();
        String foodRecipientName = AssigneeName.getText();
        Location destination = new Location();
        destination.setLongName(destinationField.getValue());
//        srList.add(
//                new FoodDeliverySR(
//                        "1", "WAITING", destination, foodName, foodRecipientName, new Employee()));
//        System.out.println(srList.get(srList.size() - 1).toString());

        FoodDeliverySRDBI foodDeliverySRDBI = new FoodDeliverySRDBI();
        foodDeliverySRDBI.insertNode(
                new FoodDeliverySR(idField.getText(), statusField.getValue(), locMap.get(destinationField.getValue()), ComboMeals.getValue(), AssigneeName.getText(), employeeMap.get(assignedEmployeeField.getValue())));
        clear();
    }

    @Override
    public void submit(edu.wpi.cs3733.c22.teamB.entity.AbstractSR sr) {

    }

    @Override
    public void clear() {
        AssigneeName.clear();
        ComboMeals.cancelEdit();
        assignedEmployeeField.setValue("");
        statusField.setValue("");
        destinationField.setValue("");
        idField.clear();
    }
}
