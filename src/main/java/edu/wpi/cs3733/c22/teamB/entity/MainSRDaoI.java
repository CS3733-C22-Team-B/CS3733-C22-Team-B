package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainSRDaoI implements IDatabase<AbstractSR> {
    Connection conn;

    public MainSRDaoI() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void addValue(AbstractSR object) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO MainSR(srID, srType, status, locationID, requestorID, assignedEmployeeID, dateRequested, notes) VALUES(?,?,?,?,?,?,?,?)");
            pstmt.setString(1, object.getSrID());
            pstmt.setString(2, object.getSrType());
            pstmt.setString(3, object.getStatus());
            pstmt.setString(4, object.getLocation().getNodeID());
            pstmt.setString(5, object.getRequestor().getEmployeeID());
            pstmt.setString(6, object.getAssignedEmployee().getEmployeeID());
            pstmt.setString(7, object.getDateRequested().toString());
            pstmt.setString(8, object.getNotes());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Insert Into MainSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteValue(String objectID) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM MainSR WHERE srID = ?");
            pstmt.setString(1, objectID);

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Delete From MainSR Table Using SR ID: Failed!");
            e.printStackTrace();
        }

    }

    @Override
    public void updateValue(AbstractSR object) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE MainSR SET srType = ?, status = ?, locationID = ?, requestorID = ?, assignedEmployeeID = ?, dateRequested = ?, notes = ? WHERE srID = ?");

            pstmt.setString(1, object.getSrType());
            pstmt.setString(2, object.getStatus());
            pstmt.setString(3, object.getLocation().getNodeID());
            pstmt.setString(4, object.getRequestor().getEmployeeID());
            pstmt.setString(5, object.getAssignedEmployee().getEmployeeID());
            pstmt.setString(6, object.getDateRequested().toString());
            pstmt.setString(7, object.getNotes());
            pstmt.setString(8, object.getSrID());


            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update MainSR ID: Failed!");
            e.printStackTrace();
            return;
        }

    }

    @Override
    public AbstractSR getValue(String objectID) {
        MainSR mainSR = new MainSR();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM MainSR WHERE srID = ?");
            pstmt.setString(1, objectID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();
            String srType = rset.getString("srType");
            String status = rset.getString("status");
            String locationID = rset.getString("locationID");
            String requestorEmployeeID = rset.getString("requestorEmployeeID");
            String assignedEmployeeID = rset.getString("assignedEmployeeID");
            String dateRequested = rset.getString("dateRequested");
            String notes = rset.getString("notes");

            LocationDaoI locationDaoI = new LocationDaoI();
            EmployeeDaoI employeeDaoI = new EmployeeDaoI();

            Location location = locationDaoI.getValue(locationID);
            Employee requestorID = employeeDaoI.getValue(requestorEmployeeID);
            Employee assignedID = employeeDaoI.getValue(assignedEmployeeID);

            mainSR = new MainSR(objectID, srType, status, location, requestorID, assignedID, LocalDate.parse(dateRequested), notes);

        } catch (SQLException e) {
            System.out.println("Get MainSR Values: Failed!");
            e.printStackTrace();
        }
        return mainSR;
    }

    @Override
    public List<AbstractSR> getAllValues() {

        List<AbstractSR> MainList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MainSR");
            ResultSet rset = pstmt.executeQuery();

            String srID = "";
            String srType = "";
            String status = "";
            Location location;
            Employee requestor;
            Employee assignedEmployee;
            LocalDate dateRequested;
            String notes = "";

            String locationID = "";
            String requestorID = "";
            String assignedEmployeeID = "";
            String date = "";

            while (rset.next()) {
                srID = rset.getString("srID");
                srType = rset.getString("srType");
                status = rset.getString("status");
                locationID = rset.getString("locationID");
                requestorID = rset.getString("requestorID");
                assignedEmployeeID = rset.getString("assignedEmployeeID");
                date = rset.getString("dateRequested");
                notes = rset.getString("notes");

                LocationDaoI locationDaoI = new LocationDaoI();
                EmployeeDaoI employeeDaoI = new EmployeeDaoI();

                location = locationDaoI.getValue(locationID);
                requestor = employeeDaoI.getValue(requestorID);
                assignedEmployee = employeeDaoI.getValue(assignedEmployeeID);

                dateRequested = LocalDate.parse(date);

                MainList.add(new MainSR(srID, srType, status, location, requestor, assignedEmployee, dateRequested,notes));
            }
        } catch (SQLException e) {
            System.out.println("Get All MainSR Values: SQL Failed!");
            e.printStackTrace();
        }
        return MainList;
    }

    @Override
    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "MAINSR", null);

            if (rset.next() && rset.getString(3).equals("MAINSR")){
                // table exists
            } else {
                Statement stmt = conn.createStatement();
                stmt.execute(
                        "create table MainSR( "
                                + "srID VARCHAR(50), "
                                + "srType VARCHAR(50), "
                                + "status VARCHAR(50), "
                                + "LocationID VARCHAR(50), "
                                + "requestorID VARCHAR(50) , "
                                + "assignedEmployeeID VARCHAR(50), "
                                + "dateRequested VARCHAR(50), "
                                + "notes VARCHAR(50), "
                                + "PRIMARY KEY (srID)," +
                                "CONSTRAINT FK_MainSR_Location FOREIGN KEY (locationID) REFERENCES Location (nodeID) ON DELETE SET NULL ," + //ON DELETE SET NULL
                                "CONSTRAINT FK_MainSR_Employee1 FOREIGN KEY (requestorID) REFERENCES Employee (employeeID) ON DELETE SET NULL )"); //
                       //         "CONSTRAINT FK_MainSR_Employee2 FOREIGN KEY (assignedEmployeeID) REFERENCES Employee (employeeID) ON DELETE SET NULL)");
            }
        } catch (SQLException e) {
            System.out.println("Create MainSR Table: Failed!");
            e.printStackTrace();
        }


    }

    @Override
    public void dropTable() {

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE MainSR");
        } catch (SQLException e) {
            System.out.println("Drop MainSR Table: Failed!");
        }

    }

    @Override
    public void restoreTable(List<AbstractSR> list) {
        
        createTable();
        for (AbstractSR abstractSR : list) {
            addValue(abstractSR);
        }

    }
}
