/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SovelluslogiikanTestit;

import Logiikka.Genomi;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author lvapaaka
 */
public class GenomiTest {

    private Genomi genomi;

    @Before
    public void setUp() {
        int[] geenit = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        genomi = new Genomi(geenit);
    }

    @Test
    public void genominKopiointiLuoAidonKopion() {
        Genomi genominKopio = genomi.kopioiGenomi();
        assertFalse(genominKopio == genomi);
    }
}
