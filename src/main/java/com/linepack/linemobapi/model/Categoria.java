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
public class Categoria implements Serializable {

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
    @Size(max = 4000)
    @Column(name = "NOMESUBCATEGORIA")
    private String nomesubcategoria;
    @Column(name = "IDSQLITE")
    private BigInteger idsqlite;
    @OneToMany(mappedBy = "idcategoria")
    private Collection<Movimento> linemobMovimentoCollection;

    public Categoria() {
    }

    public Categoria(BigDecimal id) {
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

    public String getNomesubcategoria() {
        return nomesubcategoria;
    }

    public void setNomesubcategoria(String nomesubcategoria) {
        this.nomesubcategoria = nomesubcategoria;
    }

    public BigInteger getIdsqlite() {
        return idsqlite;
    }

    public void setIdsqlite(BigInteger idsqlite) {
        this.idsqlite = idsqlite;
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linepack.linemobapi.LinemobCategoria[ id=" + id + " ]";
    }
    
}
