package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class ConnectionManager {

    private static Connection embeddedConnection;
    private static Connection clientConnection;
    private static boolean useClient = false;

    public ConnectionManager() throws SQLException {
        embeddedConnection = DriverManager.getConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        clientConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/BWDB;create=true", "admin", "admin");
    }

    public static Connection getConnection() {
        if (useClient) {
            return clientConnection;
        } else {
            return embeddedConnection;
        }
    }

    public static void setConnectionStrategy(boolean client) {
        useClient = client;
    }

    public void closeConnection() {
        try {
            if (useClient){
                clientConnection.close();
            } else {
                embeddedConnection.close();
            }
        } catch (SQLException e) {
            System.out.println("Close Connection: Failed!");
            e.printStackTrace();
        }
    }

}
