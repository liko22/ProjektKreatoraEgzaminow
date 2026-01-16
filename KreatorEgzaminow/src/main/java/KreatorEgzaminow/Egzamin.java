package KreatorEgzaminow;

import java.util.ArrayList;
import java.util.List;

public class Egzamin {
    private List<Pytanie> listaPytanEgzaminacyjnych;

    public Egzamin() {
        this.listaPytanEgzaminacyjnych = new ArrayList<>();
    }

    public void dodajPytanie(Pytanie p) {
        listaPytanEgzaminacyjnych.add(p);
    }

    public List<Pytanie> getListaPytanEgzaminacyjnych() {
        return listaPytanEgzaminacyjnych;
    }
}