package edu.wpi.cs3733.c22.teamB.oldEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalEquipmentDBI implements IDatabase<MedicalEquipment> {

    Connection conn;

    public MedicalEquipmentDBI() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void drop() {
        try {
            Statement stmt = conn.createStatement();

            stmt.execute("DROP TABLE MedicalEquipment");
        } catch (SQLException e) {
            System.out.println("Drop Medical Equipment Table: Failed!");
        }
    }

    public void createTable() {

        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "MEDICALEQUIPMENT", null);

            if (rset.next() && rset.getString(3).equals("MEDICALEQUIPMENT")) {
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute(
                        "CREATE TABLE MedicalEquipment( "
                                + "equipmentID VARCHAR(50), "
                                + "equipmentName VARCHAR(255), "
                                + "equipmentType VARCHAR(255), "
                                + "manufacturer VARCHAR(255), "
                                + "locationID VARCHAR(50), "
                                + "status VARCHAR(255), "
                                + "color VARCHAR(255), "
                                + "size VARCHAR(255), "
                                + "description VARCHAR(255),"
                                + "PRIMARY KEY (equipmentID),"
                                + "CONSTRAINT FK_MedicalEquipment_Location FOREIGN KEY (locationID) REFERENCES Location (nodeID) ON DELETE SET NULL)");
            }
        } catch (SQLException e) {
            System.out.println("Create MedicalEquipment Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void restore(List<MedicalEquipment> list) {

        try {
            createTable();
            //            Statement stmt = conn.createStatement();
            //
            //            stmt.execute(
            //                    "create table MedicalEquipment( "
            //                            + "equipmentID VARCHAR(50), "
            //                            + "equipmentName VARCHAR(255), "
            //                            + "equipmentType VARCHAR(255), "
            //                            + "manufacturer VARCHAR(255), "
            //                            + "locationID VARCHAR(50) REFERENCES Location (nodeID), "
            //                            + "status VARCHAR(255), "
            //                            + "color VARCHAR(255), "
            //                            + "size VARCHAR(255), "
            //                            + "description VARCHAR(255),"
            //                            + "PRIMARY KEY (equipmentID),"
            //                            + "CONSTRAINT FK_MedicalEquipment_Location FOREIGN KEY
            // (locationID) REFERENCES Location (nodeID) ON DELETE SET NULL)");

            // For each iteration of location in the list of location
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

                PreparedStatement pstmt =
                        conn.prepareStatement(
                                "INSERT INTO MedicalEquipment (equipmentID, equipmentName, equipmentType, manufacturer, locationID, status, color, size, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, equipmentID);
                pstmt.setString(2, equipmentName);
                pstmt.setString(3, equipmentType);
                pstmt.setString(4, manufacturer);
                pstmt.setString(5, locationID);
                pstmt.setString(6, status);
                pstmt.setString(7, color);
                pstmt.setString(8, size);
                pstmt.setString(9, description);

                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException e) {
            System.out.println("Restore Medical Equipment Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public List<MedicalEquipment> getAllNodes() {

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

                LocationDBI locationDBI = new LocationDBI();
                //                locationDBI.initconn("jdbc:derby:bDB;create=true","admin","admin");
                Location location = locationDBI.getNode(locationID);

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
                                description));
            }
        } catch (SQLException e) {
            System.out.println("Get All MedicalEquipment Nodes: SQL Failed!");
            e.printStackTrace();
        }
        return medEquipments;
    }

    @Override
    public MedicalEquipment getNode(String equipmentID) {
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

            LocationDBI locationDBI = new LocationDBI();
            //            locationDBI.initconn("jdbc:derby:bDB;create=true", "admin", "admin");
            Location location = locationDBI.getNode(locationID);

            medEquipment =
                    new MedicalEquipment(
                            equipmentID,
                            equipmentName,
                            equipmentType,
                            manufacturer,
                            location,
                            status,
                            color,
                            size,
                            description);

        } catch (SQLException e) {
            System.out.println("Get MedicalEquipment Node Failed");
            e.printStackTrace();
        }
        return medEquipment;
    }

    @Override
    public void deleteNode(String nodeID) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM MedicalEquipment WHERE equipmentID = ?");
            pstmt.setString(1, nodeID);
            if (isInTable(nodeID)) {
                pstmt.executeUpdate();
            }

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Delete From Medical Equipment Table Using Equipment ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateNode(MedicalEquipment newNode) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE MedicalEquipment SET equipmentName = ?, equipmentType = ?, manufacturer = ?, locationID = ?, status = ?, color = ?, size = ?, description = ? WHERE equipmentID = ? ");

            pstmt.setString(1, newNode.getEquipmentName());
            pstmt.setString(2, newNode.getEquipmentType());
            pstmt.setString(3, newNode.getManufacturer());
            pstmt.setString(4, newNode.getLocation().getNodeID());
            pstmt.setString(5, newNode.getStatus());
            pstmt.setString(6, newNode.getColor());
            pstmt.setString(7, newNode.getSize());
            pstmt.setString(8, newNode.getDescription());
            pstmt.setString(9, newNode.getEquipmentID());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update MedicalEquipment ID: Failed!");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void insertNode(MedicalEquipment node) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO MedicalEquipment(equipmentID, equipmentName, equipmentType, manufacturer, locationID, status, color, size, description) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, node.getEquipmentID());
            pstmt.setString(2, node.getEquipmentName());
            pstmt.setString(3, node.getEquipmentType());
            pstmt.setString(4, node.getManufacturer());
            pstmt.setString(5, node.getLocation().getNodeID());
            pstmt.setString(6, node.getStatus());
            pstmt.setString(7, node.getColor());
            pstmt.setString(8, node.getSize());
            pstmt.setString(9, node.getDescription());

            if (!isInTable(node.getEquipmentID())) {
                pstmt.executeUpdate();
            }

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into Medical Equipment Table Using Equipment ID: Failed!");
            e.printStackTrace();
        }
    }

    public boolean isInTable(String nodeID) { // check if there is a node with given ID in table
        boolean ans = false;
        try {
            // search for NodeID
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM MedicalEquipment WHERE EQUIPMENTID = ?");
            pstmt.setString(1, nodeID);
            ResultSet rs = pstmt.executeQuery();
            ans = rs.next(); // if any ids are found
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Search for MedicalEquipment ID Failed!");
            e.printStackTrace();
        }
        return ans;
    }
}
