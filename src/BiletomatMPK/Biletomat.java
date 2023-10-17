package BiletomatMPK;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Biletomat {
    private ArrayList<Bilet> zakupioneBilety = new ArrayList<>();
    private final ArrayList<Bilet> ofertaBiletow = new ArrayList<>();
    private ArrayList<Pieniadz> pieniadzeWPortfelu = new ArrayList<>();
    private Karta pieniadzeNaKarcie;
    private String lokalizacja;
    private Scanner input = new Scanner(System.in);

    public Biletomat(ArrayList pieniadzeWPortfelu, Karta pieniadzeNaKarcie, String lokalizacja) {
        ofertaBiletow.add(new Bilet("Ulgowy 20-minutowy", 2));
        ofertaBiletow.add(new Bilet("Ulgowy 60-minutowy", 4));
        ofertaBiletow.add(new Bilet("Ulgowy 24-godzinny", 23.5));
        ofertaBiletow.add(new Bilet("Normalny 20-minutowy", 4));
        ofertaBiletow.add(new Bilet("Normalny 60-minutowy", 8));
        ofertaBiletow.add(new Bilet("Normalny 24-godzinny", 32));
        this.pieniadzeWPortfelu = pieniadzeWPortfelu;
        this.pieniadzeNaKarcie = pieniadzeNaKarcie;
        this.lokalizacja = lokalizacja;
    }

    private boolean platnosc(double ileDoZaplaty) {
        ArrayList<Pieniadz> pieniadzeWBiletomacie = new ArrayList<>();
        final double[] nominaly = {0.01, 0.02, 0.05, 0.1, 0.2, 0.5, 1, 2, 5, 10, 20, 50, 100, 200, 500};
        int opcjaZaplaty = -1;
        int i;

        while (true) {
            System.out.printf("Wybierz sposób zapłaty: \n1. Karta Płatnicza\n2. Gotówka\nTwój wybór >> ");
            opcjaZaplaty = input.nextInt();

            while (opcjaZaplaty != 1 && opcjaZaplaty != 2) {
                System.out.println("Zła opcja!!! Wybierz 1 lub 2");
                opcjaZaplaty = input.nextInt();
            }

            if (opcjaZaplaty == 1) {
                if (pieniadzeNaKarcie.getValue() >= ileDoZaplaty) {
                    pieniadzeNaKarcie.payment(ileDoZaplaty);
                    return true;
                } else {
                    System.out.println("Za mało środków na karcie! Transakcja odrzucona!");
                    return false;
                }
            } else {
                while (!pieniadzeWPortfelu.isEmpty()) {
                    if (ileDoZaplaty >= 0) {
                        ileDoZaplaty -= pieniadzeWPortfelu.get(pieniadzeWPortfelu.size() - 1).getValue();
                        pieniadzeWBiletomacie.add(pieniadzeWPortfelu.get(pieniadzeWPortfelu.size() - 1));
                        pieniadzeWPortfelu.remove(pieniadzeWPortfelu.get(pieniadzeWPortfelu.size() - 1));
                    } else if (ileDoZaplaty == 0)
                        return true;
                    else { // wydawanie reszty
                        ileDoZaplaty *= (-1); // bo mamy reszte na minusie a chcemy wydac
                        System.out.println("Twoja reszta: " + ileDoZaplaty + " zł");
                        i = nominaly.length - 1; // 14
                        while (i != 0) {
                            if (nominaly[i] <= ileDoZaplaty) {
                                pieniadzeWPortfelu.add(new Gotowka(nominaly[i]));
                                ileDoZaplaty -= nominaly[i];
                            } else
                                i--;
                        }
                        return true;
                    }
                }
                System.out.println("Za mało środków!!! Transakcja odrzucona!");
                while (!pieniadzeWBiletomacie.isEmpty()) { // zwracanie pieniedzy z biletomatu
                    pieniadzeWPortfelu.add(pieniadzeWBiletomacie.get(pieniadzeWBiletomacie.size() - 1));
                    pieniadzeWBiletomacie.remove(pieniadzeWBiletomacie.get(pieniadzeWBiletomacie.size() - 1));
                }
                return false;
            }
        }
    }

    public void sprzedarzBiletow() {
        int i = 0;
        LocalDate dataZakupu = null;
        double ileDoZaplaty = 0.0;
        int czyKolejnyBilet = 0;

        ArrayList<Bilet> tempZakupioneBilety = new ArrayList<>();
        System.out.println("Witaj w biletomacie!");

        while (true) {
            System.out.println("Oto lista dostępnych biletów: ");
            for (i = 0; i < ofertaBiletow.size(); i++)
                System.out.println((i + 1) + ". " + ofertaBiletow.get(i).getRodzajBiletu() + " - " + ofertaBiletow.get(i).getCenaBiletow() + " zł");
            System.out.println((++i) + ". Anuluj");

            System.out.printf("Wybierz bilet który chcesz kupić. Twój wybór >> ");
            int opcjaBiletu = input.nextInt();

            if (opcjaBiletu > i) {
                System.out.println("Nieprawdiłowa opcja!!! Wybierz z pośród opcji 1-7.");
                continue;
            } else if (opcjaBiletu == 7)
                continue;

            System.out.printf("Podaj liczbę biletów, które chcesz kupić. Twój wybór >> ");
            int liczbaBiletow = input.nextInt();

            while (liczbaBiletow <= 0) {
                System.out.println("Nieprawidłowa liczba biletów! Wybierz liczbę większą od 0.");
                liczbaBiletow = input.nextInt();
            }

            dataZakupu = LocalDate.now();
            tempZakupioneBilety.add(new Bilet(ofertaBiletow.get(opcjaBiletu - 1).getRodzajBiletu(), ofertaBiletow.get(opcjaBiletu - 1).getCenaBiletow(), dataZakupu, liczbaBiletow));
            ileDoZaplaty += (liczbaBiletow * ofertaBiletow.get(opcjaBiletu - 1).getCenaBiletow());

            System.out.printf("Chcesz kupić jeszcze jakiś bilet?\n1. Tak  2. Nie\nTwój wybór >> ");
            czyKolejnyBilet = input.nextInt();

            while (czyKolejnyBilet != 1 && czyKolejnyBilet != 2) {
                System.out.println("Nieprawidłowa opcja! Wybierz liczbę 1 lub 2");
                czyKolejnyBilet = input.nextInt();
            }

            if (czyKolejnyBilet == 1)
                continue;

            System.out.println("Cena Twoich biletów to: " + ileDoZaplaty);
            if (platnosc(ileDoZaplaty)) {
                System.out.println("Bilety zakupione pomyślnie!");

                while (!tempZakupioneBilety.isEmpty()) { // wydanie biletów
                    zakupioneBilety.add(tempZakupioneBilety.get(tempZakupioneBilety.size() -1));
                    tempZakupioneBilety.remove(tempZakupioneBilety.size() - 1);
                }
            } else
                System.out.println("Błąd podczas zakupu biletów!");

            break; // koniec zakupów
        }
    }

    public String toString() {
        for (Bilet bilet : zakupioneBilety) {
            System.out.println(bilet);
        }
        return "";
    }

    public void wydrukujTransakcje(LocalDate date) {
        LocalDate tempDate;
        boolean flag = true;

        for (int i = 0; i < zakupioneBilety.size(); i++) {
            tempDate = zakupioneBilety.get(i).getDataZakupu();

            if (tempDate.equals(date)) {
                System.out.println(tempDate + " : " + zakupioneBilety.get(i).getRodzajBiletu() + " : " + zakupioneBilety.get(i).getLiczbaBiletow() + " : " + zakupioneBilety.get(i).getCenaBiletow() + " : " + zakupioneBilety.get(i).getLiczbaBiletow() * zakupioneBilety.get(i).getCenaBiletow());
                flag = false;
            }
        }

        if(flag)
            System.out.println("Brak transakcji z dnia " + date);
    }
}
