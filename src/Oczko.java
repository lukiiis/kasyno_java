import java.util.ArrayList;
import java.util.Scanner;

public class Oczko extends Gra {
    ArrayList<Karta> Krupier;
    int PunktyNaRenceKrupiera;

    int SumaKrupiera = 0;

    public Oczko(Uzytkownik gracz) {
        super(gracz);
        Krupier = new ArrayList<Karta>();
        SumaKrupiera = 0;
        PunktyNaRenceKrupiera = 0;
    }

    private boolean SprawdzCzyKoniec() {
        if (PunktyNaRenceKrupiera > 21) {
            System.out.println("Wygrales!");
            gracz.PunktyUzytkownika.Wartosc += zaklad;
            return true;
        }

        if (PunktyNaRenceKrupiera == 21 && gracz.PunktyNaReceUzytkownika == 21 ||
                PunktyNaRenceKrupiera == 20 && gracz.PunktyNaReceUzytkownika == 20) {
            System.out.println("Remis!");
            return true;
        }

        if (gracz.PunktyNaReceUzytkownika > 21) {
            System.out.println("Przegrales!");
            gracz.PunktyUzytkownika.Wartosc -= zaklad;
            return true;
        }
        if (gracz.PunktyNaReceUzytkownika == 22 && gracz.KartyUzytkownika.toArray().length == 2) {
            System.out.println("Wygrales perskie oczko WOW!");
            gracz.PunktyUzytkownika.Wartosc += zaklad;
            return true;
        }
        if (PunktyNaRenceKrupiera == 22 && Krupier.toArray().length == 2) {
            System.out.println("Krupier trafil perskie oczko WOW!");
            gracz.PunktyUzytkownika.Wartosc -= zaklad;
            return false;
        }

        return false;
    }

    public void RozpocznijGre() {

        talia.UtworzTalie();

        while (gracz.PunktyUzytkownika.Wartosc > 0) {
            gracz.KartyUzytkownika.clear();
            Krupier.clear();

            gracz.PunktyNaReceUzytkownika = 0;
            PunktyNaRenceKrupiera = 0;
            boolean koniecRundyUzytkownika = false;
            boolean koniecRundyKrupiera = false;
            zaklad = Zaklad();
            if(zaklad == -1)
                break;

            DobierzPoDwieKarty();
            WyswietlKarty();

            if (SprawdzCzyKoniec())
                continue;

            while (!koniecRundyUzytkownika) {
                if (CzyDobrac()) {
                    gracz.KartyUzytkownika.add(talia.DobierzKarte());
                    LiczRence();
                    WyswietlKarty();
                } else {
                    koniecRundyUzytkownika = true;
                }

                if (SprawdzCzyKoniec()) {
                    koniecRundyUzytkownika = true;
                    koniecRundyKrupiera = true;
                }

            }

            while (!koniecRundyKrupiera) {
                if (PunktyNaRenceKrupiera <= gracz.PunktyNaReceUzytkownika) {
                    Krupier.add(talia.DobierzKarte());
                    LiczRence();
                    WyswietlKarty();
                }
                if (SprawdzCzyKoniec())
                    koniecRundyKrupiera = true;

                if (PunktyNaRenceKrupiera > gracz.PunktyNaReceUzytkownika
                        && koniecRundyKrupiera == false) {
                    System.out.println("Przegrales!");
                    gracz.PunktyUzytkownika.Wartosc -= zaklad;
                    koniecRundyKrupiera = true;
                }

            }

            talia.ResetujTalie();
            CzyscEkran();
        }
        ZakonczGre();
    }

    private void WyswietlKarty() {
        System.out.println("Karty krupiera: ");
        for (var n : Krupier) {
            System.out.print(n.wyswietl() + " " + n.Kolor + " ");
        }
        System.out.println();
        System.out.println("Suma kart krupiera: ");
        System.out.println(PunktyNaRenceKrupiera);

        System.out.print("Karty uztykownika: ");

        for (var n : gracz.KartyUzytkownika) {
            System.out.print(n.wyswietl() + " " + n.Kolor + " ");
        }

        System.out.println();
        System.out.println("Suma kart uzytkownika: ");
        System.out.println(gracz.PunktyNaReceUzytkownika);

        System.out.println();
    }

    private void DobierzPoDwieKarty() {
        System.out.println("Dobranie kart: ");
        Krupier.add(talia.DobierzKarte());
        Krupier.add(talia.DobierzKarte());

        gracz.KartyUzytkownika.add(talia.DobierzKarte());
        gracz.KartyUzytkownika.add(talia.DobierzKarte());

        LiczRence();
    }

    private void LiczRence() {
        PunktyNaRenceKrupiera = 0;
        gracz.PunktyNaReceUzytkownika = 0;
        for (var n : gracz.KartyUzytkownika) {
            gracz.PunktyNaReceUzytkownika += n.Wartosc;
        }
        for (var n : Krupier) {
            PunktyNaRenceKrupiera += n.Wartosc;
        }
    }
}