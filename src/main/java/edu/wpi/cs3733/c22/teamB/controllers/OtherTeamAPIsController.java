package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamC.GiftServiceRequest;
import edu.wpi.teame.TeamESecurityServiceRequest;
import edu.wpi.teame.model.Employee;
import edu.wpi.teame.model.enums.DepartmentType;
import edu.wpi.teame.model.enums.EmployeeType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

public class OtherTeamAPIsController extends AbsPage{
    @FXML private JFXToggleButton toggleName;
    @FXML private Label name6;
    @FXML private Label name8;

    @FXML private Pane contentPane;
    @FXML private Pane anchorPane;

    @FXML private void toggleName(ActionEvent event) {
        name6.setVisible(toggleName.isSelected());
        name8.setVisible(toggleName.isSelected());
    }

    @FXML
    public void goToGiftSR(ActionEvent actionEvent) {
        List<Location> locs = DatabaseWrapper.getInstance().getAllLocation();
        (new GiftServiceRequest()).run(100, 100, 900, 600, null, locs.get(23).getNodeID(), locs.get(54).getNodeID());
    }

    @FXML
    public void goToSecuritySR(ActionEvent actionEvent) {
        DatabaseWrapper db = DatabaseWrapper.getInstance();
        List<Location> andrew = db.getAllLocation();
        Employee Hushmand = new Employee(1, DepartmentType.CLINICALSERVICES, "Hushmand", EmployeeType.Admin);
        Employee Sits = new Employee(2, DepartmentType.PLASTICSURGERY, "Sits", EmployeeType.Admin);
        Employee Nick = new Employee(3, DepartmentType.NEUROSURGERY, "Nick", EmployeeType.Admin);
        Employee Calvin = new Employee(4, DepartmentType.CLINICALSERVICES, "Calvin", EmployeeType.Admin);
        Employee Chris = new Employee(5, DepartmentType.CLINICALSERVICES, "Chris", EmployeeType.Admin);
        Employee andrewEmployee = new Employee(6, DepartmentType.MICU, "Andrew", EmployeeType.Admin);
        Employee Ben = new Employee(7, DepartmentType.FAMILYMEDICINE, "Ben", EmployeeType.Admin);
        Employee Noah = new Employee(8, DepartmentType.PLASTICSURGERY, "Noah", EmployeeType.Admin);
        Employee Kiki = new Employee(9, DepartmentType.NEUROSURGERY, "Kiki", EmployeeType.Admin);
        Employee Duc = new Employee(10, DepartmentType.MICU, "Duc", EmployeeType.Admin);
        Employee Wong = new Employee(11, DepartmentType.NEUROSURGERY, "Wilson", EmployeeType.Admin);
        Employee William = new Employee(12, DepartmentType.MICU, "William", EmployeeType.Staff);
        try {
            TeamESecurityServiceRequest.getInstance().run(0, 0, (int) Bapp.getPrimaryStage().getWidth(), (int) Bapp.getPrimaryStage().getHeight(), "resources/edu/wpi/cs3733/c22/teamB/styles/style.css", andrew.get(1).getNodeID());
            TeamESecurityServiceRequest.getInstance().insertEmployee(Hushmand);
            TeamESecurityServiceRequest.getInstance().insertEmployee(Sits);
            TeamESecurityServiceRequest.getInstance().insertEmployee(Nick);
            TeamESecurityServiceRequest.getInstance().insertEmployee(Calvin);
            TeamESecurityServiceRequest.getInstance().insertEmployee(Chris);
            TeamESecurityServiceRequest.getInstance().insertEmployee(andrewEmployee);
            TeamESecurityServiceRequest.getInstance().insertEmployee(Ben);
            TeamESecurityServiceRequest.getInstance().insertEmployee(Noah);
            TeamESecurityServiceRequest.getInstance().insertEmployee(Kiki);
            TeamESecurityServiceRequest.getInstance().insertEmployee(Duc);
            TeamESecurityServiceRequest.getInstance().insertEmployee(Wong);
            TeamESecurityServiceRequest.getInstance().insertEmployee(William);
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        toggleName.setSelected(false);
        name6.setVisible(false);
        name8.setVisible(false);

        initResize();
        resize();
        namePage();
    }

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.pageName.setText("Service Request Systems");
    }
}
