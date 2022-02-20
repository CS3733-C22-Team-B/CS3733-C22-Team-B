package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ServiceRequestMenuController implements IPage{
    @FXML private JFXToggleButton toggleName;
    @FXML private Label name1;
    @FXML private Label name2;
    @FXML private Label name3;
    @FXML private Label name4;
    @FXML private Label name5;
    @FXML private Label name6;
    @FXML private Label name7;
    @FXML private Label name8;

    @FXML private Pane contentPane;

    @FXML private void toggleName(ActionEvent event) {
        name1.setVisible(toggleName.isSelected());
        name2.setVisible(toggleName.isSelected());
        name3.setVisible(toggleName.isSelected());
        name4.setVisible(toggleName.isSelected());
        name5.setVisible(toggleName.isSelected());
        name6.setVisible(toggleName.isSelected());
        name7.setVisible(toggleName.isSelected());
        name8.setVisible(toggleName.isSelected());
    }

    @FXML
    private void goToMedicalEquipmentSR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("MedicalEquipmentSR"));

            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML private void goToFoodDeliverySR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("FoodDeliverySR"));

            BorderHomeController.curBorderHomeController.changeNode(loader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToExternalTransportSR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("ExternalTransportSR"));

            BorderHomeController.curBorderHomeController.changeNode(loader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void goToComputerServiceSR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("ComputerServiceSR"));

            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToMedicineDeliverySR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("MedicineDeliverySR"));

            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToLaundryServiceSR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("LaundrySR"));

            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToGiftFloralSR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("GiftFloralSR"));

            BorderHomeController.curBorderHomeController.changeNode(loader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToSanitationSR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("SanitationSR"));

            BorderHomeController.curBorderHomeController.changeNode(loader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        toggleName.setSelected(false);
        name1.setVisible(false);
        name2.setVisible(false);
        name3.setVisible(false);
        name4.setVisible(false);
        name5.setVisible(false);
        name6.setVisible(false);
        name7.setVisible(false);
        name8.setVisible(false);
        resize();
        namePage();
    }

    @Override
    public void resize() {
        Bapp.getPrimaryStage().heightProperty().addListener((observable)-> {
            contentPane.setLayoutX(Bapp.getPrimaryStage().getWidth()/4);
            contentPane.setLayoutY(Bapp.getPrimaryStage().getHeight()/6);
        });
    }

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.pageName.setText("Service Request Systems");
    }
}
