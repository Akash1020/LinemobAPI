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
import org.linepack.linemobapi.model.Cartao;
import org.linepack.linemobapi.model.Movimento;

/**
 *
 * @author Leandro
 */
@Stateless
@Path("conta")
public class ContaFacadeREST extends AbstractFacade<Conta> {

    @EJB
    private CartaoFacadeREST cartaoFacadeREST;
    @EJB
    private MovimentoFacadeREST movimentoFacadeREST;

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

    @PUT
    @Path("{id}/del")
    @Override    
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String remove(@PathParam("id") String id, Conta entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Document document = new Document("idExternoConta", id);
        List<Cartao> cartaoList = cartaoFacadeREST.findByDocument(document);
        if (!cartaoList.isEmpty()) {
            return "server-messages.remove-conta-used-cartao";
        }
        List<Movimento> movimentoList = movimentoFacadeREST.findByDocument(document);
        if (!movimentoList.isEmpty()) {
            return "server-messages.remove-conta-used-movimento";
        }
        return super.remove(id, entity);
    }

    @PUT
    @Override
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String edit(@PathParam("id") String id, Conta entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        return super.edit(id, entity);
    }

    @GET
    @Path("{versao}/{filtraVersao}/{menorQue}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<Conta> findAll(
            @PathParam("versao") Integer versao,
            @PathParam("filtraVersao") String filtraVersao,
            @PathParam("menorQue") String menorQue) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.findAll(versao, filtraVersao, menorQue);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Conta find(@PathParam("id") String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.find(id);
    }
}
