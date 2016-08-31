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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "LINEMOB_CARTAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinemobCartao.findAll", query = "SELECT l FROM LinemobCartao l"),
    @NamedQuery(name = "LinemobCartao.findById", query = "SELECT l FROM LinemobCartao l WHERE l.id = :id"),
    @NamedQuery(name = "LinemobCartao.findByNome", query = "SELECT l FROM LinemobCartao l WHERE l.nome = :nome"),
    @NamedQuery(name = "LinemobCartao.findByDiavencimento", query = "SELECT l FROM LinemobCartao l WHERE l.diavencimento = :diavencimento"),
    @NamedQuery(name = "LinemobCartao.findByDiafechamento", query = "SELECT l FROM LinemobCartao l WHERE l.diafechamento = :diafechamento"),
    @NamedQuery(name = "LinemobCartao.findByValorlimite", query = "SELECT l FROM LinemobCartao l WHERE l.valorlimite = :valorlimite"),
    @NamedQuery(name = "LinemobCartao.findByIdsqlite", query = "SELECT l FROM LinemobCartao l WHERE l.idsqlite = :idsqlite")})
public class Cartao implements Serializable {

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
    @Column(name = "DIAVENCIMENTO")
    private BigInteger diavencimento;
    @Column(name = "DIAFECHAMENTO")
    private BigInteger diafechamento;
    @Column(name = "VALORLIMITE")
    private BigInteger valorlimite;
    @Column(name = "IDSQLITE")
    private BigInteger idsqlite;
    @JoinColumn(name = "IDCONTA", referencedColumnName = "ID")
    @ManyToOne
    private Conta idconta;
    @OneToMany(mappedBy = "idcartao")
    private Collection<Movimento> linemobMovimentoCollection;

    public Cartao() {
    }

    public Cartao(BigDecimal id) {
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

    public BigInteger getDiavencimento() {
        return diavencimento;
    }

    public void setDiavencimento(BigInteger diavencimento) {
        this.diavencimento = diavencimento;
    }

    public BigInteger getDiafechamento() {
        return diafechamento;
    }

    public void setDiafechamento(BigInteger diafechamento) {
        this.diafechamento = diafechamento;
    }

    public BigInteger getValorlimite() {
        return valorlimite;
    }

    public void setValorlimite(BigInteger valorlimite) {
        this.valorlimite = valorlimite;
    }

    public BigInteger getIdsqlite() {
        return idsqlite;
    }

    public void setIdsqlite(BigInteger idsqlite) {
        this.idsqlite = idsqlite;
    }

    public Conta getIdconta() {
        return idconta;
    }

    public void setIdconta(Conta idconta) {
        this.idconta = idconta;
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
        if (!(object instanceof Cartao)) {
            return false;
        }
        Cartao other = (Cartao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linepack.linemobapi.LinemobCartao[ id=" + id + " ]";
    }

}
