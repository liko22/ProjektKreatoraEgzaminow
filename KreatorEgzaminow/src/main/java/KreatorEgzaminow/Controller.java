package KreatorEgzaminow;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private Button przyciskTrybJedno;
    @FXML
    private Button przyciskTrybWiele;
    @FXML
    private TextField poleTrescPytania;
    @FXML
    private TextField poleOdp1;
    @FXML
    private TextField poleOdp2;
    @FXML
    private TextField poleOdp3;
    @FXML
    private TextField poleOdp4;
    @FXML
    private CheckBox wybor1;
    @FXML
    private CheckBox wybor2;
    @FXML
    private CheckBox wybor3;
    @FXML
    private CheckBox wybor4;
    @FXML
    private ListView<Pytanie> widokListyPytan;

    private BazaPytan bazaPytan = new BazaPytan();
    private boolean czyJednokrotne = true;

    @FXML
    public void initialize() {
        widokListyPytan.setCellFactory(parametr -> new ListCell<Pytanie>() {
            @Override
            protected void updateItem(Pytanie pytanie, boolean puste) {
                super.updateItem(pytanie, puste);
                if (puste || pytanie == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(stworzWierszPytania(pytanie));
                }
            }
        });
        ustawStylePrzyciskow();
    }

    //Pomoc chata bo to dziwne xd
    private HBox stworzWierszPytania(Pytanie pytanie) {
        HBox kontenerGlowny = new HBox(10);
        VBox kontenerTekstu = new VBox(5);

        Label etykietaTresc = new Label(pytanie.getTresc());
        etykietaTresc.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label etykietaOpcje = new Label("A: " + pytanie.getOpcjaa() + " | B: " + pytanie.getOpcjab() +
                " | C: " + pytanie.getOpcjac() + " | D: " + pytanie.getOpcjad());
        etykietaOpcje.setStyle("-fx-font-size: 11px; -fx-text-fill: #7f8c8d;");

        kontenerTekstu.getChildren().addAll(etykietaTresc, etykietaOpcje);
        HBox.setHgrow(kontenerTekstu, Priority.ALWAYS);

        Button przyciskUsun = new Button("USUŃ");
        przyciskUsun.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 10px;");
        przyciskUsun.setOnAction(klikniecie -> {
            bazaPytan.usunPytanie(pytanie);
            odswiezListe();
        });

        kontenerGlowny.getChildren().addAll(kontenerTekstu, przyciskUsun);
        return kontenerGlowny;
    }
    //koniec

    @FXML
    private void ustawTrybJedno() {
        czyJednokrotne = true;
        ustawStylePrzyciskow();
        wyczyscZaznaczenia();
    }

    @FXML
    private void ustawTrybWiele() {
        czyJednokrotne = false;
        ustawStylePrzyciskow();
        wyczyscZaznaczenia();
    }

    private void ustawStylePrzyciskow() {
        if (czyJednokrotne) {
            przyciskTrybJedno.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
            przyciskTrybWiele.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #2c3e50;");
        } else {
            przyciskTrybJedno.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #2c3e50;");
            przyciskTrybWiele.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        }
    }

    @FXML
    private void obsluzKlikniecieWyboru(javafx.event.ActionEvent klikniecie) {
        if (czyJednokrotne) {
            CheckBox pole = (CheckBox) klikniecie.getSource();
            wybor1.setSelected(false);
            wybor2.setSelected(false);
            wybor3.setSelected(false);
            wybor4.setSelected(false);
            pole.setSelected(true);
        }
    }

    private void wyczyscZaznaczenia() {
        wybor1.setSelected(false);
        wybor2.setSelected(false);
        wybor3.setSelected(false);
        wybor4.setSelected(false);
    }

    @FXML
    public void dodajPytanieDoBazy() {
        String tresc = poleTrescPytania.getText();
        String odp1 = poleOdp1.getText();
        String odp2 = poleOdp2.getText();
        String odp3 = poleOdp3.getText();
        String odp4 = poleOdp4.getText();
        if (czyJednokrotne) {
            String poprawna = "";
            if (wybor1.isSelected()) poprawna = odp1;
            else if (wybor2.isSelected()) poprawna = odp2;
            else if (wybor3.isSelected()) poprawna = odp3;
            else if (wybor4.isSelected()) poprawna = odp4;
            bazaPytan.dodajPytanie(new PytanieJednaOdp(tresc, odp1, odp2, odp3, odp4, poprawna));
        } else {
            List<String> poprawne = new ArrayList<>();
            if (wybor1.isSelected()) poprawne.add(odp1);
            if (wybor2.isSelected()) poprawne.add(odp2);
            if (wybor3.isSelected()) poprawne.add(odp3);
            if (wybor4.isSelected()) poprawne.add(odp4);
            bazaPytan.dodajPytanie(new PytanieWieleOdp(tresc, odp1, odp2, odp3, odp4, poprawne));
        }
        odswiezListe();
        wyczyscPolaFormularza();
    }

    private void odswiezListe() {
        widokListyPytan.getItems().setAll(bazaPytan.getListaWszystkichPytan());
    }

    private void wyczyscPolaFormularza() {
        poleTrescPytania.clear();
        poleOdp1.clear();
        poleOdp2.clear();
        poleOdp3.clear();
        poleOdp4.clear();
        wyczyscZaznaczenia();
    }
}