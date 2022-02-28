package edu.wpi.cs3733.c22.teamB.entity;

import java.util.Scanner;
import com.fazecast.jSerialComm.SerialPort;

public class BedBrotherCV extends Thread{

    static SerialPort chosenPort;
    public void call(){
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

}
