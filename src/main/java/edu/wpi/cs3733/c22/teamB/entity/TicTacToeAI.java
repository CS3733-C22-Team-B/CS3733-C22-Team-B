package edu.wpi.cs3733.c22.teamB.entity;

import com.jfoenix.controls.JFXButton;

import java.util.List;
import java.util.Random;

public class TicTacToeAI {
    private boolean on;
    private int turnNum;
    private boolean aiTurn;

    public TicTacToeAI(boolean isOn, int turnNumb, boolean aiTurnBool){
        this.on = isOn;
        this.turnNum = turnNumb;
        this.aiTurn = aiTurnBool;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getTurnNum() {
        return turnNum;
    }

    public void setTurnNum(int turnNum) {
        this.turnNum = turnNum;
    }

    public boolean isAiTurn() {
        return aiTurn;
    }

    public void setAiTurn(boolean aiTurn) {
        this.aiTurn = aiTurn;
    }

    public void playTurn(List<JFXButton> jfxButtonList){
        if(on && aiTurn){
            Random rn = new Random();
            int buttonToPress = rn.nextInt(8) + 0;
                if(!jfxButtonList.get(buttonToPress).isDisable() && getTurnNum() % 2 == 0){
                    jfxButtonList.get(buttonToPress).setText("X");
                    jfxButtonList.get(buttonToPress).setDisable(true);
                    setTurnNum(1);
                }
                else if(!jfxButtonList.get(buttonToPress).isDisable() ){
                    jfxButtonList.get(buttonToPress).setText("O");
                    jfxButtonList.get(buttonToPress).setDisable(true);
                    setTurnNum(0);
                }
                else {
                    playTurn(jfxButtonList);
                }
        }
    }
}
