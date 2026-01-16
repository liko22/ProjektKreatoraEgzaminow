package KreatorEgzaminow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EgzaminController {

    @FXML
    private VBox kontenerPytan;

    private Egzamin egzamin;

    public void rozpocznijTest(List<Pytanie> wszystkie) {

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
        kontenerPytan.getChildren().clear();
        int numer = 1;

        for (Pytanie p : egzamin.getListaPytanEgzaminacyjnych()) {
            VBox blokPytania = new VBox(5);

            Label tresc = new Label(numer + ". " + p.getTresc());
            tresc.setStyle("-fx-font-weight: bold;");

            HBox liniaOdpowiedzi = new HBox(20);

            Label oA = new Label("A) " + p.getOpcjaa());
            Label oB = new Label("B) " + p.getOpcjab());
            Label oC = new Label("C) " + p.getOpcjac());
            Label oD = new Label("D) " + p.getOpcjad());

            liniaOdpowiedzi.getChildren().addAll(oA, oB, oC, oD);

            blokPytania.getChildren().addAll(tresc, liniaOdpowiedzi);
            kontenerPytan.getChildren().add(blokPytania);

            numer++;
        }
    }
}