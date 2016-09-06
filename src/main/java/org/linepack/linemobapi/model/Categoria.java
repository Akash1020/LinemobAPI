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
public class Categoria extends BaseModel {

    private String nome;
    private String nomeSubCategoria;

    public Categoria() {
    }

    public Categoria(String nome, String nomeSubCategoria) {
        this.nome = nome;
        this.nomeSubCategoria = nomeSubCategoria;
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

    public String getNomeSubCategoria() {
        return nomeSubCategoria;
    }

    public void setNomeSubCategoria(String nomeSubCategoria) {
        this.nomeSubCategoria = nomeSubCategoria;
    }

}
