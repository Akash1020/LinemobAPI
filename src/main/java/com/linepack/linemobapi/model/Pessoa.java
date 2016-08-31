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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name = "LINEMOB_PESSOA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinemobPessoa.findAll", query = "SELECT l FROM LinemobPessoa l"),
    @NamedQuery(name = "LinemobPessoa.findById", query = "SELECT l FROM LinemobPessoa l WHERE l.id = :id"),
    @NamedQuery(name = "LinemobPessoa.findByNome", query = "SELECT l FROM LinemobPessoa l WHERE l.nome = :nome"),
    @NamedQuery(name = "LinemobPessoa.findByApelido", query = "SELECT l FROM LinemobPessoa l WHERE l.apelido = :apelido"),
    @NamedQuery(name = "LinemobPessoa.findByIdsqlite", query = "SELECT l FROM LinemobPessoa l WHERE l.idsqlite = :idsqlite")})
public class Pessoa extends BaseModel {

    @Size(max = 4000)
    @Column(name = "NOME")
    private String nome;
    @Size(max = 4000)
    @Column(name = "APELIDO")
    private String apelido;

    @OneToMany(mappedBy = "idpessoa")
    private Collection<Movimento> linemobMovimentoCollection;

    public Pessoa() {
    }

    public Pessoa(String nome, String apelido) {
        this.nome = nome;
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    @XmlTransient
    public Collection<Movimento> getLinemobMovimentoCollection() {
        return linemobMovimentoCollection;
    }

    public void setLinemobMovimentoCollection(Collection<Movimento> linemobMovimentoCollection) {
        this.linemobMovimentoCollection = linemobMovimentoCollection;
    }
}
