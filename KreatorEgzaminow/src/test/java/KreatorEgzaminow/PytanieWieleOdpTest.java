package KreatorEgzaminow;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PytanieWieleOdpTest {

    @Test
    void testListyOdpowiedziWielokrotnej() {
        PytanieWieleOdp p = new PytanieWieleOdp("Wybierz jezyki programowania:", "Java", "HTML", "Python", "CSS", List.of("Java", "Python"));

        assertEquals(2, p.getPoprawneodpowiedzi().size());
        assertTrue(p.getPoprawneodpowiedzi().containsAll(List.of("Java", "Python")));
    }
}