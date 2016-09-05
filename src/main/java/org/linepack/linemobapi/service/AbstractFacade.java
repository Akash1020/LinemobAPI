/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.linepack.linemobapi.model.Conta;

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

    private Document getDocument(T entity) throws IllegalArgumentException, IllegalAccessException {
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
        Document document = this.getDocument(entity);
        this.getMongoDatabase().getCollection(entity.getClass().getSimpleName()).insertOne(document);
        return document.get("_id").toString();
    }

    public void edit(String id, T entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        try {
            this.getMongoDatabase().getCollection(entity.getClass().getSimpleName()).replaceOne(
                    new Document("_id", new ObjectId(String.valueOf(id))),
                    new Document(this.getDocument(entity))
            );
        } catch (IllegalAccessException iae) {

        } catch (Exception ex) {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(String id) throws UnknownHostException {
        try {
            Bson filter = new Document("_id", new ObjectId(id));
            this.getMongoDatabase().getCollection(entityClass.getClass().getSimpleName()).deleteOne(filter);
        } catch (Exception ex) {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public T find(Object id) throws UnknownHostException {
        FindIterable iterable = null;
        try {
            iterable = this.getMongoDatabase().getCollection(entityClass.getSimpleName()).find(
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
        FindIterable iterable = this.getMongoDatabase().getCollection(entityClass.getSimpleName()).find();
        return this.getListFromIterable(iterable);
    }

    public List<T> findRange(int[] range) {
        return null;
    }

    public int count() {
        return 1;
    }

}
