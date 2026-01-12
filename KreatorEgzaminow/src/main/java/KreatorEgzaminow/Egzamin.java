package KreatorEgzaminow;

import java.util.ArrayList;
import java.util.List;

public class Egzamin {
    private String nazwaegzaminu;
    private List<Pytanie> listapytan;

    public Egzamin(String nazwaegzaminu) {
        this.nazwaegzaminu = nazwaegzaminu;
        this.listapytan = new ArrayList<>();
    }

    public void dodajPytanie(Pytanie p) {
        this.listapytan.add(p);
    }

    public String getNazwaegzaminu() {
        return nazwaegzaminu;
    }

    public List<Pytanie> getListapytan() {
        return listapytan;
    }
}