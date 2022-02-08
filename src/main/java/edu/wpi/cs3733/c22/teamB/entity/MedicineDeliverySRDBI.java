package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDeliverySRDBI implements IDatabase<MedicineDeliverySR> {

    private Connection conn;

    public MedicineDeliverySRDBI() {
        this.conn = DBConnection.getConnection();
    }

    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "MEDICINEDELIVERYSR", null);

            if (rset.next() && rset.getString(3).equals("MEDICINEDELIVERYSR")) {
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute(
                        "CREATE TABLE MedicineDeliverySR( "
                                + "srID VARCHAR(50), "
                                + "status VARCHAR(50), "
                                + "locationID VARCHAR(50), "
                                + "medicineID VARCHAR(50),"
                                + "employeeID VARCHAR(50),"
                                + "patientFirstName VARCHAR(50),"
                                + "patientLastName VARCHAR(50),"
                                + "patientID VARCHAR(50),"
                                + "DOB VARCHAR(50),"
                                + "email VARCHAR(50),"
                                + "room VARCHAR(50),"
                                + "dosage VARCHAR(50),"
                                + "medicineName VARCHAR(50),"
                                + "dispenseAmount VARCHAR(50),"
                                + "frequency VARCHAR(50),"
                                + "form VARCHAR(50),"
                                + "mgPerDose VARCHAR(50),"
                                + " PRIMARY KEY (srID)," +
                                "CONSTRAINT FK_MedicineDeliverySR_Location FOREIGN KEY (locationID) REFERENCES Location (nodeID) ON DELETE SET NULL," +
                                "CONSTRAINT FK_MedicineDeliverySR_Employee FOREIGN KEY (employeeID) REFERENCES Employee (employeeID) ON DELETE SET NULL)");

            }
        } catch (SQLException e) {
            System.out.println("Create MedicineDeliverySR Table: Failed!");
            e.printStackTrace();
        }
    }


    public List<MedicineDeliverySR> getAllNodes() {
        List<MedicineDeliverySR> medicineDeliverySRList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MedicineDeliverySR");
            ResultSet rset = pstmt.executeQuery();

            String srID;
            String status;
            Location destination;
            Medicine medicine;
            Employee assignedEmployee;
            String patientFirstName;
            String patientLastName;
            String patientID;
            String DOB;
            String email;
            String room;
            String dosage;
            String medicineName;
            String dispenseAmount;
            String frequency;
            String form;
            String mgPerDose;

            String locationID = "";
            String medicineID = "";
            String employeeID = "";

            while (rset.next()) {
                srID = rset.getString("srID");
                status = rset.getString("status");
                locationID = rset.getString("locationID");
                medicineID = rset.getString("medicineID");
                employeeID = rset.getString("employeeID");
                patientFirstName = rset.getString("patientFirstName");
                patientLastName = rset.getString("patientLastName");
                patientID = rset.getString("patientID");
                DOB = rset.getString("DOB");
                email = rset.getString("email");
                room = rset.getString("room");
                dosage = rset.getString("dosage");
                medicineName = rset.getString("medicineName");
                dispenseAmount = rset.getString("dispenseAmount");
                frequency = rset.getString("frequency");
                form = rset.getString("form");
                mgPerDose = rset.getString("mgPerDose");

                LocationDBI locationDBI = new LocationDBI();
                Location location = locationDBI.getNode(locationID);

                EmployeeDBI employeeDBI = new EmployeeDBI();
                Employee employee = employeeDBI.getNode(employeeID);

                medicine = new Medicine();
                medicine.setMedicationID(medicineID);

                medicineDeliverySRList.add(
                        new MedicineDeliverySR(
                                srID,
                                status,
                                location,
                                medicine,
                                employee,
                                patientFirstName,
                                patientLastName,
                                patientID,
                                DOB,
                                email,
                                room,
                                dosage,
                                medicineName,
                                dispenseAmount,
                                frequency,
                                form,
                                mgPerDose));
            }
        } catch (SQLException e) {
            System.out.println("Get all MedicineDeliverySR: SQL Failed!");
            e.printStackTrace();
        }
        return medicineDeliverySRList;
    }


    public MedicineDeliverySR getNode(String nodeID) {
        MedicineDeliverySR medicineDeliverySR = new MedicineDeliverySR();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM MedicineDeliverySR WHERE srID = ?");
            pstmt.setString(1, nodeID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();
            String status = rset.getString("status");
            String locationID = rset.getString("locationID");
            String medicineID = rset.getString("medicineID");
            String employeeID = rset.getString("employeeID");
            String patientFirstName = rset.getString("patientFirstName");
            String patientLastName = rset.getString("patientLastName");
            String patientID = rset.getString("patientID");
            String DOB = rset.getString("DOB");
            String email = rset.getString("email");
            String room = rset.getString("room");
            String dosage = rset.getString("dosage");
            String medicineName = rset.getString("medicineName");
            String dispenseAmount = rset.getString("dispenseAmount");
            String frequency = rset.getString("frequency");
            String form = rset.getString("form");
            String mgPerDose = rset.getString("mgPerDose");

            LocationDBI locationDBI = new LocationDBI();
            Location location = locationDBI.getNode(locationID);

            EmployeeDBI employeeDBI = new EmployeeDBI();
            Employee employee = employeeDBI.getNode(employeeID);

            Medicine medicine = new Medicine();
            medicine.setMedicationID(medicineID);

            medicineDeliverySR =  new MedicineDeliverySR(nodeID, status,location,medicine,employee,patientFirstName,patientLastName,patientID,DOB,email,room,dosage,medicineName,dispenseAmount,frequency,form,mgPerDose);

        } catch (SQLException e) {
            System.out.println("Get MedicineDeliverySR ID Failed");
            e.printStackTrace();
        }
        return medicineDeliverySR;
    }

    public void deleteNode(String nodeID) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM MedicineDeliverySR WHERE srID = ?");
            pstmt.setString(1, nodeID);

                pstmt.executeUpdate();


            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Delete From MedicineDeliverySR SR ID: Failed!");
            e.printStackTrace();
        }
    }


    public void updateNode(MedicineDeliverySR node) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE MedicineDeliverySR SET status = ?, locationID = ?, medicineID = ?, employeeID = ?, patientFirstName = ?, patientLastName = ?, patientID = ?, DOB = ?, email = ?, room = ?, dosage = ?, medicineName = ?, dispenseAmount = ?, frequency = ?, form = ?, mgPerDose = ? WHERE srID = ? ");

            pstmt.setString(1, node.getStatusString());
            pstmt.setString(2,node.getDestination().getNodeID());
            pstmt.setString(3, node.getMedicine().getMedicationID());
            pstmt.setString(4, node.getAssignedEmployee().getEmployeeID());
            pstmt.setString(5, node.getPatientFirstName());
            pstmt.setString(6, node.getPatientLastName());
            pstmt.setString(7, node.getPatientID());
            pstmt.setString(8, node.getDOB());
            pstmt.setString(9, node.getEmail());
            pstmt.setString(10, node.getRoom());
            pstmt.setString(11, node.getDosage());
            pstmt.setString(12, node.getMedicineName());
            pstmt.setString(13, node.getDispenseAmount());
            pstmt.setString(14, node.getFrequency());
            pstmt.setString(15, node.getForm());
            pstmt.setString(16, node.getMgPerDose());
            pstmt.setString(17, node.getSrID());


            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update MedicineDeliverySR SR ID: Failed!");
            e.printStackTrace();
            return;
        }
    }

    public void insertNode(MedicineDeliverySR node) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO MedicineDeliverySR(srID,status, locationID ,medicineID ,employeeID ,patientFirstName,patientLastName,patientID,DOB, email,room, dosage,medicineName,dispenseAmount,frequency,form,mgPerDose) VALUES(?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, node.getStatusString());
            pstmt.setString(2,node.getDestination().getNodeID());
            pstmt.setString(3, node.getMedicine().getMedicationID());
            pstmt.setString(4, node.getAssignedEmployee().getEmployeeID());
            pstmt.setString(5, node.getPatientFirstName());
            pstmt.setString(6, node.getPatientLastName());
            pstmt.setString(7, node.getPatientID());
            pstmt.setString(8, node.getDOB());
            pstmt.setString(9, node.getEmail());
            pstmt.setString(10, node.getRoom());
            pstmt.setString(11, node.getDosage());
            pstmt.setString(12, node.getMedicineName());
            pstmt.setString(13, node.getDispenseAmount());
            pstmt.setString(14, node.getFrequency());
            pstmt.setString(15, node.getForm());
            pstmt.setString(16, node.getMgPerDose());
            pstmt.setString(17, node.getSrID());


            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into MedicineDeliverySR Table: Failed!");
            e.printStackTrace();
        }
    }

    public void restore(List<MedicineDeliverySR> list) {

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE MEDICINEDELIVERYSR");
        } catch (SQLException e) {
            System.out.println("Drop Medicine Delivery SR Table: Failed!");
        }

        try {
            createTable();
            for (MedicineDeliverySR medicineDeliverySR : list) {

                // Get all the parameter information
                String srID = medicineDeliverySR.getSrID();
                String status = medicineDeliverySR.getStatusString();
                Location destination = medicineDeliverySR.getDestination();
                Medicine medicine = medicineDeliverySR.getMedicine();
                Employee employee = medicineDeliverySR.getAssignedEmployee();
                String patientFirstName = medicineDeliverySR.getPatientFirstName();
                String patientLastName = medicineDeliverySR.getPatientLastName();
                String patientID = medicineDeliverySR.getPatientID();
                String DOB = medicineDeliverySR.getDOB();
                String email = medicineDeliverySR.getEmail();
                String room = medicineDeliverySR.getRoom();
                String dosage = medicineDeliverySR.getDosage();
                String medicineName = medicineDeliverySR.getMedicineName();
                String dispenseAmount = medicineDeliverySR.getDispenseAmount();
                String frequency = medicineDeliverySR.getFrequency();
                String form = medicineDeliverySR.getForm();
                String mgPerDose = medicineDeliverySR.getMgPerDose();

                PreparedStatement pstmt =
                        conn.prepareStatement(
                                "INSERT INTO MedicineDeliverySR (srID, status, locationID, medicineID, employeeID, patientFirstName, patientLastName, patientID, DOB, email, room, dosage, medicineName, dispenseAmount, frequency, form, mgPerDose) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, srID);
                pstmt.setString(2, status);
                pstmt.setString(3, destination.getNodeID());
                pstmt.setString(4, medicine.getMedicationID());
                pstmt.setString(5, employee.getEmployeeID());
                pstmt.setString(6, patientFirstName);
                pstmt.setString(7, patientLastName);
                pstmt.setString(8, patientID);
                pstmt.setString(9, DOB);
                pstmt.setString(10, email);
                pstmt.setString(11, room);
                pstmt.setString(12, dosage);
                pstmt.setString(13, medicineName);
                pstmt.setString(14, dispenseAmount);
                pstmt.setString(15, frequency);
                pstmt.setString(16, form);
                pstmt.setString(17, mgPerDose);

                pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Restore MedicineDeliverySR Table: Failed!");
            e.printStackTrace();
        }
    }
}
