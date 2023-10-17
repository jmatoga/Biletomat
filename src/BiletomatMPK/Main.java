package BiletomatMPK;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pieniadz> pieniadzeWPortfelu = new ArrayList<>();
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2023, 4, 23);

        pieniadzeWPortfelu.add(new Gotowka(5));
        pieniadzeWPortfelu.add(new Gotowka(0.2));
        pieniadzeWPortfelu.add(new Gotowka(10));
        pieniadzeWPortfelu.add(new Gotowka(20));

        Biletomat biletomat1 = new Biletomat(pieniadzeWPortfelu, new Karta(123, "123456789"), "Rondo Mogilskie");
        biletomat1.sprzedarzBiletow();

        System.out.println("Bilety z dzisiaj: ");
        biletomat1.wydrukujTransakcje(date1);
        System.out.println("Bilety z dnia 23-04-2023: ");
        biletomat1.wydrukujTransakcje(date2);
    }
}
