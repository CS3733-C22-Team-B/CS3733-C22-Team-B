package edu.wpi.cs3733.c22.teamB.entity;

import java.util.Objects;

public class MedicalEquipment {
    private String equipmentID;
    private String equipmentName;
    private String equipmentType;
    private String manufacturer;
    private Location location; // foreign key, connect to location table
    private String status;
    private String color;
    private String size;
    private String description;

    // constructors

    public MedicalEquipment() {
        this.equipmentID = null;
        this.equipmentName = null;
        this.equipmentType = null;
        this.manufacturer = null;
        this.location = null;
        this.status = null;
        this.color = null;
        this.size = null;
        this.description = null;
    }

    public MedicalEquipment(
            String equipmentID,
            String equipmentName,
            String equipmentType,
            String manufacturer,
            Location location,
            String status,
            String color,
            String size,
            String description) {

        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.manufacturer = manufacturer;
        this.location = location;
        this.status = status;
        this.color = color;
        this.size = size;
        this.description = description;
    }

    // getters and setters

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // to string

    @Override
    public String toString() {
        return "MedicalEquipment{"
                + "equipmentID='"
                + equipmentID
                + '\''
                + ", equipmentName='"
                + equipmentName
                + '\''
                + ", equipmentType='"
                + equipmentType
                + '\''
                + ", manufacturer='"
                + manufacturer
                + '\''
                + ", location="
                + location
                + ", status='"
                + status
                + '\''
                + ", color='"
                + color
                + '\''
                + ", size='"
                + size
                + '\''
                + ", description='"
                + description
                + '\''
                + '}';
    }

    public String toStringFields() {

        return equipmentID
                + ","
                + equipmentName
                + ","
                + equipmentType
                + ","
                + manufacturer
                + ","
                + location.getNodeID()
                + ","
                + status
                + ","
                + color
                + ","
                + size
                + ","
                + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalEquipment that = (MedicalEquipment) o;
        return equipmentID.equals(that.equipmentID)
                && equipmentName.equals(that.equipmentName)
                && equipmentType.equals(that.equipmentType)
                && manufacturer.equals(that.manufacturer)
                && location.equals(that.location)
                && status.equals(that.status)
                && color.equals(that.color)
                && size.equals(that.size)
                && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                equipmentID,
                equipmentName,
                equipmentType,
                manufacturer,
                location,
                status,
                color,
                size,
                description);
    }
}
