package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalEquipmentDaoI implements IDatabase<MedicalEquipment> {

    private Connection conn;

    public MedicalEquipmentDaoI() {
        this.conn = ConnectionManager.getInstance().getConnection();
    }

    @Override
    public void addValue(MedicalEquipment equipment) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO MEDICALEQUIPMENT(EQUIPMENTID, EQUIPMENTNAME, EQUIPMENTTYPE, MANUFACTURER, locationID, status, COLOR, SIZE, DESCRIPTION, amount) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, equipment.getEquipmentID());
            pstmt.setString(2, equipment.getEquipmentName());
            pstmt.setString(3, equipment.getEquipmentType());
            pstmt.setString(4, equipment.getManufacturer());
            pstmt.setString(5, equipment.getLocation().getNodeID());
            pstmt.setString(6, equipment.getStatus());
            pstmt.setString(7, equipment.getColor());
            pstmt.setString(8, equipment.getSize());
            pstmt.setString(9, equipment.getDescription());
            pstmt.setInt(10, equipment.getAmount());


            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into Employee Table Using Employee ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteValue(String equipmentID) {

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM MedicalEquipment WHERE equipmentID = ?");
            pstmt.setString(1, equipmentID);

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Delete From Medical Equipment Table Using Equipment ID: Failed!");
            e.printStackTrace();
        }
    }


    @Override
    public void updateValue(MedicalEquipment equipment) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE MEDICALEQUIPMENT SET equipmentName = ?, equipmentType = ?, manufacturer = ?, locationID = ?, status = ?, color = ?, size = ?, description = ?, amount = ?WHERE equipmentID = ? ");

            pstmt.setString(1, equipment.getEquipmentName());
            pstmt.setString(2, equipment.getEquipmentType());
            pstmt.setString(3, equipment.getManufacturer());
            pstmt.setString(4, equipment.getLocation().getNodeID());
            pstmt.setString(5, equipment.getStatus());
            pstmt.setString(6, equipment.getColor());
            pstmt.setString(7, equipment.getSize());
            pstmt.setString(8, equipment.getDescription());
            pstmt.setString(10, equipment.getEquipmentID());
            pstmt.setInt(9, equipment.getAmount());
            System.out.println(equipment.getLocation().getNodeID());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update MedicalEquipment ID: Failed!");
            e.printStackTrace();
            return;
        }

    }

    @Override
    public MedicalEquipment getValue(String equipmentID) {
        MedicalEquipment medEquipment = new MedicalEquipment();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM MedicalEquipment WHERE equipmentID = ?");
            pstmt.setString(1, equipmentID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();
            String equipmentName = rset.getString("equipmentName");
            String equipmentType = rset.getString("equipmentType");
            String manufacturer = rset.getString("manufacturer");
            String locationID = rset.getString("locationID");
            String status = rset.getString("status");
            String color = rset.getString("color");
            String size = rset.getString("size");
            String description = rset.getString("description");
            int amount = rset.getInt("amount");

            LocationDaoI locationDao = new LocationDaoI();
            Location location = locationDao.getValue(locationID);

            medEquipment = new MedicalEquipment(
                    equipmentID,
                    equipmentName,
                    equipmentType,
                    manufacturer,
                    location,
                    status,
                    color,
                    size,
                    description,
                    amount);

        } catch (SQLException e) {
            System.out.println("Get MedicalEquipment Node Failed");
            e.printStackTrace();
        }
        return medEquipment;
    }

    @Override
    public List<MedicalEquipment> getAllValues() {
        List<MedicalEquipment> medEquipments = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MedicalEquipment");
            ResultSet rset = pstmt.executeQuery();

            String equipmentID = "";
            String equipmentName = "";
            String equipmentType = "";
            String manufacturer = "";
            String locationID = "";
            String status = "";
            String color = "";
            String size = "";
            String description = "";
            int amount = 0;

            while (rset.next()) {
                equipmentID = rset.getString("equipmentID");
                equipmentName = rset.getString("equipmentName");
                equipmentType = rset.getString("equipmentType");
                manufacturer = rset.getString("manufacturer");
                locationID = rset.getString("locationID");
                status = rset.getString("status");
                color = rset.getString("color");
                size = rset.getString("size");
                description = rset.getString("description");
                amount = rset.getInt("amount");

                LocationDaoI locationDao = new LocationDaoI();
                Location location = locationDao.getValue(locationID);

                medEquipments.add(
                        new MedicalEquipment(
                                equipmentID,
                                equipmentName,
                                equipmentType,
                                manufacturer,
                                location,
                                status,
                                color,
                                size,
                                description,
                                amount));
            }
        } catch (SQLException e) {
            System.out.println("Get All MedicalEquipment Nodes: SQL Failed!");
            e.printStackTrace();
        }
        return medEquipments;
    }

    @Override
    public void createTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "MEDICALEQUIPMENT", null);

            if (rset.next() && rset.getString(3).equals("MEDICALEQUIPMENT")) {
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE MedicalEquipment( "
                        + "equipmentID VARCHAR(50), "
                        + "equipmentName VARCHAR(255), "
                        + "equipmentType VARCHAR(255), "
                        + "manufacturer VARCHAR(255), "
                        + "locationID VARCHAR(50), "
                        + "status VARCHAR(255), "
                        + "color VARCHAR(255), "
                        + "size VARCHAR(255), "
                        + "description VARCHAR(255),"
                        + "amount int not null,"
                        + "PRIMARY KEY (equipmentID),"
                        + "CONSTRAINT FK_MedicalEquipment_Location FOREIGN KEY (locationID) REFERENCES Location (nodeID) ON DELETE SET NULL)");
            }
        } catch (SQLException e) {
            System.out.println("Create MedicalEquipment Table: Failed!");
            e.printStackTrace();
        }

    }

    @Override
    public void dropTable() {
        try {
            Statement stmt = conn.createStatement();

            stmt.execute("DROP TABLE MedicalEquipment");
        } catch (SQLException e) {
            System.out.println("Drop Medical Equipment Table: Failed!");
        }

    }

    @Override
    public void restoreTable(List<MedicalEquipment> list) {

        try {
            createTable();

            for (MedicalEquipment medEquipment : list) {

                // Get all the parameter information
                String equipmentID = medEquipment.getEquipmentID();
                String equipmentName = medEquipment.getEquipmentName();
                String equipmentType = medEquipment.getEquipmentType();
                String manufacturer = medEquipment.getManufacturer();
                String locationID = medEquipment.getLocation().getNodeID();
                String status = medEquipment.getStatus();
                String color = medEquipment.getColor();
                String size = medEquipment.getSize();
                String description = medEquipment.getDescription();
                int amount = medEquipment.getAmount();

                PreparedStatement pstmt =
                        conn.prepareStatement(
                                "INSERT INTO MedicalEquipment (equipmentID, equipmentName, equipmentType, manufacturer, locationID, status, color, size, description, amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, equipmentID);
                pstmt.setString(2, equipmentName);
                pstmt.setString(3, equipmentType);
                pstmt.setString(4, manufacturer);
                pstmt.setString(5, locationID);
                pstmt.setString(6, status);
                pstmt.setString(7, color);
                pstmt.setString(8, size);
                pstmt.setString(9, description);
                pstmt.setInt(10, amount);

                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException e) {
            System.out.println("Restore Medical Equipment Table: Failed!");
            e.printStackTrace();
        }
    }
}
