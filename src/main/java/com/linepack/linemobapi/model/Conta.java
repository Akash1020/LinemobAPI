/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linepack.linemobapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name = "LINEMOB_CONTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinemobConta.findAll", query = "SELECT l FROM LinemobConta l"),
    @NamedQuery(name = "LinemobConta.findById", query = "SELECT l FROM LinemobConta l WHERE l.id = :id"),
    @NamedQuery(name = "LinemobConta.findByNome", query = "SELECT l FROM LinemobConta l WHERE l.nome = :nome"),
    @NamedQuery(name = "LinemobConta.findByDatafundacao", query = "SELECT l FROM LinemobConta l WHERE l.datafundacao = :datafundacao"),
    @NamedQuery(name = "LinemobConta.findByValorsaldoinicial", query = "SELECT l FROM LinemobConta l WHERE l.valorsaldoinicial = :valorsaldoinicial"),
    @NamedQuery(name = "LinemobConta.findByIdsqlite", query = "SELECT l FROM LinemobConta l WHERE l.idsqlite = :idsqlite")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 4000)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DATAFUNDACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datafundacao;
    @Column(name = "VALORSALDOINICIAL")
    private BigInteger valorsaldoinicial;
    @Column(name = "IDSQLITE")
    private BigInteger idsqlite;
    @OneToMany(mappedBy = "idconta")
    private Collection<Cartao> linemobCartaoCollection;
    @OneToMany(mappedBy = "idconta")
    private Collection<Movimento> linemobMovimentoCollection;

    public Conta() {
    }

    public Conta(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatafundacao() {
        return datafundacao;
    }

    public void setDatafundacao(Date datafundacao) {
        this.datafundacao = datafundacao;
    }

    public BigInteger getValorsaldoinicial() {
        return valorsaldoinicial;
    }

    public void setValorsaldoinicial(BigInteger valorsaldoinicial) {
        this.valorsaldoinicial = valorsaldoinicial;
    }

    public BigInteger getIdsqlite() {
        return idsqlite;
    }

    public void setIdsqlite(BigInteger idsqlite) {
        this.idsqlite = idsqlite;
    }

    @XmlTransient
    public Collection<Cartao> getLinemobCartaoCollection() {
        return linemobCartaoCollection;
    }

    public void setLinemobCartaoCollection(Collection<Cartao> linemobCartaoCollection) {
        this.linemobCartaoCollection = linemobCartaoCollection;
    }

    @XmlTransient
    public Collection<Movimento> getLinemobMovimentoCollection() {
        return linemobMovimentoCollection;
    }

    public void setLinemobMovimentoCollection(Collection<Movimento> linemobMovimentoCollection) {
        this.linemobMovimentoCollection = linemobMovimentoCollection;
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
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linepack.linemobapi.LinemobConta[ id=" + id + " ]";
    }
    
}
