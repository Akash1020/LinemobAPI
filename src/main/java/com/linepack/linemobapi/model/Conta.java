/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linepack.linemobapi.model;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
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
public class Conta extends BaseModel {

    @Size(max = 4000)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DATAFUNDACAO")
    @Temporal(TemporalType.TIMESTAMP)    
    private Date datafundacao;
    @Column(name = "VALORSALDOINICIAL")
    private BigInteger valorsaldoinicial;
    @OneToMany(mappedBy = "idconta")
    private Collection<Cartao> linemobCartaoCollection;
    @OneToMany(mappedBy = "idconta")
    private Collection<Movimento> linemobMovimentoCollection;

    public Conta() {
    }

    public Conta(String nome, Date datafundacao, BigInteger valorsaldoinicial) {
        this.nome = nome;
        this.datafundacao = datafundacao;
        this.valorsaldoinicial = valorsaldoinicial;
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

}
