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
@Table(name = "LINEMOB_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinemobUsuario.findAll", query = "SELECT l FROM LinemobUsuario l"),
    @NamedQuery(name = "LinemobUsuario.findById", query = "SELECT l FROM LinemobUsuario l WHERE l.id = :id"),
    @NamedQuery(name = "LinemobUsuario.findByUsuario", query = "SELECT l FROM LinemobUsuario l WHERE l.usuario = :usuario"),
    @NamedQuery(name = "LinemobUsuario.findByIdsqlite", query = "SELECT l FROM LinemobUsuario l WHERE l.idsqlite = :idsqlite")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 4000)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "IDSQLITE")
    private BigInteger idsqlite;

    public Usuario() {
    }

    public Usuario(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigInteger getIdsqlite() {
        return idsqlite;
    }

    public void setIdsqlite(BigInteger idsqlite) {
        this.idsqlite = idsqlite;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linepack.linemobapi.LinemobUsuario[ id=" + id + " ]";
    }

}
