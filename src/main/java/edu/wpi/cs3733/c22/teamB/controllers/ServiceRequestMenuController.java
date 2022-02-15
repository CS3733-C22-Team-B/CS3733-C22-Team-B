package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ServiceRequestMenuController {
    @FXML
    private void goToMedicalEquipmentSR(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MasterServiceRequest.fxml"));
            loader.setControllerFactory(
                    param -> new MasterServiceRequestController("MedicalEquipmentSR"));
            Parent root = loader.load();
            Bapp.getPrimaryStage().getScene().setRoot(root);
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
            Parent root = loader.load();
            Bapp.getPrimaryStage().getScene().setRoot(root);
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
            Parent root = loader.load();
            Bapp.getPrimaryStage().getScene().setRoot(root);
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
            Parent root = loader.load();
            Bapp.getPrimaryStage().getScene().setRoot(root);
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
            Parent root = loader.load();
            Bapp.getPrimaryStage().getScene().setRoot(root);
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
            Parent root = loader.load();
            Bapp.getPrimaryStage().getScene().setRoot(root);
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
            Parent root = loader.load();
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
