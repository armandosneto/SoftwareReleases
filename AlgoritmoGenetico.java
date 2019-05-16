/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarereleases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Armando Neto
 */
public class AlgoritmoGenetico {
    
    private int cromossomoTam;
    private int populacaoTam;
    private int geracoesMax;
    private float taxaCruzamento;
    private float taxaMutacao;
    
   

    public Random r = new Random();
    
    public int getCromossomoTam() {
        return cromossomoTam;
    }

    public void setCromossomoTam(int cromossomoTam) {
        this.cromossomoTam = cromossomoTam;
    }

    public int getPopulacaoTam() {
        return populacaoTam;
    }

    public void setPopulacaoTam(int populacaoTam) {
        this.populacaoTam = populacaoTam;
    }

    public int getGeracoesMax() {
        return geracoesMax;
    }

    public void setGeracoesMax(int geracoes) {
        this.geracoesMax = geracoes;
    }

    public float getTaxaCruzamento() {
        return taxaCruzamento;
    }

    public void setTaxaCruzamento(float taxaCruzamento) {
        this.taxaCruzamento = taxaCruzamento;
    }

    public float getTaxaMutacao() {
        return taxaMutacao;
    }

    public void setTaxaMutacao(float taxaMutacao) {
        this.taxaMutacao = taxaMutacao;
    }
    
    AlgoritmoGenetico(){
        cromossomoTam= 10;
        populacaoTam = 150;
        geracoesMax = 200;
        taxaCruzamento = 0.95f;
        taxaMutacao = 0.05f;
    }
    
    public ArrayList<Individuo> populacao = new ArrayList<>();
    public ArrayList<Individuo> filhosDoAmanha = new ArrayList<>();
    
    public Individuo Resolver(){
        this.iniciarPopulacao();
        Collections.sort(this.populacao);
        int geracao = 0;
        
        while(geracao < this.geracoesMax){
            this.cruzamento();
            this.mutacao();
            this.Rank();
            System.out.println(this.avgGenerationFitness() + "\t" + this.populacao.get(0).getFitness()+"\t" + this.populacao.get(populacaoTam-1).getFitness());

            geracao++;
        }
        
        System.out.println("\n\n Temos um Campeão!\t"+ Arrays.toString(populacao.get(0).cromossomo) + "Fitness: " + populacao.get(0).getFitness());
        
        return populacao.get(0);
        
        
    }
    
    public void iniciarPopulacao(){
        for(int i =0; i< populacaoTam; i++ ){
            int[] genes = new int[cromossomoTam];
            for (int j = 0; j < genes.length; j++){
                genes[j] = r.nextInt(4);
            }
            Individuo novo = new Individuo(genes);
            novo.reparar();
            populacao.add(novo);
            
        }
        
    }
    
    public void cruzamento(){
        while (filhosDoAmanha.size()< populacaoTam){
            int papai;
            int mamae;
            do{
                papai = torneioBinario();
                mamae = torneioBinario();   
            }while(papai == mamae);
            
            if(r.nextFloat() < taxaCruzamento){
                ArrayList<Individuo> filhos = this.pontoDeCorte(populacao.get(papai),populacao.get(mamae));
                this.filhosDoAmanha.add(new Individuo(filhos.get(0).cromossomo));
                this.filhosDoAmanha.add(new Individuo(filhos.get(1).cromossomo));
            }else{
                this.filhosDoAmanha.add(new Individuo(populacao.get(papai).cromossomo));
                this.filhosDoAmanha.add(new Individuo(populacao.get(mamae).cromossomo));
            }
        }
        //for(Individuo individuo : filhosDoAmanha){
                
          //      individuo.reparar();
                
            //}
        
    }
    
    	public void mutacao() {
		for (Individuo individuo : filhosDoAmanha) {
			for (int i = 0; i < individuo.cromossomo.length; i++) {
				if (r.nextFloat() <= taxaMutacao){
					individuo.cromossomo[i] = r.nextInt(4);
                                }
				
			}
                        individuo.reparar();
		}
	}

    
    public int torneioBinario(){
        int[] candidato = new int[2];
        for(int i=0;i<2;i++){
            candidato[i] = r.nextInt(populacaoTam);
        }
        
        if(populacao.get(candidato[0]).getFitness()>populacao.get(candidato[1]).getFitness()){
            return candidato[0];
        }else{
            return candidato[1];
        }
        
    }
    
    public ArrayList<Individuo> pontoDeCorte(Individuo papai, Individuo mamae) {

		ArrayList<Individuo> filhos = new ArrayList<>();

		int cut_point = r.nextInt(cromossomoTam - 2) + 1;
		int[] filho_a = new int[cromossomoTam];
		int[] filho_b = new int[cromossomoTam];

		for (int i = 0; i < cromossomoTam; i++) {

			if (i <= cut_point) {
				filho_a[i] = papai.cromossomo[i];
				filho_b[i] = mamae.cromossomo[i];
			} else {
				filho_a[i] = mamae.cromossomo[i];
				filho_b[i] = papai.cromossomo[i];
			}

		}
                Individuo filhote_a = new Individuo(filho_a);
                filhote_a.reparar();
                Individuo filhote_b = new Individuo(filho_b);
                filhote_b.reparar();
                
                
		filhos.add(filhote_a);
		filhos.add(filhote_b);

		return filhos;
	}
    
    	private void Rank() {

		for (Individuo individuo : filhosDoAmanha)
			this.populacao.add(new Individuo(individuo.cromossomo));
                
                for(int i=(populacao.size()-filhosDoAmanha.size());i<populacao.size();i++){
                    populacao.get(i).reparar();
                }
		Collections.sort(this.populacao);
                

		for (int i = this.populacao.size() - 1; i >= this.populacaoTam; i--)
			this.populacao.remove(i);

		this.filhosDoAmanha.clear();

	}
        
        public float avgGenerationFitness() {
		float avg = 0;
		for (Individuo individuo : populacao)
			avg += individuo.getFitness();
		return avg / populacao.size();
	}
}
