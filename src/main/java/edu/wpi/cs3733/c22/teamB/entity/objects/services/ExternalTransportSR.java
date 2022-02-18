package edu.wpi.cs3733.c22.teamB.entity.objects.services;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;

import java.time.LocalDate;
import java.util.Objects;

public class ExternalTransportSR extends AbstractSR {


    private String patientID;
    private String dropOffLocation;
    private String formOfTransport;

    public ExternalTransportSR() {
        super(null, "ExternalTransportSR", null, null, null, null, null, null);
        this.patientID = null;
        this.dropOffLocation = null;
        this.formOfTransport = null;
    }

    public ExternalTransportSR(String srID, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes, String patientID, String dropOffLocation, String formOfTransport) {
        super(srID, "ExternalTransportSR", status, location, requestor, assignedEmployee, dateRequested, notes);
        this.patientID = patientID;
        this.dropOffLocation = dropOffLocation;
        this.formOfTransport = formOfTransport;
    }

    public ExternalTransportSR(AbstractSR csr, String patientID, String dropOffLocation, String formOfTransport){
        super(csr);
        this.setSrType("ExternalTransportSR");
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
                "srID= " + getSrID() + '\'' +
                "srType= " + getSrType() + '\'' +
                "status= " + getStatus() + '\'' +
                "location= " + getLocation() + '\'' +
                "requestor= " + getRequestor() + '\'' +
                "assignedEmployee= " + getAssignedEmployee() + '\'' +
                "dateRequested= " + getDateRequested() + '\'' +
                "notes= " + getNotes() + '\'' +
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
        ExternalTransportSR that = (ExternalTransportSR) o;
        return Objects.equals(patientID, that.patientID) && Objects.equals(dropOffLocation, that.dropOffLocation) && Objects.equals(formOfTransport, that.formOfTransport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientID, dropOffLocation, formOfTransport);
    }
}
