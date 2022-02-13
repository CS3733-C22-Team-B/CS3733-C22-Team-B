package edu.wpi.cs3733.c22.teamB.entity;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalEquipmentSRDaoI implements ISpecificServiceRequest<MedicalEquipmentSR> {

    Connection conn;

    public MedicalEquipmentSRDaoI() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void addValue(MedicalEquipmentSR object) {
//        MainSRDaoI mainDao = new MainSRDaoI();
//        mainDao.addValue(object);
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO MedicalEquipmentSR(srID, equipmentID) VALUES(?, ?)");
            pstmt.setString(1, object.getSrID());
            pstmt.setString(2, object.getMedicalEquipment().getEquipmentID());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Insert Into MedicalEquipmentSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteValue(String objectID) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM MedicalEquipmentSR WHERE srID = ?");
            pstmt.setString(1, objectID);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Delete From MedicalEquipmentSR Using SR ID: Failed!");
            e.printStackTrace();
        }
        MainSRDaoI mainDao = new MainSRDaoI();
        mainDao.deleteValue(objectID);
    }

    @Override
    public void updateValue(MedicalEquipmentSR object) {

    }

    @Override
    public MedicalEquipmentSR getValue(String objectID) {
        MedicalEquipmentSR ret = null;
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM MedicalEquipmentSR WHERE srID = ?");
            pstmt.setString(1, objectID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();
            String equipmentID = rset.getString("equipmentID");

            MainSRDaoI mainDao = new MainSRDaoI();
            MainSR csr = (MainSR) mainDao.getValue(objectID);
            MedicalEquipment medEquip = (new MedicalEquipmentDaoI()).getValue(equipmentID);
            ret = new MedicalEquipmentSR(csr, medEquip);
        } catch (SQLException e) {
            System.out.println("Get MedicalEquipmentSR Failed");
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<MedicalEquipmentSR> getAllValues() {
        List<MedicalEquipmentSR> medEquipmentSRList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MedicalEquipmentSR");
            ResultSet rset = pstmt.executeQuery();

            String srID = "";
            String equipmentID = "";
            MainSRDaoI mainDao = new MainSRDaoI();
            while (rset.next()) {
                srID = rset.getString("srID");
                equipmentID = rset.getString("equipmentID");

                MainSR csr = (MainSR) mainDao.getValue(srID);
                medEquipmentSRList.add(
                        new MedicalEquipmentSR(csr, (new MedicalEquipmentDaoI()).getValue(equipmentID)));
            }
        } catch (SQLException e) {
            System.out.println("Get All MedicalEquipmentSR Nodes: Failed!");
            e.printStackTrace();
        }
        return medEquipmentSRList;
    }

    @Override
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
                                + "equipmentID VARCHAR(50), "
                                + "PRIMARY KEY (srID), "
                                + "CONSTRAINT FK_MedicalEquipmentSR_MainSR FOREIGN KEY (srID) REFERENCES MainSR (srID) )");
            }
        } catch (SQLException e) {
            System.out.println("Create MedicalEquipmentSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE MedicalEquipmentSR");
        } catch (SQLException e) {
            System.out.println("Drop MedicalEquipmentSR Table: Failed!");
        }
    }

    @Override
    public void restoreTable(List<MedicalEquipmentSR> list) {
        createTable();
        // For each iteration of location in the list of location
        for (MedicalEquipmentSR medEquipSR : list) {
            try {
                addValue(medEquipSR);
            } catch (Exception e) {
                System.out.println("Restore Medical Equipment Table Failed!");
            }
        }
    }
}
