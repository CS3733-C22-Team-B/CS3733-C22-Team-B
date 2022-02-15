package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SanitationSRDaoI implements IDatabase<SanitationSR> {

    private Connection conn;

    public SanitationSRDaoI() {
        this.conn = ConnectionManager.getInstance().getConnection();
    }

    @Override
    public void addValue(SanitationSR object) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SanitationSR (srID, condition) VALUES(?, ?)");
            pstmt.setString(1, object.getSrID());
            pstmt.setString(2, object.getCondition());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into SanitationSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteValue(String objectID) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM SanitationSR WHERE srID = ?");
            pstmt.setString(1, objectID);
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Delete From SanitationSR Table Using SR ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateValue(SanitationSR object) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE SanitationSR SET condition = ? WHERE srID = ?");

            pstmt.setString(1, object.getCondition());
            pstmt.setString(2, object.getSrID());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Update SanitationSR Node: Failed!");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public SanitationSR getValue(String objectID) {
        SanitationSR sanitationSR = new SanitationSR();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM SanitationSR WHERE srID = ?");
            pstmt.setString(1, objectID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();

            String condition = rset.getString("condition");

            MainSRDaoI mainSRDaoI = new MainSRDaoI();
            AbstractSR mainSR = mainSRDaoI.getValue(objectID);

            String status = mainSR.getStatus();
            Location location = mainSR.getLocation();
            Employee requestor = mainSR.getRequestor();
            Employee assignedEmployee = mainSR.getAssignedEmployee();
            LocalDate dateRequested = mainSR.getDateRequested();
            String notes = mainSR.getNotes();

            sanitationSR = new SanitationSR(objectID, status, location, requestor, assignedEmployee, dateRequested, notes, condition);

        } catch (SQLException e) {
            System.out.println("Get SanitationSR Node Failed");
            e.printStackTrace();
        }
        return sanitationSR;
    }

    @Override
    public List<SanitationSR> getAllValues() {
        List<SanitationSR> sanitationSRList = new ArrayList<>();
        try{
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT SRID FROM SanitationSR ");
            ResultSet rset = pstmt.executeQuery();


            while(rset.next()){
                sanitationSRList.add(getValue(rset.getString("SRID")));
            }
        } catch (SQLException e) {
            System.out.println("Get SanitationSR Node Failed");
            e.printStackTrace();
        }
        return sanitationSRList;
    }

    @Override
    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "SanitationSR", null);

            if (rset.next() && rset.getString(3).equals("SanitationSR")){
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE SanitationSR ( "
                        + "srID VARCHAR(50) , "
                        + "condition VARCHAR(50), "
                        + "PRIMARY KEY (srID),"
                        + "CONSTRAINT FK_SanitationSR_MainSR FOREIGN KEY (srID) REFERENCES MainSR (srID) )");
            }
        } catch (SQLException e) {
            System.out.println("Create SanitationSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE SanitationSR");
        } catch (SQLException e) {
            System.out.println("Drop SanitationSR Table: Failed!");
        }
    }

    @Override
    public void restoreTable(List<SanitationSR> list) {
        createTable();

        for (SanitationSR sanitationSR : list) {
            addValue(sanitationSR);
        }
    }
}
