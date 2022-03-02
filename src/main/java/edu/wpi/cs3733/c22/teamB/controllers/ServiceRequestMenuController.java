package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import java.util.*;

import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.teame.TeamESecurityServiceRequest;
import edu.wpi.teame.model.Employee;
import edu.wpi.teame.model.enums.DepartmentType;
import edu.wpi.teame.model.enums.EmployeeType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.rmi.ServerException;


public class ServiceRequestMenuController extends AbsPage{
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
    @FXML private Pane anchorPane;

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

            AnchorHomeController.curAnchorHomeController.changeNode(loader);

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

            AnchorHomeController.curAnchorHomeController.changeNode(loader);
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

            AnchorHomeController.curAnchorHomeController.changeNode(loader);
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

            AnchorHomeController.curAnchorHomeController.changeNode(loader);

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

            AnchorHomeController.curAnchorHomeController.changeNode(loader);

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

            AnchorHomeController.curAnchorHomeController.changeNode(loader);

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

            AnchorHomeController.curAnchorHomeController.changeNode(loader);
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

            AnchorHomeController.curAnchorHomeController.changeNode(loader);
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

        initResize();
        resize();
        namePage();
    }

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.pageName.setText("Service Request Systems");
    }


    public void goToSecurityAPI(ActionEvent actionEvent) throws IOException {
//        try {
//            FXMLLoader loader = new FXMLLoader(
//                    getClass().getResource("libs/TeamESecurityServiceRequest"));
//        DatabaseWrapper db = DatabaseWrapper.getInstance();
//        List<Location> andrew = db.getAllLocation();
//        Employee Hushmand = new Employee(1, DepartmentType.CLINICALSERVICES, "Hushmand", EmployeeType.Admin);
//        Employee Sits = new Employee(2, DepartmentType.PLASTICSURGERY, "Sits", EmployeeType.Admin);
//        Employee Nick = new Employee(3, DepartmentType.NEUROSURGERY, "Nick", EmployeeType.Admin);
//        Employee Calvin = new Employee(4, DepartmentType.CLINICALSERVICES, "Calvin", EmployeeType.Admin);
//        Employee Chris = new Employee(5, DepartmentType.CLINICALSERVICES, "Chris", EmployeeType.Admin);
//        Employee andrewEmployee = new Employee(6, DepartmentType.MICU, "Andrew", EmployeeType.Admin);
//        Employee Ben = new Employee(7, DepartmentType.FAMILYMEDICINE, "Ben", EmployeeType.Admin);
//        Employee Noah = new Employee(8, DepartmentType.PLASTICSURGERY, "Noah", EmployeeType.Admin);
//        Employee Kiki = new Employee(9, DepartmentType.NEUROSURGERY, "Kiki", EmployeeType.Admin);
//        Employee Duc = new Employee(10, DepartmentType.MICU, "Duc", EmployeeType.Admin);
//        Employee Wong = new Employee(11, DepartmentType.NEUROSURGERY, "Wilson", EmployeeType.Admin);
//        Employee William = new Employee(12, DepartmentType.MICU, "William", EmployeeType.Staff);
//        TeamESecurityServiceRequest.getInstance().run(0, 0, (int) Bapp.getPrimaryStage().getWidth(), (int) Bapp.getPrimaryStage().getHeight(), "resources/edu/wpi/cs3733/c22/teamB/styles/style.css", andrew.get(1).getNodeID());
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Hushmand);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Sits);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Nick);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Calvin);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Chris);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(andrewEmployee);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Ben);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Noah);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Kiki);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Duc);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(Wong);
//        TeamESecurityServiceRequest.getInstance().insertEmployee(William);
        //System.out.println("----------------------------------------------");
        //System.out.println(TeamESecurityServiceRequest.getInstance().getAllEmployees().size());
        //for(Employee employee: TeamESecurityServiceRequest.getInstance().getAllEmployees()){
          //  System.out.println("----------------------------------------------");
           // System.out.println(employee.getId());
     //   }
        //            AnchorHomeController.curAnchorHomeController.changeNode(loader);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }
}
