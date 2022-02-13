package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class LaundrySRDaoI implements ISpecificServiceRequest<LaundrySR>{

    Connection conn;

    public LaundrySRDaoI() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void addValue(LaundrySR object) {
        MainSRDaoI mainDao = new MainSRDaoI();
        mainDao.addValue(object);

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO LaundrySR(srID) VALUES(?)");

            pstmt.setString(1, object.getSrID());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into LaundrySR Table: Failed!");
            e.printStackTrace();
        }

    }

    @Override
    public void deleteValue(String objectID) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM LaundrySR WHERE srID = ?");
            pstmt.setString(1, objectID);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Delete From LaundrySR Using SR ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateValue(LaundrySR object) {
        // nothing to update :(
    }

    @Override
    public LaundrySR getValue(String objectID) {
        LaundrySR LaundrySR = new LaundrySR();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM LaundrySR WHERE srID = ?");
            pstmt.setString(1, objectID);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            MainSRDaoI mainSRDaoI = new MainSRDaoI();
            AbstractSR mainSR = mainSRDaoI.getValue(objectID);
            String srType = mainSR.getSrType();
            String status = mainSR.getStatus();
            Location location = mainSR.getLocation();
            Employee requestor = mainSR.getRequestor();
            Employee assignedEmployee = mainSR.getAssignedEmployee();
            LocalDate dateRequested = mainSR.getDateRequested();
            String notes = mainSR.getNotes();
            LaundrySR = new LaundrySR(objectID, status, location, requestor, assignedEmployee, dateRequested, notes);
        } catch (SQLException e) {
            System.out.println("Get LaundrySR Node Failed");
            e.printStackTrace();
        }
        return LaundrySR;
    }

    @Override
    public List<LaundrySR> getAllValues() {
        List<LaundrySR> LaundrySRList = new ArrayList<>();
        try{
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT srID FROM LaundrySR ");
            ResultSet rset = pstmt.executeQuery();


            while(rset.next()){
                LaundrySRList.add(getValue(rset.getString("SRID")));
            }
        } catch (SQLException e) {
            System.out.println("Get LaundrySR Node Failed");
            e.printStackTrace();
        }
        return LaundrySRList;
    }

    @Override
    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "LAUNDRYSR", null);
            if (rset.next() && rset.getString(3).equals("LAUNDRYSR")){
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE LaundrySR ( "
                        + "srID VARCHAR(50) , "
                        + "PRIMARY KEY (srID),"
                        + "CONSTRAINT FK_LAUNDRYSR_MainSR FOREIGN KEY (srID) REFERENCES MainSR (srID) )");
            }
        } catch (SQLException e) {
            System.out.println("Create LaundrySR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE LaundrySR");
        } catch (SQLException e) {
            System.out.println("Drop LaundrySR Table: Failed!");
        }
    }

    @Override
    public void restoreTable(List<LaundrySR> list) {
        createTable();
        for (LaundrySR laundrySR : list) {
            addValue(laundrySR);
        }
    }
}
