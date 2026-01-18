package KreatorEgzaminow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PytanieJednaOdpTest {

    @Test
    void testPoprawnosciOdpowiedziJednokrotnej() {
        PytanieJednaOdp p = new PytanieJednaOdp("Stolica Polski?", "Krakow", "Warszawa", "Poznan", "Gdansk", "Warszawa");

        assertEquals("Warszawa", p.getPoprawnaodpowiedz());
    }
}