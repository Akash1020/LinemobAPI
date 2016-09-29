/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
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
    private MongoDbUtil mongoDbUtil;

    @Context
    private HttpHeaders headers;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private MongoDbUtil getMongoDbUtil() {
        if (mongoDbUtil != null && mongoDbUtil.getDbName().equals(headers.getHeaderString("Usuario"))) {
            return this.mongoDbUtil;
        }
        return this.mongoDbUtil = new MongoDbUtil(headers.getHeaderString("Usuario"), entityClass);
    }

    public String create(T entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Document document = this.getMongoDbUtil().getDocumentFromEntity(entity);
        this.getMongoDbUtil().getMongoCollection().insertOne(document);
        this.mongoDbUtil.closeMongoConnection();
        return document.get("_id").toString();
    }

    public String edit(String id, T entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        try {
            UpdateResult updateResult = this.getMongoDbUtil().getMongoCollection().replaceOne(
                    new Document("_id", new ObjectId(String.valueOf(id))),
                    new Document(this.getMongoDbUtil().getDocumentFromEntity(entity))
            );
            this.mongoDbUtil.closeMongoConnection();
            return String.valueOf(updateResult.getModifiedCount());
        } catch (UnknownHostException | IllegalArgumentException | IllegalAccessException ex) {
            this.mongoDbUtil.closeMongoConnection();
            throw ex;
        }
    }

    public String remove(String id, T entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        try {
            Document document = this.getMongoDbUtil().getDocumentFromEntity(entity);
            document.replace("versao", Integer.parseInt((String) document.get("versao")) - 1);
            UpdateResult updateResult = this.getMongoDbUtil().getMongoCollection().replaceOne(
                    new Document("_id", new ObjectId(String.valueOf(id))),
                    document
            );
            this.mongoDbUtil.closeMongoConnection();
            return String.valueOf(updateResult.getModifiedCount());
        } catch (IllegalArgumentException | IllegalAccessException | UnknownHostException ex) {
            this.mongoDbUtil.closeMongoConnection();
            throw ex;
        }
    }

    public T find(String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        FindIterable iterable = null;
        try {
            iterable = this.getMongoDbUtil().getMongoCollection().find(
                    new Document("_id", new ObjectId(String.valueOf(id)))
            );
        } catch (IllegalArgumentException iae) {
        }

        List<T> list;
        list = this.getMongoDbUtil().getListFromIterable(iterable);
        this.mongoDbUtil.closeMongoConnection();
        if (!list.isEmpty()) {
            return (T) list.get(0);
        }
        return null;
    }

    public List<T> findAll(String versao, String filtraVersao, String menorQue) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        FindIterable iterable = null;
        if (filtraVersao.equals("1")) {
            if (menorQue.equals("1")) {
                iterable = this.getMongoDbUtil().getMongoCollection().find(new Document("versao", new Document("$lt", versao)));
            } else {
                iterable = this.getMongoDbUtil().getMongoCollection().find(gt("versao", versao));
            }
        } else {
            iterable = this.getMongoDbUtil().getMongoCollection().find();
        }
        List<T> list = this.getMongoDbUtil().getListFromIterable(iterable);
        this.mongoDbUtil.closeMongoConnection();
        return list;
    }

    public List<T> findRange(Integer from, Integer to) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        FindIterable iterable = this.getMongoDbUtil().getMongoCollection().find().skip(from).limit(to);
        List<T> list = this.getMongoDbUtil().getListFromIterable(iterable);
        this.mongoDbUtil.closeMongoConnection();
        return list;
    }

    public List<T> findByDocument(Document document) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        FindIterable iterable = this.getMongoDbUtil().getMongoCollection().find(document);
        List<T> list = this.getMongoDbUtil().getListFromIterable(iterable);
        this.mongoDbUtil.closeMongoConnection();
        return list;
    }

    public Long count() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Long count = this.getMongoDbUtil().getMongoCollection().count();
        this.mongoDbUtil.closeMongoConnection();
        return count;
    }
}
