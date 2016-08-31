/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linepack.linemobapi.model;

import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
public class Cartao extends BaseModel {

    @Size(max = 4000)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DIAVENCIMENTO")
    private BigInteger diavencimento;
    @Column(name = "DIAFECHAMENTO")
    private BigInteger diafechamento;
    @Column(name = "VALORLIMITE")
    private BigInteger valorlimite;
    @JoinColumn(name = "IDCONTA", referencedColumnName = "ID")
    @ManyToOne
    private Conta idconta;
    @OneToMany(mappedBy = "idcartao")
    private Collection<Movimento> linemobMovimentoCollection;

    public Cartao() {
    }

    public Cartao(String nome, BigInteger diavencimento, BigInteger diafechamento, BigInteger valorlimite, Conta idconta) {
        this.nome = nome;
        this.diavencimento = diavencimento;
        this.diafechamento = diafechamento;
        this.valorlimite = valorlimite;
        this.idconta = idconta;
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

}
