package edu.wpi.cs3733.c22.teamB.entity;

import java.util.List;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;
import edu.wpi.cs3733.c22.teamB.controllers.MapEditorController;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import javafx.application.Platform;

public class BedBrotherCV implements Runnable {

    MapEditorController mapController;
    Location cameraLocation;

    public void setMapController(MapEditorController mapController) {
        this.mapController = mapController;
    }

    static SerialPort chosenPort;
    DatabaseWrapper dbWrapper = DatabaseWrapper.getInstance();
    List<Location> locationList = dbWrapper.getAllLocation();

    public void run() {
        for(SerialPort port : SerialPort.getCommPorts()){
            if(port.getPortDescription().equals("OpenMV Virtual Comm Port in FS Mode")){
                System.out.println("Camera found let's gooooooo!");
                chosenPort = port;
            }
            System.out.println(port.getPortDescription());
        }
        if(chosenPort!=null){
            chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
            if (chosenPort.openPort()) {
                Scanner input = new Scanner(chosenPort.getInputStream());
                while (input.hasNext()) {
                    String tagID = input.next();
                    System.out.println("Data: " + tagID);
                    if(tagID.equals("cam1")){
                        cameraLocation = dbWrapper.getLocation("HHALL01203");
                    } else if(tagID.equals("cam2")){
                        cameraLocation = dbWrapper.getLocation("HHALL01403");
                    } else{
                        moveEquipTagID(tagID);
                    }
                }
                try{
                    System.out.println("CV thread sleep start");
                    Thread.currentThread().sleep(500);
                    System.out.println("CV thread sleep end");
                } catch(InterruptedException e){
                    System.out.println(e);
                }
            } else{
                System.out.println("Port closed");
            }
        } else{
            System.out.println("no camera :( stopping camera thread");
            Thread.currentThread().interrupt();
        }
    }

    public void moveEquipTagID(String tagID) {
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
        if(equipID.equals("No equip")){
            System.out.println("invalid tag");
        } else{
            if(dbWrapper.getMedicalEquipment(equipID).getLocation().equals(cameraLocation)){
            } else{
                Platform.runLater(() -> {
                    mapController.moveEquip(equipID,cameraLocation);
                    mapController.shuffleMed(oldLoc);
                });
            }
        }
    }
}
