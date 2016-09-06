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
public class Movimento extends BaseModel {

    private Integer valor;
    private Character natureza;
    private String descricao;
    private Cartao idCartao;
    private Categoria idCategoria;
    private Conta idConta;
    private Pessoa idPessoa;

    public Movimento() {
    }

    public Movimento(Integer valor, Character natureza, String descricao, Cartao idCartao, Categoria idCategoria, Conta idConta, Pessoa idPessoa) {
        this.valor = valor;
        this.natureza = natureza;
        this.descricao = descricao;
        this.idCartao = idCartao;
        this.idCategoria = idCategoria;
        this.idConta = idConta;
        this.idPessoa = idPessoa;
    }

    public Movimento(Integer valor, Character natureza, String descricao, Categoria idcategoria, Conta idconta, Pessoa idpessoa) {
        this.valor = valor;
        this.natureza = natureza;
        this.descricao = descricao;
        this.idCategoria = idcategoria;
        this.idConta = idconta;
        this.idPessoa = idpessoa;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Character getNatureza() {
        return natureza;
    }

    public void setNatureza(Character natureza) {
        this.natureza = natureza;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cartao getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Cartao idCartao) {
        this.idCartao = idCartao;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Conta getIdConta() {
        return idConta;
    }

    public void setIdConta(Conta idConta) {
        this.idConta = idConta;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }
}
