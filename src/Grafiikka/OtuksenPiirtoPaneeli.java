/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafiikka;

import Logiikka.Otus;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Otusten piirtämisen hoitava JPanel-luokka.
 *
 * @author lvapaaka
 */
public class OtuksenPiirtoPaneeli extends JPanel {

    /**
     * Piirrettävä otus.
     */
    private Otus otus;
    /**
     * Otuksen piirtämiselle elintärkeä oksalista.
     */
    private LinkedList<Oksa> oksat = new LinkedList<Oksa>();
    /**
     * Ensimmäinen Geeni, lisää (tai vähentää) haarautumiskulmaa.
     */
    private int kulmanMuutos;
    /**
     * Toinen Geeni, pidentää (tai lyhentää) piirrettäviä viivoja.
     */
    private int pituudenMuutos;
    /**
     * Kolmas Geeni, määrittää puun haarautumisten määrän välillä 0-18 (-9 = 0,
     * 9 = 18).
     */
    private int puunHaarautumistenMaara;
    /**
     * Neljäs Geeni, jolla määritetään viivojen pituuteen vaikuttava kerroin.
     */
    private int oksienLyhenemisVauhti;
    /**
     * Viides Geeni, joka määrittää otuksen leveyden kertoimen välillä (n.)
     * 0.1-2.0.
     */
    private int ynKasvunVahvistin;
    /**
     * Kuudes Geeni, joka määrittää otuksen korkeuden kertoimen välillä (n.)
     * 0.1-2.0.
     */
    private int xnKasvunVahvistin;
    /**
     * Seitsemäs Geeni, joka muuttaa haarautumiskulmaa tulona haaran suhteen.
     */
    private int kieroutuminen;
    /**
     * Kahdeksas Geeni, joka toimii kieroutumisen kaltaisesti, mutta vahvemmin
     * alussa ja heikommin lopussa.
     */
    private int kaanteisKieroutuminen;
    /**
     * Viimeinen Geeni, joka lisää puun haarautumista, eli oksien lähtösuuntaa
     * suhteessa toisiinsa.
     */
    private int haarautuvuus;
    /**
     * Kulmien määrittämisen mahdollistava perusarvo.
     */
    private int punaisuus;
    private int vihreys;
    private int sinisyys;
    private int varinSiirtyma;
    private double kymmenisenAstetta = (Math.PI / 180) * 9.5;
    /**
     * Puun viivojen pituuteen vaikuttava perusarvo.
     */
    private int oletusPituus = 15;

    public OtuksenPiirtoPaneeli(Otus otus) {
        this.otus = otus;
        puraGenomi(otus.getGenomi().getGeenit());
    }

    /**
     * Metodi purkaa genomin yksittäisiksi geeneiksi, jotta niiden vaikutuksen
     * voi nimetä paremmin ja niitä käyttää intuitiivisemmin.
     *
     * @param geenit otuksen Genomilta pyydetty taulukko.
     */
    private void puraGenomi(int[] geenit) {
        kulmanMuutos = geenit[0];
        pituudenMuutos = geenit[1];
        puunHaarautumistenMaara = geenit[2];
        oksienLyhenemisVauhti = geenit[3];
        ynKasvunVahvistin = geenit[4];
        xnKasvunVahvistin = geenit[5];
        kieroutuminen = geenit[6];
        kaanteisKieroutuminen = geenit[7];
        haarautuvuus = geenit[8];
        punaisuus = geenit[9];
        vihreys = geenit[10];
        sinisyys = geenit[11];
        varinSiirtyma = geenit[12];
    }

    /**
     * Paneelin sisältämän otuksen päivittämisen mahdollistava setteri.
     *
     * @param otus
     */
    public void setOtus(Otus otus) {
        this.otus = otus;
        puraGenomi(otus.getGenomi().getGeenit());
    }

    /**
     * Metodi jossa otuksen puurakenne määritetään listan avulla piste pisteeltä
     * käyttäen apumetodia seuraavatPisteet
     *
     * @see Logiikka.GenominTulkitsin#seuraavatPisteet(java.util.LinkedList)
     *
     * @return palauttaa (tyhjän) listan. Ilman syytä?
     */
    @Override
    public void paintComponent(Graphics g) {
        float[] varit = Color.RGBtoHSB(RGBioi(punaisuus), RGBioi(vihreys), RGBioi(sinisyys), null);
        saadaVari(g, 1);


        g.drawLine(200, 150 + (oletusPituus + (pituudenMuutos * 2)), 200, 150);
        oksat.addFirst(new Oksa(200, 150, 0, 0));
        while (oksat.peekFirst() != null) {
            seuraavatPisteet(g);
        }
    }

    private void saadaVari(Graphics g, float kerroin) {
        float[] varit = Color.RGBtoHSB(RGBioi(punaisuus), RGBioi(vihreys), RGBioi(sinisyys), null);
        float savy = varit[0] * kerroin;
        float satu = varit[1] * kerroin;
        float kirk = varit[2] * kerroin;
        g.setColor(Color.getHSBColor(savy, satu, kirk));
    }

