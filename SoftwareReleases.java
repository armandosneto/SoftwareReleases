/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarereleases;

/**
 *
 * @author Armando Neto
 */
public class SoftwareReleases {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Requisitos req = new Requisitos();
        AlgoritmoGenetico ga = new AlgoritmoGenetico();
    
        ga.Resolver();
        req.imprimirTabela();
       
    }
    
}
