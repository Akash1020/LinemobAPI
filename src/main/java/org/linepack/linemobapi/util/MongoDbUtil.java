/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.util;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.linepack.linemobapi.service.AbstractFacade;

/**
 *
 * @author Leandro
 * @param <T>
 */
public class MongoDbUtil<T> {

    private String dbName;
    private final Class<T> entityClass;
    private MongoClient mongoClient;

    public MongoDbUtil(String dbName, Class<T> entityClass) {
        this.dbName = dbName;
        this.entityClass = entityClass;
    }

    public MongoClient getMongoClient() {
        MongoClientURI mongoClientURI = new MongoClientURI("");                                                                
        mongoClient = new MongoClient(mongoClientURI);
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() throws UnknownHostException {
        MongoDatabase db = this.getMongoClient().getDatabase(dbName);
        return db;
    }

    public void closeMongoConnection() {
        if (this.mongoClient != null) {
            this.mongoClient.close();
        }
    }

    public void renameMongoDatabase(String newDB) throws UnknownHostException {
        MongoDatabase db = this.getMongoClient().getDatabase("admin");
        Bson cmdObj = new BasicDBObject("copydb", "1").append("fromdb", this.dbName).append("todb", newDB);
        db.runCommand(cmdObj);
        this.getMongoDatabase().drop();        
    }

    public MongoCollection<Document> getMongoCollection() throws UnknownHostException {
        return this.getMongoDatabase().getCollection(entityClass.getSimpleName());
    }

    public Document getDocumentFromEntity(T entity) throws IllegalArgumentException, IllegalAccessException {
        Document document = new Document();
        document.putAll(this.getDocument(entity.getClass().getDeclaredFields(), entity));
        document.putAll(this.getDocument(entity.getClass().getSuperclass().getDeclaredFields(), entity));
        return document;
    }

    private Map<String, Object> getDocument(Field[] fieldsArray, T entity) throws IllegalArgumentException, IllegalAccessException {
        Document document = new Document();
        for (Field field : fieldsArray) {
            field.setAccessible(true);
            Object fieldValue = "";
            if (field.getName().equals("versao")) {
                if (field.get(entity) == "") {
                    fieldValue = 1;
                } else {
                    fieldValue = (Integer) field.get(entity) + 1;
                }
            } else {
                fieldValue = field.get(entity);
            }
            if (!field.getName().equals("id")) {
                document.append(field.getName(), fieldValue);
            }
        }
        return document;
    }

    public List<T> getListFromIterable(FindIterable iterable) {
        final List<T> list = new ArrayList<>();
        try {
            iterable.forEach(new Block<Document>() {
                @Override
                public void apply(Document document) {
                    try {
                        Gson gson = new Gson();
                        T object = gson.fromJson(document.toJson(), entityClass);
                        ObjectId objId = document.getObjectId("_id");
                        Field idField = object.getClass().getSuperclass().getDeclaredField("id");
                        idField.setAccessible(true);
                        idField.set(object, objId.toString());
                        list.add((T) object);

                    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(AbstractFacade.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (NullPointerException npe) {

        }
        return list;
    }

    public String getDbName() {
        return dbName;
    }

}
