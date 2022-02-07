package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;

public class GiftFloralSRDBI extends AbstractDatabaseI<GiftFloralSR> {
    private List<GiftFloralSR> db = new ArrayList<>();

    @Override
    public List<GiftFloralSR> getAllNodes() {
        return null;
    }

    @Override
    public GiftFloralSR getNode(String nodeID) {
        return null;
    }

    @Override
    public void deleteNode(String nodeID) {}

    @Override
    public void updateNode(GiftFloralSR node) {}

    @Override
    public void insertNode(GiftFloralSR node) {
        db.add(node);
        System.out.println(db);
    }

    @Override
    public void restore(List<GiftFloralSR> list) {}
}
