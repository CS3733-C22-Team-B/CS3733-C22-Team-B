package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.oldEntity.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MedicalEquipmentTableController {

    @FXML private GridPane gridPane;
    @FXML private JFXButton confirmButton;
    @FXML private TextField equipmentIDField;
    @FXML private TextField equipmentTypeField;
    @FXML private TextField equipmentNameField;
    @FXML private TextField manufacturerField;
    @FXML private JFXComboBox statusField;
    @FXML private TextField colorField;
    @FXML private TextField sizeField;
    @FXML private TextField descriptionField;
    @FXML private JFXButton addButton;
    @FXML private JFXButton modifyButton;
    @FXML private JFXButton deleteButton;
    @FXML private TableView<MedicalEquipment> table;
    @FXML private JFXComboBox<String> LocationChoice;
    @FXML private JFXButton loadButton;
    private List<Location> locList;
    private Map<String, Location> locMap;

    private enum Function {ADD, MODIFY, DELETE, NOTHING, IDLOOKUP};
    MedicalEquipmentTableController.Function func = MedicalEquipmentTableController.Function.NOTHING;

    private boolean initTable = false;
    private MedicalEquipmentDBI medicalEquipmentDBI = new MedicalEquipmentDBI();
    List<MedicalEquipment> listOfMedicalEquipment;

    public MedicalEquipmentTableController() {}

    @FXML
    private void initialize() throws NullPointerException {
        modifyButton.setDisable(true);
        deleteButton.setDisable(true);

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

        statusField.getItems().addAll(AbstractSR.StringToSRStatus.keySet());
        statusField.setValue("BLANK");

        LocationChoice.getItems().addAll(locMap.keySet());
        gridPane.setVisible(false);
        gridPane.setDisable(true);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });

        loadTable();
    }

    public void loadTable() throws NullPointerException {
        if (!initTable) {
            initTable = true;


            TableColumn<MedicalEquipment, String> col1 = new TableColumn<>("equipmentID"); // column names
            TableColumn<MedicalEquipment, String> col2 = new TableColumn<>("equipmentName");
            TableColumn<MedicalEquipment, String> col3 = new TableColumn<>("equipmentType");
            TableColumn<MedicalEquipment, String> col4 = new TableColumn<>("manufacturer");
            TableColumn<MedicalEquipment, Location> col5 = new TableColumn<>("location");
            TableColumn<MedicalEquipment, String> col6 = new TableColumn<>("status"); // column names
            TableColumn<MedicalEquipment, String> col7 = new TableColumn<>("color");
            TableColumn<MedicalEquipment, String> col8 = new TableColumn<>("size");
            TableColumn<MedicalEquipment, String> col9 = new TableColumn<>("description");

            col1.setCellValueFactory(
                    new PropertyValueFactory<>("equipmentID")); // MedicalEquipmentSR fields
            col2.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
            col3.setCellValueFactory(new PropertyValueFactory<>("equipmentType"));
            col4.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
            col5.setCellValueFactory(new PropertyValueFactory<>("location"));
            col6.setCellValueFactory(new PropertyValueFactory<>("status"));
            col7.setCellValueFactory(new PropertyValueFactory<>("color"));
            col8.setCellValueFactory(new PropertyValueFactory<>("size"));
            col9.setCellValueFactory(new PropertyValueFactory<>("description"));

            table.getColumns().add(col1); // adding columns to setup table
            table.getColumns().add(col2);
            table.getColumns().add(col3);
            table.getColumns().add(col4);
            table.getColumns().add(col5);
            table.getColumns().add(col6);
            table.getColumns().add(col7);
            table.getColumns().add(col8);
            table.getColumns().add(col9);

            table.setEditable(true);
        }
        table.getItems().clear();
        listOfMedicalEquipment = medicalEquipmentDBI.getAllNodes();
        table.getItems().addAll(listOfMedicalEquipment); // create and add object
        //table.getItems().addAll(listMedicalEquipment);
        // table.getItems().addAll(new MedicalEquipment("15", "jeff", "jeffery", "jeff himself", new
        // Location("3", 3 , 4, "floor 3", "This building", " idkman", "longname", "shortname"),
        // "Done","blue","large", "funn"));

        // table.getItems().addAll(--list of objects here--); //

    }
    // Go to the home fxml when the home button is pressed
    @FXML
    void goToHome(ActionEvent event) {
        // Try to go home
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
            // Print stack trace if unable to go home
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void addLocation(ActionEvent actionEvent) {
        gridPane.setVisible(true);
        gridPane.setDisable(false);
        equipmentNameField.setVisible(true);
        equipmentTypeField.setVisible(true);
        manufacturerField.setVisible(true);
        statusField.setVisible(true);
        LocationChoice.setVisible(true);
        colorField.setVisible(true);
        sizeField.setVisible(true);
        descriptionField.setVisible(true);
        equipmentNameField.setDisable(false);
        equipmentTypeField.setDisable(false);
        manufacturerField.setDisable(false);
        statusField.setDisable(false);
        LocationChoice.setDisable(false);
        colorField.setDisable(false);
        sizeField.setDisable(false);
        descriptionField.setDisable(false);
        func = MedicalEquipmentTableController.Function.ADD;
    }
    @FXML
    private void modifyLocation(ActionEvent actionEvent) {
        gridPane.setVisible(true);
        gridPane.setDisable(false);
        equipmentNameField.setVisible(true);
        equipmentTypeField.setVisible(true);
        manufacturerField.setVisible(true);
        statusField.setVisible(true);
        LocationChoice.setVisible(true);
        colorField.setVisible(true);
        sizeField.setVisible(true);
        descriptionField.setVisible(true);
        equipmentNameField.setDisable(false);
        equipmentTypeField.setDisable(false);
        manufacturerField.setDisable(false);
        statusField.setDisable(false);
        LocationChoice.setDisable(false);
        colorField.setDisable(false);
        sizeField.setDisable(false);
        descriptionField.setDisable(false);

        MedicalEquipment loc = table.getSelectionModel().getSelectedItem();
        equipmentIDField.setText(loc.getEquipmentID());
        equipmentNameField.setText(loc.getEquipmentName());
        equipmentTypeField.setText(loc.getEquipmentType());
        manufacturerField.setText(loc.getManufacturer());
        statusField.setValue(loc.getStatus());
        LocationChoice.setValue(loc.getLocation().getNodeID() + ' ' + loc.getLocation().getLongName());
        colorField.setText(loc.getColor());
        sizeField.setText(loc.getDescription());
        descriptionField.setText(loc.getDescription());

        func = MedicalEquipmentTableController.Function.MODIFY;
    }

    @FXML
    private void deleteLocation(ActionEvent actionEvent) {
        medicalEquipmentDBI.deleteNode(table.getSelectionModel().getSelectedItem().getEquipmentID());
        loadTable();
    }

    @FXML private void locationTableClick(MouseEvent mouseEvent) {
        modifyButton.setVisible(true);
        deleteButton.setVisible(true);
    }

    @FXML private void confirm(ActionEvent actionEvent) {
        if(func == MedicalEquipmentTableController.Function.ADD) {
            MedicalEquipment m = new MedicalEquipment(
                    equipmentIDField.getText(),
                    equipmentNameField.getText(),
                    equipmentTypeField.getText(),
                    manufacturerField.getText(),
                    locMap.get(LocationChoice.getValue()),
                    statusField.getValue().toString(),
                    colorField.getText(),
                    sizeField.getText(),
                    descriptionField.getText());
            System.out.println(m);
            medicalEquipmentDBI.insertNode(m);
            loadTable();
        } else if (func == MedicalEquipmentTableController.Function.MODIFY) {
            medicalEquipmentDBI.updateNode(
                    new MedicalEquipment(
                            equipmentIDField.getText(),
                            equipmentNameField.getText(),
                            equipmentTypeField.getText(),
                            manufacturerField.getText(),
                            locMap.get(LocationChoice.getValue()),
                            statusField.getValue().toString(),
                            colorField.getText(),
                            sizeField.getText(),
                            descriptionField.getText()));
            loadTable();
        } else if (func == MedicalEquipmentTableController.Function.IDLOOKUP) {
            table.getItems().clear();
            table.getItems().add(medicalEquipmentDBI.getNode(equipmentIDField.getText())); // create and add object
        }

        clearForm(actionEvent);
        func = MedicalEquipmentTableController.Function.NOTHING;
    }

    @FXML private void clearForm(ActionEvent actionEvent) {
        equipmentIDField.clear();
        equipmentNameField.clear();
        equipmentTypeField.clear();
        manufacturerField.clear();
        statusField.setValue("");
        LocationChoice.setValue("");
        colorField.clear();
        sizeField.clear();
        descriptionField.clear();
    }

    @FXML private void cancelForm(ActionEvent actionEvent) {
        gridPane.setDisable(true);
        gridPane.setVisible(false);
        clearForm(actionEvent);

        addButton.setVisible(true);
        addButton.setDisable(false);

        modifyButton.setVisible(true);
        modifyButton.setDisable(false);

        deleteButton.setVisible(true);
        deleteButton.setDisable(false);

        func = MedicalEquipmentTableController.Function.NOTHING;
    }
    @FXML private void idLookup(ActionEvent actionEvent) {
        gridPane.setVisible(true);
        gridPane.setDisable(false);
        equipmentTypeField.setVisible(false);
        equipmentNameField.setVisible(false);
        manufacturerField.setVisible(false);
        statusField.setVisible(false);
        LocationChoice.setVisible(false);
        colorField.setVisible(false);
        sizeField.setVisible(false);
        descriptionField.setVisible(false);

        func = MedicalEquipmentTableController.Function.IDLOOKUP;
    }
}

