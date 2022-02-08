package edu.wpi.cs3733.c22.teamB.entity;

public class ExternalTransportSR extends AbstractSR {

    private String pickupLoc; //  not location type (outside of building)
    private String destination; // not location type
    private String info;
    private String date; // date type
    private String formOfTransport;
    private Employee employee;
    //    private Employee assignedEmployee;
    String assignedEmployee;

    public ExternalTransportSR(){
        super(null, null);
        this.pickupLoc = null;
        this.destination = null;
        this.info = null;
        this.date = null;
        this.formOfTransport = null;
        this.employee = null;
    }

    public ExternalTransportSR(
            String srID,
            String pickupLoc,
            String destination,
            String status,
            String info,
            String date,
            String formOfTransport,
            Employee employee) {
        super(srID, status);
        this.destination = destination;
        this.pickupLoc = pickupLoc;
        this.info = info;
        this.date = date;
        this.formOfTransport = formOfTransport;
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
    public String getPickupLoc() {
        return pickupLoc;
    }

    public String getDestination() {
        return destination;
    }

    public String getInfo() {
        return info;
    }

    public String getDate() {
        return date;
    }

    public String getFormOfTransport() {
        return formOfTransport;
    }


    public String getSrID() {
        return this.srID;
    }
}
