package edu.wpi.cs3733.c22.teamB.entity.MongoDB;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.IDatabase;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.ExternalTransportSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.FoodDeliverySR;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MedicineDeliverySR;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
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
        MedicineDeliverySR medicineDeliverySR;

        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = MedicineDeliverySRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();


        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();
//        String srType
        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();

        Document externalObj = cursor.next();
        String srID = externalObj.getString("_id");
        String medicineID = externalObj.getString("medicineID");
        String patientID = externalObj.getString("patientID");

        medicineDeliverySR = new MedicineDeliverySR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, medicineID, patientID);

        return medicineDeliverySR;
    }

    @Override
    public List<MedicineDeliverySR> getAllValues() {
        List<MedicineDeliverySR> medicineDeliverySRList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = MedicineDeliverySRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

            String srID = (String) object.get("_id");
            medicineDeliverySRList.add(getValue(srID));
        }

        return medicineDeliverySRList;
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
