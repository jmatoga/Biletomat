package BiletomatMPK;

abstract class Pieniadz { //klasa abstrakcyjna z której dziedziczą pozostałe dwie klasy
    protected double nominal;

    public Pieniadz(double nominal) { //funkcja abstrakcyjna
        this.nominal = nominal;
    }

    public double getValue() {
        return nominal;
    }

    public abstract String toString(); //funkcja abstrakcyjna
}

class Gotowka extends Pieniadz {
    public Gotowka(double nominal) {
        super(nominal);
    }

    @Override
    public String toString() {
        return "" + nominal;
    }
}

class Karta extends  Pieniadz {
    private final String numerKarty;
    public Karta(double nominal, String numerKarty) {
        super(nominal);
        this.numerKarty = numerKarty;
    }

    public void payment(double value){
        nominal -= value;
    }

    @Override
    public String toString() {
        return "Stan konta: " + nominal;
    }
}