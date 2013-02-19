/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

/**
 * Ohjelman kohde ja perusyksikkö, Genomin sisältävä luokka, joita
 * OtuksenPiirtoPaneeli piirtää.
 *
 * @see Logiikka.Genomi
 * @see Grafiikka.OtuksenPiirtoPaneeli
 * @author lvapaaka
 */
public class Otus {

    /**
     * Koko Otus perustuu sen Genomiin.
     */
    private Genomi genomi;

    public Otus(Genomi genomi) {
        this.genomi = genomi;
    }

    /**
     * Palauttaa otuksen genomin
     *
     * @return palautettava Genomi
     */
    public Genomi getGenomi() {
        return genomi;
    }

    /**
     * Otuksesta printataaan jokaisen geenin arvo.
     *
     * @return muotoa (x,x,x,x,x,x,x,x,x)
     */
    @Override
    public String toString() {
        String palautettava = "";
        int[] geenit = genomi.getGeenit();
        for (int i = 0; i < geenit.length; i++) {
            palautettava += geenit[i];
            if (i < geenit.length - 1) {
                palautettava += ",";
            }
        }
        return palautettava;
    }
}
