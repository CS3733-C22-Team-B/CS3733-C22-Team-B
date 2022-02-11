package edu.wpi.cs3733.c22.teamB.entity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MedicalEquipmentSRDaoI implements ISpecificServiceRequest<MedicalEquipmentSR> {

    Connection conn;

    public MedicalEquipmentSRDaoI() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void addValue(MedicalEquipmentSR object) {
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "INSERT INTO MedicalEquipmentSR(srID, status, locationID, equipmentID, employeeID) VALUES(?, ?, ?, ?, ?)");
            pstmt.setString(1, object.getSrID());
            pstmt.setString(2, object.getStatusString());
            pstmt.setString(3, object.getDestination().getNodeID());
            pstmt.setString(4, object.getMedicalEquipment().getEquipmentID());
            pstmt.setString(5, object.getAssignedEmployee().getEmployeeID());

            if (!isInTable(object.getSrID())) {
                pstmt.executeUpdate();
            }
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into MedicalEquipmentSR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteValue(String objectID) {

    }

    @Override
    public void updateValue(MedicalEquipmentSR object) {

    }

    @Override
    public MedicalEquipmentSR getValue(String objectID) {
        return null;
    }

    @Override
    public List<MedicalEquipmentSR> getAllValues() {
        return null;
    }

    @Override
    public void createTable() {

    }

    @Override
    public void dropTable() {

    }

    @Override
    public void restoreTable() {

    }
}
