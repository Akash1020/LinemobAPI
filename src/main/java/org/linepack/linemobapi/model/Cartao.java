/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.model;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leandro
 */
@XmlRootElement
public class Cartao extends BaseModel {

    private String nome;
    private Integer diavencimento;
    private Integer diafechamento;
    private Integer valorlimite;
    private Conta idconta;

    public Cartao() {
    }

    public Cartao(String nome, Integer diavencimento, Integer diafechamento, Integer valorlimite, Conta idconta) {
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

    public Integer getDiavencimento() {
        return diavencimento;
    }

    public void setDiavencimento(Integer diavencimento) {
        this.diavencimento = diavencimento;
    }

    public Integer getDiafechamento() {
        return diafechamento;
    }

    public void setDiafechamento(Integer diafechamento) {
        this.diafechamento = diafechamento;
    }

    public Integer getValorlimite() {
        return valorlimite;
    }

    public void setValorlimite(Integer valorlimite) {
        this.valorlimite = valorlimite;
    }

    public Conta getIdconta() {
        return idconta;
    }

    public void setIdconta(Conta idconta) {
        this.idconta = idconta;
    }

}
