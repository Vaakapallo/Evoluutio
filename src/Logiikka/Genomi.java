/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

/**
 * Otuksen genomista kirjaa pitävä luokka, joka voi myös kopioida sen tarvittaessa.
 *
 * @author lvapaaka
 */
public class Genomi {
    
    /**
     * Genomin sisältö, tulkitaan geeneiksi GenominTulkitsimessa
     */
    private int[] geenit = new int[9];

    public Genomi(int[] geenit) {
        this.geenit = geenit;
    }
     /**
     *  Metodi palauttaa geenit 9-paikkaisena int-taulukkona
     * 
     * @return taulukko
     */
    public int[] getGeenit(){
        return geenit;
    }
    
     /**
     * Metodi tekee Genomista aidon kopion, joka mahdollistaa otusten Genomien
     * mutatoinnin ilman niiden vanhempien genomien muuttamista.
     * 
     * @return Palauttaa luonnollisesti uuden Genomin.
     */
    public Genomi kopioiGenomi(){
        int[] kopio = new int[9];
        System.arraycopy(geenit, 0, kopio, 0, 9);
        return new Genomi(kopio);
    }
}
