package edu.wpi.cs3733.c22.teamB.entity;

public class GiftFloralSR extends AbstractSR {

    // maybe?? add employee? but its probably too late

    // things that need to be added in all of these;
    // srID, status, location, requestor, assignedEmployee, dateRequested,
    // notes, srType

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
        this.deliveryRoom = null;
    }

    // Getters and Setters

    public String getSRID() { return this.srID; }

    public String getGift() { return gift; }
    public void setGift(String gift) { this.gift = gift; }

    public String getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(String deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getDeliveryRoom() { return deliveryRoom; }
    public void setDeliveryRoom(String deliveryRoom) { this.deliveryRoom = deliveryRoom; }

    @Override
    public String toString(){
        return "GiftFloralSR{" +
                "srID='" + srID + '\'' +
                ", status=" + status + '\'' +
                ", gift=" + gift + '\'' +
                ", deliveryDate=" + deliveryDate + '\'' +
                ", deliveryRoom=" + deliveryRoom + '\'' +
                '}';
    }

}
