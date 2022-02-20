package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.controllers.services.*;
import edu.wpi.cs3733.c22.teamB.entity.SRIDGenerator;
import edu.wpi.cs3733.c22.teamB.entity.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MasterServiceRequestController extends AbsPage {
    @FXML private JFXButton submitButton;
    @FXML private JFXButton clearButton;
    @FXML private JFXButton backButton;
    @FXML private TextField idField;
    @FXML private JFXComboBox<String> statusField;
    @FXML private JFXComboBox<String> assignedEmployeeField;
    @FXML private TextArea notesField;
    @FXML private JFXComboBox<String> floorField;
    @FXML private JFXComboBox<String> locationField;
    @FXML private AnchorPane srPane;
    @FXML private Label srLabel;
    @FXML private Pane anchorPane;
    @FXML private Pane contentPane;

    private Pane childPane;
    private IController childController;

    private String childSRType;
    private AbstractSR childSR = null;

    private List<Location> locList;
    private Map<String, Location> locMap;
    private List<Employee> employeeList;
    private Map<String, Employee> employeeMap;

    public MasterServiceRequestController() {}

    public MasterServiceRequestController(String srType) {
        try {
            childSRType = srType;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(srTypeToFXMLPath(srType)));
            childPane = loader.load();
            System.out.println(childPane);
            childController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MasterServiceRequestController(String srType, AbstractSR sr) {
        try {
            childSR = sr;
            childSRType = srType;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(srTypeToFXMLPath(srType)));
            loader.setControllerFactory(param -> {
                // Important: add your controller below in an else if
                switch (srType) {
                    case "MedicalEquipmentSR":
                        return new MedicalEquipmentSRController((MedicalEquipmentSR) sr);
                    case "ComputerServiceSR":
                        return new ComputerServiceSRController((ComputerServiceSR) sr);
                    case "FoodDeliverySR":
                        return new FoodDeliverySRController((FoodDeliverySR) sr);
                    case "ExternalTransportSR":
                        return new ExternalTransportController((ExternalTransportSR) sr);
                    case "MedicineDeliverySR":
                        return new MedicineDeliverySRController((MedicineDeliverySR) sr);
                    case "LaundrySR":
                        return new LaundrySRController((LaundrySR) sr);
                    case "GiftFloralSR":
                        return new GiftFloralServiceController((GiftFloralSR) sr);
                    case "SanitationSR":
//                        srLabel.setText("Sanitation Service");
                        return new SanitationSRController((SanitationSR) sr);
                }
                return null;

            });
            childPane = loader.load();
            childController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DO NOT TOUCH THIS
    @FXML private void initialize() {
        DatabaseWrapper dw = new DatabaseWrapper();
        // idField

        srLabel.setText(getLabel());


        // statusField
        statusField.getItems().addAll(AbstractSR.SRstatus);

        // assignedEmployeeField
        employeeList = dw.getAllEmployee();
        employeeMap =
                IntStream.range(0, employeeList.size())
                        .boxed()
                        .collect(
                                Collectors.toMap(
                                        i ->
                                                (employeeList.get(i).getEmployeeID() + ' ' + employeeList.get(i).getName()),
                                        i -> employeeList.get(i)));
        assignedEmployeeField.getItems().addAll(employeeMap.keySet());

        // floorField init
        floorField.getItems().addAll("ALL", "L1", "L2", "1", "2", "3"); // all floors

        // locationField init
        locList = dw.getAllLocation();
        locMap =
                IntStream.range(0, locList.size())
                        .boxed()
                        .collect(
                                Collectors.toMap(
                                        i -> (locList.get(i).getNodeID() + ' ' + locList.get(i).getLongName()), // assuming no dup in long name
                                        i -> locList.get(i)));

        // notesField init

        // srLabel (Page title)
//        srLabel.setText(childSRType); // change this to be more correct

        if (childSR == null) {
            clear(null);
            idField.setDisable(true);
            statusField.setDisable(true);
            assignedEmployeeField.setDisable(true);
        }
        else {
            idField.setText(childSR.getSrID());
            statusField.setValue(childSR.getStatus());
            assignedEmployeeField.setValue(childSR.getAssignedEmployee().getEmployeeID() + ' ' + childSR.getAssignedEmployee().getName());
            floorField.setValue(childSR.getLocation().getFloor());
            locationField.setValue(childSR.getLocation().getNodeID() + ' ' + childSR.getLocation().getLongName());
            notesField.setText(childSR.getNotes());

            idField.setDisable(false);
            statusField.setDisable(false);
            assignedEmployeeField.setDisable(false);
        }
        locationField.getItems().addAll(locMap.keySet()
                .stream()
                .filter(
                        lstr -> floorField.getValue().equals("ALL")
                                || locMap.get(lstr).getFloor().equals(floorField.getValue()))
                .collect(Collectors.toList()));

        // load specific SR fxml
        srPane.getChildren().add(childPane);
        initResize();
        resize();
        namePage();
    }

    private String getLabel() {
        String name = "";
        if (childSRType.equals("ExternalTransportSR")){
            name =  "External Patient Transport Service Request";
        }
        if (childSRType.equals("ComputerServiceSR")){
            return "Computer Service Request";
        }
        if (childSRType.equals("FoodDeliverySR")){
            return "Food Delivery Service Request";
        }
        if (childSRType.equals("GiftFloralSR")){
            return "Gift and Floral Service Request";
        }
        if (childSRType.equals("LaundrySR")){
            return "Laundry Service Request";
        }
        if (childSRType.equals("MedicalEquipmentSR")){
            return "Medical Equipment Service Request";
        }
        if (childSRType.equals("MedicineDeliverySr")){
            return "Medicine Delivery Request";
        }
        if (childSRType.equals("SanitationSR")){
            return "Sanitation Service Request";
        }

        return name;
    }

    // DO NOT TOUCH THIS
    @FXML private void submit(ActionEvent actionEvent) {
        childSR = new MainSR(
                idField.getText(),
                childSRType,
                statusField.getValue(),
                locMap.get(locationField.getValue()),
                employeeMap.get(assignedEmployeeField.getValue()),
                employeeMap.get(assignedEmployeeField.getValue()),
                LocalDate.now(),
                notesField.getText());
        childController.submit(childSR);
        this.clear(null);
    }

    // DO NOT TOUCH THIS
    @FXML private void clear(ActionEvent actionEvent) {
        idField.setText(SRIDGenerator.generateID());
        statusField.setValue("WAITING");
        assignedEmployeeField.setValue(employeeList.get(0).getEmployeeID() + ' ' + employeeList.get(0).getName());
        floorField.setValue("ALL");
        locationField.setValue(null);
        notesField.clear();

        childController.clear();
    }

    @FXML private void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/ServiceRequestMenu.fxml"));
            AnchorHomeController.curAnchorHomeController.changeNode(loader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML private void onFloorFieldChange(ActionEvent actionEvent) {
        // change locationField accordingly
        locationField.setValue(null);
        locationField.getItems().removeAll();
        locationField.getItems().clear();
        locationField.getItems().addAll(locMap.keySet()
                .stream()
                .filter(
                        lstr -> floorField.getValue().equals("ALL")
                                || locMap.get(lstr).getFloor().equals(floorField.getValue()))
                .collect(Collectors.toList()));
    }
    // Important: add your path here
    public static String srTypeToFXMLPath(String srType) {
        switch (srType) {
            case "MedicalEquipmentSR":
                return "/edu/wpi/cs3733/c22/teamB/views/services/MedicalEquipmentSR.fxml";
            case "ComputerServiceSR":
                return "/edu/wpi/cs3733/c22/teamB/views/services/ComputerServiceSR.fxml";
            case "FoodDeliverySR":
                return "/edu/wpi/cs3733/c22/teamB/views/services/FoodDeliveryService.fxml";
            case "ExternalTransportSR":
                return "/edu/wpi/cs3733/c22/teamB/views/services/ExternalTransport.fxml";
            case "MedicineDeliverySR":
                return "/edu/wpi/cs3733/c22/teamB/views/services/MedicineDeliveryService.fxml";
            case "LaundrySR":
                return "/edu/wpi/cs3733/c22/teamB/views/services/LaundryService.fxml";
            case "GiftFloralSR":
                return "/edu/wpi/cs3733/c22/teamB/views/services/GiftFloralService.fxml";
            case "SanitationSR":
                return "/edu/wpi/cs3733/c22/teamB/views/services/SanitationSR.fxml";
            default:
                throw new RuntimeException("srType invalid");
        }
    }


    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.pageName.setText(getLabel());
    }
}
