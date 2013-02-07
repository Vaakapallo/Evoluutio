/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafiikka;

import Logiikka.Genomi;
import Logiikka.Otus;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 * Pääohjelma, joka syöttää EvoluutioFrameen 9 sattumanvaraista Otusta.
 *
 * @see Grafiikka.EvoluutioFrame
 * @author lvapaaka
 */
public class EvoluutioMain {

    /**
     * Lista eteenpäin syötettäviä otuksia.
     */
    private static ArrayList<Otus> otukset = new ArrayList<Otus>();
    private static Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            otukset.add(randomOtus());
        }
        EvoluutioFrame ikkuna = new EvoluutioFrame(otukset);
        ikkuna.setTitle("Evoluutio");
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setSize(1200, 1000);
        ikkuna.setVisible(true);
    }

    /**
     * Sattumanvaraisia otuksia luova metodi. Arvottujen Otusten geenien arvot
     * on rajattu välille (-5,5), vähentämään liian äärimmäisten otusten
     * ylimäärää alkuvalinnassa.
     *
     * Toisen geenin arvoa on vähennetty vielä muutamalla asteella lisää, sillä
     * se määrittää puun jakautumisia ja suurilla arvoilla hidastaa ohjelman
     * toimintaa.
     *
     * @return Hieno uusi sattumanvarainen otus.
     */
    private static Otus randomOtus() {
        int[] genomi = new int[9];
        for (int i = 0; i < 9; i++) {
            genomi[i] = random.nextInt(11) - 5;
            if (i == 2) {
                genomi[i] -= 2;
            }
        }

        return new Otus(new Genomi(genomi));
    }
}
