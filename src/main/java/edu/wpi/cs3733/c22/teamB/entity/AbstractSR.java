package edu.wpi.cs3733.c22.teamB.entity;

import javafx.collections.FXCollections;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractSR {

    public static String[] SRstatus = {"BLANK", "WAITING", "CANCELED", "DONE"};

    private String srID;
    private String srType;
    private String status;
    private Location location;
    private Employee requestor;
    private Employee assignedEmployee;
    private LocalDate dateRequested;
    private String notes;

    public AbstractSR(AbstractSR csr) {
        this.srID = csr.getSrID();
        this.srType = csr.getSrType();
        this.status = csr.getStatus();
        this.location = csr.getLocation();
        this.requestor = csr.getRequestor();
        this.assignedEmployee = csr.getAssignedEmployee();
        this.dateRequested = csr.getDateRequested();
        this.notes = csr.getNotes();
    }

    public AbstractSR(String srID, String srType, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes) {
        this.srID = srID;
        this.srType = srType;

//        if (status == null) status = "BLANK";
        if (!validateStatus(status)) {
            System.err.println("WARNING: status is set to BLANK");
            this.status = "BLANK";
        }
        else this.status = status;

        this.location = location;
        this.requestor = requestor;
        this.assignedEmployee = assignedEmployee;
        this.dateRequested = dateRequested;
        this.notes = notes;
    }

    private boolean validateStatus(String st) {
        for (String s : SRstatus)
            if (st != null && st.equals(s)) return true;
        return false;
    }

    @Override
    public String toString() {
        return "AbstractSR{" +
                "srID='" + srID + '\'' +
                ", srType='" + srType + '\'' +
                ", status='" + status + '\'' +
                ", location=" + location +
                ", requestor=" + requestor +
                ", assignedEmployee=" + assignedEmployee +
                ", dateRequested=" + dateRequested +
                ", notes='" + notes + '\'' +
                '}';
    }

    public String getSrID() {
        return srID;
    }

    public void setSrID(String srID) {
        this.srID = srID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String statusStr) {
        this.status = (statusStr);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Employee getRequestor() {
        return requestor;
    }

    public void setRequestor(Employee requestor) {
        this.requestor = requestor;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public LocalDate getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(LocalDate dateRequested) {
        this.dateRequested = dateRequested;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSrType() {
        return srType;
    }

    public void setSrType(String srType) {
        this.srType = srType;
    }


}
