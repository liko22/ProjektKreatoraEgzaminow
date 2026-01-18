package KreatorEgzaminow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BazaPytanTest {

    private BazaPytan baza;

    @BeforeEach
    void setUp() {
        baza = new BazaPytan();
    }

    @Test
    void testDodawaniaPytania() {
        PytanieJednaOdp p = new PytanieJednaOdp("Stolica Polski?", "Krakow", "Warszawa", "Poznan", "Gdansk", "Warszawa");

        baza.dodajPytanie(p);

        assertEquals(1, baza.getListaWszystkichPytan().size());
        assertTrue(baza.getListaWszystkichPytan().contains(p));
    }
    @Test
    void testUsuwaniaPytania() {
        PytanieJednaOdp p = new PytanieJednaOdp("Stolica Niemiec?", "Berlin", "Monachium", "Hamburg", "Bonn", "Berlin");

        baza.dodajPytanie(p);
        baza.usunPytanie(p);

        assertEquals(0, baza.getListaWszystkichPytan().size());
        assertFalse(baza.getListaWszystkichPytan().contains(p));
    }
}