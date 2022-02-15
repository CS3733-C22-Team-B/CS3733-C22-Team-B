package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class ConnectionManager {

    private static Connection embeddedConnection;
    private static Connection clientConnection;
    private boolean useRemote = false;

    public ConnectionManager() throws SQLException {
        this.embeddedConnection = DriverManager.getConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        this.clientConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/BWDB;create=true", "admin", "admin");
    }

    public Connection getConnection() {
        if (useRemote) {
            return clientConnection;
        } else {
            return embeddedConnection;
        }
    }

    public void setConnectionStrategy(boolean remote) {
        this.useRemote = remote;
    }

    public void closeConnection() {
        try {
            if (useRemote){
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
