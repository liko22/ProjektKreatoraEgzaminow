package KreatorEgzaminow;

import java.util.List;

public class PytanieWieleOdp extends Pytanie {
    private List<String> poprawneodpowiedzi;

    public PytanieWieleOdp(String tresc, String a, String b, String c, String d, List<String> poprawneodpowiedzi) {
        super(tresc, a, b, c, d);
        this.poprawneodpowiedzi = poprawneodpowiedzi;
    }

    public List<String> getPoprawneodpowiedzi() {
        return poprawneodpowiedzi;
    }
}