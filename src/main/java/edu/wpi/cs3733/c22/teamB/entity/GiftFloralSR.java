package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;
import java.util.Objects;

public class GiftFloralSR extends AbstractSR {

    private String giftType;
    private String giftName;

    public GiftFloralSR(){
        super(null, "GiftFloralSR", null, null, null, null, null, null);
        this.giftType = null;
        this.giftName = null;
    }

    public GiftFloralSR(String srID, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes,  String giftType, String giftName) {
        super(srID, "GiftFloralSR", status, location, requestor, assignedEmployee, dateRequested, notes);
        this.giftType = giftType;
        this.giftName = giftName;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    @Override
    public String toString() {
        return "GiftFloralSR{ " +
                "srID= " + getSrID() + '\'' +
                "srType= " + getSrType() + '\'' +
                "status= " + getStatus() + '\'' +
                "location= " + getLocation() + '\'' +
                "requestor= " + getRequestor() + '\'' +
                "assignedEmployee= " + getAssignedEmployee() + '\'' +
                "dateRequested= " + getDateRequested() + '\'' +
                "notes= " + getNotes() + '\'' +
                "giftType='" + giftType + '\'' +
                "giftName='" + giftName + '\'' +
                '}';
    }

    public String toStringFields() {
        return getSrID()
                + ","
                + giftType
                + ","
                + giftName;
    }

    public static String toStringHeader() {
        return "srID"
                + ","
                + "giftType"
                + ","
                + "giftName";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftFloralSR that = (GiftFloralSR) o;
        return Objects.equals(giftType, that.giftType) && Objects.equals(giftName, that.giftName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giftType, giftName);
    }
}
