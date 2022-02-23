package edu.wpi.cs3733.c22.teamB.entity.inheritance;

import java.net.UnknownHostException;
import java.util.List;

public interface IDatabase<T> {

    void addValue(T object);
    void deleteValue(String objectID);
    void updateValue(T object);
    T getValue(String objectID);
    List<T> getAllValues();
    void createTable();
    void dropTable();
    void restoreTable(List<T> list);
}
