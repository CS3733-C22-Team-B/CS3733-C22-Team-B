package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Arrays;


public class TictactoeController extends AbsPage {
    @FXML
    private JFXButton button1;
    @FXML
    private JFXButton button2;
    @FXML
    private JFXButton button3;
    @FXML
    private JFXButton button4;
    @FXML
    private JFXButton button5;
    @FXML
    private JFXButton button6;
    @FXML
    private JFXButton button7;
    @FXML
    private JFXButton button8;
    @FXML
    private JFXButton button9;


    ArrayList<JFXButton> buttons;
    private int playerTurn = 0;
    @FXML
    private Label winnerText;

    private void setupButton(JFXButton button){
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    private void checkIfGameIsOver() {
        for (int a = 0; a < 8; a++) {
            String line;
            switch (a) {
                case 0:
                    line = button1.getText() + button2.getText() + button3.getText();
                    break;
                case 1:
                    line = button4.getText() + button5.getText() + button6.getText();
                    break;
                case 2:
                    line = button7.getText() + button8.getText() + button9.getText();
                    break;
                case 3:
                    line = button1.getText() + button5.getText() + button9.getText();
                    break;
                case 4:
                    line = button3.getText() + button5.getText() + button7.getText();
                    break;
                case 5:
                    line = button1.getText() + button4.getText() + button7.getText();
                    break;
                case 6:
                    line = button2.getText() + button5.getText() + button8.getText();
                    break;
                case 7:
                    line = button3.getText() + button6.getText() + button9.getText();
                    break;
                default:
                    line = null;
                    break;
            }

            //X winner
            if (line.equals("XXX")) {
                winnerText.setText("X won!");
                for(JFXButton button : buttons){
                    button.setDisable(true);
                }
            }

            //O winner
            else if (line.equals("OOO")) {
                winnerText.setText("O won!");
                for (JFXButton button : buttons) {
                    button.setDisable(true);
                }
            }

        }
    }

    private void setPlayerSymbol(JFXButton button) {
        if(playerTurn % 2 == 0){
            button.setText("X");
            playerTurn = 1;
        }
        else{
            button.setText("O");
            playerTurn = 0;
        }
    }


    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        buttons.forEach(jfxButton -> {
            setupButton(jfxButton);
            jfxButton.setFocusTraversable(false);
        });
        initResize();
        resize();
        namePage();

    }


    public void restartGame(ActionEvent actionEvent) {
        buttons.forEach(this::resetButton);
        winnerText.setText("Tic-Tac-Toe");
    }

    private void resetButton(JFXButton jfxButton) {
        jfxButton.setDisable(false);
        jfxButton.setText("");
    }

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.setPageName("TicTacToe");
    }
}
