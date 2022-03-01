package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXToggleButton;
//import edu.wpi.GoldenGandaberundas.Main;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamD.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
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

    public void goToInternalTransportSR(ActionEvent actionEvent) {
        List<Location> locs = DatabaseWrapper.getInstance().getAllLocation();
        try {
            Main.run(100, 100, 900, 600, "/edu/wpi/cs3733/c22/teamB/styles/style.css", locs.get(23).getNodeID(), locs.get(54).getNodeID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToSanitationSR(ActionEvent actionEvent) {
//        (new Main()).run();
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
