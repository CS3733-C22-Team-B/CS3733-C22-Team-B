package edu.wpi.cs3733.c22.teamB.oldEntity;

public class FoodDeliverySR extends AbstractSR {
    private String foodName;
    private String foodRecipientName;
    private Location destination;
    private Employee assignedEmployee;

    public FoodDeliverySR(
            String srID,
            String status,
            Location destination,
            String foodName,
            String foodRecipientName,
            Employee assignedEmployee) {
        super(srID, status);
        this.destination = destination;
        this.foodName = foodName;
        this.foodRecipientName = foodRecipientName;
        this.assignedEmployee = assignedEmployee;
    }

    public FoodDeliverySR() {
        super(null, null);
        this.destination = null;
        this.foodName = null;
        this.foodRecipientName = null;
        this.assignedEmployee = null;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public String foodName() {
        return foodName;
    }

    public String getFoodRecipientName() {
        return foodRecipientName;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public void setFoodRecipientName(String foodRecipientName) {
        this.foodRecipientName = foodRecipientName;
    }

    // to string

    @Override
    public String toString() {
        return "FoodDeliverySR{"
                + "srID=1"
                + srID
                + ", destination="
                + destination.getNodeID()
                + ", foodName="
                + foodName
                + ", foodRecipientName="
                + foodRecipientName
                + ", assignedEmployee="
                + assignedEmployee.getEmployeeID()
                + '}';
    }

    public String toStringFields() {
        return srID
                + ","
                + status
                + ","
                + destination.getNodeID()
                + ","
                + foodName
                + ","
                + foodRecipientName
                + ","
                + assignedEmployee.getEmployeeID();
    }

    public static String toStringHeader() {
        return "srID,status,locationID,foodName,foodRecipientName,employeeID";
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
