package BiletomatMPK;

import java.time.LocalDate;

public class Bilet {
    private String rodzajBiletu;
    private double cenaBiletow;
    private LocalDate dataZakupu;
    private int liczbaBiletow;

    protected Bilet(String rodzajBiletu, double cenaBiletow, LocalDate dataZakupu, int liczbaBiletow) {
        this.rodzajBiletu = rodzajBiletu;
        this.cenaBiletow = cenaBiletow;
        this.dataZakupu = dataZakupu;
        this.liczbaBiletow = liczbaBiletow;
    }

    public Bilet(String rodzajBiletu, double cenaBiletow) {
        this.rodzajBiletu = rodzajBiletu;
        this.cenaBiletow = cenaBiletow;
    }

    public String toString() {
        return dataZakupu + " : " + rodzajBiletu + " : " + liczbaBiletow + " : " + cenaBiletow + " : " + liczbaBiletow * cenaBiletow;
    }

    public String getRodzajBiletu() {
        return rodzajBiletu;
    }

    public double getCenaBiletow() {
        return cenaBiletow;
    }

    public LocalDate getDataZakupu() {
        return dataZakupu;
    }

    public int getLiczbaBiletow() {
        return liczbaBiletow;
    }
}