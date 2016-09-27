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
import org.linepack.linemobapi.model.Cartao;
import org.linepack.linemobapi.model.Categoria;
import org.linepack.linemobapi.model.Conta;
import org.linepack.linemobapi.model.Movimento;
import org.linepack.linemobapi.model.Pessoa;

/**
 *
 * @author Leandro
 */
@Stateless
@Path("movimento")
public class MovimentoFacadeREST extends AbstractFacade<Movimento> {

    @EJB
    private ContaFacadeREST contaFacadeREST;
    @EJB
    private PessoaFacadeREST pessoaFacadeREST;
    @EJB
    private CategoriaFacadeREST categoriaFacadeREST;
    @EJB
    private CartaoFacadeREST cartaoFacadeREST;

    public MovimentoFacadeREST() {
        super(Movimento.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String create(Movimento entity) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Conta conta = contaFacadeREST.find(entity.getIdExternoConta());
        if (conta == null) {
            return "server-message.insert-movimento-without-conta";
        }
        Pessoa pessoa = pessoaFacadeREST.find(entity.getIdExternoPessoa());
        if (pessoa == null) {
            return "server-message.insert-movimento-without-pessoa";
        }
        Categoria categoria = categoriaFacadeREST.find(entity.getIdExternoCategoria());
        if (categoria == null) {
            return "server-message.insert-movimento-without-categora";
        }
        if (entity.getIdExternoCartao() != null) {
            Cartao cartao = cartaoFacadeREST.find(entity.getIdExternoCartao());
            if (cartao == null) {
                return "server-message.insert-movimento-without-cartao";
            }
        }
        return super.create(entity);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String remove(@PathParam("id") String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.remove(id);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String edit(@PathParam("id") String id, Movimento entity) throws UnknownHostException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        return super.edit(id, entity);
    }

    @GET
    @Path("{versao}/{filtraVersao}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public List<Movimento> findAll(
            @PathParam("versao") String versao,
            @PathParam("filtraVersao") String filtraVersao) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.findAll(versao, filtraVersao);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Movimento find(@PathParam("id") String id) throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        return super.find(id);
    }
}
