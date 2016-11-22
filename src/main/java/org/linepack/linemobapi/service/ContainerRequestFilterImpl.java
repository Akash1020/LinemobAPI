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
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.bson.Document;
import org.linepack.linemobapi.mail.EmailController;
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
    public void filter(ContainerRequestContext requestContext) throws IOException, UnknownHostException, UnsupportedEncodingException {
        if (requestContext.getRequest().getMethod().equals("OPTIONS")) {
            requestContext.abortWith(Response.status(Response.Status.OK).build());
            return;
        }

        String usuario = requestContext.getHeaderString("Usuario");
        String token = requestContext.getHeaderString("Token");

        if (!requestContext.getUriInfo().getPath().equals("/usuario/signup")) {
            try {
                this.validaToken(usuario, token, requestContext);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new ForbiddenException();
            }
        }
    }

    private void validaToken(String nomeUsuario, String token, ContainerRequestContext requestContext) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        if (nomeUsuario == null || token == null) {
            System.out.println("errEntity: " + requestContext.getUriInfo().getPath());
            throw new HeadlessException();
        }

        MongoDbUtil mongoDbUtil = new MongoDbUtil(nomeUsuario, Usuario.class);
        MongoCollection userCollection = mongoDbUtil.getMongoDatabase().getCollection("Usuario");
        Document document = new Document();
        document.append("nome", nomeUsuario);
        document.append("password", token);
        FindIterable iterable = userCollection.find(document);
        List list;
        list = mongoDbUtil.getListFromIterable(iterable);
        mongoDbUtil.closeMongoConnection();
        if (list.isEmpty()) {
            throw new ForbiddenException();
        }
    }
}
