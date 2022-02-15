package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GiftFloralServiceController implements IController {


    @FXML private JFXComboBox<String> giftOptions;
    @FXML private JFXComboBox<String> giftType;

    private GiftFloralSR sr = null;

    public GiftFloralServiceController(){}
    public GiftFloralServiceController(GiftFloralSR sr){
        this.sr = sr;
    }

    @FXML
    public void initialize(){
        DatabaseWrapper dw = new DatabaseWrapper();
        String type[] = {"Floral", "Gift"};
        String opts[] = {"Teddy Bear", "Pajamas", "Slippers", "Rock", "Coloring Books"};
        String optsFl[] = {"Red Bouquet", "Orange Bouquet", "Yellow Bouquet"};

        giftType.setItems(FXCollections.observableArrayList(type));

        if (giftType.getValue().equals("Floral")){
            giftOptions.setItems(FXCollections.observableArrayList(optsFl));
        }
        else if (giftType.getValue().equals("Gift")){
            giftOptions.setItems(FXCollections.observableArrayList(opts));
        }

    }


   /* @Override
        public void initialize(URL location, ResourceBundle resources) {

            // Implement it so that when you press "gift" or "floral", it only shows
            // the drop down for one or the other

            // regular gift options
            giftOptions.getItems().add("Blanket");
            giftOptions.getItems().add("Book");
            giftOptions.getItems().add("Board Game");
            giftOptions.getItems().add("Chocolate");
            giftOptions.getItems().add("Cotton Pajamas");
            giftOptions.getItems().add("Coloring Books");
            giftOptions.getItems().add("Journal");
            giftOptions.getItems().add("Socks");
            giftOptions.getItems().add("Slippers");
            giftOptions.getItems().add("Sleep Mask");
            giftOptions.getItems().add("Teddy Bear");

            // Floral options
            giftOptions.getItems().add("Romantic Bouquet");
            giftOptions.getItems().add("Red Bouquet");
            giftOptions.getItems().add("Orange Bouquet");
            giftOptions.getItems().add("Yellow Bouquet");
            giftOptions.getItems().add("Blue Bouquet");
            giftOptions.getItems().add("Purple Bouquet");
            giftOptions.getItems().add("Pink Bouquet");
            giftOptions.getItems().add("White Bouquet");
            giftOptions.getItems().add("Floral Wreath");
        }
*/
    @Override
    public void submit() {
    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        dw.addSR(new GiftFloralSR(sr, giftOptions.getValue()));
    }

    @Override
    public void clear() {
        giftOptions.setValue("");
        giftType.setValue("");
    }
}
