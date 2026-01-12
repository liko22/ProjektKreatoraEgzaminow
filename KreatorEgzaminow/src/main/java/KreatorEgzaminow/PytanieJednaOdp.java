package KreatorEgzaminow;

public class PytanieJednaOdp extends Pytanie {
    private String poprawnaodpowiedz;

    public PytanieJednaOdp(String tresc, String a, String b, String c, String d, String poprawnaodpowiedz) {
        super(tresc, a, b, c, d);
        this.poprawnaodpowiedz = poprawnaodpowiedz;
    }

    public String getPoprawnaodpowiedz() {
        return poprawnaodpowiedz;
    }
}