/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import java.net.UnknownHostException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.linepack.linemobapi.exception.ContaNotFoundException;
import org.linepack.linemobapi.model.Cartao;
import org.linepack.linemobapi.model.Conta;

/**
 *
 * @author Leandro
 */
@Stateless
@Path("cartao")
public class CartaoFacadeREST extends AbstractFacade<Cartao> {

    @EJB
    private ContaFacadeREST contaFacadeREST;

    public CartaoFacadeREST() {
        super(Cartao.class);
    }

    @GET
    @Path("/count")
    @Override
    @Produces(MediaType.TEXT_PLAIN)
    public Long count() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.count();
    }

    @GET
    @Path("{from}/{to}")
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cartao> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.findRange(from, to);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cartao> findAll() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.findAll();
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cartao find(@PathParam("id") String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.find(id);
    }

    @DELETE
    @Path("{id}")
    @Override
    @Produces(MediaType.TEXT_XML)
    public String remove(@PathParam("id") String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.remove(id);
    }

    @PUT
    @Override
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String edit(@PathParam("id") String id, Cartao entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        return super.edit(id, entity);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String create(Cartao entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Conta conta = contaFacadeREST.find(entity.getIdExternoConta());
        if (conta == null) {
            throw new ContaNotFoundException();
        }
        return super.create(entity);
    }
}
