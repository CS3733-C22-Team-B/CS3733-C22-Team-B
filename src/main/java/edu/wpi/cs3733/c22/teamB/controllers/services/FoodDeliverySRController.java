package edu.wpi.cs3733.c22.teamB.controllers.services;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.controllers.IController;
import edu.wpi.cs3733.c22.teamB.entity.*;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;
import javafx.fxml.FXML;

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
        comboMeals.getItems().add("Chicken and Potatoes");
        comboMeals.getItems().add("Salad and Fruits");
        comboMeals.getItems().add("Soup of The Day");
        drinkMenu.getItems().add("Water");
        drinkMenu.getItems().add("Orange Juice");
        drinkMenu.getItems().add("Apple Juice");
        drinkMenu.getItems().add("1% milk");

        if (sr == null)
            clear();
        else {
            comboMeals.setValue(sr.getFoodName());
            drinkMenu.setValue(sr.getDrinkName());
        }
    }



    @Override
    public void submit() {

    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = DatabaseWrapper.getInstance();
        if (this.sr == null)
            dw.addSR(new FoodDeliverySR(sr, comboMeals.getValue(), drinkMenu.getValue()));
        else
            dw.updateSR(new FoodDeliverySR(sr, comboMeals.getValue(), drinkMenu.getValue()));
    }

    @Override
    public void clear() {
        comboMeals.setValue(null);
        drinkMenu.setValue(null);
    }
}
