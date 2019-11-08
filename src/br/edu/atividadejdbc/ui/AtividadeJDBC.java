/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.atividadejdbc.ui;

import br.edu.atividadejdbc.dao.ProdutoDAO;
import br.edu.atividadejdbc.model.Produto;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author henri
 */
public class AtividadeJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
            Produto p;
            String op="";
            int id;
            ProdutoDAO dao = new ProdutoDAO();
            List<Produto> list;
            
            do{
                System.out.println("------------------ MENU -----------------------");
                System.out.println("-01- INSERIR ");  
                System.out.println("-02- EDITAR ");
                System.out.println("-03- BUSCAR ");
                System.out.println("-04- LISTAR  ");
                System.out.println("-05- EXCLUIR ");
                System.out.println("-06- SAIR");
                
                op= input.nextLine();
                System.out.println("opcao escolhida: "+ op);
                
                switch(op) {
                    case "1":
                        p = new Produto();
                        
                        System.out.println("---------------- INSERIR ----------------------");
                        System.out.println(" Nome produto: ");
                        p.setNome(input.nextLine());
                        
                        System.out.println("Preço :");
                        p.setPreco(input.nextFloat());
                        input.nextLine();
                        
                        System.out.println("Fabricante: ");
                        p.getEspecificacao().setFabricante(input.nextLine());
                        
                        System.out.println("Sistema: ");
                        p.getEspecificacao().setSistema(input.nextLine());
                        
                        System.out.println("Cor: ");
                        p.getEspecificacao().setCor(input.nextLine());
                        System.out.println("detalhes: ");
                        p.getEspecificacao().setDetalhes(input.nextLine());
                        
                        dao.inserir(p);
                        break;
                        
                    case "2":
                        System.out.println("-------------- EDITAR ----------------------------");
                        System.out.println("Qual codigo do produto para edição: ");
                        id = Integer.parseInt(input.nextLine());
                        
                        p = dao.buscarPorCodigo(id);
                        if(p != null)
                        {
                            System.out.println("Produto encontrado: "+ p);
                            System.out.println("novo nome: ");
                            p.setNome(input.nextLine());
                            System.out.println("novo preço: ");
                            p.setPreco(Float.parseFloat(input.nextLine()));
                            System.out.println("novo Fabricante:");
                            p.getEspecificacao().setFabricante(input.nextLine());
                        
                            System.out.println("novo Sistema: ");
                            p.getEspecificacao().setSistema(input.nextLine());
                        
                            System.out.println("nova Cor: ");
                            p.getEspecificacao().setCor(input.nextLine());
                            System.out.println("novos detalhes:");
                            p.getEspecificacao().setDetalhes(input.nextLine());
                            
                            
                            dao.editar(p);
                            System.out.println("Registro alterado com Sucesso!");
                        }
                        else
                            System.out.println("Registro não alterado ou não existe!");
                        break;
                    
                    case "3":
                        System.out.println("-------------BUSCAR POR CODIGO-------------------");
                        System.out.println("Qual Código quer consultar:");
                        id = Integer.parseInt(input.nextLine());
                        p = dao.buscarPorCodigo(id);
                        if(p!=null)
                            System.out.println(p);
                        else
                            System.out.println("Código de Produto não encontrado ou não existe!!!");
                        break;
                
                    case "4":
                        System.out.println("-------------LISTAR PRODUTOS-------------------");
                        list = dao.listar();
                        //System.out.println(list);
                        if(list!=null)
                        {
                            for (Produto pr : list)
                                System.out.println(pr);
                        }
                        else
                            System.out.println("Sem produtos para listar!");
                        break;
                        
                    case "5":
                        System.out.println("-------------------EXCLUIR----------------------");
                        System.out.println("digite o codigo do produto que deseja excluir:");
                        id = Integer.parseInt(input.nextLine());
                        p = dao.buscarPorCodigo(id);
                        if(p != null){
                            dao.excluir(p);
                            System.out.println("produto " +p.getNome()+ " , excluido");
                        }
                        else
                            System.out.println("Produto nao existe ou nao excluido!!");
                        break;
                        
                    case "6":
                        System.out.println("Saindo!");
                        break;
                    default:
                        System.out.println("entrada inválida!");
                        
                }
                
            } while(!op.equals("6"));
    }
    
}
