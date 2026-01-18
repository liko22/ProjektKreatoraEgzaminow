package KreatorEgzaminow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Egzamin {
    private BazaPytan bazaEgzaminacyjna;

    public Egzamin() {
        this.bazaEgzaminacyjna = new BazaPytan();
    }

    public void wylosujPytania(List<Pytanie> listaPytanBazy) {
        List<Pytanie> kopia = new ArrayList<>(listaPytanBazy);
        Collections.shuffle(kopia);;

        for (int i = 0; i < 10; i++) {
            this.bazaEgzaminacyjna.dodajPytanie(kopia.get(i));
        }
    }

    public BazaPytan getBazaEgzaminacyjna() {
        return bazaEgzaminacyjna;
    }
}