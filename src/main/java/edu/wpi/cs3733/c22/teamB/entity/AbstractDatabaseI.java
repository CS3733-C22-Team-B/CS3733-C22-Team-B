package edu.wpi.cs3733.c22.teamB.entity;

abstract class AbstractDatabaseI<T> implements IDatabase<T> {
//
//    public Connection connection;
//
//    /**
//    * Initialize the Apache Derby JDBC connection
//    *
//    * @param url the url for apache derby jdbc embedded driver
//    * @param userID the username used to access the database
//    * @param password the password used to access the database
//    */
//    public void initConnection(String url, String userID, String password) {
//
//        System.out.println("-------Apache Derby JDBC Connection Testing ---------");
//        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("Apache Derby Driver not found. Add the classpath to your module.");
//            System.out.println("For IntelliJ do the following:");
//            System.out.println("File | Project Structure, Modules, Dependency tab");
//            System.out.println("Add by clicking on the green plus icon on the right of the window");
//            System.out.println(
//                    "Select JARs or directories. Go to the folder where the database JAR is located");
//            System.out.println("Click OK, now you can compile your program and run it.");
//            e.printStackTrace();
//            return;
//        }
//
//        System.out.println("Apache Derby JDBC Driver Registered!");
//
//        try {
//            connection = DriverManager.getConnection(url, userID, password);
//        } catch (SQLException e) {
//            System.out.println("Connection Failed! Check output console");
//            e.printStackTrace();
//            return;
//        }
//        System.out.println("Apache Derby JDBC Driver Connected!");
//    }
//
//    /** Close the Apache Derby JDBC connection */
//    public void closeConnection() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Exit: Failed!");
//            e.printStackTrace();
//        }
//    }
}
