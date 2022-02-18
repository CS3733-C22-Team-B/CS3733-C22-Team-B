package edu.wpi.cs3733.c22.teamB.entity.inheritance;

import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;

import java.util.List;

public interface ISpecificServiceRequest<T> extends IDatabase<T> {
    void addValue(T object);
    void deleteValue(String objectID);
    void updateValue(T object);
    T getValue(String objectID);
    List<T> getAllValues();
    void createTable();
    void dropTable();
    void restoreTable(List<T> list);
}
