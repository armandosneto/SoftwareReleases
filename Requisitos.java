/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarereleases;

import java.util.Random;

/**
 *
 * @author Armando Neto
 */
public class Requisitos {
    // Gera os requisitos, seus riscos e suas importâncias
    public int numRequisitos;
    int[][] tabela;
    private Random r = new Random();
    

    public Requisitos() {
        
        numRequisitos = 10;
        //Tabela de requisitos exemplo na ordem: risco,importancia,custo
        tabela = new int[][] {{3,80,60},{6,76,40},{2,50,40},{6,53,30},{4,59,20},
            {8,52,20},{9,50,25},{7,59,70},{6,56,50},{6,84,20}};
    }
        


    public int getNumRequisitos() {
        return numRequisitos;
    }
    
    public Requisitos(int tabela[][]){
        numRequisitos = tabela.length;
        this.tabela = tabela.clone();
    }

    public int[][] getTabela() {
        return tabela;
    }
   
    public void imprimirTabela(){
        System.out.println("Tabela de Requisitos: \n");
        System.out.println("N\tRisco\tImpor.\tCusto");
        for(int i = 0; i < 10; i++){    
            System.out.println(i+"\t"+tabela[i][0]+"\t"+tabela[i][1]+"\t"+tabela[i][2]);
        }
    }
    
    
     
     
}
