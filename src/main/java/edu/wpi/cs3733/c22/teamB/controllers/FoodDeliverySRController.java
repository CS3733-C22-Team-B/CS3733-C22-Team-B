package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.entity.Employee;
import edu.wpi.cs3733.c22.teamB.entity.FoodDeliverySR;
import edu.wpi.cs3733.c22.teamB.entity.Location;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class FoodDeliverySRController implements IController, Initializable {

    @FXML private TextField LocationField;
    @FXML private TextField AssigneeName;
    @FXML private JFXComboBox<String> ComboMeals;
    List<FoodDeliverySR> srList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboMeals.getItems().add("Chicken and Potatoes");
        ComboMeals.getItems().add("Salad and Fruits");
        ComboMeals.getItems().add("Soup of The Day");
    }

    @Override
    public void submit() {
        System.out.println("Submit: ");
        String foodName = ComboMeals.getValue();
        String foodRecipientName = AssigneeName.getText();
        Location destination = new Location();
        destination.setLongName(LocationField.getText());
        srList.add(
                new FoodDeliverySR(
                        "1", "WAITING", destination, foodName, foodRecipientName, new Employee()));
        System.out.println(srList.get(srList.size() - 1).toString());
    }

    @Override
    public void clear() {
        LocationField.clear();
        AssigneeName.clear();
        ComboMeals.cancelEdit();
    }
}
