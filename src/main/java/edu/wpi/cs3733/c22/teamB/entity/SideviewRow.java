package edu.wpi.cs3733.c22.teamB.entity;

public class SideviewRow {

    public String Floor;
    public String Locations;
    public String DirtyEquipment;
    public String CleanEquipment;
    public String ServiceRequests;

    public SideviewRow(String floor, String location, String dirtyEquipment, String cleanEquipment, String serviceRequest){
        this.Floor = floor;
        this.Locations = location;
        this.DirtyEquipment = dirtyEquipment;
        this.CleanEquipment = cleanEquipment;
        this.ServiceRequests = serviceRequest;
    }

    public String getLocations() {
        return Locations;
    }

    public String getDirtyEquipment() {
        return DirtyEquipment;
    }

    public String getCleanEquipment() {
        return CleanEquipment;
    }

    public String getServiceRequests() {
        return ServiceRequests;
    }

    public String getFloor() {
        return Floor;
    }
}
