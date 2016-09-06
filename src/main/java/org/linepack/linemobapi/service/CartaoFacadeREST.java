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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

    @Override
    public List<Cartao> findRange(Integer from, Integer to) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.findRange(from, to); //To change body of generated methods, choose Tools | Templates.
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cartao> findAll() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.findAll();
    }

    @Override
    public Cartao find(Object id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String remove(String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.remove(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String edit(String id, Cartao entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        return super.edit(id, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String create(Cartao entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Conta conta = contaFacadeREST.find(entity.getIdConta());
        if (conta == null) {
            throw new ContaNotFoundException();
        }
        return super.create(entity);
    }
}
