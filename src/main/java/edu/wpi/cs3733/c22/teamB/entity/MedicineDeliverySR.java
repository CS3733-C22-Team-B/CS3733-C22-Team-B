package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;

public class MedicineDeliverySR extends AbstractSR {
    
    private String medicineID;
    private String patientID;

    public MedicineDeliverySR() {
        super(null, null, null, null, null, null, null, null);
        this.medicineID = null;
        this.patientID = null;
    }

    public MedicineDeliverySR(String srID, String srType, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes, String medicineID, String patientID) {
        super(srID, srType, status, location, requestor, assignedEmployee, dateRequested, notes);
        this.medicineID = medicineID;
        this.patientID = patientID;
    }

    public String getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "MedicineDeliverySR{" +
                "medicineID='" + medicineID + '\'' +
                ", patientID='" + patientID + '\'' +
                '}';
    }

    public String toStringFields() {
        return getSrID()
                + ","
                + medicineID
                + ","
                + patientID;
    }

    public static String toStringHeader() {
        return "srID"
                + ","
                + "medicineID"
                + ","
                + "patientID";
    }


}
