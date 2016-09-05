/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import java.net.UnknownHostException;
import java.util.List;
import org.linepack.linemobapi.model.Conta;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Leandro
 */
@Stateless
@Path("conta")
public class ContaFacadeREST extends AbstractFacade<Conta> {

    public ContaFacadeREST() {
        super(Conta.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String createREST(Conta entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.create(entity);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Conta> findAll() throws UnknownHostException {
        return super.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Conta find(@PathParam("id") String id) throws UnknownHostException {
        return super.find(id);
    }

    @PUT
    @Override
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Conta entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        super.edit(id, entity);
    }
    
    @DELETE
    @Override
    @Path("{id}")
    public void remove(@PathParam("id") String id) throws UnknownHostException{
        super.remove(id);
    }

}