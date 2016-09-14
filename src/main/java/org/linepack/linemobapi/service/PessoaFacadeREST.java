/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import java.net.UnknownHostException;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.linepack.linemobapi.model.Pessoa;

/**
 *
 * @author Leandro
 */
@Stateless
@Path("pessoa")
public class PessoaFacadeREST extends AbstractFacade<Pessoa>{

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

    
    
    
    
}
