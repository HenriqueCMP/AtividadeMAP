/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.atividadejdbc.dao;

import br.edu.atividadejdbc.model.Produto;
import br.edu.atividadejdbc.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author henri
 */
public class ProdutoDAO {

    private Connection con;
    private String sql;
    private PreparedStatement st;
    private ResultSet rs;

    public void inserir(Produto produto) throws SQLException {
        // inserir caracteristica
        sql = "insert into especificacao (fabricante, sistema, cor) values(?, ?, ?)";

        con = ConnectionFactory.getConnection();

        // avisa que retornará  a chave primaria gerada, que será usada como chave estrangeira na tabela produto
        st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, produto.getEspecificacao().getFabricante());
        st.setString(2, produto.getEspecificacao().getSistema());
        st.setString(3, produto.getEspecificacao().getCor());

        st.executeUpdate();

        // recuperar a chave primaria(codigo) gerado
        rs = st.getGeneratedKeys();
        int codigoEsp = 0;
        if (rs.next()) {
            codigoEsp = rs.getInt(1);
        }

//        System.out.println(st);
        //inserir produto
        sql = "insert into produto (nome , preco, codigo_especificacao) values(?, ?, ?)";

        con = ConnectionFactory.getConnection();

        st = con.prepareStatement(sql);

        st.setString(1, produto.getNome());
        st.setFloat(2, produto.getPreco());
        st.setInt(3, codigoEsp);

        st.executeUpdate();

        con.close();
    }

    public void editar(Produto produto) throws SQLException {
        sql = "update produto set nome = ?, preco = ? where codigo = ?";

        con = ConnectionFactory.getConnection();
        st = con.prepareStatement(sql);
        st.setString(1, produto.getNome());
        st.setFloat(2, produto.getPreco());
        st.setInt(3, produto.getId());
        st.executeUpdate();

        sql = "update especificacao set fabricante = ?, sistema = ?, cor = ?, detalhes = ? where codigo = ?";
        st = con.prepareStatement(sql);
        st.setString(1, produto.getEspecificacao().getFabricante());
        st.setString(2, produto.getEspecificacao().getSistema());
        st.setString(3, produto.getEspecificacao().getCor());
        st.setString(4, produto.getEspecificacao().getDetalhes());
        st.setInt(5, produto.getEspecificacao().getId());
        st.executeUpdate();

        con.close();

    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        sql = "select p.*, e.* "
                + "from produto p , especificacao e "
                + "where p.codigo_especificacao = e.codigo";
        st = con.prepareStatement(sql);
        rs = st.executeQuery();

        while (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nome = rs.getString(2);
            float preco = rs.getFloat(3);
            int codigo_especificacao = rs.getInt(4);
            String fabricante = rs.getString(6);
            String cor = rs.getString("cor");
            String sistema = rs.getString(8);
            String detalhes = rs.getString(9);

            Produto p = new Produto();

            p.setId(codigo);
            p.setNome(nome);
            p.setPreco(preco);
            p.getEspecificacao().setId(codigo_especificacao);
            p.getEspecificacao().setFabricante(fabricante);
            p.getEspecificacao().setSistema(sistema);
            p.getEspecificacao().setCor(cor);
            p.getEspecificacao().setDetalhes(detalhes);
            produtos.add(p);
        }
        con.close();
        return produtos;
    }

    public Produto buscarPorCodigo(int codigo) throws SQLException {
        sql = "select p.*, e.* "
                + "from produtos p , especificacao e "
                + "where p.codigo_caract = e.codigo "
                + "and p.codigo = ?";
        con = ConnectionFactory.getConnection();
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);

        rs = st.executeQuery();
        Produto p = null;
        if (rs.next()) {

            String nome = rs.getString(2);
            float preco = rs.getFloat(3);
            int codigo_especificacao = rs.getInt(4);
            String fabricante = rs.getString(6);
            String cor = rs.getString("cor");
            String sistema = rs.getString(8);
            String detalhes = rs.getString(9);

            p = new Produto();
            p.setId(codigo);
            p.setNome(nome);
            p.setPreco(preco);
            p.getEspecificacao().setId(codigo_especificacao);
            p.getEspecificacao().setFabricante(fabricante);
            p.getEspecificacao().setSistema(sistema);
            p.getEspecificacao().setCor(cor);
            p.getEspecificacao().setDetalhes(detalhes);

        }
        con.close();
        return p;

    }

    public void excluir(Produto produto) throws SQLException {
        sql = "delete from produtos where codigo = ?";
        con = ConnectionFactory.getConnection();
        st = con.prepareStatement(sql);
        st.setInt(1, produto.getId());
        st.executeUpdate();

        con.close();
    }
}
