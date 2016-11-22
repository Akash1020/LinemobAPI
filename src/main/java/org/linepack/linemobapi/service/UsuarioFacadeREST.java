/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.service;

import com.mongodb.MongoClient;
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
import org.linepack.linemobapi.mail.EmailController;
import org.linepack.linemobapi.model.Usuario;
import org.linepack.linemobapi.util.MongoDbUtil;

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
    public String signUp() throws UnknownHostException, IllegalArgumentException, IllegalAccessException, MessagingException, UnsupportedEncodingException {
        String usuario = super.headers.getHeaderString("Usuario");
        String token = super.headers.getHeaderString("Token");
        String nome = super.headers.getHeaderString("Nome");
        
        MongoDbUtil mongoDbUtil = new MongoDbUtil(usuario, Usuario.class);
        MongoClient mongoClient = mongoDbUtil.getMongoClient();
        List<String> dbList = mongoClient.getDatabaseNames();
        Boolean existDB = dbList.contains(usuario);

        if (existDB) {
            mongoDbUtil.closeMongoConnection();
            return "server-messages.user-exists";
        }

        Document document = new Document();
        document.append("nome", usuario);
        document.append("password", token);
        document.append("nomeNovo", nome);
        mongoClient.getDatabase(usuario).getCollection("Usuario").insertOne(document);
        mongoDbUtil.closeMongoConnection();

        EmailController emailController = new EmailController();
        emailController.bemVindo(usuario, nome);
        return "";
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

    @POST
    @Path("/alteracaoSenha")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String alteracaoSenha(Usuario usuario) throws UnknownHostException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, MessagingException, UnsupportedEncodingException {
        Document document = new Document("nome", super.headers.getHeaderString("Usuario"));
        List<Usuario> usuarios = super.findByDocument(document);
        if (!usuarios.isEmpty()) {
            String idForUpdate = usuarios.get(0).getId();
            String updateResult = super.edit(idForUpdate, usuario);
            EmailController emailController = new EmailController();
            emailController.alteracaoSenha(usuario.getNome(), usuario.getNomeNovo());
            return updateResult;
        } else {
            throw new ForbiddenException();
        }
    }
}