    private int RGBioi(int arvo) {
        return (arvo + 9) * 13;
    }

    /**
     * Metodi, joka ottaa listan ensimmäisen Oksan, tarkistaa haaramäärän ja
     * toimii "portsarina" piirtometodeille.
     *
     * @param g grafiikka-Olio
     */
    private void seuraavatPisteet(Graphics g) {
        Oksa oksa = oksat.poll();
        int haara = oksa.getHaara();
        if (!(haara > puunHaarautumistenMaara + 9)) {
            piirraHaarat(g, oksa);
        }
    }

    /**
     * Metodi, joka kerää piirtämiseen tarvittavat arvot oksalta ja
     * apumetodeilta, jotka se lähettää lopulliselle piirtometodille.
     *
     * @param g Grafiikka-olio
     * @param oksa listasta vedetty ensimmäinen oksa.
     */
    private void piirraHaarat(Graphics g, Oksa oksa) {
        int haara = oksa.getHaara();
        double kulma = oksa.getKulma();
        double[] xyKasvu = xJayKasvu(haara);
        double[] kulmat = vasenJaOikeaKulma(kulma, haara);
        for (int i = 0; i < 2; i++) {
            piirraJaLisaaOksa(oksa.getX(), oksa.getY(), kulmat[i], xyKasvu[0], xyKasvu[1], g, haara);
        }
    }

    /**
     * Metodi, joka palauttaa vasemman ja oikean kulman arvot taulukkona.
     *
     * Metodi käyttää Otuksen geenejä numerot: 1: kulmanMuutos 7: kieroutuminen
     * 8: käänteiskieroutuminen 9: haarautuvuus
     *
     * @param kulma edellisen oksan kulma, johon lisäämällä ja vähentämällä uusi
     * kulma saadaan.
     * @param haara käsiteltävän haaran järjestysnumero
     * @return kaksipaikkainen taulukko [vasen kulma, oikea kulma]
     */
    private double[] vasenJaOikeaKulma(double kulma, int haara) {
        double[] kulmat = new double[2];
        double haaraKohtainenKulmanMuutos = (9 * kymmenisenAstetta)
                + (kymmenisenAstetta * kulmanMuutos)
                + ((kymmenisenAstetta / 10) * (kieroutuminen * haara))
                + (kymmenisenAstetta * 2 * (kaanteisKieroutuminen - haara));
        kulmat[0] = kulma + haaraKohtainenKulmanMuutos + haarautuvuus;
        kulmat[1] = kulma - haaraKohtainenKulmanMuutos - haarautuvuus;
        return kulmat;
    }

    /**
     * Metodi, joka palauttaa x:n ja y:n kasvumäärät taulukossa.
     *
     * Metodi käyttää Otuksen geenejä numerot: 2: pituudenMuutos 4:
     * oksienLyhenemisVauhti 5: xnkasvunVahvistin 6: ynkasvunVahvistin
     *
     * @param haara käsiteltävän haaran järjestysnumero
     * @return kaksipaikkainen taulukko [xKasvu,yKasvu]
     */
    private double[] xJayKasvu(int haara) {
        double[] xyKasvu = new double[2];
        double oksienLyhenemisKerroin = ((100 - (oksienLyhenemisVauhti * haara * 5.0)) / 100.0);
        double pituus = (oletusPituus + (pituudenMuutos * 2)) * oksienLyhenemisKerroin;
        xyKasvu[0] = pituus * ((5.5 + xnKasvunVahvistin) / 9.0);
        xyKasvu[1] = pituus * ((5.5 + ynKasvunVahvistin) / 9.0);
        return xyKasvu;
    }

    /**
     * Hyvin paljon arvoja kaipaava puunoksia eli viivoja piirtävä metodi.
     * Metodi myös lisää listaan uuden Oksan.
     *
     * @param alkux edellisen oksan loppupisteen x:n arvo
     * @param alkuy edellisen oksan loppupisteen y:n arvo
     * @param kulma kulma jossa viiva piirretään
     * @param xKasvu x:n pituuden määrittävä arvo
     * @param yKasvu y:n pituuden määrittävä arvo
     * @param g Grafiikka-olio
     * @param haara haaran järjestysnumero
     */
    private void piirraJaLisaaOksa(double alkux, double alkuy, double kulma, double xKasvu, double yKasvu, Graphics g, int haara) {
        double loppux = alkux - (Math.sin(kulma) * xKasvu);
        double loppuy = alkuy - (Math.cos(kulma) * yKasvu);
        saadaVari(g, (float) (1 * ((100 - (varinSiirtyma * haara * 5.0)) / 100.0)));
        g.drawLine((int) alkux, (int) alkuy, (int) loppux, (int) loppuy);
//        g.drawString(otus.toString(), 0, 10);
        oksat.addFirst(new Oksa(loppux, loppuy, haara + 1, kulma));
    }
}
