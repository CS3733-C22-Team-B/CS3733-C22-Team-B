package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;

public class MedicineDeliverySR extends AbstractSR {
    
    private String medicineID;
    private String patientID;
    private String dosage;

    public MedicineDeliverySR() {
        super(null, null, null, null, null, null, null, null);
        this.medicineID = null;
        this.patientID = null;
        this.dosage = null;
    }

    public MedicineDeliverySR(String srID, String srType, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes, String medicineID, String patientID, String dosage) {
        super(srID, srType, status, location, requestor, assignedEmployee, dateRequested, notes);
        this.medicineID = medicineID;
        this.patientID = patientID;
        this.dosage = dosage;
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

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "MedicineDeliverySR{" +
                "medicineID='" + medicineID + '\'' +
                ", patientID='" + patientID + '\'' +
                ", dosage='" + dosage + '\'' +
                '}';
    }

    public String toStringFields() {
        return getSrID() + ","
                + medicineID + ","
                + patientID + ","
                + dosage;
    }


}
