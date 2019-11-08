/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.atividadejdbc.model;

/**
 *
 * @author henri
 */
public class Produto {

    private int id;
    private String nome;
    private float preco;
    private Especificacao especificacao = new Especificacao();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("id: ")
                .append(id)
                .append(" - Nome: ")
                .append(nome)
                .append(" - Preço: ")
                .append(preco)
                .append(" - Codigo Especificacao: ")
                .append(especificacao);

        return sb.toString();
    }

}
