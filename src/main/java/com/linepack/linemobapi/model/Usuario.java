/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linepack.linemobapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "LINEMOB_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinemobUsuario.findAll", query = "SELECT l FROM LinemobUsuario l"),
    @NamedQuery(name = "LinemobUsuario.findById", query = "SELECT l FROM LinemobUsuario l WHERE l.id = :id"),
    @NamedQuery(name = "LinemobUsuario.findByUsuario", query = "SELECT l FROM LinemobUsuario l WHERE l.usuario = :usuario"),
    @NamedQuery(name = "LinemobUsuario.findByIdsqlite", query = "SELECT l FROM LinemobUsuario l WHERE l.idsqlite = :idsqlite")})
public class Usuario extends BaseModel {

    @Size(max = 4000)
    @Column(name = "USUARIO")
    private String usuario;

    public Usuario() {
    }

    public Usuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
