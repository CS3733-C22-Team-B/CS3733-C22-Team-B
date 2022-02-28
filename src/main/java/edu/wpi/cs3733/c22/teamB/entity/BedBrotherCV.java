package edu.wpi.cs3733.c22.teamB.entity;

import java.util.List;
import java.util.Scanner;
import com.fazecast.jSerialComm.SerialPort;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;

public class BedBrotherCV implements Runnable{

    static SerialPort chosenPort;
    DatabaseWrapper dbWrapper = DatabaseWrapper.getInstance();
    List<Location> locationList = dbWrapper.getAllLocation();

    public void run(){
        System.out.println("e");
        chosenPort = SerialPort.getCommPort("COM5");
        chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER,0,0);
        if(chosenPort.openPort()){
            Scanner input = new Scanner(chosenPort.getInputStream());
            while(input.hasNext()){
                String tagID = input.next();
                System.out.println("Data: " + tagID);

            }
        }
    }

    public void changeLocation(){
        MedicalEquipment old = dbWrapper.getMedicalEquipment();
        old.setLocation((Location) Locations.getValue());
        dbWrapper.updateMedicalEquipment(old);//TODO
    }

}
