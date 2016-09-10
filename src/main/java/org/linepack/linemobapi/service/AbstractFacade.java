/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.net.UnknownHostException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.linepack.linemobapi.util.MongoDbUtil;

/**
 *
 * @author Leandro
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;
    private final MongoDbUtil mongoDbUtil;

    @Context
    private HttpHeaders headers;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.mongoDbUtil = new MongoDbUtil(headers.getHeaderString("Usuario"), entityClass);
    }

    public String create(T entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Document document = mongoDbUtil.getDocumentFromEntity(entity);
        mongoDbUtil.getMongoCollection().insertOne(document);
        return document.get("_id").toString();
    }

    public String edit(String id, T entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        try {
            UpdateResult updateResult = mongoDbUtil.getMongoCollection().replaceOne(
                    new Document("_id", new ObjectId(String.valueOf(id))),
                    new Document(mongoDbUtil.getDocumentFromEntity(entity))
            );
            return String.valueOf(updateResult.getModifiedCount());
        } catch (UnknownHostException | IllegalArgumentException | IllegalAccessException ex) {
            throw ex;
        }
    }

    public String remove(String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        try {
            DeleteResult deleteResult = mongoDbUtil.getMongoCollection().deleteOne(eq("_id", new ObjectId(id)));
            return String.valueOf(deleteResult.getDeletedCount());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public T find(String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        FindIterable iterable = null;
        try {
            iterable = mongoDbUtil.getMongoCollection().find(
                    new Document("_id", new ObjectId(String.valueOf(id)))
            );
        } catch (IllegalArgumentException iae) {
        }

        List<T> list;
        list = mongoDbUtil.getListFromIterable(iterable);
        if (!list.isEmpty()) {
            return (T) list.get(0);
        }
        return null;
    }

    public List<T> findAll() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        FindIterable iterable = mongoDbUtil.getMongoCollection().find();
        return mongoDbUtil.getListFromIterable(iterable);
    }

    public List<T> findRange(Integer from, Integer to) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        FindIterable iterable = mongoDbUtil.getMongoCollection().find().skip(from).limit(to);
        return mongoDbUtil.getListFromIterable(iterable);
    }

    public List<T> findByDocument(Document document) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        FindIterable iterable = mongoDbUtil.getMongoCollection().find(document);
        return mongoDbUtil.getListFromIterable(iterable);
    }

    public Long count() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return mongoDbUtil.getMongoCollection().count();
    }
}
