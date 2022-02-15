package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GiftFloralSRDaoI implements IDatabase<GiftFloralSR> {

    private Connection conn;

    public GiftFloralSRDaoI() {
        this.conn = EmbeddedConnection.getConnection();
    }

    @Override
    public void addValue(GiftFloralSR object) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO GIFTFLORALSR (srID, giftName) VALUES(?, ?)");
            pstmt.setString(1, object.getSrID());
            pstmt.setString(2, object.getGiftName());

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into GIFTFLORALSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteValue(String objectID) {

        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM GIFTFLORALSR WHERE srID = ?");
            pstmt.setString(1, objectID);
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Delete From GIFTFLORALSR Table Using SR ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateValue(GiftFloralSR object) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE GIFTFLORALSR SET giftName = ? WHERE srID = ?");

            pstmt.setString(1, object.getGiftName());
            pstmt.setString(2, object.getSrID());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Update GIFTFLORALSR Node: Failed!");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public GiftFloralSR getValue(String objectID) {

        GiftFloralSR giftFloralSR = new GiftFloralSR();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM GIFTFLORALSR WHERE srID = ?");
            pstmt.setString(1, objectID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();

            String giftName = rset.getString("giftName");

            MainSRDaoI mainSRDaoI = new MainSRDaoI();
            AbstractSR mainSR = mainSRDaoI.getValue(objectID);

            String srType = "Gift";
            String status = mainSR.getStatus();
            Location location = mainSR.getLocation();
            Employee requestor = mainSR.getRequestor();
            Employee assignedEmployee = mainSR.getAssignedEmployee();
            LocalDate dateRequested = mainSR.getDateRequested();
            String notes = mainSR.getNotes();

            giftFloralSR = new GiftFloralSR(objectID, status, location, requestor, assignedEmployee, dateRequested, notes, giftName);

        } catch (SQLException e) {
            System.out.println("Get FoodDeliverySR Node Failed");
            e.printStackTrace();
        }
        return giftFloralSR;
    }



    @Override
    public List<GiftFloralSR> getAllValues() {
        List<GiftFloralSR> giftFloralSRList = new ArrayList<>();
        try{
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT SRID FROM GIFTFLORALSR ");
            ResultSet rset = pstmt.executeQuery();


            while(rset.next()){
                giftFloralSRList.add(getValue(rset.getString("SRID")));
            }
        } catch (SQLException e) {
            System.out.println("Get GIFTFLORALSR Node Failed");
            e.printStackTrace();
        }
        return giftFloralSRList;
    }

    @Override
    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "GIFTFLORALSR", null);

            if (rset.next() && rset.getString(3).equals("GIFTFLORALSR")){
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE GIFTFLORALSR ( "
                        + "srID VARCHAR(50) , "
                        + "giftName VARCHAR(50), "
                        + "PRIMARY KEY (srID),"
                        + "CONSTRAINT FK_GIFTFLORALSR_MainSR FOREIGN KEY (srID) REFERENCES MainSR (srID) )");
            }
        } catch (SQLException e) {
            System.out.println("Create Location Table: Failed!");
            e.printStackTrace();
        }

    }

    @Override
    public void dropTable() {

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE GIFTFLORALSR");
        } catch (SQLException e) {
            System.out.println("Drop GIFTFLORALSR Table: Failed!");
        }
    }

    @Override
    public void restoreTable(List<GiftFloralSR> list) {

        createTable();

        for (GiftFloralSR giftFloralSR : list) {
            addValue(giftFloralSR);
        }

    }
}
