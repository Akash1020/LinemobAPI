/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

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
        if (requestContext.getRequest().getMethod().equals("OPTIONS")){
            requestContext.abortWith(Response.status(Response.Status.OK).build());
            return;
        }
        String usuario = requestContext.getHeaderString("Usuario");
        String token = requestContext.getHeaderString("Token");     
        try {
            this.validaToken(usuario, token);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(ContainerRequestFilterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void validaToken(String nomeUsuario, String token) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        if (nomeUsuario == null || token == null) {
            throw new HeadlessException();
        }

        Usuario usuario = new Usuario(nomeUsuario, token);
        MongoDbUtil mongoDbUtil = new MongoDbUtil(nomeUsuario, Usuario.class);
        MongoCollection userCollection = mongoDbUtil.getMongoDatabase().getCollection(usuario.getClass().getSimpleName());
        FindIterable iterable = userCollection.find(mongoDbUtil.getDocumentFromEntity(usuario));
        List list;
        list = mongoDbUtil.getListFromIterable(iterable);
        if (list.isEmpty()) {
            throw new ForbiddenException();
        }
    }

}
