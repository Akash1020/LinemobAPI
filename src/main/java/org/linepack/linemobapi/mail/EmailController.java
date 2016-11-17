/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.mail;

import java.io.UnsupportedEncodingException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Franciscato
 */
public class EmailController {

    private final String from = "linepack@linepack.org";
    private final String password = ""

    public EmailController() {
    }

    public void bemVindo(String to, String usuario) throws MessagingException, UnsupportedEncodingException {
        Email email = new Email();
        email.setFrom(this.from);
        email.setPassword(this.password);
        email.setTo(to.replace(',', '.'));
        email.setCco("linepack@linepack.org");
        email.setSubject("[Linemob] Bem Vindo " + usuario);
        String body = "<html> <head> <title>Boas Vindas</title> <meta charset=\"UTF-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> </head> <body> <div> <center> <img src=\"https://avatars2.githubusercontent.com/u/19695477?v=3&s=200\" width=\"100px\"> </center> <p>Olá <Strong>{{Usuario}}</strong>.</p><p>Seja muito bem vindo ao <strong>Linemob Finanças</strong>.</p><p>Se você tiver alguma dúvida quanto ao uso do App, ou sugestões de melhoria, entre em contato pelo e-mail: <strong>android-support@linepack.org</strong>, ou pelo telefone: <strong>+55(44)99865-9032</strong> </p><p>Atenciosamente,<br><i>Linepack Systems / Leandro Franciscato</i></p></div></body></html>";
        body = body.replace("{{Usuario}}", usuario);
        email.setBody(body);
        this.envia(email);
    }

    public void alteracaoDadosCadastrais(String to, String usuario) throws MessagingException, UnsupportedEncodingException {
        Email email = new Email();
        email.setFrom(this.from);
        email.setPassword(this.password);
        email.setTo(to.replace(',', '.'));
        email.setCco("linepack@linepack.org");
        email.setSubject("[Linemob] Alteração de dados cadastrais");
        String body = "<html> <head> <title>Alteração de dados cadastrais</title> <meta charset=\"UTF-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> </head> <body> <div> <center> <img src=\"https:/avatars2.githubusercontent.com/u/19695477?v=3&s=200\" width=\"100px\"> </center> <p>Olá <Strong>{{Usuario}}</strong>.</p><p>Seus dados cadastrais foram alterados conforme abaixo:<br><p><strong>Nome: </strong>{{Usuario}}.<br><strong>E-mail: </strong>{{Email}}. </p><p>Se não foi você quem realizou estas alterações, entre em contato pelo telefone <strong>+55(44)99865-9032</strong> ou pelo E-mail: <strong>android-support@linepack.org</strong></p><p>Atenciosamente,<br><i>Linepack Systems / Leandro Franciscato</i></p></div></body></html>";
        body = body.replace("{{Usuario}}", usuario);
        body = body.replace("{{Email}}", to.replace(',', '.'));
        email.setBody(body);
        this.envia(email);
    }

    public void envia(Email email) throws javax.mail.MessagingException, UnsupportedEncodingException {

        Map<String, String> to;
        Map<String, String> cc = null;
        Map<String, String> cco = null;
        List<String> anexo = null;
        final String from;
        final String password;
        String subject;
        String body;

        to = EmailConverter.stringToMap(email.getTo(), ";");
        if (email.getCc() != null) {
            cc = EmailConverter.stringToMap(email.getCc(), ";");
        }
        if (email.getCco() != null) {
            cco = EmailConverter.stringToMap(email.getCco(), ";");
        }
        if (email.getAnexo() != null) {
            anexo = EmailConverter.stringToList(email.getAnexo(), ";");
        }
        from = email.getFrom();
        password = email.getPassword();
        subject = email.getSubject();
        body = email.getBody();

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "*");

        Session session = null;
        Authenticator authenticator = null;
        if (properties.get("mail.smtp.auth") == "true") {
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(from, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
            session = Session.getInstance(properties, authenticator);
        } else {
            session = Session.getInstance(properties);
        }

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setSubject(subject);

        //To            
        for (Map.Entry<String, String> map : to.entrySet()) {
            message.addRecipient(RecipientType.TO,
                    new InternetAddress(map.getKey(), map.getValue())
            );
        }

        //CC
        if (cc != null) {
            for (Map.Entry<String, String> map : cc.entrySet()) {
                message.addRecipient(RecipientType.CC,
                        new InternetAddress(map.getKey(), map.getValue())
                );
            }
        }

        //CCO                        
        if (cco != null) {
            for (Map.Entry<String, String> map : cco.entrySet()) {
                message.addRecipient(RecipientType.BCC,
                        new InternetAddress(map.getKey(), map.getValue())
                );
            }
        }

        //Anexo
        Multipart mp = new MimeMultipart();
        if (anexo != null) {
            for (int index = 0; index < anexo.size(); index++) {
                MimeBodyPart mbp = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(anexo.get(index));
                mbp.setDataHandler(new DataHandler(fds));
                mbp.setFileName(fds.getName());

                mp.addBodyPart(mbp, index);
            }
        }

        // Corpo
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(body, "text/html");
        mp.addBodyPart(bodyPart);
        message.setContent(mp);
        Transport.send(message);
    }
}
