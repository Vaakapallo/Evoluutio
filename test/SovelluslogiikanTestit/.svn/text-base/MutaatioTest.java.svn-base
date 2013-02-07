/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SovelluslogiikanTestit;

import Logiikka.Genomi;
import Logiikka.Mutaatio;
import Logiikka.Otus;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lvapaaka
 */
public class MutaatioTest {

    private Otus otus;
    private Mutaatio mutaattori;

    @Before
    public void setUp() {
        int[] genomi = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        otus = new Otus(new Genomi(genomi));
        mutaattori = new Mutaatio();
    }

    @Test
    public void mutaationJalkeenOtuksenGenomiOnErilainen() {
        Otus otus2 = mutaattori.mutatoi(otus);
        assertFalse(Arrays.equals(otus.getGenomi().getGeenit(), otus2.getGenomi().getGeenit()));
    }

    @Test
    public void mutaattoriPalauttaaKahdeksanJalkelaista() {
        ArrayList<Otus> jalkelaiset = mutaattori.jalkelaisetJaVanhempi(otus);
        assertEquals(8+1, jalkelaiset.size()); // +1 on vanhempi
    }

    @Test
    public void jalkelaisetOvatErilaisia() {
        ArrayList<Otus> jalkelaiset = mutaattori.jalkelaisetJaVanhempi(otus);
        assertTrue(jalkelaisetEiSisallaKopioita(jalkelaiset));
    }

    @Test
    public void kahdenGeneraationJalkeenOntapahtunutKaksiMutaatiota() {
        ArrayList<Otus> jalkelaiset = mutaattori.jalkelaisetJaVanhempi(otus);
        assertTrue(otuksenGenomiOnmuuttunutYhdella(otus, jalkelaiset.get(0)));
        otus = jalkelaiset.get(0);
        jalkelaiset = mutaattori.jalkelaisetJaVanhempi(otus);
        assertTrue(otuksenGenomiOnmuuttunutYhdella(otus, jalkelaiset.get(0)));
    }

    @Test
    public void geenienArvoEiVoiOllaYliYhdeksan() {
        int[] isoGenomi = {9, 9, 9, 9, 9, 9, 9, 9, 9};
        assertTrue(geenienItseisarvoEiYlitaMaksimia(isoGenomi));
    }

    @Test
    public void geenienArvoEiVoiOllaAlleMiinusYhdeksan() {
        int[] pieniGenomi = {-9, -9, -9, -9, -9, -9, -9, -9, -9};
        assertTrue(geenienItseisarvoEiYlitaMaksimia(pieniGenomi));
    }

    @Test
    public void mutaattoriVoiTuottaaKaikkiPermutaatiotOtuksesta() {
        // maksimimäärä jälkeläisiä on 18, koska otuksella on 9 geeniä jotka
        // voivat jokainen muuttua kahteen suuntaan. Testataan 18+1 koska vanhempi on mukana.
        Mutaatio kaikki = new Mutaatio(18);
        ArrayList<Otus> jalkelaiset = kaikki.jalkelaisetJaVanhempi(otus);
        assertEquals(18+1, jalkelaiset.size());
        assertTrue(jalkelaisetEiSisallaKopioita(jalkelaiset));
    }

    private boolean jalkelaisetEiSisallaKopioita(ArrayList<Otus> jalkelaiset) {
        for (Otus elio : jalkelaiset) {
            if (sisaltaaSamanlaisenOtuksen(elio, jalkelaiset)) {
                return false;
            }
        }
        return true;
    }

    private boolean sisaltaaSamanlaisenOtuksen(Otus otus, ArrayList<Otus> jalkelaiset) {
        ArrayList<Otus> otukset = new ArrayList<Otus>();
        for (Otus ottiainen : jalkelaiset) {
            otukset.add(ottiainen);
        }
        otukset.remove(otus);
        for (Otus elio : otukset) {
            if (otus.toString().equals(elio.toString())) {
                return true;
            }
        }
        return false;
    }

    private int geenienItseisarvoSumma(Otus otus) {
        int[] geenit = otus.getGenomi().getGeenit();
        int geenienSumma = 0;
        for (int i = 0; i < 9; i++) {
            geenienSumma += Math.abs(geenit[i]);
        }
        return geenienSumma;
    }

    private boolean geenienItseisarvoEiYlitaMaksimia(int[] geenit) {
        Otus isogeeni = new Otus(new Genomi(geenit));
        ArrayList<Otus> jalkelaiset;
        for (int i = 0; i < 100; i++) {
            jalkelaiset = mutaattori.jalkelaisetJaVanhempi(isogeeni);
            isogeeni = jalkelaiset.get(0);
            if (geenienItseisarvoSumma(isogeeni) > 81) {
                return false;
            }
        }
        return true;
    }

    private boolean otuksenGenomiOnmuuttunutYhdella(Otus vertailtava1, Otus vertailtava2) {
        if (Math.abs(geenienItseisarvoSumma(vertailtava1) - geenienItseisarvoSumma(vertailtava2)) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
