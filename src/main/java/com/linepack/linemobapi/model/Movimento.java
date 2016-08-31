/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linepack.linemobapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
public class Movimento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "VALOR")
    private BigInteger valor;
    @Column(name = "NATUREZA")
    private Character natureza;
    @Size(max = 4000)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "IDSQLITE")
    private BigInteger idsqlite;
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

    public Movimento(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public BigInteger getIdsqlite() {
        return idsqlite;
    }

    public void setIdsqlite(BigInteger idsqlite) {
        this.idsqlite = idsqlite;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimento)) {
            return false;
        }
        Movimento other = (Movimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linepack.linemobapi.LinemobMovimento[ id=" + id + " ]";
    }
    
}
