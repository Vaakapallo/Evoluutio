/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SovelluslogiikanTestit;

import Logiikka.Genomi;
import Logiikka.Otus;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lvapaaka
 */
public class OtusTest {

    private Otus otus;

    @Before
    public void setUp() {
        int[] genomi = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        otus = new Otus(new Genomi(genomi));
    }

    @Test
    public void otuksenToStringAntaaGenominJarkevanaNumerosarjana() {
        assertEquals("0,0,0,0,0,0,0,0,0", otus.toString());
    }
}
