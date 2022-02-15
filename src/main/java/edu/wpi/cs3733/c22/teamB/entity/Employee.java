package edu.wpi.cs3733.c22.teamB.entity;

public class Employee {

    private String employeeID;
    private String lastName;
    private String firstName;
    private String position; // Enum (IT, Doctor, Nurse, Ward Assistant, Building Manager)
    private int accessLevel;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;


    public Employee() {
        this.employeeID = null;
        this.lastName = null;
        this.firstName = null;
        this.position = null;
        this.accessLevel = -1;
        this.username = null;
        this.password = null;
        this.email = null;
        this.phoneNumber = null;
    }

    public Employee(String employeeID, String lastName, String firstName, String position, int accessLevel, String username, String password, String email, String phoneNumber) {
        this.employeeID = employeeID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.accessLevel = accessLevel;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFistName(String firstName) {
        this.firstName = firstName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fistName='" + firstName + '\'' +
                ", position='" + position + '\'' +
                ", accessLevel=" + accessLevel +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String toStringFields() {
        return employeeID
                + ","
                + lastName
                + ","
                + firstName
                + ","
                + position
                + ","
                + accessLevel
                + ","
                + username
                + ","
                + password
                + ","
                + email
                + ","
                + phoneNumber;
    }

    public static String toStringHeader() {
        return "employeeID"
                + ","
                + "lastName"
                + ","
                + "fistName"
                + ","
                + "position"
                + ","
                + "accessLevel"
                + ","
                + "username"
                + ","
                + "password"
                + ","
                + "email"
                + ","
                + "phoneNumber";
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}

