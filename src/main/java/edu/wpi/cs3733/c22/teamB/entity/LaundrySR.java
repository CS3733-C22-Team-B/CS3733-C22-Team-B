package edu.wpi.cs3733.c22.teamB.entity;

public class LaundrySR extends AbstractSR {
    String location;
    String assignedEmployee;

    public LaundrySR(String srID, String location, String assignedEmployee, String status) {
        super(srID, status);
        this.location = location;
        this.assignedEmployee = assignedEmployee;
    }

    public LaundrySR(String srID, String location, String assignedEmployee) {
        super(srID, "WAITING");
        this.location = location;
        this.assignedEmployee = assignedEmployee;
    }

    public String getSrID() {
        return this.srID;
    }

    public String getLocation() {
        return location;
    }

    public String toStringFields() {
        return srID + "," + status + "," + location + "," + assignedEmployee;
    }
}
