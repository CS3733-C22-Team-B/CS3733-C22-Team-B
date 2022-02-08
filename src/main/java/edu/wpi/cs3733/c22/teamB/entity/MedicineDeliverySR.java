package edu.wpi.cs3733.c22.teamB.entity;

public class MedicineDeliverySR extends AbstractSR {
    private Location destination;
    private String medicineID;
    private Employee assignedEmployee;
    private String patientFirstName;
    private String patientLastName;
    private String patientID;
    private String DOB;
    private String email;
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
                String medicineID,
                Employee assignedEmployee,
                String patientFirstName,
                String patientLastName,
                String patientID,
                String DOB,
                String email,
                String dosage,
                String medicineName,
                String dispenseAmount,
                String frequency,
                String form,
                String mgPerDose
                ) {
        super(srID, statusStr);
        this.destination = destination;
        this.medicineID = medicineID;
        this.assignedEmployee = assignedEmployee;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientID = patientID;
        this.DOB = DOB;
        this.email = email;
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

    public String getMedicineID() {
        return medicineID;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setDispenseAmount(String dispenseAmount) {
        this.dispenseAmount = dispenseAmount;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setMgPerDose(String mgPerDose) {
        this.mgPerDose = mgPerDose;
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
                + medicineID
                + ", assignedEmployee="
                + assignedEmployee.getEmployeeID()
                + '}';
    }

    public String toStringFields() {
        return srID
                + ","
                + status
                + ","
                + destination.getNodeID()
                + ","
                + medicineID
                + ","
                + assignedEmployee.getEmployeeID()
                + ","
                + patientFirstName
                + ","
                + patientLastName
                + ","
                + patientID
                + ","
                + DOB
                + ","
                + email
                + ","
                + dosage
                + ","
                + medicineName
                + ","
                + dispenseAmount
                + ","
                +frequency
                + ","
                + form
                + ","
                + mgPerDose;
    }


    public static String toStringHeader() {

        return "srID"
                + ",status"
                + ",locationID"
                + ",medicineID"
                + ",employeeID"
                + ",patientFirstName"
                + ",patientLastName"
                + ",patientID"
                + ",DOB"
                + ",email"
                + ",dosage"
                + ",medicineName"
                + ",dispenseAmount"
                + ",frequency"
                + ",form"
                + ",mGPerDose";
    }

}
