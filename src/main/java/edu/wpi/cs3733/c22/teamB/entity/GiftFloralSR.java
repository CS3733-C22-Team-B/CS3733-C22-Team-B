package edu.wpi.cs3733.c22.teamB.entity;

import java.time.LocalDate;
import java.util.List;

public class GiftFloralSR extends AbstractSR {

    List<GiftType> _listOfGifts;
    LocalDate _deliveryDate;
    String _deliveryFloor;
    String _deliveryRoom;

    public GiftFloralSR(
            String srID,
            String statusStr,
            List<GiftType> listOfGifts,
            LocalDate deliveryDate,
            String deliveryFloor,
            String deliveryRoom) {

        super(srID, statusStr);
        _listOfGifts = listOfGifts;
        _deliveryDate = deliveryDate;
        _deliveryFloor = deliveryFloor;
        _deliveryRoom = deliveryRoom;
    }
}
