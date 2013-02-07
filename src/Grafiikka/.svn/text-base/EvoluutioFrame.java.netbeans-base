/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafiikka;

import Logiikka.Mutaatio;
import Logiikka.Otus;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * JFrame-luokka, joka sisältää otukset ja niiden piirtopaneelit listoina ja
 * rekisteröi hiirenklikkaukset.
 *
 * @author Lassi
 */
public class EvoluutioFrame extends JFrame implements MouseListener {

    /**
     * Framen mutaattori, jolla saadaan luotua emo-otuksesta uusia otuksia.
     */
    private Mutaatio muta = new Mutaatio();
    /**
     * Lista jossa on senhetkisen sukupolven otukset ja viimeisenä niiden emo.
     */
    private ArrayList<Otus> otukset = new ArrayList<Otus>();
    /**
     * 9-paikkainen lista Framen Jpaneleista, joiden sisältöä päivitetään.
     */
    private OtuksenPiirtoPaneeli[] paneelit = new OtuksenPiirtoPaneeli[9];

    /**
     * Luokan konstruktori, joka alustaa paneelit ja lisää ne JFrameen.
     *
     * @param otukset mikä tahansa lista otuksia (jossa on ainakin 9 jäsentä)
     */
    public EvoluutioFrame(ArrayList<Otus> otukset) {
        this.otukset = otukset;
        setLayout(new GridLayout(3, 3));
        addMouseListener(this);
        for (int i = 0; i < 9; i++) {
            paneelit[i] = (new OtuksenPiirtoPaneeli(otukset.get(i)));
        }
        /**
         * paneelit 4 ja 8 vaihdetaan keskenään, jotta ohjelma voi piirtää
         * otukset suoraan yhdestä listasta, niin että vanhempana toiminut otus
         * säilyy keskellä.
         */
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                add(paneelit[8]);
            } else if (i == 8) {
                add(paneelit[4]);
            } else {
                add(paneelit[i]);
            }
            paneelit[i].addMouseListener(this);
        }
    }

    /**
     * Hiiren klikkauksesta käynnistyvä metodi, joka määrittää mikä otuksista
     * valittiin tuottamaan jälkeläisiä. Toimii, vaikka ikkunan kokoa muuttaisi.
     *
     * @param me klikkaustapahtuma
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getXOnScreen();
        int y = me.getYOnScreen();
        int kolmasOsaLeveys = getWidth() / 3;
        int kolmasOsaKorkeus = getHeight() / 3;
        int valinta = 0;
        if (x < kolmasOsaLeveys) {
            if (y < kolmasOsaKorkeus) {
                valinta = 0;
            } else if (y > kolmasOsaKorkeus * 2) {
                valinta = 6;
            } else {
                valinta = 3;
            }
        } else if (x > kolmasOsaLeveys && x < kolmasOsaLeveys * 2) {
            if (y < kolmasOsaKorkeus) {
                valinta = 1;
            } else if (y > kolmasOsaKorkeus * 2) {
                valinta = 7;
            } else {
                valinta = 8;
            }
        } else if (x > kolmasOsaLeveys * 2) {
            if (y < kolmasOsaKorkeus) {
                valinta = 2;
            } else if (y > kolmasOsaKorkeus * 2) {
                valinta = 4;
            } else {
                valinta = 5;
            }
        }
        paivitaOtukset(valinta);
    }

    /**
     * Metodi joka tekee listan uusia jälkeläisiä ja päivittää ne paneeleihin.
     *
     * @param jalkelaisenNumero otuksen järjestysnumero otukset-listassa.
     */
    private void paivitaOtukset(int jalkelaisenNumero) {
        otukset = muta.jalkelaisetJaVanhempi(otukset.get(jalkelaisenNumero));
        for (int i = 0; i < 9; i++) {
            paneelit[i].setOtus(otukset.get(i));
        }
        repaint();
    }
    
    /**
     * Pakolliset toteutettavat MouseListener-metodit.
     * 
     * @param me 
     */

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
