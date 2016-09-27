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
import org.bson.Document;
import org.linepack.linemobapi.model.Movimento;
import org.linepack.linemobapi.model.Pessoa;

/**
 *
 * @author Leandro
 */
@Stateless
@Path("pessoa")
public class PessoaFacadeREST extends AbstractFacade<Pessoa> {

    @EJB
    private MovimentoFacadeREST movimentoFacadeREST;

    public PessoaFacadeREST() {
        super(Pessoa.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String create(Pessoa entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.create(entity);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String remove(@PathParam("id") String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Document document = new Document("idExternoPessoa", id);
        List<Movimento> movimentoList = movimentoFacadeREST.findByDocument(document);
        if (!movimentoList.isEmpty()) {
            return "server-messages.remove-pessoa-used-movimento";
        }
        return super.remove(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String edit(@PathParam("id") String id, Pessoa entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        return super.edit(id, entity);
    }

    @GET
    @Path("{versao}/{filtraVersao}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<Pessoa> findAll(
            @PathParam("versao") String versao,
            @PathParam("filtraVersao") String filtraVersao) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.findAll(versao, filtraVersao);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Pessoa find(@PathParam("id") String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.find(id);
    }
}
