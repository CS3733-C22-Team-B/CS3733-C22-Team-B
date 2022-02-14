package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;

public class SecuritySR extends AbstractSR{


    public SecuritySR() {
        super(null, "Security", null, null, null, null, null, null);
    }

    public SecuritySR(String srID, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes) {
        super(srID, "Security", status, location, requestor, assignedEmployee, dateRequested, notes);
    }
    public SecuritySR(AbstractSR csr) {
        super(csr);
        this.setSrType("Security");
    }


    @Override
    public String toString() {
        return "SecuritySR{" +
                "srID= " + getSrID() + '\'' +
                "srType= " + getSrType() + '\'' +
                "status= " + getStatus() + '\'' +
                "location= " + getLocation() + '\'' +
                "requestor= " + getRequestor() + '\'' +
                "assignedEmployee= " + getAssignedEmployee() + '\'' +
                "dateRequested= " + getDateRequested() + '\'' +
                "notes= " + getNotes() + '\'' +
                '}';
    }

    public String toStringFields() {
        return getSrID();
    }

    public static String toStringHeader() {
        return "srID";
    }
}
