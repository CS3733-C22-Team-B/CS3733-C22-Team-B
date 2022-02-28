package edu.wpi.cs3733.c22.teamB.entity;

import java.util.List;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;
import edu.wpi.cs3733.c22.teamB.controllers.MapEditorController;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

public class BedBrotherCV implements Runnable {

    MapEditorController mapController;

    public void setMapController(MapEditorController mapController) {
        this.mapController = mapController;
    }

    static SerialPort chosenPort;
    DatabaseWrapper dbWrapper = DatabaseWrapper.getInstance();
    List<Location> locationList = dbWrapper.getAllLocation();

    public void run() {
        System.out.println("e");
        chosenPort = SerialPort.getCommPort("COM5");
        chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        if (chosenPort.openPort()) {
            Scanner input = new Scanner(chosenPort.getInputStream());
            while (input.hasNext()) {
                String tagID = input.next();
                System.out.println("Data: " + tagID);
                String equipID = tagToEquipID(tagID);
                if (equipID.equals("No equip")) {
                    System.out.println("Located tag does not correspond to a medical equipment");
                } else{
                    changeLocation(equipID);
                }
            }
        }
    }

    private void changeLocation(String equipID) {
        final String cameraLocation = "HHALL01203";
        //Check if the camera is in a valid location
        if(dbWrapper.isInTableLocation(cameraLocation)){
            //Check if medical equipment is in DB
            //if(dbWrapper.isInTableLocation() dbWrapper.getMedicalEquipment(equipID)){
                MedicalEquipment old = dbWrapper.getMedicalEquipment(equipID);
                old.setLocation(dbWrapper.getLocation(cameraLocation));
                dbWrapper.updateMedicalEquipment(old);
            //} else{
            //    System.out.println("Equipment has a corresponding Apriltag but does not exist in DB");
            //}
        } else{
            System.out.println("Camera is not set to a valid location");
        }
    }

    public String tagToEquipID(String tagID) {
        String equipID;
        switch (tagID) {
            case "0":
                equipID = "bBED00101";
                break;
            case "1":
                equipID = "bBED00201";
                break;
            case "2":
                equipID = "bXRAY00101";
                break;
            case "3":
                equipID = "bRECLINER00101";
                break;
            case "4":
                equipID = "bRECLINER00202";
                break;
            default:
                equipID = "No equip";
                break;
        }
        Platform.runLater(() -> {
            mapController.refresh();
        });
        return equipID;
    }
}
