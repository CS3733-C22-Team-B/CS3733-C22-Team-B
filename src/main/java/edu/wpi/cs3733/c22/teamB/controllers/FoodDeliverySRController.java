package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class FoodDeliverySRController implements IController {
    @FXML private JFXComboBox<String> comboMeals;
    @FXML private JFXComboBox<String> drinkMenu;

    private FoodDeliverySR sr = null;

    public FoodDeliverySRController() {}

    public FoodDeliverySRController(FoodDeliverySR sr) {
        this.sr = sr;
    }

    @FXML
    public void initialize() {
        DatabaseWrapper dw = new DatabaseWrapper();

        comboMeals.getItems().add("Chicken and Potatoes");
        comboMeals.getItems().add("Salad and Fruits");
        comboMeals.getItems().add("Soup of The Day");
        drinkMenu.getItems().add("Water");
        drinkMenu.getItems().add("Orange Juice");
        drinkMenu.getItems().add("Apple Juice");
        drinkMenu.getItems().add("1% milk");
    }



    @Override
    public void submit() {

    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        dw.addSR(new FoodDeliverySR(sr, comboMeals.getValue(), drinkMenu.getValue()));
    }

    @Override
    public void clear() {
        comboMeals.cancelEdit();
        drinkMenu.cancelEdit();
    }
}
