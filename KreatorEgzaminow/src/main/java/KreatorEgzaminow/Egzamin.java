package KreatorEgzaminow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Egzamin {
    private List<Pytanie> listaPytanEgzaminacyjnych = new ArrayList<>();

    public void wylosujPytania(List<Pytanie> baza) {
        List<Pytanie> kopia = new ArrayList<>(baza);
        Collections.shuffle(kopia);

        listaPytanEgzaminacyjnych.clear();
        for (int i = 0; i < 10; i++) {
            listaPytanEgzaminacyjnych.add(kopia.get(i));
        }
    }

    public List<Pytanie> getListaPytanEgzaminacyjnych() {
        return listaPytanEgzaminacyjnych;
    }
}