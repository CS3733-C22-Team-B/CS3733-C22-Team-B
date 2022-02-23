package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MedicineDeliverySR;

import java.time.LocalDate;
import java.util.List;

public class MedicineDeliverySRMongo implements IDatabase<MedicineDeliverySR> {
    
    private DB conn;
    private DBCollection MedicineDeliverySRTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public MedicineDeliverySRMongo(IDatabase<AbstractSR> mainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = mainSRMongo;
    }
    
    public static DBObject convertMedicineDeliverySR(MedicineDeliverySR sr) {
        BasicDBObject document = new BasicDBObject();
        document.put("_id", sr.getSrID());
        document.put("medicineID", sr.getMedicineID());
        document.put("patientID", sr.getPatientID());

        return document;
    }

    @Override
    public void addValue(MedicineDeliverySR object) {
        conn.getCollection("MedicineDeliverySR").insert(convertMedicineDeliverySR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        MedicineDeliverySRTable.remove(convertMedicineDeliverySR(getValue(objectID)));
    }

    @Override
    public void updateValue(MedicineDeliverySR object) {
        DBObject query = new BasicDBObject("_id", object.getSrID());
        MedicineDeliverySRTable.findAndModify(query, convertMedicineDeliverySR(object));
    }

    @Override
    public MedicineDeliverySR getValue(String objectID) {
        MedicineDeliverySR medicineDeliverySR;

        DBObject query = new BasicDBObject("_id", objectID);
        DBCursor cursor = MedicineDeliverySRTable.find(query);

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
//        String srType
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();

        BasicDBObject foodObj = (BasicDBObject) cursor.one();
        String srID = foodObj.getString("_id");
        String medicineID = foodObj.getString("medicineID");
        String patientID = foodObj.getString("patientID");

        medicineDeliverySR = new MedicineDeliverySR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, medicineID, patientID);

        return medicineDeliverySR;    }

    @Override
    public List<MedicineDeliverySR> getAllValues() {
        return null;
    }

    @Override
    public void createTable() {
        MedicineDeliverySRTable = conn.getCollection("MedicineDeliverySR");
    }

    @Override
    public void dropTable() {
        conn.getCollection("MedicineDeliverySR").drop();
    }

    @Override
    public void restoreTable(List<MedicineDeliverySR> list) {
        createTable();
        for(MedicineDeliverySR medicineDeliverySR : list) {
            addValue(medicineDeliverySR);
        }
    }
}
