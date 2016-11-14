/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.mail;

/**
 *
 * @author Leandro
 */
public class Email {

    private String from;
    private String to;
    private String cc;
    private String cco;
    private String subject;
    private String body;
    private String anexo;
    private String password;

    public Email() {
    }

    public Email(String from, String to, String cc, String cco, String subject, String body, String anexo, String password) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.cco = cco;
        this.subject = subject;
        this.body = body;
        this.anexo = anexo;
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCco() {
        return cco;
    }

    public void setCco(String cco) {
        this.cco = cco;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
