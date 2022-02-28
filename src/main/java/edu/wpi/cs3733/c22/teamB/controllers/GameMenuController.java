package edu.wpi.cs3733.c22.teamB.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameMenuController extends AbsPage{
    @FXML
    private Pane contentPane;
    @FXML private Pane anchorPane;

    @FXML
    public void initialize(){
        initResize();
        resize();
        namePage();

    }

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.setPageName("Games");
    }

    public void goToTicTacToe(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/tictactoe.fxml"));
            AnchorHomeController.curAnchorHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToSnake(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/snake.fxml"));
            AnchorHomeController.curAnchorHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
