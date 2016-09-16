/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.awt.HeadlessException;
import java.net.UnknownHostException;
import java.util.List;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.linepack.linemobapi.model.Usuario;
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
        if (mongoDbUtil == null) {
            return this.mongoDbUtil = new MongoDbUtil(headers.getHeaderString("Usuario"), entityClass);
        }
        return this.mongoDbUtil;
    }

    public String create(T entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        Document document = this.getMongoDbUtil().getDocumentFromEntity(entity);
        this.getMongoDbUtil().getMongoCollection().insertOne(document);
        return document.get("_id").toString();
    }

    public String edit(String id, T entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        try {
            UpdateResult updateResult = this.getMongoDbUtil().getMongoCollection().replaceOne(
                    new Document("_id", new ObjectId(String.valueOf(id))),
                    new Document(this.getMongoDbUtil().getDocumentFromEntity(entity))
            );
            return String.valueOf(updateResult.getModifiedCount());
        } catch (UnknownHostException | IllegalArgumentException | IllegalAccessException ex) {
            throw ex;
        }
    }

    public String remove(String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        try {
            DeleteResult deleteResult = this.getMongoDbUtil().getMongoCollection().deleteOne(eq("_id", new ObjectId(id)));
            return String.valueOf(deleteResult.getDeletedCount());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public T find(String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        FindIterable iterable = null;
        try {
            iterable = this.getMongoDbUtil().getMongoCollection().find(
                    new Document("_id", new ObjectId(String.valueOf(id)))
            );
        } catch (IllegalArgumentException iae) {
        }

        List<T> list;
        list = this.getMongoDbUtil().getListFromIterable(iterable);
        if (!list.isEmpty()) {
            return (T) list.get(0);
        }
        return null;
    }

    public List<T> findAll() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        FindIterable iterable = this.getMongoDbUtil().getMongoCollection().find();
        return this.getMongoDbUtil().getListFromIterable(iterable);
    }

    public List<T> findRange(Integer from, Integer to) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        FindIterable iterable = this.getMongoDbUtil().getMongoCollection().find().skip(from).limit(to);
        return this.getMongoDbUtil().getListFromIterable(iterable);
    }

    public List<T> findByDocument(Document document) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        FindIterable iterable = this.getMongoDbUtil().getMongoCollection().find(document);
        return this.getMongoDbUtil().getListFromIterable(iterable);
    }

    public Long count() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        this.validaToken();
        return this.getMongoDbUtil().getMongoCollection().count();
    }

    private void validaToken() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        String nomeUsuario = headers.getHeaderString("Usuario");
        String token = headers.getHeaderString("Token");
        if (nomeUsuario == null || token == null) {
            throw new HeadlessException();
        }

        Usuario usuario = new Usuario(nomeUsuario, token);
        MongoDbUtil anotherMongoDbUtil = new MongoDbUtil(nomeUsuario, Usuario.class);
        MongoCollection userCollection = anotherMongoDbUtil.getMongoDatabase().getCollection(usuario.getClass().getSimpleName());
        FindIterable iterable = userCollection.find(anotherMongoDbUtil.getDocumentFromEntity(usuario));
        List list;
        list = anotherMongoDbUtil.getListFromIterable(iterable);
        if (list.isEmpty()) {
            throw new ForbiddenException();
        }
    }
}
