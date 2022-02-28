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
                if (!equipID.equals("No equip")) {
                    changeLocation(equipID);
                }
            }
        }
    }

    private void changeLocation(String equipID) {
        MedicalEquipment old = dbWrapper.getMedicalEquipment(equipID);
        old.setLocation(dbWrapper.getLocation("HHALL01203"));
        dbWrapper.updateMedicalEquipment(old);//TODO
    }

    public String tagToEquipID(String tagID) {
        String equipID;
        switch (tagID) {
            case "1":
                equipID = "bBED00201";
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
