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
    private Cartao idcartao;
    private Categoria idcategoria;
    private Conta idconta;
    private Pessoa idpessoa;

    public Movimento() {
    }

    public Movimento(Integer valor, Character natureza, String descricao, Cartao idcartao, Categoria idcategoria, Conta idconta, Pessoa idpessoa) {
        this.valor = valor;
        this.natureza = natureza;
        this.descricao = descricao;
        this.idcartao = idcartao;
        this.idcategoria = idcategoria;
        this.idconta = idconta;
        this.idpessoa = idpessoa;
    }

    public Movimento(Integer valor, Character natureza, String descricao, Categoria idcategoria, Conta idconta, Pessoa idpessoa) {
        this.valor = valor;
        this.natureza = natureza;
        this.descricao = descricao;
        this.idcategoria = idcategoria;
        this.idconta = idconta;
        this.idpessoa = idpessoa;
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

    public Cartao getIdcartao() {
        return idcartao;
    }

    public void setIdcartao(Cartao idcartao) {
        this.idcartao = idcartao;
    }

    public Categoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categoria idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Conta getIdconta() {
        return idconta;
    }

    public void setIdconta(Conta idconta) {
        this.idconta = idconta;
    }

    public Pessoa getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(Pessoa idpessoa) {
        this.idpessoa = idpessoa;
    }
}
