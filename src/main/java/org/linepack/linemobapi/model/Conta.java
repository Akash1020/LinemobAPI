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
public class Conta extends BaseModel {

    private String nome;
    private String dataFundacao;
    private Integer valorSaldoInicial;

    public Conta() {
    }

    public Conta(String nome, String dataFundacao, Integer valorSaldoInicial) {
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.valorSaldoInicial = valorSaldoInicial;
    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValorSaldoInicial() {
        return valorSaldoInicial;
    }

    public void setValorSaldoInicial(Integer valorSaldoInicial) {
        this.valorSaldoInicial = valorSaldoInicial;
    }

}
