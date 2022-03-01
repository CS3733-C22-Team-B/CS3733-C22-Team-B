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
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import edu.wpi.cs3733.c22.teamB.entity.objects.services.MedicalEquipmentSR;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalEquipmentSRMongo implements IDatabase<MedicalEquipmentSR> {
    private MongoDatabase conn;
    private MongoCollection MedicalEquipmentSRTable;
    private IDatabase<AbstractSR> MainSRMongo;
    private IDatabase<MedicalEquipment> MedicalEquipmentMongo;

    public MedicalEquipmentSRMongo(IDatabase<AbstractSR> mainSRMongo, IDatabase<MedicalEquipment> MedicalEquipmentMongo) {
        conn = MongoDB.getBDBMongo();
        this.MainSRMongo = mainSRMongo;
        this.MedicalEquipmentMongo = MedicalEquipmentMongo;
    }

    public static Document convertMedicalEquipmentSR(MedicalEquipmentSR sr) {
        Document document = new Document();
        document.put("_id", sr.getSrID());
        document.put("medicalEquipment", sr.getMedicalEquipment().getEquipmentID());

        return document;
    }

    @Override
    public void addValue(MedicalEquipmentSR object) {
        conn.getCollection("MedicalEquipmentSR").insertOne(convertMedicalEquipmentSR(object));
    }

    @Override
    public void deleteValue(String objectID) {
        MedicalEquipmentSRTable.deleteOne(convertMedicalEquipmentSR(getValue(objectID)));
    }

    @Override
    public void updateValue(MedicalEquipmentSR object) {
        Document query = new Document("_id", object.getSrID());
        MedicalEquipmentSRTable.findOneAndReplace(query, convertMedicalEquipmentSR(object));
    }

    @Override
    public MedicalEquipmentSR getValue(String objectID) {
        MedicalEquipmentSR medicalEquipmentSR;

        Document query = new Document("_id", objectID);
        FindIterable<Document> iterable = MedicalEquipmentSRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        AbstractSR mainSR = MainSRMongo.getValue(objectID);

        String status = mainSR.getStatus();

        Location location = mainSR.getLocation();
        Employee requestor = mainSR.getRequestor();
        Employee assignedEmployee = mainSR.getAssignedEmployee();
        LocalDate dateRequested = mainSR.getDateRequested();
        String notes = mainSR.getNotes();

        Document medEqSR = cursor.next();
        String srID = medEqSR.getString("_id");
        String medicalEquipmentID = medEqSR.getString("medicalEquipment");
        MedicalEquipment medicalEquipment = MedicalEquipmentMongo.getValue(medicalEquipmentID);

        medicalEquipmentSR = new MedicalEquipmentSR(srID, status, location, requestor, assignedEmployee, dateRequested, notes, medicalEquipment);

        return medicalEquipmentSR;
    }

    @Override
    public List<MedicalEquipmentSR> getAllValues() {
        List<MedicalEquipmentSR> equipmentSRList = new ArrayList<>();

        Document query = new Document();
        FindIterable<Document> iterable = MedicalEquipmentSRTable.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document object = cursor.next();

            String nodeID = (String) object.get("_id");
            equipmentSRList.add(getValue(nodeID));
        }

        return equipmentSRList;
    }

    @Override
    public void createTable() {
        MedicalEquipmentSRTable = conn.getCollection("MedicalEquipmentSR");
    }

    @Override
    public void dropTable() {
        conn.getCollection("MedicalEquipmentSR").drop();
    }

    @Override
    public void restoreTable(List<MedicalEquipmentSR> list) {
        createTable();
        List<Document> newList = new ArrayList<>();

        for(MedicalEquipmentSR medicalEquipmentSR : list) {
            newList.add(convertMedicalEquipmentSR(medicalEquipmentSR));
        }
        MedicalEquipmentSRTable.insertMany(newList);
    }
}
