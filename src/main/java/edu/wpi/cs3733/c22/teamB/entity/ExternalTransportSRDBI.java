package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExternalTransportSRDBI implements IDatabase<ExternalTransportSR> {

    private Connection conn;

    public ExternalTransportSRDBI() {

        this.conn = DBConnection.getConnection();
    }

    @Override
    public void drop() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE ExternalTransportSR");
        } catch (SQLException e) {
            System.out.println("Drop ExternalTransportationSR Table: Failed!");
        }
    }

    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "EXTERNALTRANSPORTSR", null);

            if (rset.next() && rset.getString(3).equals("EXTERNALTRANSPORTSR")) {
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute(
                        "create table ExternalTransportSR( "
                                + "srID VARCHAR(50), "
                                + "status VARCHAR(50), "
                                + "pickupLocation VARCHAR(50),"
                                + "destination VARCHAR(50),"
                                + "info VARCHAR(50),"
                                + "date VARCHAR(50),"
                                + "formOfTransport VARCHAR(50),"
                                + "assignedEmployee VARCHAR(50),"
                                + " PRIMARY KEY (srID),"
                                + "CONSTRAINT FK_ExternalTransportSR_Employee FOREIGN KEY (assignedEmployee) REFERENCES Employee (employeeID) ON DELETE SET NULL)");
            }
        } catch (SQLException e) {
            System.out.println("Create ExternalTransportSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void restore(List<ExternalTransportSR> list) {

        try {
            createTable();

            // For each iteration of location in the list of location
            for (ExternalTransportSR externalTransportSR : list) {

                // Get all the parameter information
                String srID = externalTransportSR.getSrID();
                String status = externalTransportSR.getStatusString();
                String pickupLocation = externalTransportSR.getPickupLoc();
                String destination = externalTransportSR.getDestination();
                String info = externalTransportSR.getInfo();
                String date = externalTransportSR.getDate();
                String formOfTransport = externalTransportSR.getFormOfTransport();
                String assignedEmployee = externalTransportSR.getAssignedEmployee();

                PreparedStatement pstmt =
                        conn.prepareStatement(
                                "INSERT INTO ExternalTransportSR(srID, status, pickupLocation, destination, info, date, formOfTransport, assignedEmployee) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, srID);
                pstmt.setString(2, status);
                pstmt.setString(3, pickupLocation);
                pstmt.setString(4, destination);
                pstmt.setString(5, info);
                pstmt.setString(6, date);
                pstmt.setString(7, formOfTransport);
                pstmt.setString(8, assignedEmployee);

                pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Restore ExternalTransportSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public List<ExternalTransportSR> getAllNodes() {

        List<ExternalTransportSR> externalTransportSRList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ExternalTransportSR");
            ResultSet rset = pstmt.executeQuery();

            String srID = "";
            String status = "";
            String pickupLocation = "";
            String destination = "";
            String info = "";
            String date = "";
            String formOfTransport = "";
            String employeeID = "";

            while (rset.next()) {
                srID = rset.getString("equipmentID");
                status = rset.getString("equipmentName");
                pickupLocation = rset.getString("equipmentType");
                destination = rset.getString("manufacturer");
                info = rset.getString("locationID");
                date = rset.getString("status");
                formOfTransport = rset.getString("formOfTransport");
                employeeID = rset.getString("color");

                EmployeeDBI employeeDBI = new EmployeeDBI();
                Employee employee = employeeDBI.getNode(employeeID);

                externalTransportSRList.add(
                        new ExternalTransportSR(
                                srID, status, pickupLocation, destination, info, date, formOfTransport, employee));
            }
        } catch (SQLException e) {
            System.out.println("Get All ExternalTransportSR Nodes: SQL Failed!");
            e.printStackTrace();
        }
        return externalTransportSRList;
    }

    @Override
    public ExternalTransportSR getNode(String srID) {

        ExternalTransportSR externalTransportSR = new ExternalTransportSR();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM ExternalTransportSR WHERE srID = ?");
            pstmt.setString(1, srID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();
            String status = rset.getString("equipmentName");
            String pickupLocation = rset.getString("equipmentType");
            String destination = rset.getString("manufacturer");
            String info = rset.getString("locationID");
            String date = rset.getString("status");
            String formOfTransport = rset.getString("formOfTransport");
            String employeeID = rset.getString("color");

            EmployeeDBI employeeDBI = new EmployeeDBI();
            Employee employee = employeeDBI.getNode(employeeID);

            externalTransportSR =
                    new ExternalTransportSR(
                            srID, status, pickupLocation, destination, info, date, formOfTransport, employee);

        } catch (SQLException e) {
            System.out.println("Get ExternalTransportSR Node Failed");
            e.printStackTrace();
        }
        return externalTransportSR;
    }

    @Override
    public void deleteNode(String srID) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM ExternalTransportSR WHERE srID = ?");
            pstmt.setString(1, srID);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Delete From ExternalTransportSR Table Using Equipment ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateNode(ExternalTransportSR node) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE ExternalTransportSR SET status = ?, pickupLocation = ?, destination = ?, info = ?, date = ?, formOfTransport = ? , ASSIGNEDEMPLOYEE = ? WHERE srID = ? ");

            pstmt.setString(1, node.getStatusString());
            pstmt.setString(2, node.getPickupLoc());
            pstmt.setString(3, node.getDestination());
            pstmt.setString(4, node.getInfo());
            pstmt.setString(5, node.getDate());
            pstmt.setString(6, node.getFormOfTransport());
            pstmt.setString(7, node.getEmployee().getEmployeeID());
            pstmt.setString(8, node.getSrID());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update ExternalTransportSR ID: Failed!");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void insertNode(ExternalTransportSR node) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO ExternalTransportSR(srID, status, pickupLocation, destination, info, date, formOfTransport, assignedEmployee) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, node.getSrID());
            pstmt.setString(2, node.getStatusString());
            pstmt.setString(3, node.getPickupLoc());
            pstmt.setString(4, node.getDestination());
            pstmt.setString(5, node.getInfo());
            pstmt.setString(6, node.getDate());
            pstmt.setString(7, node.getFormOfTransport());
            pstmt.setString(8, node.getEmployee().getEmployeeID());

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into ExternalTransportSR Table: Failed!");
            e.printStackTrace();
        }
    }
}
