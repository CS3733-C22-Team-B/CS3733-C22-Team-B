package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;
import java.util.Objects;

public class FoodDeliverySR extends AbstractSR{

    private String foodName;
    private String drinkName;


    public FoodDeliverySR() {
        super(null, null, null, null, null, null, null, null);
        this.foodName = null;
        this.drinkName = null;
    }

    public FoodDeliverySR(String srID, String srType, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes, String foodName, String frinkName) {
        super(srID, srType, status, location, requestor, assignedEmployee, dateRequested, notes);
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
