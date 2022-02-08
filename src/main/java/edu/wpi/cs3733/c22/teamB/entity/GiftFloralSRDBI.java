package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GiftFloralSRDBI implements IDatabase<GiftFloralSR> {
//    private List<GiftFloralSR> db = new ArrayList<>();

    private Connection conn;

    public GiftFloralSRDBI() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void drop() {

    }

    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "GIFTFLORALSR", null);

            if (rset.next() && rset.getString(3).equals("GIFTFLORALSR")){
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE GiftFloralSR ( "
                        + "srID VARCHAR(50) , "
                        + "statusStr VARCHAR(50), "
                        + "giftName VARCHAR(50), "
                        + "deliveryDate VARCHAR(50), "
                        + "deliveryFloor VARCHAR(50), "
                        + "deliveryRoom VARCHAR(50), "
                        + "PRIMARY KEY (srID))");
            }
        } catch (SQLException e) {
            System.out.println("Create GiftFloralSR Table: Failed!");
            e.printStackTrace();
        }
    }
//
//    @Override
    public List<GiftFloralSR> getAllNodes() {
//        List<GiftFloralSR> listOfGiftFloralSR = new ArrayList<>();
//
//        try {
//            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM GiftFloralSR");
//            ResultSet rset = pstmt.executeQuery();
//
//            String srID;
//            String statusStr;
//            List<GiftType> listOfGifts;
//            String deliveryDate;
//            String deliveryFloor;
//            String deliveryRoom;
//
//            GiftFloralSR giftFloralSR = new GiftFloralSR();
//            for (GiftType giftType : giftFloralSR.getListOfGifts() ) {
//
//                if
//            }
//
//            while (rset.next()) {
//                srID = rset.getString("srID");
//                status = rset.getString("status");
//                locationID = rset.getString("locationID");
//                equipmentID = rset.getString("equipmentID");
//                employeeID = rset.getString("employeeID");
//
//
//                medEquipmentSRlist.add(
//                        new MedicalEquipmentSR(srID, status, destination, medicalEquipment, assignedEmployee));
//            }
//        } catch (SQLException e) {
//            System.out.println("Get All MedicalEquipmentSR Nodes: Failed!");
//            e.printStackTrace();
//        }
//        return medEquipmentSRlist;
//    }
        return null;

    }

    @Override
    public GiftFloralSR getNode(String nodeID) {
        return null;
    }

    @Override
    public void deleteNode(String nodeID) {}

    @Override
    public void updateNode(GiftFloralSR node) {}

    @Override
    public void insertNode(GiftFloralSR node) {
//        db.add(node);
//        System.out.println(db);
    }

    @Override
    public void restore(List<GiftFloralSR> list) {}
}
