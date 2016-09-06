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
import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.linepack.linemobapi.model.Usuario;

/**
 *
 * @author Leandro
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;
    @Context
    private HttpHeaders headers;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private MongoDatabase getMongoDatabase() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db;
        db = mongoClient.getDatabase(headers.getHeaderString("Usuario"));
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
        this.validaToken();
        Document document = this.getDocumentFromEntity(entity);
        this.getMongoCollection().insertOne(document);
        return document.get("_id").toString();
    }

    public String edit(String id, T entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        try {
            UpdateResult updateResult = this.getMongoCollection().replaceOne(
                    new Document("_id", new ObjectId(String.valueOf(id))),
                    new Document(this.getDocumentFromEntity(entity))
            );
            return String.valueOf(updateResult.getModifiedCount());
        } catch (UnknownHostException | IllegalArgumentException | IllegalAccessException ex) {
            throw ex;
        }
    }

    public String remove(String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        try {
            DeleteResult deleteResult = this.getMongoCollection().deleteOne(eq("_id", new ObjectId(id)));
            return String.valueOf(deleteResult.getDeletedCount());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public T find(String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        FindIterable iterable = null;
        try {
            iterable = this.getMongoCollection().find(
                    new Document("_id", new ObjectId(String.valueOf(id)))
            );
        } catch (IllegalArgumentException iae) {
        }

        List<T> list;
        list = this.getListFromIterable(iterable);
        if (!list.isEmpty()) {
            return (T) list.get(0);
        }
        return null;
    }

    public List<T> findAll() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        FindIterable iterable = this.getMongoCollection().find();
        return this.getListFromIterable(iterable);
    }

    public List<T> findRange(Integer from, Integer to) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        FindIterable iterable = this.getMongoCollection().find().skip(from).limit(to);
        return this.getListFromIterable(iterable);
    }
    
    public List<T> findByDocument(Document document) throws UnknownHostException, IllegalArgumentException, IllegalAccessException{        
        this.validaToken();
        FindIterable iterable = this.getMongoCollection().find(document);
        return this.getListFromIterable(iterable);
    }
        
    public Long count() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        return this.getMongoCollection().count();
    }

    public Boolean validaToken() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        if (headers.getHeaderString("Usuario") == null
                || headers.getHeaderString("Token") == null) {
            throw new HeadlessException();
        }

        Usuario usuario = new Usuario(headers.getHeaderString("Usuario"), headers.getHeaderString("Token"));
        MongoCollection userCollection = this.getMongoDatabase().getCollection(usuario.getClass().getSimpleName());
        FindIterable iterable = userCollection.find(this.getDocumentFromEntity((T) usuario));        
        List<T> list;
        list = this.getListFromIterable(iterable);
        if (!list.isEmpty()) {
            return true;
        }
        throw new ForbiddenException();
    }
}
