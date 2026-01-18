package KreatorEgzaminow;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;

public class EgzaminController {

    @FXML
    private VBox kontenerPytan;

    private Egzamin egzamin = new Egzamin();
    private List<HBox> konteneryOdpowiedzi = new ArrayList<>();

    public void rozpocznijTest(List<Pytanie> wszystkie) {
        egzamin.wylosujPytania(wszystkie);
        wyswietlPytania();
    }

    private void wyswietlPytania() {
        kontenerPytan.getChildren().clear();
        konteneryOdpowiedzi.clear();

        for (int i = 0; i < egzamin.getListaPytanEgzaminacyjnych().size(); i++) {
            Pytanie p = egzamin.getListaPytanEgzaminacyjnych().get(i);
            VBox blok = new VBox(5);
            blok.setAlignment(javafx.geometry.Pos.CENTER);

            Label tresc = new Label((i + 1) + ". " + p.getTresc());
            tresc.setStyle("-fx-font-weight: bold;");

            HBox linia = new HBox(15);
            linia.setAlignment(javafx.geometry.Pos.CENTER);

            CheckBox cb1 = new CheckBox(p.getOpcjaa());
            CheckBox cb2 = new CheckBox(p.getOpcjab());
            CheckBox cb3 = new CheckBox(p.getOpcjac());
            CheckBox cb4 = new CheckBox(p.getOpcjad());

            if (p instanceof PytanieJednaOdp) {
                CheckBox[] wszystkieCB = {cb1, cb2, cb3, cb4};
                for (CheckBox cb : wszystkieCB) {
                    cb.setOnAction(e -> {
                        if (cb.isSelected()) {
                            for (CheckBox inne : wszystkieCB) if (inne != cb) inne.setSelected(false);
                        }
                    });
                }
            }

            linia.getChildren().addAll(cb1, cb2, cb3, cb4);
            konteneryOdpowiedzi.add(linia);
            blok.getChildren().addAll(tresc, linia);
            kontenerPytan.getChildren().add(blok);
        }

        Button przyciskWynik = new Button("Sprawdź wynik");
        przyciskWynik.setOnAction(e -> pokazWynik());
        kontenerPytan.getChildren().add(przyciskWynik);
    }

    private void pokazWynik() {
        int punkty = 0;

        for (int i = 0; i < egzamin.getListaPytanEgzaminacyjnych().size(); i++) {
            Pytanie p = egzamin.getListaPytanEgzaminacyjnych().get(i);
            HBox linia = konteneryOdpowiedzi.get(i);

            List<String> wybrane = new ArrayList<>();
            for (javafx.scene.Node node : linia.getChildren()) {
                CheckBox cb = (CheckBox) node;
                if (cb.isSelected()) wybrane.add(cb.getText());
            }

            if (p instanceof PytanieJednaOdp) {
                if (wybrane.size() == 1 && wybrane.get(0).equals(((PytanieJednaOdp) p).getPoprawnaodpowiedz())) {
                    punkty++;
                }
            } else if (p instanceof PytanieWieleOdp) {
                List<String> poprawne = ((PytanieWieleOdp) p).getPoprawneodpowiedzi();
                if (wybrane.size() == poprawne.size() && wybrane.containsAll(poprawne)) {
                    punkty++;
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Wynik: " + punkty + "/" + egzamin.getListaPytanEgzaminacyjnych().size());
        alert.show();
    }
}