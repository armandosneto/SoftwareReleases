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
public class Individuo implements Comparable<Individuo>{
    Requisitos req = new Requisitos();
    private int cromossomoTam;
    public int[] cromossomo;
    private boolean valido = false;
    
    public int getFitness() {
        return fitness;
    }
    private int fitness;
    
    public Individuo(int[] releases) {
	this.cromossomoTam = releases.length;
	this.cromossomo = releases.clone();
        
	this.fitness=0;
    }

    public int[] getCromossomo() {
        return cromossomo;
    }
    
    public int valorar(){
      int score = 0;
      
      for(int xi=0; xi<cromossomoTam;xi++){
          if(cromossomo[xi]!= 0){
              score += ((4- cromossomo[xi])*req.tabela[xi][1] - cromossomo[xi]* req.tabela[xi][0]);
          }
          
      }
        return score;
    }
    
    public void validar(int[] custo){

       if(custo[1]>125||custo[2]>125||custo[3]>125){
           valido = false;
       }else{
           valido = true;
       }
    }

    public void reparar() {
        int[] custo = new int[4];
        Random r = new Random();
        while(valido != true){
            for(int j = 0;j<4;j++){
                custo[j] = 0;
            }
            switch (r.nextInt(2)){
                case 1:
                    for(int i =0;i<cromossomoTam;i++){
                        switch(this.cromossomo[i]){
                            case 1:
                                if((custo[1]+req.tabela[i][2])<=125){
                                    custo[1] += req.tabela[i][2];
                                }
                                /*else if(custo[2]<125 || custo[3]<125){
                                    this.cromossomo[i] = menorCusto(custo,2,3);
                                    custo[menorCusto(custo,2,3)] += req.tabela[i][2];
                                }*/
                                else{
                                    this.cromossomo[i] = 0;
                                }
                                break;
                            case 2:
                                if((custo[2]+req.tabela[i][2])<=125){
                                    custo[2] += req.tabela[i][2];
                                }
                                /*else if(custo[1]<125 || custo[3]<125){
                                    this.cromossomo[i] = menorCusto(custo,1,3);
                                    custo[menorCusto(custo,1,3)] += req.tabela[i][2];
                                }*/
                                else{
                                    this.cromossomo[i] = 0;
                                }
                                break;
                            case 3:
                                if((custo[3]+req.tabela[i][2])<=125){
                                    custo[3] += req.tabela[i][2];
                                }
                                /*else if(custo[1]<125 || custo[2]<125){
                                    this.cromossomo[i] = menorCusto(custo,1,2);
                                    custo[menorCusto(custo,1,2)] += req.tabela[i][2];
                               }*/
                                else{
                                    this.cromossomo[i] = 0;
                                }
                                break;
                            default:
                                break;
                        }
                      
                    }
                break;
                    
                case 0:
                    for(int i=(cromossomoTam-1);i>=0;i--){
                        switch(this.cromossomo[i]){
                            case 1:
                                if((custo[1]+req.tabela[i][2])<=125){
                                    custo[1] += req.tabela[i][2];
                                }
                                /*else if(custo[2]<125 || custo[3]<125){
                                    this.cromossomo[i] = menorCusto(custo,2,3);
                                    custo[menorCusto(custo,2,3)] += req.tabela[i][2];
                                }*/
                                else{
                                    this.cromossomo[i] = 0;
                                }
                                break;
                            case 2:
                                if((custo[2]+req.tabela[i][2])<=125){
                                    custo[2] += req.tabela[i][2];
                                }
                                /*else if(custo[1]<125 || custo[3]<125){
                                    this.cromossomo[i] = menorCusto(custo,1,3);
                                    custo[menorCusto(custo,1,3)] += req.tabela[i][2];
                                }*/
                                else{
                                    this.cromossomo[i] = 0;
                                }
                                break;
                            case 3:
                                if((custo[3]+req.tabela[i][2])<=125){
                                    custo[3] += req.tabela[i][2];
                                }
                                /*else if(custo[1]<125 || custo[2]<125){
                                    this.cromossomo[i] = menorCusto(custo,1,2);
                                    custo[menorCusto(custo,1,2)] += req.tabela[i][2];
                                }*/
                                else{
                                    this.cromossomo[i] = 0;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                break;
            }
            validar(custo);
        }
        fitness = valorar();

    }

public int menorCusto(int[] releases,int a, int b) {

    if(releases[a]>releases[b]){
        return b;
    }else{
        return a;
    }
}

    @Override
    public int compareTo(Individuo o) {
		
		if(this.fitness > o.fitness) {
			return -1;
		}
		if(this.fitness < o.fitness) {
			return 1;
		}
		
		return 0;
	}

}