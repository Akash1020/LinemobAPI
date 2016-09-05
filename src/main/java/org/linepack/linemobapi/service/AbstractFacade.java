/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Leandro
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private MongoDatabase getMongoDatabase() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db;
        db = mongoClient.getDatabase("linemob");
        return db;
    }

    private MongoCollection<Document> getMongoCollection() throws UnknownHostException {
        return this.getMongoDatabase().getCollection(entityClass.getSimpleName());
    }

    private Document getDocumentFromEntity(T entity) throws IllegalArgumentException, IllegalAccessException {
        Document document = new Document();
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            document.append(field.getName(), field.get(entity));
        }
        return document;
    }

    private List<T> getListFromIterable(FindIterable iterable) {
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
                        Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (NullPointerException npe) {

        }
        return list;
    }

    public String create(T entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Document document = this.getDocumentFromEntity(entity);
        this.getMongoCollection().insertOne(document);
        return document.get("_id").toString();
    }

    public String edit(String id, T entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        try {
            UpdateResult updateResult = this.getMongoCollection().replaceOne(
                    new Document("_id", new ObjectId(String.valueOf(id))),
                    new Document(this.getDocumentFromEntity(entity))
            );
            return String.valueOf(updateResult.getModifiedCount());
        } catch (IllegalAccessException iae) {
            return "0";
        } catch (Exception ex) {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
            return "0";
        }
    }

    public String remove(String id) throws UnknownHostException {
        try {
            DeleteResult deleteResult = this.getMongoCollection().deleteOne(eq("_id", new ObjectId(id)));
            return String.valueOf(deleteResult.getDeletedCount());
        } catch (Exception ex) {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
            return "0";
        }
    }

    public T find(Object id) throws UnknownHostException {
        FindIterable iterable = null;
        try {
            iterable = this.getMongoCollection().find(
                    new Document("_id", new ObjectId(String.valueOf(id)))
            );
        } catch (IllegalArgumentException iae) {
        }

        List<T> list = new ArrayList<>();
        list = this.getListFromIterable(iterable);
        if (!list.isEmpty()) {
            return (T) list.get(0);
        }
        return null;
    }

    public List<T> findAll() throws UnknownHostException {
        FindIterable iterable = this.getMongoCollection().find();
        return this.getListFromIterable(iterable);
    }

    public List<T> findRange(int[] range) {
        return null;
    }

    public int count() {
        return 1;
    }
}
