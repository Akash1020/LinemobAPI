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
@Table(name = "LINEMOB_CATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinemobCategoria.findAll", query = "SELECT l FROM LinemobCategoria l"),
    @NamedQuery(name = "LinemobCategoria.findById", query = "SELECT l FROM LinemobCategoria l WHERE l.id = :id"),
    @NamedQuery(name = "LinemobCategoria.findByNome", query = "SELECT l FROM LinemobCategoria l WHERE l.nome = :nome"),
    @NamedQuery(name = "LinemobCategoria.findByNomesubcategoria", query = "SELECT l FROM LinemobCategoria l WHERE l.nomesubcategoria = :nomesubcategoria"),
    @NamedQuery(name = "LinemobCategoria.findByIdsqlite", query = "SELECT l FROM LinemobCategoria l WHERE l.idsqlite = :idsqlite")})
public class Categoria extends BaseModel {

    @Size(max = 4000)
    @Column(name = "NOME")
    private String nome;
    @Size(max = 4000)
    @Column(name = "NOMESUBCATEGORIA")
    private String nomesubcategoria;
    @OneToMany(mappedBy = "idcategoria")
    private Collection<Movimento> linemobMovimentoCollection;

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

    @XmlTransient
    public Collection<Movimento> getLinemobMovimentoCollection() {
        return linemobMovimentoCollection;
    }

    public void setLinemobMovimentoCollection(Collection<Movimento> linemobMovimentoCollection) {
        this.linemobMovimentoCollection = linemobMovimentoCollection;
    }

}
