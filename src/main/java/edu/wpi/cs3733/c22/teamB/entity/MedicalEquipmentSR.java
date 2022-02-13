package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;
import java.util.Objects;

public class MedicalEquipmentSR extends AbstractSR {
//    public static String name = "MedicalEquipmentSR";
    private MedicalEquipment medicalEquipment;

    public MedicalEquipmentSR() {
        super(null, "MedicalEquipmentSR", null, null, null, null, null, null);
        this.medicalEquipment = null;
    }

    public MedicalEquipmentSR(String srID, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes, MedicalEquipment medicalEquipment) {
        super(srID, "MedicalEquipmentSR", status, location, requestor, assignedEmployee, dateRequested, notes);
        this.medicalEquipment = medicalEquipment;
    }

    public MedicalEquipmentSR(MainSR csr, MedicalEquipment medicalEquipment) {
        super(csr);
        this.setSrType("MedicalEquipmentSR"); // important
        this.medicalEquipment = medicalEquipment;
    }

    public MedicalEquipment getMedicalEquipment() {
        return medicalEquipment;
    }

    public void setMedicalEquipment(MedicalEquipment medicalEquipment) {
        this.medicalEquipment = medicalEquipment;
    }

    @Override
    public String toString() {
        return "MedicalEquipmentSR{" +
                "medicalEquipment=" + medicalEquipment +
                '}';
    }

    public String toStringFields() {
        return getSrID()
                + ","
                + medicalEquipment.getEquipmentID();
    }

    public static String toStringHeader() {
        return "srID"
                + ","
                + "medicalEquipmentID";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalEquipmentSR that = (MedicalEquipmentSR) o;
        return Objects.equals(medicalEquipment, that.medicalEquipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicalEquipment);
    }
}
