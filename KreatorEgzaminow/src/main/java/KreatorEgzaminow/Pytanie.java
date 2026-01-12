package KreatorEgzaminow;

public class Pytanie {
    private String tresc;
    private String opcjaa;
    private String opcjab;
    private String opcjac;
    private String opcjad;

    public Pytanie(String tresc, String a, String b, String c, String d) {
        this.tresc = tresc;
        this.opcjaa = a;
        this.opcjab = b;
        this.opcjac = c;
        this.opcjad = d;
    }

    public String getTresc() {
        return tresc;
    }
    public String getOpcjaa() {
        return opcjaa;
    }
    public String getOpcjab() {
        return opcjab;
    }
    public String getOpcjac() {
        return opcjac;
    }
    public String getOpcjad() {
        return opcjad;
    }
}