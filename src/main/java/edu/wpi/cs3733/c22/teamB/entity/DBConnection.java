package edu.wpi.cs3733.c22.teamB.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection  {

    private static Connection conn;

    private DBConnection() {}

    public static Connection getConnection() {
        try {
            if (conn == null) {
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
                }
                System.out.println("Apache Derby JDBC Driver Registered!");

                try {

                    conn = DriverManager.getConnection("jdbc:derby:bDB;create=true", "admin", "admin");
                } catch (SQLException e) {
                    System.out.println("Connection Failed! Check output console");
                    e.printStackTrace();
                }
                System.out.println("Apache Derby JDBC Driver Connected!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /** Close the Apache Derby JDBC connection */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Close Connection: Failed!");
            e.printStackTrace();
        }
    }

}
