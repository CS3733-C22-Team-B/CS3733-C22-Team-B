package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDaoI implements IDatabase<Location> {

    public LocationDaoI() {
    }

    @Override
    public void dropTable() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE LOCATION");
        } catch (SQLException e) {
            System.out.println("Drop Location Table: Failed!");
        }
    }

    public void createTable() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "LOCATION", null);

            if (rset.next() && rset.getString(3).equals("LOCATION")){
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE Location ( "
                        + "nodeID VARCHAR(50) , "
                        + "xcoord int not null, "
                        + "ycoord int not null, "
                        + "floor VARCHAR(2), "
                        + "building VARCHAR(50), "
                        + "nodeType VARCHAR(50), "
                        + "longName VARCHAR(50), "
                        + "shortName VARCHAR(50),"
                        + "PRIMARY KEY (nodeID))");
            }
        } catch (SQLException e) {
            System.out.println("Create Location Table: Failed!");
            e.printStackTrace();
        }
    }


    public void restoreTable(List<Location> list) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            createTable();

            // For each iteration of location in the list of location
            for (Location location : list) {

                // Get all the parameter information
                String nodeID = location.getNodeID();
                int xcoord = location.getXcoord();
                int ycoord = location.getYcoord();
                String floor = location.getFloor();
                String building = location.getBuilding();
                String nodeType = location.getNodeType();
                String longName = location.getLongName();
                String shortName = location.getShortName();

                PreparedStatement pstmt =
                        conn.prepareStatement(
                                "INSERT INTO Location (nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, nodeID);
                pstmt.setInt(2, xcoord);
                pstmt.setInt(3, ycoord);
                pstmt.setString(4, floor);
                pstmt.setString(5, building);
                pstmt.setString(6, nodeType);
                pstmt.setString(7, longName);
                pstmt.setString(8, shortName);

                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException e) {
            System.out.println("Restore Location Table: Failed!");
            e.printStackTrace();
        }
    }

    public List<Location> getAllValues() {
        Connection conn = ConnectionManager.getInstance().getConnection();

        List<Location> locations = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Location");
            ResultSet rset = pstmt.executeQuery();

            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";

            while (rset.next()) {
                nodeID = rset.getString("nodeID");
                xcoord = rset.getInt("xcoord");
                ycoord = rset.getInt("ycoord");
                floor = rset.getString("floor");
                building = rset.getString("building");
                nodeType = rset.getString("nodeType");
                longName = rset.getString("longName");
                shortName = rset.getString("shortname");

                locations.add(
                        new Location(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName));
            }
        } catch (SQLException e) {
            System.out.println("Get All Location Nodes: SQL Failed!");
            e.printStackTrace();
        }
        return locations;
    }


    public Location getValue(String nodeID) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Location location = new Location();
        try {PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Location WHERE nodeID = ?");
            pstmt.setString(1, nodeID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();
            int xcoord = rset.getInt("xcoord");
            int ycoord = rset.getInt("ycoord");
            String floor = rset.getString("floor");
            String building = rset.getString("building");
            String nodeType = rset.getString("nodeType");
            String longName = rset.getString("longName");
            String shortName = rset.getString("shortname");

            location =
                    new Location(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName);

        } catch (SQLException e) {
            System.out.println("Get Location Node: Failed!");
            e.printStackTrace();
        }
        return location;
    }

    public void deleteValue(String nodeID) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement("DELETE FROM Location WHERE nodeID = ?");
            pstmt.setString(1, nodeID);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Delete From Table Using Node ID: Failed!");
            e.printStackTrace();
        }
    }

    public void updateValue(Location node) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE Location SET xcoord = ?, ycoord = ?, floor = ?, building = ?, nodeType = ?, longName = ?, shortName = ? WHERE nodeID = ?");
            pstmt.setInt(1, node.getXcoord());
            pstmt.setInt(2, node.getYcoord());
            pstmt.setString(3, node.getFloor());
            pstmt.setString(4, node.getBuilding());
            pstmt.setString(5, node.getNodeType());
            pstmt.setString(6, node.getLongName());
            pstmt.setString(7, node.getShortName());
            pstmt.setString(8, node.getNodeID());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update Node ID: Failed!");
            e.printStackTrace();
            return;
        }
    }

    public void addValue(Location node) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO Location(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName) VALUES( ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, node.getNodeID());
            pstmt.setInt(2, node.getXcoord());
            pstmt.setInt(3, node.getYcoord());
            pstmt.setString(4, node.getFloor());
            pstmt.setString(5, node.getBuilding());
            pstmt.setString(6, node.getNodeType());
            pstmt.setString(7, node.getLongName());
            pstmt.setString(8, node.getShortName());

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into Table Using Node ID: Failed!");
            e.printStackTrace();
        }


    }

    // Cleanup for Iteration 3
    public boolean isFirstRestore() {
        boolean first = true;
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            DatabaseMetaData dbmd = conn.getMetaData();
//            ResultSet rset = dbmd.getTables(null, "ADMIN", "LOCATION", null);
            ResultSet rset = dbmd.getSchemas(null, "ADMIN");

            if (rset.next() && rset.getString(1).equals("ADMIN")){
                // table exists
                first = false;
            }
            else
                first = true;

        } catch (SQLException e) {
            System.out.println("First Restore check: Failed!");
            e.printStackTrace();
        }

        return first;
    }

    // Cleanup for Iteration 3
    public boolean isInTable(String nodeID) {    //check if there is a node with given ID in table
        boolean ans = false;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            //search for NodeID
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM Location WHERE nodeID = ?");
            pstmt.setString(1, nodeID);
            ResultSet rs = pstmt.executeQuery();
            ans = rs.next();    //if any ids are found
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Search for NodeID Failed!");
            e.printStackTrace();
        }
        return ans;
    }

    // Cleanup for Iteration 3
    public int nodeTypeCount(String nodeType, String floor) {

        List<Location> ans = getAllValues();
        int count = 0;
        for (Location location : ans) {
            if ((location.getNodeType().equals(nodeType)) && (location.getFloor().equals(floor))) {
                count += 1;
            }
        }
        return count;
    }
}
