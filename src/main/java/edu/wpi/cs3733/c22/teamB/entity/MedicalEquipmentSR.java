package edu.wpi.cs3733.c22.teamB.entity;

public class MedicalEquipmentSR extends AbstractSR {
    private MedicalEquipment medicalEquipment;
    private Location destination;
    private Employee assignedEmployee;

    public MedicalEquipmentSR(
            String srID,
            String status,
            Location destination,
            MedicalEquipment medicalEquipment,
            Employee assignedEmployee) {
        super(srID, status);
        this.destination = destination;
        this.medicalEquipment = medicalEquipment;
        this.assignedEmployee = assignedEmployee;
    }

    public MedicalEquipmentSR() {
        super(null, null);
        this.destination = null;
        this.medicalEquipment = null;
        this.assignedEmployee = null;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public MedicalEquipment getMedicalEquipment() {
        return medicalEquipment;
    }

    public void setMedicalEquipment(MedicalEquipment medicalEquipment) {
        this.medicalEquipment = medicalEquipment;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    // to string


    @Override
    public String toString() {
        return "MedicalEquipmentSR{" +
                "srID='" + srID + '\'' +
                ", status=" + status +
                ", medicalEquipment=" + medicalEquipment +
                ", destination=" + destination +
                ", assignedEmployee=" + assignedEmployee +
                '}';
    }

    public String toStringFields() {
        return srID
                + ","
                + destination.getNodeID()
                + ","
                + medicalEquipment.getEquipmentID()
                + ","
                + assignedEmployee.getEmployeeID();
    }
}
