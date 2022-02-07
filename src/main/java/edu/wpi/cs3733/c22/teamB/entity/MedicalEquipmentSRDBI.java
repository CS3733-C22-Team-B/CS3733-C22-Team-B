package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalEquipmentSRDBI implements IDatabase<MedicalEquipmentSR> {

    private Connection conn;

    public MedicalEquipmentSRDBI() {
        this.conn = DBConnection.getConnection();
    }

    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "MEDICALEQUIPMENTSR", null);

            if (rset.next() && rset.getString(3).equals("MEDICALEQUIPMENTSR")){
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute(
                        "create table MedicalEquipmentSR( "
                                + "srID VARCHAR(50), "
                                + "status VARCHAR(50), "
                                + "locationID VARCHAR(50), "
                                + "equipmentID VARCHAR(50), "
                                + "employeeID VARCHAR(50), "
                                + "PRIMARY KEY (srID), "
                                + "CONSTRAINT FK_MedicalEquipmentSR_Location FOREIGN KEY (locationID) REFERENCES Location (nodeID) ON DELETE SET NULL,"
                                + "CONSTRAINT FK_MedicalEquipmentSR_MedicalEquipment FOREIGN KEY (equipmentID) REFERENCES MedicalEquipment (equipmentID) ON DELETE SET NULL,"
                                + "CONSTRAINT FK_MedicalEquipmentSR_Employee FOREIGN KEY (employeeID) REFERENCES Employee (employeeID) ON DELETE SET NULL)");
            }
        } catch (SQLException e) {
            System.out.println("Create MedicalEquipmentSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void restore(List<MedicalEquipmentSR> list) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE MedicalEquipmentSR");
        } catch (SQLException e) {
            System.out.println("Drop MedicalEquipmentSR Table: Failed!");
        }

        try {
            createTable();
//            Statement stmt = conn.createStatement();
//            stmt.execute(
//                    "create table MedicalEquipmentSR( "
//                            + "srID VARCHAR(50) PRIMARY KEY,"
//                            + "status VARCHAR(50), "
//                            + "locationID VARCHAR(50), "
//                            + "equipmentID VARCHAR(50), "
//                            + "employeeID VARCHAR(50),"
//                            + "CONSTRAINT FK_MedicalEquipmentSR_Location FOREIGN KEY (locationID) REFERENCES Location (nodeID) ON DELETE SET NULL,"
//                            + "CONSTRAINT FK_MedicalEquipmentSR_MedicalEquipment FOREIGN KEY (equipmentID) REFERENCES MedicalEquipment (equipmentID) ON DELETE SET NULL,"
//                            + "CONSTRAINT FK_MedicalEquipmentSR_Employee FOREIGN KEY (employeeID) REFERENCES Employee (employeeID) ON DELETE SET NULL)");

            // For each iteration of location in the list of location
            for (MedicalEquipmentSR medEquipSR : list) {

                // Get all the parameter information
                String srID = medEquipSR.getSrID();
                String status = medEquipSR.getStatusString();
                String locationID = medEquipSR.getDestination().getNodeID();
                String equipmentID = medEquipSR.getMedicalEquipment().getEquipmentID();
                String employeeID = medEquipSR.getAssignedEmployee().getEmployeeID();

                PreparedStatement pstmt =
                        conn.prepareStatement(
                                "INSERT INTO MedicalEquipmentSR (srID, status, locationID, equipmentID, employeeID) VALUES (?, ?, ?, ?, ?)");
                pstmt.setString(1, srID);
                pstmt.setString(2, status);
                pstmt.setString(3, locationID);
                pstmt.setString(4, equipmentID);
                pstmt.setString(5, employeeID);

                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException e) {
            System.out.println("Restore MedicalEquipmentSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public List<MedicalEquipmentSR> getAllNodes() {

        List<MedicalEquipmentSR> medEquipmentSRlist = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MedicalEquipmentSR");
            ResultSet rset = pstmt.executeQuery();

            String srID = "";
            String status = "";
            Location destination;
            MedicalEquipment medicalEquipment;
            Employee assignedEmployee;

            String locationID = "";
            String equipmentID = "";
            String employeeID = "";

            while (rset.next()) {
                srID = rset.getString("srID");
                status = rset.getString("status");
                locationID = rset.getString("locationID");
                equipmentID = rset.getString("equipmentID");
                employeeID = rset.getString("employeeID");

                LocationDBI locationDBI = new LocationDBI();
//                locationDBI.initconn("jdbc:derby:bDB;create=true", "admin", "admin");
                destination = locationDBI.getNode(locationID);

                MedicalEquipmentDBI equipmentDBI = new MedicalEquipmentDBI();
//                equipmentDBI.initconn("jdbc:derby:bDB;create=true", "admin", "admin");
                medicalEquipment = equipmentDBI.getNode(equipmentID);

                EmployeeDBI employeeDBI = new EmployeeDBI();
//                employeeDBI.initconn("jdbc:derby:bDB;create=true", "admin", "admin");
                assignedEmployee = employeeDBI.getNode(employeeID);

                medEquipmentSRlist.add(
                        new MedicalEquipmentSR(srID, status, destination, medicalEquipment, assignedEmployee));
            }
        } catch (SQLException e) {
            System.out.println("Get All MedicalEquipmentSR Nodes: Failed!");
            e.printStackTrace();
        }
        return medEquipmentSRlist;
    }

    @Override
    public MedicalEquipmentSR getNode(String srID) {

        MedicalEquipmentSR medEquipmentSR = new MedicalEquipmentSR();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM MedicalEquipmentSR WHERE srID = ?");
            pstmt.setString(1, srID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();

            String status = rset.getString("status");
            String locationID = rset.getString("locationID");
            String equipmentID = rset.getString("equipmentID");
            String employeeID = rset.getString("employeeID");

            LocationDBI locationDBI = new LocationDBI();
//            locationDBI.initconn("jdbc:derby:bDB;create=true", "admin", "admin");
            Location destination = locationDBI.getNode(locationID);

            MedicalEquipmentDBI equipmentDBI = new MedicalEquipmentDBI();
//            equipmentDBI.initconn("jdbc:derby:bDB;create=true", "admin", "admin");
            MedicalEquipment medicalEquipment = equipmentDBI.getNode(equipmentID);

            EmployeeDBI employeeDBI = new EmployeeDBI();
//            employeeDBI.initconn("jdbc:derby:bDB;create=true", "admin", "admin");
            Employee assignedEmployee = employeeDBI.getNode(employeeID);

            medEquipmentSR =
                    new MedicalEquipmentSR(srID, status, destination, medicalEquipment, assignedEmployee);

        } catch (SQLException e) {
            System.out.println("Get MedicalEquipmentSR Failed");
            e.printStackTrace();
        }
        return medEquipmentSR;
    }

    @Override
    public void deleteNode(String srID) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM MedicalEquipmentSR WHERE srID = ?");
            pstmt.setString(1, srID);
            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Delete From MedicalEquipmentSR Using SR ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateNode(MedicalEquipmentSR node) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE MedicalEquipmentSR SET status = ?, locationID = ?, equipmentID = ?, employeeID = ? WHERE srID = ?");

            pstmt.setString(1, node.getStatusString());
            pstmt.setString(2, node.getDestination().getNodeID());
            pstmt.setString(3, node.getMedicalEquipment().getEquipmentID());
            pstmt.setString(4, node.getAssignedEmployee().getEmployeeID());
            pstmt.setString(5, node.getSrID());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Update MedicalEquipmentSR Node: Failed!");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void insertNode(MedicalEquipmentSR node) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO MedicalEquipmentSR(srID, status, locationID, equipmentID, employeeID) VALUES(?, ?, ?, ?, ?)");
            pstmt.setString(1, node.getSrID());
            pstmt.setString(2, node.getStatusString());
            pstmt.setString(3, node.getDestination().getNodeID());
            pstmt.setString(4, node.getMedicalEquipment().getEquipmentID());
            pstmt.setString(5, node.getAssignedEmployee().getEmployeeID());

            if (!isInTable(node.getSrID())) {
                pstmt.executeUpdate();
            }
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into MedicalEquipmentSR Table: Failed!");
            e.printStackTrace();
        }
    }

    public boolean isInTable(String nodeID) { // check if there is a node with given ID in table
        boolean ans = false;
        try {
            // search for NodeID
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM Location WHERE nodeID = ?");
            pstmt.setString(1, nodeID);
            ResultSet rs = pstmt.executeQuery();
            ans = rs.next(); // if any ids are found
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Search for MedicalEquipmentSR Failed!");
            e.printStackTrace();
        }
        return ans;
    }
}
