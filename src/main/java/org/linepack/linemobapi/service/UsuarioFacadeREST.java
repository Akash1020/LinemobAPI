/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.List;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.Document;
import org.linepack.linemobapi.model.Usuario;

/**
 *
 * @author Leandro
 */
@Stateless
@Path("usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @GET
    @Path("/login")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String login() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        Document document = new Document("nome", super.headers.getHeaderString("Usuario"));
        List<Usuario> usuarios = super.findByDocument(document);
        if (!usuarios.isEmpty()) {
            return usuarios.get(0).getNomeNovo();
        }
        return "";
    }

    @GET
    @Path("/signup")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean signUp() throws UnknownHostException, IllegalArgumentException, IllegalAccessException {
        //return super.validaToken();
        return true;
    }

    @POST
    @Path("/alteracaoDadosCadastrais")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String alteracaoDadosCadastrais(Usuario usuario) throws UnknownHostException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, MessagingException, UnsupportedEncodingException {
        Document document = new Document("nome", super.headers.getHeaderString("Usuario"));
        List<Usuario> usuarios = super.findByDocument(document);
        String idForUpdate = "";
        if (!usuarios.isEmpty()) {
            idForUpdate = usuarios.get(0).getId();
        } else {
            throw new ForbiddenException();
        }
        return super.renameDatabase(usuario.getNome(), idForUpdate, usuario);
    }
}
