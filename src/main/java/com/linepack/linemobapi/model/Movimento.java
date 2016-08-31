/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linepack.linemobapi.model;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name = "LINEMOB_MOVIMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinemobMovimento.findAll", query = "SELECT l FROM LinemobMovimento l"),
    @NamedQuery(name = "LinemobMovimento.findById", query = "SELECT l FROM LinemobMovimento l WHERE l.id = :id"),
    @NamedQuery(name = "LinemobMovimento.findByValor", query = "SELECT l FROM LinemobMovimento l WHERE l.valor = :valor"),
    @NamedQuery(name = "LinemobMovimento.findByNatureza", query = "SELECT l FROM LinemobMovimento l WHERE l.natureza = :natureza"),
    @NamedQuery(name = "LinemobMovimento.findByDescricao", query = "SELECT l FROM LinemobMovimento l WHERE l.descricao = :descricao"),
    @NamedQuery(name = "LinemobMovimento.findByIdsqlite", query = "SELECT l FROM LinemobMovimento l WHERE l.idsqlite = :idsqlite")})
public class Movimento extends BaseModel {

    @Column(name = "VALOR")
    private BigInteger valor;
    @Column(name = "NATUREZA")
    private Character natureza;
    @Size(max = 4000)
    @Column(name = "DESCRICAO")
    private String descricao;

    @JoinColumn(name = "IDCARTAO", referencedColumnName = "ID")
    @ManyToOne
    private Cartao idcartao;
    @JoinColumn(name = "IDCATEGORIA", referencedColumnName = "ID")
    @ManyToOne
    private Categoria idcategoria;
    @JoinColumn(name = "IDCONTA", referencedColumnName = "ID")
    @ManyToOne
    private Conta idconta;
    @JoinColumn(name = "IDPESSOA", referencedColumnName = "ID")
    @ManyToOne
    private Pessoa idpessoa;

    public Movimento() {
    }

    public Movimento(BigInteger valor, Character natureza, String descricao, Cartao idcartao, Categoria idcategoria, Conta idconta, Pessoa idpessoa) {
        this.valor = valor;
        this.natureza = natureza;
        this.descricao = descricao;
        this.idcartao = idcartao;
        this.idcategoria = idcategoria;
        this.idconta = idconta;
        this.idpessoa = idpessoa;
    }

    public Movimento(BigInteger valor, Character natureza, String descricao, Categoria idcategoria, Conta idconta, Pessoa idpessoa) {
        this.valor = valor;
        this.natureza = natureza;
        this.descricao = descricao;
        this.idcategoria = idcategoria;
        this.idconta = idconta;
        this.idpessoa = idpessoa;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
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
