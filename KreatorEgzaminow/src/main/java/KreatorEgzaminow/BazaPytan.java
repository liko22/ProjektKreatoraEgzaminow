package KreatorEgzaminow;

import java.util.ArrayList;
import java.util.List;

public class BazaPytan {
    private List<Pytanie> listaWszystkichPytan;

    public BazaPytan() {
        this.listaWszystkichPytan = new ArrayList<>();
    }

    public void dodajPytanie(Pytanie pytanie) {
        listaWszystkichPytan.add(pytanie);
    }

    public void usunPytanie(Pytanie pytanie) {
        listaWszystkichPytan.remove(pytanie);
    }

    public List<Pytanie> getListaWszystkichPytan() {
        return listaWszystkichPytan;
    }
}