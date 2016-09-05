/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.model;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leandro
 */
@XmlRootElement
public class Categoria extends BaseModel {

    private String nome;
    private String nomesubcategoria;

    public Categoria() {
    }

    public Categoria(String nome, String nomesubcategoria) {
        this.nome = nome;
        this.nomesubcategoria = nomesubcategoria;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomesubcategoria() {
        return nomesubcategoria;
    }

    public void setNomesubcategoria(String nomesubcategoria) {
        this.nomesubcategoria = nomesubcategoria;
    }

}
