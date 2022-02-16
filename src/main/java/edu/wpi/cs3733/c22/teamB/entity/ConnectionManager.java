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
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            try {
                embeddedConnection = DriverManager.getConnection("jdbc:derby:bDB;create=true", "admin", "admin");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Embedded Apache Derby Driver not found. Add the classpath to your module.");
            System.out.println("For IntelliJ do the following:");
            System.out.println("File | Project Structure, Modules, Dependency tab");
            System.out.println("Add by clicking on the green plus icon on the right of the window");
            System.out.println("Select JARs or directories. Go to the folder where the database JAR is located");
            System.out.println("Click OK, now you can compile your program and run it.");
            e.printStackTrace();
        }
        System.out.println("Embedded Apache Derby JDBC Driver Registered!");

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            try{
                clientConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/BWDB;create=true", "admin", "admin");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Client Apache Derby Driver not found. Add the classpath to your module.");
            System.out.println("For IntelliJ do the following:");
            System.out.println("File | Project Structure, Modules, Dependency tab");
            System.out.println("Add by clicking on the green plus icon on the right of the window");
            System.out.println("Select JARs or directories. Go to the folder where the database JAR is located");
            System.out.println("Click OK, now you can compile your program and run it.");
            e.printStackTrace();
        }
        System.out.println("Client Apache Derby JDBC Driver Registered!");
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
            return this.clientConnection;
        } else {
            return this.embeddedConnection;
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
