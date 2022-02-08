package edu.wpi.cs3733.c22.teamB.entity;

public class GiftFloralSR extends AbstractSR {

    String gift;
    String deliveryDate;
    String deliveryRoom;

    //    public GiftFloralSR(){}

    public GiftFloralSR(
            String srID, String statusStr, String gift, String deliveryDate, String deliveryRoom) {

        super(srID, statusStr);
        this.gift = gift;
        this.deliveryDate = deliveryDate;
        this.deliveryRoom = deliveryRoom;
    }

    public GiftFloralSR(String srID, String statusStr) {
        super(srID, statusStr);
    }
}
