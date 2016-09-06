/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.linemobapi.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leandro
 */
@XmlRootElement
public class Cartao extends BaseModel {

    private String nome;
    private Integer diaVencimento;
    private Integer diaFechamento;
    private Integer valorLimite;
    private String idConta;

    public Cartao() {
    }

    public Cartao(String nome, Integer diaVencimento, Integer diaFechamento, Integer valorLimite, String idConta) {
        this.nome = nome;
        this.diaVencimento = diaVencimento;
        this.diaFechamento = diaFechamento;
        this.valorLimite = valorLimite;
        this.idConta = idConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Integer getDiaFechamento() {
        return diaFechamento;
    }

    public void setDiaFechamento(Integer diaFechamento) {
        this.diaFechamento = diaFechamento;
    }

    public Integer getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(Integer valorLimite) {
        this.valorLimite = valorLimite;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }
}
