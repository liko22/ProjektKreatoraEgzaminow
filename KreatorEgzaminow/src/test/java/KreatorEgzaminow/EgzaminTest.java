package KreatorEgzaminow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EgzaminTest {

    private Egzamin egzamin;
    private List<Pytanie> lista;

    @BeforeEach
    void setUp() {
        egzamin = new Egzamin();
        lista = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            lista.add(new PytanieJednaOdp("Pytanie Jednokrotne" + i, "A", "B", "C", "D", "A"));
        }
        for (int i = 0; i < 8; i++) {
            lista.add(new PytanieWieleOdp("Pytanie Wielokrotne" + i, "D", "C", "B", "A", List.of("A", "B")));
        }
    }





    @Test
    void testCzyDziesiecPytan() {
        egzamin.wylosujPytania(lista);
        assertEquals(10, egzamin.getBazaEgzaminacyjna().getListaWszystkichPytan().size());
    }

    @Test
    void testSprawdzaniaOdpowiedzi() {

        egzamin.wylosujPytania(lista);
        int poprawneLicznik = 0;

        for (Pytanie p : egzamin.getBazaEgzaminacyjna().getListaWszystkichPytan()) {
            if (p instanceof PytanieJednaOdp) {
                PytanieJednaOdp pJedna = (PytanieJednaOdp) p;
                if (pJedna.getPoprawnaodpowiedz().equals("A")) {
                    poprawneLicznik++;
                }
            } else if (p instanceof PytanieWieleOdp) {
                PytanieWieleOdp pWiele = (PytanieWieleOdp) p;
                List<String> odpowiedziTestowe = List.of("A", "B");
                if (pWiele.getPoprawneodpowiedzi().containsAll(odpowiedziTestowe) &&
                        odpowiedziTestowe.size() == pWiele.getPoprawneodpowiedzi().size()) {
                    poprawneLicznik++;
                }
            }
        }

        assertEquals(10, poprawneLicznik);
    }
}