package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.*;
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
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MasterServiceRequestController {
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

    private Pane childPane;
    private IController childController;

    private String childSRType;
    private AbstractSR childSR = null;

    private List<Location> locList;
    private Map<String, Location> locMap;

    public MasterServiceRequestController() {}

    public MasterServiceRequestController(String srType) {
        try {
            childSRType = srType;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(srTypeToFXMLPath(srType)));
            childPane = loader.load();
            childController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MasterServiceRequestController(String srType, AbstractSR sr) {
        try {
            childSR = sr;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(srTypeToFXMLPath(srType)));
            loader.setControllerFactory(param -> {
                if (srType.equals("MedicalEquipmentSR"))
                     return new MedicalEquipmentSRController((MedicalEquipmentSR) sr);
                return null;
            });
            childPane = loader.load();
            childController = loader.getController();
            System.out.println(childController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void initialize() {
        // idField

        // statusField
        statusField.getItems().addAll(AbstractSR.SRstatus);

        // assignedEmployeeField
        // TODO: populate

        // floorField init
        floorField.getItems().addAll("ALL", "L1", "L2", "1", "2", "3"); // all floors

        // locationField init
        locList = (new LocationDaoI()).getAllValues();
        locMap =
                IntStream.range(0, locList.size())
                        .boxed()
                        .collect(
                                Collectors.toMap(
                                        i -> (locList.get(i).getLongName()), // assuming no dup in long name
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
            assignedEmployeeField.setValue(childSR.getAssignedEmployee().getFirstName() + childSR.getAssignedEmployee().getLastName());
            floorField.setValue(childSR.getLocation().getFloor());
            locationField.setValue(childSR.getLocation().getLongName());
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
    }

    @FXML private void submit(ActionEvent actionEvent) {
        MainSR sr = new MainSR(idField.getText(),
                                childSRType,
                                statusField.getValue(),
                locMap.get(locationField.getValue()),
                assignedEmployeeField.getValue(),
                assignedEmployeeField.getValue(),
                LocalDate.now(),
                notesField.getText());
        childController.submit(childSR);
    }

    @FXML private void clear(ActionEvent actionEvent) {
        idField.clear(); // set to correct one
        statusField.setValue("BLANK");
        assignedEmployeeField.setValue(""); // set to an employee
        floorField.setValue("ALL");
        locationField.getItems().clear();
        notesField.clear();

        childController.clear();
    }

    @FXML private void back(ActionEvent actionEvent) {
    }

    @FXML private void onFloorFieldChange(ActionEvent actionEvent) {
        // change locationField accordingly
        locationField.getItems().clear();
        locationField.getItems().removeAll();
        locationField.getItems().addAll(locMap.keySet()
                .stream()
                .filter(
                        lstr -> floorField.getValue().equals("ALL")
                                || locMap.get(lstr).getFloor().equals(floorField.getValue()))
                .collect(Collectors.toList()));
    }

    private static String srTypeToFXMLPath(String srType) {
        switch (srType) {
            case "MedicalEquipmentSR":
                return "/edu/wpi/cs3733/c22/teamB/views/MedicalEquipmentSR.fxml";
            default:
                throw new RuntimeException("srType invalid");
        }
    }
}
