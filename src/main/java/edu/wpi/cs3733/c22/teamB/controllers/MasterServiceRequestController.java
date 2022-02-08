package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class MasterServiceRequestController {
    @FXML private MedicalEquipmentSRController medicalEquipmentSRController;
    @FXML private LaundrySRController laundrySRController;
    @FXML private FoodDeliverySRController foodDeliverySRController;
    @FXML private GiftFloralServiceController giftFloralSRController;
    @FXML private ExternalTransportController externalTransportSRController;
    @FXML private MedicineDeliverySRController medicineDeliverySRController;

    @FXML TabPane tabPane;
    @FXML private Label serviceRequestTitle;

    final String MED_EQUIP = "Medical Equipment Delivery";
    final String FOOD_DELIV = "Food Delivery";
    final String MEDI_DELIV = "Medicine Delivery";
    final String EXTERN_TRANS = "External Transport";
    final String LAUNDRY = "Laundry Service";
    final String GIFT_DELIV = "Gift and Floral Delivery";

    @FXML
    private void initialize() {
        serviceRequestTitle.setText("Medical Equipment Delivery");
    }

    // Go to the home fxml when the home button is pressed
    @FXML
    void homeButton(ActionEvent event) {
        // Try to go home
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
            // Print stack trace if unable to go home
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Set the Service Request Title to the name of the service request selected
    @FXML
    void tabPaneClicked(MouseEvent event) {
        switch (tabPane.getSelectionModel().getSelectedIndex()) {
            case 0:
                serviceRequestTitle.setText(MED_EQUIP);
                break;
            case 1:
                serviceRequestTitle.setText(FOOD_DELIV);
                break;
            case 2:
                serviceRequestTitle.setText(MEDI_DELIV);
                break;
            case 3:
                serviceRequestTitle.setText(EXTERN_TRANS);
                break;
            case 4:
                serviceRequestTitle.setText(LAUNDRY);
                break;
            case 5:
                serviceRequestTitle.setText(GIFT_DELIV);
                break;
        }
    }

    @FXML
    void submitButton(ActionEvent event) {
        switch (serviceRequestTitle.getText()) {
            case MED_EQUIP:
                medicalEquipmentSRController.submit();
                break;
            case FOOD_DELIV:
                System.out.println("Out of submit");
                foodDeliverySRController.submit();
                break;
            case MEDI_DELIV:
                medicineDeliverySRController.submit();
                break;
            case EXTERN_TRANS:
                externalTransportSRController.submit();
                break;
            case LAUNDRY:
                laundrySRController.submit();
                break;
            case GIFT_DELIV:
                // Call SRController.submit here
                giftFloralSRController.submit();
                break;
            default:
                System.out.println("No case found in submitButton()");
                break;
        }
    }

    public void clearButton(ActionEvent actionEvent) {
        switch (serviceRequestTitle.getText()) {
            case MED_EQUIP:
                medicalEquipmentSRController.clear();
                break;
            case FOOD_DELIV:
                foodDeliverySRController.clear();
                break;
            case MEDI_DELIV:
                medicineDeliverySRController.clear();
                break;
            case EXTERN_TRANS:
                externalTransportSRController.clear();
                break;
            case LAUNDRY:
                laundrySRController.clear();
                break;
            case GIFT_DELIV:
                // Call SRController.submit here
                giftFloralSRController.clear();
                break;
            default:
                System.out.println("No case found in clearButton()");
                break;
        }
    }
}
