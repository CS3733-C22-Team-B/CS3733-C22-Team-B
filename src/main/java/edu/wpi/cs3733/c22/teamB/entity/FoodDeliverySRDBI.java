package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDeliverySRDBI implements IDatabase<FoodDeliverySR> {

    private Connection conn;

    public FoodDeliverySRDBI() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void drop() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE FoodDeliverySR");
        } catch (SQLException e) {
            System.out.println("Drop Employee Table: Failed!");
        }
    }

    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "FOODDELIVERYSR", null);

            if (rset.next() && rset.getString(3).equals("FOODDELIVERYSR")) {
                // table exists
            } else {
                // Create table

                Statement stmt = conn.createStatement();
                stmt.execute(
                        "CREATE TABLE FoodDeliverySR( "
                                + "srID VARCHAR(50), "
                                + "status VARCHAR(50), "
                                + "locationID VARCHAR(50), "
                                + "foodName VARCHAR(50), "
                                + "foodRecipientName VARCHAR(50),"
                                + "employeeID VARCHAR(50),"
                                + "PRIMARY KEY (srID),"
                                + "CONSTRAINT FK_FoodDeliverySR_Location FOREIGN KEY (locationID) REFERENCES Location (nodeID) ON DELETE SET NULL,"
                                + "CONSTRAINT FK_FoodDeliverySR_Employee FOREIGN KEY (employeeID) REFERENCES Employee (employeeID) ON DELETE SET NULL)");
            }
        } catch (SQLException e) {
            System.out.println("Create FoodDeliverySR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void restore(List<FoodDeliverySR> list) {

        try {
            createTable();

            // For each iteration of location in the list of location
            for (FoodDeliverySR foodDeliverySR : list) {

                // Get all the parameter information
                String srID = foodDeliverySR.getSrID();
                String status = foodDeliverySR.getStatusString();
                String locationID = foodDeliverySR.getDestination().getNodeID();
                String foodName = foodDeliverySR.getFoodName();
                String foodRecipientName = foodDeliverySR.getFoodRecipientName();
                String employeeID = foodDeliverySR.getAssignedEmployee().getEmployeeID();

                PreparedStatement pstmt =
                        conn.prepareStatement(
                                "INSERT INTO FoodDeliverySR (srID, status, locationID, foodName, foodRecipientName, employeeID) VALUES (?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, srID);
                pstmt.setString(2, status);
                pstmt.setString(3, locationID);
                pstmt.setString(4, foodName);
                pstmt.setString(5, foodRecipientName);
                pstmt.setString(6, employeeID);

                pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Restore FoodDeliverySR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public List<FoodDeliverySR> getAllNodes() {

        List<FoodDeliverySR> foodDeliverySRList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM FoodDeliverySR");
            ResultSet rset = pstmt.executeQuery();

            String srID;
            String status;
            Location destination;
            String foodName;
            String foodRecipientName;
            Employee assignedEmployee;

            String locationID = "";
            String employeeID = "";

            while (rset.next()) {
                srID = rset.getString("srID");
                status = rset.getString("status");
                locationID = rset.getString("locationID");
                foodName = rset.getString("foodName");
                foodRecipientName = rset.getString("foodRecipientName");
                employeeID = rset.getString("employeeID");

                LocationDBI locationDBI = new LocationDBI();
                destination = locationDBI.getNode(locationID);

                EmployeeDBI employeeDBI = new EmployeeDBI();
                assignedEmployee = employeeDBI.getNode(employeeID);

                foodDeliverySRList.add(
                        new FoodDeliverySR(
                                srID, status, destination, foodName, foodRecipientName, assignedEmployee));
            }
        } catch (SQLException e) {
            System.out.println("Get All FoodDeliverySR Nodes: Failed!");
            e.printStackTrace();
        }
        return foodDeliverySRList;
    }

    @Override
    public FoodDeliverySR getNode(String nodeID) {
        FoodDeliverySR foodDeliverySR = new FoodDeliverySR();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM FoodDeliverySR WHERE srID = ?");
            pstmt.setString(1, nodeID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();

            String status = rset.getString("status");
            String locationID = rset.getString("locationID");
            String foodName = rset.getString("foodName");
            String foodRecipientName = rset.getString("foodRecipientName");
            String employeeID = rset.getString("employeeID");

            LocationDBI locationDBI = new LocationDBI();
            Location destination = locationDBI.getNode(locationID);

            EmployeeDBI employeeDBI = new EmployeeDBI();
            Employee assignedEmployee = employeeDBI.getNode(employeeID);

            foodDeliverySR =
                    new FoodDeliverySR(
                            nodeID, status, destination, foodName, foodRecipientName, assignedEmployee);

        } catch (SQLException e) {
            System.out.println("Get FoodDeliverySR Node Failed");
            e.printStackTrace();
        }
        return foodDeliverySR;
    }

    @Override
    public void deleteNode(String nodeID) {

        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM FoodDeliverySR WHERE srID = ?");
            pstmt.setString(1, nodeID);
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Delete From FoodDeliverySR Table Using SR ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateNode(FoodDeliverySR node) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE FoodDeliverySR SET status = ?, LOCATIONID = ?, FOODNAME = ?, FOODRECIPIENTNAME = ?, EMPLOYEEID = ? WHERE srID = ?");

            pstmt.setString(1, node.getStatusString());
            pstmt.setString(2, node.getDestination().getNodeID());
            pstmt.setString(3, node.getFoodName());
            pstmt.setString(4, node.getFoodRecipientName());
            pstmt.setString(5, node.getAssignedEmployee().getEmployeeID());
            pstmt.setString(6, node.getSrID());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Update FoodDeliverySR Node: Failed!");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void insertNode(FoodDeliverySR node) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO FoodDeliverySR (srID, status, locationID, foodName, foodRecipientName, employeeID) VALUES(?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, node.getSrID());
            pstmt.setString(2, node.getStatusString());
            pstmt.setString(3, node.getDestination().getNodeID());
            pstmt.setString(4, node.getFoodName());
            pstmt.setString(5, node.getFoodRecipientName());
            pstmt.setString(6, node.getAssignedEmployee().getEmployeeID());

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into FoodDeliverySR Table: Failed!");
            e.printStackTrace();
        }
    }
}
