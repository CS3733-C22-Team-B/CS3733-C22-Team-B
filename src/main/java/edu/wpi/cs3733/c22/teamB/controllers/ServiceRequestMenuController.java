package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javax.swing.border.Border;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class ServiceRequestMenuController {
    @FXML private JFXToggleButton toggleName;
    @FXML private Label name1;
    @FXML private Label name2;
    @FXML private Label name3;
    @FXML private Label name4;
    @FXML private Label name5;
    @FXML private Label name6;
    @FXML private Label name7;
    @FXML private Label name8;

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

            BorderHome.curBorderHome.changeNode(loader);

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

            BorderHome.curBorderHome.changeNode(loader);
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

            BorderHome.curBorderHome.changeNode(loader);
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

            BorderHome.curBorderHome.changeNode(loader);

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

            BorderHome.curBorderHome.changeNode(loader);

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

            BorderHome.curBorderHome.changeNode(loader);

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

            BorderHome.curBorderHome.changeNode(loader);
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

            BorderHome.curBorderHome.changeNode(loader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
