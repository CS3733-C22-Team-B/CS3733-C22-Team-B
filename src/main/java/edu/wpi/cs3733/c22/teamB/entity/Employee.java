package edu.wpi.cs3733.c22.teamB.entity;

public class Employee {
    private String employeeID;
    private String name;
    private String position;
    private String address;
    private String email;
    private String phoneNumber;

    // constructors

    public Employee() {
        this.employeeID = null;
        this.name = null;
        this.position = null;
        this.address = null;
        this.email = null;
        this.phoneNumber = null;
    }

    public Employee(
            String employeeID,
            String name,
            String position,
            String address,
            String email,
            String phoneNumber) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // getters and setters

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    // to string

    @Override
    public String toString() {
        return "Employee{"
                + "employeeID='"
                + employeeID
                + '\''
                + ", name='"
                + name
                + '\''
                + ", position='"
                + position
                + '\''
                + ", address='"
                + address
                + '\''
                + ", email='"
                + email
                + '\''
                + ", phoneNumber='"
                + phoneNumber
                + '\''
                + '}';
    }

    public String toStringFields() {
        return employeeID
                + ","
                + name
                + ","
                + position
                + ","
                + address
                + ","
                + email
                + ","
                + phoneNumber;
    }

    public static String toStringHeader() {
        return "employeeID"
                + ","
                + "name"
                + ","
                + "position"
                + ","
                + "address"
                + ","
                + "email"
                + ","
                + "phoneNumber";
    }
}
