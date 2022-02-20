package edu.wpi.cs3733.c22.teamB.controllers.services;

import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.c22.teamB.controllers.IController;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.GiftFloralSR;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GiftFloralServiceController implements IController {
    String type[] = {"All", "Floral", "Gift"};
    String opts[] = {"Teddy Bear", "Pajamas", "Slippers", "Rock", "Coloring Books"};
    String optsFl[] = {"Red Bouquet", "Orange Bouquet", "Yellow Bouquet"};

    @FXML private JFXComboBox<String> giftOptions;
    @FXML private JFXComboBox<String> giftType;

    private GiftFloralSR sr = null;

    public GiftFloralServiceController(){}
    public GiftFloralServiceController(GiftFloralSR sr){
        this.sr = sr;
    }

    @FXML
    public void initialize(){
        giftType.setItems(FXCollections.observableArrayList(type));

        giftOptions.getItems().addAll(FXCollections.observableArrayList(optsFl));
        giftOptions.getItems().addAll(FXCollections.observableArrayList(opts));
        if (sr == null) {
            clear();
        } else {
            giftType.setValue("All");
            giftOptions.setValue(sr.getGiftName());
        }
    }

    @Override
    public void submit() {
    }

    @Override
    public void submit(AbstractSR sr) {
        DatabaseWrapper dw = new DatabaseWrapper();
        if (this.sr == null)
            dw.addSR(new GiftFloralSR(sr, giftOptions.getValue()));
        else
            dw.updateSR(new GiftFloralSR(sr, giftOptions.getValue()));
    }

    @Override
    public void clear() {
        giftOptions.setValue(null);
        giftType.setValue("All");
    }


    @FXML private void onGiftTypeChange(ActionEvent actionEvent) {
        giftOptions.getItems().clear();
        giftOptions.getItems().removeAll();
        if (giftType.getValue().equals("All")){
            giftOptions.getItems().addAll(FXCollections.observableArrayList(optsFl));
            giftOptions.getItems().addAll(FXCollections.observableArrayList(opts));
        }
        else if (giftType.getValue().equals("Floral")){
            giftOptions.setItems(FXCollections.observableArrayList(optsFl));
        }
        else if (giftType.getValue().equals("Gift")){
            giftOptions.setItems(FXCollections.observableArrayList(opts));
        }
    }
}
