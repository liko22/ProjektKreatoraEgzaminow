package KreatorEgzaminow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EgzaminController {

    @FXML
    private VBox kontenerPytan;

    private Egzamin egzamin;

    public void rozpocznijTest(List<Pytanie> wszystkie) {
        if (wszystkie.size() < 10) return;

        List<Pytanie> wylosowane = losujPytania(wszystkie);
        inicjalizujEgzamin(wylosowane);
        wyswietlPytania();
    }

    private List<Pytanie> losujPytania(List<Pytanie> lista) {
        List<Pytanie> kopia = new ArrayList<>(lista);
        Collections.shuffle(kopia);
        return kopia.subList(0, 10);
    }

    private void inicjalizujEgzamin(List<Pytanie> wybrane) {
        this.egzamin = new Egzamin();
        for (Pytanie p : wybrane) {
            this.egzamin.dodajPytanie(p);
        }
    }

    private void wyswietlPytania() {
        for (Pytanie p : egzamin.getListaPytanEgzaminacyjnych()) {
            Label l = new Label("- " + p.getTresc());
            kontenerPytan.getChildren().add(l);
        }
    }
}