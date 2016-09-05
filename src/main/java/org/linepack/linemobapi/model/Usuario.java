/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leandro
 */
@XmlRootElement
public class Usuario extends BaseModel {

    private String nome;
    private String passWord;

    public Usuario() {
    }

    public Usuario(String nome, String passWord) {
        this.nome = nome;
        this.passWord = passWord;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
