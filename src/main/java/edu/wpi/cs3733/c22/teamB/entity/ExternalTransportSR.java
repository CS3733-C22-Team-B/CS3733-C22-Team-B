package edu.wpi.cs3733.c22.teamB.entity;

public class ExternalTransportSR extends AbstractSR {

    private String pickupLoc; //  not location type (outside of building)
    private String destination; // not location type
    private String info;
    private String date; // date type
    private String formOfTransport;
    //    private Employee assignedEmployee;
    String assignedEmployee;

    public ExternalTransportSR(
            String srID,
            String pickupLoc,
            String destination,
            String status,
            String info,
            String date,
            String formOfTransport) {
        super(srID, status);
        this.destination = destination;
        this.pickupLoc = pickupLoc;
        this.info = info;
        this.date = date;
        this.formOfTransport = formOfTransport;
        this.assignedEmployee = assignedEmployee;
    }

    public String getSrID() {
        return this.srID;
    }
}
