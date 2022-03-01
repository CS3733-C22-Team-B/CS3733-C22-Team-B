package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.derby.impl.tools.optional.DBMDWrapper;

public class SideviewController{
    @FXML
    private Label f1CEquip;

    @FXML
    private Label f1DEquip;

    @FXML
    private Label f1Loc;

    @FXML
    private Label f1SR;

    @FXML
    private Label f2CEquip;

    @FXML
    private Label f2DEquip;

    @FXML
    private Label f2Loc;

    @FXML
    private Label f2SR;

    @FXML
    private Label f3CEquip;

    @FXML
    private Label f3DEquip;

    @FXML
    private Label f3Loc;

    @FXML
    private Label f3SR;

    @FXML
    private Label fL1CEquip;

    @FXML
    private Label fL1DEquip;

    @FXML
    private Label fL1Loc;

    @FXML
    private Label fL1SR;

    @FXML
    private Label fL2CEquip;

    @FXML
    private Label fL2DEquip;

    @FXML
    private Label fL2Loc;

    @FXML
    private Label fL2SR;

    DatabaseWrapper dbWrapper = DatabaseWrapper.getInstance();

    @FXML
    public void initialize() {
        setLocationCounts();
        setEquipCounts();
        setSRCounts();
    }

    private void setLocationCounts(){
        f3Loc.setText(Integer.toString(countLocations("03")));
        f2Loc.setText(Integer.toString(countLocations("02")));
        f1Loc.setText(Integer.toString(countLocations("01")));
        fL1Loc.setText(Integer.toString(countLocations("L1")));
        fL2Loc.setText(Integer.toString(countLocations("L2")));
    }

    private void setEquipCounts(){
        f3CEquip.setText(Integer.toString(countEquip("03",false)));
        f2CEquip.setText(Integer.toString(countEquip("02",false)));
        f1CEquip.setText(Integer.toString(countEquip("01",false)));
        fL1CEquip.setText(Integer.toString(countEquip("L1",false)));
        fL2CEquip.setText(Integer.toString(countEquip("L2",false)));

        f3DEquip.setText(Integer.toString(countEquip("03",true)));
        f2DEquip.setText(Integer.toString(countEquip("02",true)));
        f1DEquip.setText(Integer.toString(countEquip("01",true)));
        fL1DEquip.setText(Integer.toString(countEquip("L1",true)));
        fL2DEquip.setText(Integer.toString(countEquip("L2",true)));
    }

    private void setSRCounts(){
        f3SR.setText(Integer.toString(countSR("03")));
        f2SR.setText(Integer.toString(countSR("02")));
        f1SR.setText(Integer.toString(countSR("01")));
        fL1SR.setText(Integer.toString(countSR("L1")));
        fL2SR.setText(Integer.toString(countSR("L2")));
    }

    private int countLocations(String floor){
        int retval = 0;
        for(Location loc : dbWrapper.getAllLocation()){
            if(loc.getFloor().equals(floor)){
                retval++;
            }
        }
        return retval;
    }

    private int countSR(String floor){
        int retval = 0;
        for(AbstractSR sr : dbWrapper.getAllSR()){
            if(sr.getLocation().getFloor().equals(floor)){
                retval++;
            }
        }
        return retval;
    }

    private int countEquip(String floor, boolean isDirty){
        int retval = 0;
        for(MedicalEquipment equip : dbWrapper.getAllMedicalEquipment()){
            if(isDirty){
                if(equip.getLocation().getFloor().equals(floor) && equip.getStatus().equals("DIRTY")){
                    retval++;
                }
            } else{
                if(equip.getLocation().getFloor().equals(floor) && (equip.getStatus().equals("CLEAN") || equip.getStatus().equals("WAITING"))){
                    retval++;
                }
            }
        }
        return retval;
    }

}
