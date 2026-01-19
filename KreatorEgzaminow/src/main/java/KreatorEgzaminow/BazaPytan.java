package KreatorEgzaminow;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BazaPytan {
    private List<Pytanie> listaWszystkichPytan;
    private final String SCIEZKA_PLIKU = "KreatorEgzaminow/src/main/resources/pytania.txt";

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

    public void zapiszDoPliku() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCIEZKA_PLIKU))) {
            for (Pytanie p : listaWszystkichPytan) {
                String typ = (p instanceof PytanieJednaOdp) ? "JEDNO" : "WIELE";
                StringBuilder sb = new StringBuilder();
                sb.append(typ).append(";")
                        .append(p.getTresc()).append(";")
                        .append(p.getOpcjaa()).append(";")
                        .append(p.getOpcjab()).append(";")
                        .append(p.getOpcjac()).append(";")
                        .append(p.getOpcjad()).append(";");

                if (p instanceof PytanieJednaOdp) {
                    sb.append(((PytanieJednaOdp) p).getPoprawnaodpowiedz());
                } else {
                    sb.append(String.join(",", ((PytanieWieleOdp) p).getPoprawneodpowiedzi()));
                }
                writer.println(sb.toString());
            }
        } catch (IOException e) {
            System.err.println("Błąd zapisu: " + e.getMessage());
        }
    }

    public void wczytajZPliku() {
        File plik = new File(SCIEZKA_PLIKU);

        if (!plik.exists()) {
            try {
                if (plik.getParentFile() != null) {
                    plik.getParentFile().mkdirs();
                }
                plik.createNewFile();
            } catch (IOException e) {
                System.err.println("Nie udalo sie utworzyc pliku bazy: " + e.getMessage());
            }
            return;
        }

        listaWszystkichPytan.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(plik))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                if (linia.trim().isEmpty()) continue;
                String[] czesci = linia.split(";");
                if (czesci.length < 7) continue;

                String typ = czesci[0];
                String tresc = czesci[1];
                String a = czesci[2], b = czesci[3], c = czesci[4], d = czesci[5];
                String poprawneRaw = czesci[6];

                if (typ.equals("JEDNO")) {
                    listaWszystkichPytan.add(new PytanieJednaOdp(tresc, a, b, c, d, poprawneRaw));
                } else {
                    List<String> poprawneLista = Arrays.asList(poprawneRaw.split(","));
                    listaWszystkichPytan.add(new PytanieWieleOdp(tresc, a, b, c, d, poprawneLista));
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu: " + e.getMessage());
        }
    }
}