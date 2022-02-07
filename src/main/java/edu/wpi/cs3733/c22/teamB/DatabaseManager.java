package edu.wpi.cs3733.c22.teamB;

import edu.wpi.cs3733.c22.teamB.entity.Location;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private Connection connection;

    public DatabaseManager(String url, String userID, String password) {
        initConnection(url, userID, password);
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Exit: Failed!");
            e.printStackTrace();
        }
    }

    public void initConnection(String url, String userID, String password) {

        System.out.println("-------Apache Derby JDBC Connection Testing ---------");
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        } catch (ClassNotFoundException e) {
            System.out.println("Apache Derby Driver not found. Add the classpath to your module.");
            System.out.println("For IntelliJ do the following:");
            System.out.println("File | Project Structure, Modules, Dependency tab");
            System.out.println("Add by clicking on the green plus icon on the right of the window");
            System.out.println(
                    "Select JARs or directories. Go to the folder where the database JAR is located");
            System.out.println("Click OK, now you can compile your program and run it.");
            e.printStackTrace();
            return;
        }

        System.out.println("Apache Derby JDBC Driver Registered!");

        try {
            connection = DriverManager.getConnection(url, userID, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        System.out.println("Apache Derby JDBC Driver Connected!");
    }

    public void populateDatabase(List<Location> list) {

        try {
            Statement stmt = connection.createStatement();

            stmt.execute("drop table Location");
        } catch (SQLException e) {
            System.out.println("List Node Info: Failed!");
        }

        try {
            Statement stmt = connection.createStatement();

            stmt.execute(
                    "create table Location( "
                            + "nodeID VARCHAR(50) Primary Key, "
                            + "xcoord int not null, "
                            + "ycoord int not null, "
                            + "floor VARCHAR(2), "
                            + "building VARCHAR(50), "
                            + "nodeType VARCHAR(50), "
                            + "longName VARCHAR(50), "
                            + "shortName VARCHAR(50) )");

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
                        connection.prepareStatement(
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
            System.out.println("List Node Info: Failed!");
            e.printStackTrace();
        }
    }

    public String getNodesDisplay() {
        String str = "";
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Location");
            ResultSet rset = pstmt.executeQuery();

            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";

            System.out.println("Node Information\n");

            // Process the results
            while (rset.next()) {
                nodeID = rset.getString("nodeID");
                xcoord = rset.getInt("xcoord");
                ycoord = rset.getInt("ycoord");
                floor = rset.getString("floor");
                building = rset.getString("building");
                nodeType = rset.getString("nodeType");
                longName = rset.getString("longName");
                shortName = rset.getString("shortName");

                str =
                        str
                                + "Node ID: "
                                + nodeID
                                + " X-Coordinate: "
                                + xcoord
                                + " Y-Coordinate: "
                                + ycoord
                                + " Floor: "
                                + floor
                                + " building: "
                                + building
                                + " nodeType: "
                                + nodeType
                                + " longName: "
                                + longName
                                + " shortName: "
                                + shortName
                                + "\n";
            } // end while

            rset.close();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Report Location Information: Failed!");
            e.printStackTrace();
        }

        return str;
    }

    public void updateNodeLocation(int x, int y, String input) {
        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(
                            "UPDATE Location SET xcoord = ?, ycoord = ? WHERE nodeID = ?");
            pstmt.setInt(1, x);
            pstmt.setInt(2, y);
            pstmt.setString(3, input);
            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Update Node ID: Failed!");
            e.printStackTrace();
            return;
        }
    }

    public void insertNode(
            String nodeInput,
            int xcoordint,
            int ycoordint,
            String floorInput,
            String buildingInput,
            String nodeTypeInput,
            String longInput,
            String shortInput) {
        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(
                            "INSERT INTO Location(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName) VALUES( ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, nodeInput);
            pstmt.setInt(2, xcoordint);
            pstmt.setInt(3, ycoordint);
            pstmt.setString(4, floorInput);
            pstmt.setString(5, buildingInput);
            pstmt.setString(6, nodeTypeInput);
            pstmt.setString(7, longInput);
            pstmt.setString(8, shortInput);

            pstmt.executeUpdate();
            pstmt.close();

            // Add the new object to the list
            //            list.add(
            //                    new Location(
            //                            nodeInput,
            //                            xcoordint,
            //                            ycoordint,
            //                            floorInput,
            //                            buildingInput,
            //                            nodeTypeInput,
            //                            longInput,
            //                            shortInput));

        } catch (SQLException e) {
            System.out.println("Insert Into Table Using Node ID: Failed!");
            e.printStackTrace();
            return;
        }
    }

    public void deleteNode(String nodeInput) {
        try {
            PreparedStatement pstmt =
                    connection.prepareStatement("DELETE FROM Location WHERE nodeID = ?");
            pstmt.setString(1, nodeInput);
            pstmt.executeUpdate();

            pstmt.close();

            //            // Remove that specific Location object from the list of objects
            //            for (Location x : list) {
            //                if (x.getNodeID() == nodeInput1) {
            //                    list.remove(x);
            //                }
            //            }

        } catch (SQLException e) {
            System.out.println("Delete From Table Using Node ID: Failed!");
            e.printStackTrace();
            return;
        }
    }

    public List getNodesCollection() {

        List<Location> newList = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Location");
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

                newList.add(
                        new Location(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName));
            }

        } catch (SQLException e) {
            System.out.println("New CSV file: SQL Failed!");
            e.printStackTrace();
        }

        return newList;
    }
}
