package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;
import edu.wpi.cs3733.c22.teamB.controllers.MapEditorController;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import javafx.application.Platform;

public class BedBrotherCV implements Runnable {

    MapEditorController mapController;
    Location[] cameraLocation;
    List<SerialPort> chosenPortList = new ArrayList<SerialPort>();
    DatabaseWrapper dbWrapper = DatabaseWrapper.getInstance();

    public void setMapController(MapEditorController mapController) {
        this.mapController = mapController;
    }

    public void run() {
        for (SerialPort port : SerialPort.getCommPorts()) {
            if (port.getPortDescription().equals("OpenMV Virtual Comm Port in FS Mode")) {
                System.out.println("Camera found let's gooooooo!");
                chosenPortList.add(port);
            }
            System.out.println(port.getPortDescription());
        }
        if (chosenPortList.size() > 0) {
            cameraLocation = new Location[chosenPortList.size()];
            while(true) {
                for (int camIndex = 0; camIndex < chosenPortList.size(); camIndex++) {
                    chosenPortList.get(camIndex).setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
                    if (chosenPortList.get(camIndex).openPort()) {
                        System.out.println("Reading from camera index " + camIndex);
                    while (chosenPortList.get(camIndex).bytesAvailable()>0) {
                            Scanner input = new Scanner(chosenPortList.get(camIndex).getInputStream());
                            String camID = input.next();
                            String tagID = input.next();
                            System.out.println("Cam: " + camID + " Tag: " + tagID);
                            cameraLocation[camIndex] = dbWrapper.getLocation(camID);
                            moveEquipTagID(camIndex, tagID);
                        }
                    } else {
                        System.out.println("Port closed");
                        try {
                            System.out.println("CV thread 1 second sleep start");
                            Thread.currentThread().sleep(1000);
                            System.out.println("CV thread sleep end");
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                }
            }
        } else{
            System.out.println("no cameras :( stopping camera thread");
            Thread.currentThread().interrupt();
        }
    }

    public void moveEquipTagID(int camIndex,String tagID) {
        String equipID;
        System.out.println("Tag ID Found: " + tagID);
        switch (tagID) {
            case "69":
                equipID = "bBED00101";
                break;
            case "169":
                equipID = "bBED00201";
                break;
            case "269":
                equipID = "bXRAY00101";
                break;
            case "369":
                equipID = "bRECLINER00101";
                break;
            case "420":
                equipID = "bRECLINER00202";
                break;
            default:
                equipID = "No equip";
                System.out.println("no equip tag");
                break;
        }
        Location oldLoc = dbWrapper.getMedicalEquipment(equipID).getLocation();
        if (equipID.equals("No equip")) {
            System.out.println("invalid tag");
        } else {
            if (dbWrapper.getMedicalEquipment(equipID).getLocation().equals(cameraLocation[camIndex])) {
            } else {
                Platform.runLater(() -> {
                    mapController.moveEquip(equipID, cameraLocation[camIndex]);
                    mapController.shuffleMed(oldLoc);
                });
            }
        }
    }
}
