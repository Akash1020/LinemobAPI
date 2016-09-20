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
public class Movimento extends BaseModel {

    private String dataLancamento;
    private String dataVencimento;
    private Double valor;
    private Character natureza;
    private String descricao;
    private String idExternoCartao;
    private String idExternoCategoria;
    private String idExternoConta;
    private String idExternoPessoa;

    public Movimento() {
    }

    public Movimento(String dataLancamento, String dataVencimento, Double valor, Character natureza, String descricao, String idExternoCartao, String idExternoCategoria, String idExternoConta, String idExternoPessoa) {
        this.dataLancamento = dataLancamento;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.natureza = natureza;
        this.descricao = descricao;
        this.idExternoCartao = idExternoCartao;
        this.idExternoCategoria = idExternoCategoria;
        this.idExternoConta = idExternoConta;
        this.idExternoPessoa = idExternoPessoa;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Character getNatureza() {
        return natureza;
    }

    public void setNatureza(Character natureza) {
        this.natureza = natureza;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdExternoCartao() {
        return idExternoCartao;
    }

    public void setIdExternoCartao(String idExternoCartao) {
        this.idExternoCartao = idExternoCartao;
    }

    public String getIdExternoCategoria() {
        return idExternoCategoria;
    }

    public void setIdExternoCategoria(String idExternoCategoria) {
        this.idExternoCategoria = idExternoCategoria;
    }

    public String getIdExternoConta() {
        return idExternoConta;
    }

    public void setIdExternoConta(String idExternoConta) {
        this.idExternoConta = idExternoConta;
    }

    public String getIdExternoPessoa() {
        return idExternoPessoa;
    }

    public void setIdExternoPessoa(String idExternoPessoa) {
        this.idExternoPessoa = idExternoPessoa;
    }

}
