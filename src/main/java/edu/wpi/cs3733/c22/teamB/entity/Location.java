package edu.wpi.cs3733.c22.teamB.entity;

import java.util.Objects;

public class Location {

    private String nodeID; // primary key
    private int xcoord;
    private int ycoord;
    private String floor;
    private String building;
    private String nodeType;
    private String longName;
    private String shortName;

    public Location() {
        this.nodeID = null;
        this.xcoord = 0;
        this.ycoord = 0;
        this.floor = null;
        this.building = null;
        this.nodeType = null;
        this.longName = null;
        this.shortName = null;
    }

    public Location(
            String nodeID,
            int xcoord,
            int ycoord,
            String floor,
            String building,
            String nodeType,
            String longName,
            String shortName) {
        this.nodeID = nodeID;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.floor = floor;
        this.building = building;
        this.nodeType = nodeType;
        this.longName = longName;
        this.shortName = shortName;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Location{"
                + "nodeID='"
                + nodeID
                + '\''
                + ", xcoord="
                + xcoord
                + ", ycoord="
                + ycoord
                + ", floor='"
                + floor
                + '\''
                + ", building='"
                + building
                + '\''
                + ", nodeType='"
                + nodeType
                + '\''
                + ", longName='"
                + longName
                + '\''
                + ", shortName='"
                + shortName
                + '\''
                + '}';
    }

    public String toStringFields() {
        return nodeID + "," + xcoord + "," + ycoord + "," + floor + "," + building + "," + nodeType
                + "," + longName + "," + shortName;
    }

    public static String toStringHeader() {
        return "nodeID"
                + ",xcoord"
                + ",ycoord"
                + ",floor"
                + ",building"
                + ",nodeType"
                + ",longName"
                + ",shortName";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return xcoord == location.xcoord && ycoord == location.ycoord && Objects.equals(nodeID, location.nodeID) && Objects.equals(floor, location.floor) && Objects.equals(building, location.building) && Objects.equals(nodeType, location.nodeType) && Objects.equals(longName, location.longName) && Objects.equals(shortName, location.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName);
    }

}
