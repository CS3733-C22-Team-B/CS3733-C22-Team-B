package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MedicineDeliverySR;
import org.bson.Document;

import java.time.LocalDate;
import java.util.List;

public class MedicineDeliverySRMongo implements IDatabase<MedicineDeliverySR> {
    
    private MongoDatabase conn;
    private MongoCollection MedicineDeliverySRTable;
    private IDatabase<AbstractSR> MainSRMongo;

    public MedicineDeliverySRMongo(IDatabase<AbstractSR> mainSRMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = mainSRMongo;
    }
    
    public static Document convertMedicineDeliverySR(MedicineDeliverySR sr) {
        Document document = new Document();
        document.put("_id", sr.getSrID());
        document.put("medicineID", sr.getMedicineID());
        document.put("patientID", sr.getPatientID());

        return document;
    }

    @Override
    public void addValue(MedicineDeliverySR object) {
        conn.getCollection("MedicineDeliverySR").insertOne(convertMedicineDeliverySR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        MedicineDeliverySRTable.deleteOne(convertMedicineDeliverySR(getValue(objectID)));
    }

    @Override
    public void updateValue(MedicineDeliverySR object) {
        Document query = new Document("_id", object.getSrID());
        MedicineDeliverySRTable.findOneAndReplace(query, convertMedicineDeliverySR(object));
    }

    @Override
    public MedicineDeliverySR getValue(String objectID) {
//        MedicineDeliverySR medicineDeliverySR;
//
//        Document query = new Document("_id", objectID);
//        Document cursor = MedicineDeliverySRTable.find(query);
//
//        AbstractSR mainSR = MainSRMongo.getValue(objectID);
//
//        String status = mainSR.getStatus();
////        String srType
//        Location location = mainSR.getLocation();
//        Employee requestor = mainSR.getRequestor();
//        Employee assignedEmployee = mainSR.getAssignedEmployee();
//        LocalDate dateRequested = mainSR.getDateRequested();
//        String notes = mainSR.getNotes();
//
//        BasicDBObject foodObj = (BasicDBObject) cursor.one();
//        String srID = foodObj.getString("_id");
//        String medicineID = foodObj.getString("medicineID");
//        String patientID = foodObj.getString("patientID");
//
//        medicineDeliverySR = new MedicineDeliverySR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, medicineID, patientID);
//
//        return medicineDeliverySR;
        return null;
    }

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
