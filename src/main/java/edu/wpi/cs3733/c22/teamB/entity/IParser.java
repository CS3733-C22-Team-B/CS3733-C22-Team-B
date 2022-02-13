package edu.wpi.cs3733.c22.teamB.entity;

import java.util.List;

public interface IParser<T> {

    T fromStringToObject(String string);

    List<T> fromStringsToObjects(List<String> listString);

    String fromObjectToString(T object);

    List<String> fromObjectsToStrings(List<T> listT);

}
