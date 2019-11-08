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
public class Especificacao {
    private int id;
    private String fabricante;
    private String cor;
    private String sistema;
    private String detalhes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
    @Override
    public String toString(){ 
        StringBuilder sb = new StringBuilder();
       
        sb.append("Codigo Especificacao: ")
                .append(id)
                .append(" - Fabricante: ")
                .append(fabricante)
                .append(" - Cor: ")
                .append(cor)
                .append(" - Sistema: ")
                .append(sistema)
                .append(" - Detalhes: ")
                .append(detalhes);
        
        return sb.toString();
    }
}
