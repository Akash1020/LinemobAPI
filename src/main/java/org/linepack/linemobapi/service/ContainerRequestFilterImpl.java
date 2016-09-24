/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.bson.Document;
import org.linepack.linemobapi.model.Usuario;
import org.linepack.linemobapi.util.MongoDbUtil;

/**
 *
 * @author Leandro
 */
@Provider
@PreMatching
public class ContainerRequestFilterImpl implements ContainerRequestFilter {
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException, UnknownHostException {
        if (requestContext.getRequest().getMethod().equals("OPTIONS")) {
            requestContext.abortWith(Response.status(Response.Status.OK).build());
            return;
        }
        
        String usuario = requestContext.getHeaderString("Usuario");
        String token = requestContext.getHeaderString("Token");
        
        if (requestContext.getUriInfo().getPath().equals("/usuario/signup")) {
            String retornoCriacaoDB = this.createMongoDatabase(usuario, token);
            if (retornoCriacaoDB != ""){
                throw new ForbiddenException();
            }
        } else {
            try {
                this.validaToken(usuario, token, requestContext);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ContainerRequestFilterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void validaToken(String nomeUsuario, String token, ContainerRequestContext requestContext) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        if (nomeUsuario == null || token == null) {
            System.out.println("errEntity: "+ requestContext.getUriInfo().getPath());            
            throw new HeadlessException();
        }
        
        Usuario usuario = new Usuario(nomeUsuario, token);
        MongoDbUtil mongoDbUtil = new MongoDbUtil(nomeUsuario, Usuario.class);
        MongoCollection userCollection = mongoDbUtil.getMongoDatabase().getCollection(usuario.getClass().getSimpleName());
        FindIterable iterable = userCollection.find(mongoDbUtil.getDocumentFromEntity(usuario));
        List list;
        list = mongoDbUtil.getListFromIterable(iterable);        
        mongoDbUtil.closeMongoConnection();
        if (list.isEmpty()) {
            throw new ForbiddenException();
        }
    }
    
    private String createMongoDatabase(String usuario, String token) {
        MongoDbUtil mongoDbUtil = new MongoDbUtil(usuario, Usuario.class);
        MongoClient mongoClient = mongoDbUtil.getMongoClient();
        List<String> dbList = mongoClient.getDatabaseNames();
        Boolean existDB = dbList.contains(usuario);
        
        if (existDB) {
            mongoDbUtil.closeMongoConnection();
            return "server-messages.user-exists";
        }
        
        Document document = new Document();
        document.append("nome", usuario);
        document.append("password", token);
        mongoClient.getDatabase(usuario).getCollection("Usuario").insertOne(document);
        mongoDbUtil.closeMongoConnection();
        return "";
    }
}
