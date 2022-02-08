package edu.wpi.cs3733.c22.teamB.entity;

public class MedicineDeliverySR extends AbstractSR {
    private Location destination;
    private Medicine medicine;
    private Employee assignedEmployee;
    private String patientFirstName;
    private String patientLastName;
    private String patientID;
    private String DOB;
    private String email;
    private String room;
//    TODO add allergies
//    private String allergies;
    private String dosage;
    private String medicineName;
    private String dispenseAmount;
    private  String frequency;
    private String form;
    private String mgPerDose;


    public MedicineDeliverySR(
                String srID,
                String statusStr,
                Location destination,
                Medicine medicine,
                Employee assignedEmployee,
                String patientFirstName,
                String patientLastName,
                String patientID,
                String DOB,
                String email,
                String room,
                String dosage,
                String medicineName,
                String dispenseAmount,
                String frequency,
                String form,
                String mgPerDose
                ) {
        super(srID, statusStr);
        this.destination = destination;
        this.medicine = medicine;
        this.assignedEmployee = assignedEmployee;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientID = patientID;
        this.DOB = DOB;
        this.email = email;
        this.room = room;
        this.dosage = dosage;
        this.medicineName = medicineName;
        this.dispenseAmount = dispenseAmount;
        this.frequency = frequency;
        this.form = form;
        this.mgPerDose = mgPerDose;

    }

    public MedicineDeliverySR() {
        super(null, null);
        this.destination = null;
        this.assignedEmployee = null;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmail() {
        return email;
    }

    public String getRoom() {
        return room;
    }

    public String getDosage() {
        return dosage;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getDispenseAmount() {
        return dispenseAmount;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getForm() {
        return form;
    }

    public String getMgPerDose() {
        return mgPerDose;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicalEquipment(Medicine medicine) {
        this.medicine = medicine;
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
        return "MedicalEquipmentSR{"
                + "srID="
                + srID
                + "destination="
                + destination.getNodeID()
                + ", medicalEquipment="
                + medicine.getMedicationID()
                + ", assignedEmployee="
                + assignedEmployee.getEmployeeID()
                + '}';
    }

    public String toStringFields() {
        return srID
                + ","
                + destination.getNodeID()
                + ","
                + medicine.getMedicationID()
                + ","
                + assignedEmployee.getEmployeeID();
    }

}
