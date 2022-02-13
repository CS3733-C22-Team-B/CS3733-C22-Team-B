package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;
import java.util.Objects;

public class ExternalTranportSR extends AbstractSR {

    private String patientID;
    private String dropOffLocation;
    private String formOfTransport;

    public ExternalTranportSR() {
        super(null, null, null, null, null, null, null, null);
        this.patientID = null;
        this.dropOffLocation = null;
        this.formOfTransport = null;
    }

    public ExternalTranportSR(String srID, String srType, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes, String patientID, String dropOffLocation, String formOfTransport) {
        super(srID, srType, status, location, requestor, assignedEmployee, dateRequested, notes);
        this.patientID = patientID;
        this.dropOffLocation = dropOffLocation;
        this.formOfTransport = formOfTransport;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public String getFormOfTransport() {
        return formOfTransport;
    }

    public void setFormOfTransport(String formOfTransport) {
        this.formOfTransport = formOfTransport;
    }

    @Override
    public String toString() {
        return "ExternalTranportSR{" +
                "patientID='" + patientID + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", formOfTransport='" + formOfTransport + '\'' +
                '}';
    }

    public String toStringFields() {
        return getSrID()
                + ","
                + patientID
                + ","
                + dropOffLocation
                + ","
                + formOfTransport;
    }

    public static String toStringHeader() {
        return "srID"
                + ","
                + "patientID"
                + ","
                + "dropOffLocation"
                + ","
                + "formOfTransport";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalTranportSR that = (ExternalTranportSR) o;
        return Objects.equals(patientID, that.patientID) && Objects.equals(dropOffLocation, that.dropOffLocation) && Objects.equals(formOfTransport, that.formOfTransport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientID, dropOffLocation, formOfTransport);
    }
}
