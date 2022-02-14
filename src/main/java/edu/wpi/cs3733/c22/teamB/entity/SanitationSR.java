package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;

public class SanitationSR extends AbstractSR {

    private String condition; // DRY, WET, GLASS

    public SanitationSR() {
        super(null, "SanitationSR", null, null, null, null, null, null);
        this.condition = null;
    }

    public SanitationSR(String srID, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes, String condition) {
        super(srID, "SanitationSR", status, location, requestor, assignedEmployee, dateRequested, notes);
        this.condition = condition;
    }

    public SanitationSR(AbstractSR csr, String condition) {
        super(csr);
        this.setSrType("SanitationSR");
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "SanitationSR{" +
                "srID= " + getSrID() + '\'' +
                "srType= " + getSrType() + '\'' +
                "status= " + getStatus() + '\'' +
                "location= " + getLocation() + '\'' +
                "requestor= " + getRequestor() + '\'' +
                "assignedEmployee= " + getAssignedEmployee() + '\'' +
                "dateRequested= " + getDateRequested() + '\'' +
                "notes= " + getNotes() + '\'' +
                "condition='" + condition + '\'' +
                '}';
    }

    public String toStringFields() {
        return getSrID() + "," + condition;
    }

    public static String toStringHeader() {
        return "srID" + "," + "condition";
    }
}
