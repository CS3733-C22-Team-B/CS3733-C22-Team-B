package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;
import java.util.List;

public class GiftFloralSR extends AbstractSR {

    List<GiftType> listOfGifts;
    String deliveryDate;
    String deliveryFloor;
    String deliveryRoom;

//    public GiftFloralSR(){}

    public GiftFloralSR(
            String srID,
            String statusStr,
            List<GiftType> listOfGifts,
            String deliveryDate,
            String deliveryFloor,
            String deliveryRoom) {

        super(srID, statusStr);
        this.listOfGifts = listOfGifts;
        this.deliveryDate = deliveryDate;
        this.deliveryFloor = deliveryFloor;
        this.deliveryRoom = deliveryRoom;
    }

    public GiftFloralSR(String srID, String statusStr) {
        super(srID, statusStr);
    }

    public List<GiftType> getListOfGifts() {
        return listOfGifts;
    }



}
