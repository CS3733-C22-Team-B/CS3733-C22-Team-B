package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExternalTransportSRDBI implements IDatabase<ExternalTransportSR> {

    private Connection conn;

    public ExternalTransportSRDBI() {

        this.conn = DBConnection.getConnection();
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
                        "create table EXTERNALTRANSPORTSR( "
                                + "srID VARCHAR(50), "
                                + "status VARCHAR(50), "
                                + "pickupLocation VARCHAR(50),"
                                + "destination VARCHAR(50),"
                                + "info VARCHAR(50),"
                                + "date VARCHAR(50),"
                                + "formOfTransport VARCHAR(50),"
                                + "assignedEmployee VARCHAR(50) REFERENCES EMPLOYEE(EMPLOYEEID),"
                                + " PRIMARY KEY (srID))");

            }
        } catch (SQLException e) {
            System.out.println("Create EXTERNALTRANSPORTSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public List<ExternalTransportSR> getAllNodes() {

        List<ExternalTransportSR> externalTransportSRList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM EXTERNALTRANSPORTSR");
            ResultSet rset = pstmt.executeQuery();

            String srID = "";
            String status = "";
            String pickupLocation = "";
            String destination = "";
            String info = "";
            String date = "";
            String formOfTransport ="";
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

                externalTransportSRList.add(new ExternalTransportSR(srID,status,pickupLocation,destination,info,date,formOfTransport, employee));
            }
        } catch (SQLException e) {
            System.out.println("Get all nodes: SQL Failed!");
            e.printStackTrace();
        }
        return externalTransportSRList;

    }

    @Override
    public ExternalTransportSR getNode(String nodeID) {

        ExternalTransportSR externalTransportSR = new ExternalTransportSR();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM EXTERNALTRANSPORTSR WHERE srID = ?");
            pstmt.setString(1, nodeID);
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
                    new ExternalTransportSR(nodeID, status, pickupLocation, destination, info, date, formOfTransport, employee);

        } catch (SQLException e) {
            System.out.println("Get Node Failed");
            e.printStackTrace();
        }
        return externalTransportSR;
    }


    @Override
    public void deleteNode(String nodeID) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM EXTERNALTRANSPORTSR WHERE srID = ?");
            pstmt.setString(1, nodeID);

            pstmt.executeUpdate();


            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Delete From EXTERNALTRANSPORTSR Table Using Equipment ID: Failed!");
            e.printStackTrace();
        }
    }


    @Override
    public void updateNode(ExternalTransportSR node) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("UPDATE EXTERNALTRANSPORTSR SET status = ?, pickupLocation = ?, destination = ?, info = ?, date = ?, formOfTransport = ? , as = ? WHERE srID = ? ");

            pstmt.setString(1, node.getStatusString());
            pstmt.setString(2, node.getPickupLoc());
            pstmt.setString(3, node.getDestination());
            pstmt.setString(4, node.getInfo());
            pstmt.setString(5, node.getDate());
            pstmt.setString(6, node.getFormOfTransport());
            pstmt.setString(7, node.getAssignedEmployee().getEmployeeID());
            pstmt.setString(8, node.getSrID());


            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update Node ID: Failed!");
            e.printStackTrace();
            return;
        }
        }

    @Override
    public void insertNode(ExternalTransportSR node) {

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO EXTERNALTRANSPORTSR (srID, status, pickupLocation, destination, info, date, formOfTransport, employeeID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, node.getSrID());
            pstmt.setString(2, node.getStatusString());
            pstmt.setString(3, node.getPickupLoc());
            pstmt.setString(4, node.getDestination());
            pstmt.setString(5, node.getInfo());
            pstmt.setString(6, node.getDate());
            pstmt.setString(7, node.getFormOfTransport());
            pstmt.setString(8, node.getAssignedEmployee().getEmployeeID());


            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into EXTERNALTRANSPORTSR Table: Failed!");
            e.printStackTrace();
        }
    }



    @Override
    public void restore(List<ExternalTransportSR> list) {

    }
}
