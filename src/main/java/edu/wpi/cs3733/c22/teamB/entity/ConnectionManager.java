package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionManager {
    private static ConnectionManager conn;
    private Connection embeddedConnection;
    private Connection clientConnection;
    private boolean useClient = false;

    private ConnectionManager() {
        try {
            embeddedConnection = DriverManager.getConnection("jdbc:derby:bDB;create=true", "admin", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            clientConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/BWDB;create=true", "admin", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance(){
        if (conn == null){
            conn = new ConnectionManager();
        }
        return conn;
    }

    public Connection getConnection() {
        if (useClient) {
            System.out.println("Initializing Client!");
            return clientConnection;
        } else {
            return embeddedConnection;
        }
    }

    public void setConnectionStrategy(boolean client) {
        this.useClient = client;
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
