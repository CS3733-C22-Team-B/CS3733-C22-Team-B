package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;
import java.util.Objects;

public class FoodDeliverySR extends AbstractSR{

    private String foodName;
    private String drinkName;


    public FoodDeliverySR() {
        super(null, "FoodDeliverySR", null, null, null, null, null, null);
        this.foodName = null;
        this.drinkName = null;
    }

    public FoodDeliverySR(String srID, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes, String foodName, String drinkName) {
        super(srID, "FoodDeliverySR", status, location, requestor, assignedEmployee, dateRequested, notes);
        this.foodName = foodName;
        this.drinkName = drinkName;
    }

    public FoodDeliverySR(AbstractSR csr, String foodName, String drinkName){
        super(csr);
        this.setSrType("FoodDeliverySR");
        this.foodName = foodName;
        this.drinkName = drinkName;
    }
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    @Override
    public String toString() {
        return "FoodDeliverySR{" +
                "srID= " + getSrID() + '\'' +
                "srType= " + getSrType() + '\'' +
                "status= " + getStatus() + '\'' +
                "location= " + getLocation() + '\'' +
                "requestor= " + getRequestor() + '\'' +
                "assignedEmployee= " + getAssignedEmployee() + '\'' +
                "dateRequested= " + getDateRequested() + '\'' +
                "notes= " + getNotes() + '\'' +
                "foodName='" + foodName + '\'' +
                ", drinkName='" + drinkName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodDeliverySR that = (FoodDeliverySR) o;
        return Objects.equals(foodName, that.foodName) && Objects.equals(drinkName, that.drinkName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName, drinkName);
    }
}
