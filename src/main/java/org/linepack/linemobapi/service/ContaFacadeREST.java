/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import java.net.UnknownHostException;
import java.util.List;
import javax.ejb.EJB;
import org.linepack.linemobapi.model.Conta;
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
import org.bson.Document;
import org.linepack.linemobapi.exception.ContaVinculadaComCartaoException;
import org.linepack.linemobapi.model.Cartao;

/**
 *
 * @author Leandro
 */
@Stateless
@Path("conta")
public class ContaFacadeREST extends AbstractFacade<Conta> {

    @EJB
    private CartaoFacadeREST cartaoFacadeREST;

    public ContaFacadeREST() {
        super(Conta.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String create(Conta entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.create(entity);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Conta> findAll() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Conta find(@PathParam("id") String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.find(id);
    }

    @PUT
    @Override
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String edit(@PathParam("id") String id, Conta entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        return super.edit(id, entity);
    }

    @DELETE
    @Override
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String remove(@PathParam("id") String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Document cartaoDocument = new Document("idConta", id);
        List<Cartao> cartaoList = cartaoFacadeREST.findByDocument(cartaoDocument);
        if (!cartaoList.isEmpty()) {
            throw new ContaVinculadaComCartaoException();
        }
        return super.remove(id);
    }

    @GET
    @Override
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Long count() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.count();
    }

    @GET
    @Override
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Conta> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.findRange(from, to);
    }
}
