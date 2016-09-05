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

    public Conta(String nome, String datafundacao, Integer valorsaldoinicial) {
        this.nome = nome;
        this.dataFundacao = datafundacao;
        this.valorSaldoInicial = valorsaldoinicial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatafundacao() {
        return dataFundacao;
    }

    public void setDatafundacao(String datafundacao) {
        this.dataFundacao = datafundacao;
    }

    public Integer getValorsaldoinicial() {
        return valorSaldoInicial;
    }

    public void setValorsaldoinicial(Integer valorsaldoinicial) {
        this.valorSaldoInicial = valorsaldoinicial;
    }

}
