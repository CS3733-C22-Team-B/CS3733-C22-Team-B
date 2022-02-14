package edu.wpi.cs3733.c22.teamB.oldEntity;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LaundrySRDBI implements IDatabase<LaundrySR> {

    //    final private List<LaundrySR> db;
    private final Map<String, LaundrySR> dbMap;
    // for testing only, not real design
    private int internalID = 1;

    public LaundrySRDBI() {
        //        this.db = new ArrayList<>();
        this.dbMap = new HashMap<String, LaundrySR>();
    }

    @Override
    public void drop() {}

    public void add(LaundrySR sr) {
        if (dbMap.containsKey(sr.getSrID()))
            throw new RuntimeException("The LaundrySR " + sr.getSrID() + " is already in the database.");
        dbMap.put(sr.getSrID(), sr);
        System.out.println(toString());
    }

    public void add(String location, String emp, String status) {
        this.add(new LaundrySR(Integer.toString(internalID++), location, emp));
    }

    public ObservableList<String> getRooms() {
        ObservableList<String> locations = FXCollections.observableArrayList();
        LaundrySR[] allSRs = dbMap.values().toArray(new LaundrySR[0]);
        int length = allSRs.length;
        {
            for (int i = 0; i < length; i++) {
                locations.add(allSRs[i].getLocation());
            }
        }

        return locations;
    }

    @Override
    public List<LaundrySR> getAllNodes() {
        List<LaundrySR> list = null; // Arrays.asList(dbMap.values().toArray());
        return list;
    }

    @Override
    public LaundrySR getNode(String str) {
        return dbMap.get(str);
    }

    @Override
    public void deleteNode(String str) {
        dbMap.remove(str);
    }

    @Override
    public void updateNode(LaundrySR node) {
        // to be done
    }

    @Override
    public void insertNode(LaundrySR node) {
        // to be done
    }

    @Override
    public void restore(List<LaundrySR> list) {
        // to be done
    }
}
