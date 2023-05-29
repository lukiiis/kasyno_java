import java.util.ArrayList;
import java.util.Scanner;

public class Oczko extends Uzytkownik implements Gra
{
    ArrayList<Karta> Krupier;
    int PunktyNaRenceKrupiera;
    int zaklad;

    public Oczko() {
        Krupier = new ArrayList<Karta>();
        SumaKrupiera = 0;
        this.talia = new TaliaKart();
        PunktyUzytkownika.Wartosc = 1000;
        PunktyNaRenceKrupiera = 0;
        zaklad = 0;
    }

    int SumaKrupiera = 0;
    TaliaKart talia;

    private int Zaklad()
    {
        while(true)
        {
            System.out.println("Liczba posiadanych punktow: " + this.PunktyUzytkownika.getWartosc());
            System.out.println("Postaw zaklad: ");
            Scanner scanner = new Scanner(System.in);
            int suma = scanner.nextInt();
            if(suma > PunktyUzytkownika.Wartosc)
            {
                System.out.println("Nie masz tyle punktow");
            }
            else if(suma < 20){
                System.out.println("Za mala kwota (min 20)");
            }
            else {
                return suma;
            }
        }

    }

    private boolean SprawdzCzyKoniec()
    {
        if(PunktyNaRenceKrupiera > 21)
        {
            System.out.println("Wygrales!");
            PunktyUzytkownika.Wartosc += zaklad;
            return true;
        }

        if(PunktyNaRenceKrupiera == 21 && PunktyNaReceUzytkownika == 21 ||
            PunktyNaRenceKrupiera == 20 && PunktyNaReceUzytkownika == 20
        )
        {
            System.out.println("Remis!");
            return true;
        }

        if(PunktyNaReceUzytkownika > 21)
        {
            System.out.println("Przegrales!");
            PunktyUzytkownika.Wartosc -= zaklad;
            return  true;
        }
        if(PunktyNaReceUzytkownika == 22 && KartyUzytkownika.toArray().length == 2)
        {
            System.out.println("Wygrales perskie oczko WOW!");
            PunktyUzytkownika.Wartosc += zaklad;
            return  true;
        }
        if(PunktyNaRenceKrupiera == 22 && Krupier.toArray().length == 2)
        {
            System.out.println("Krupier trafil perskie oczko WOW!");
            PunktyUzytkownika.Wartosc -= zaklad;
            return  false;
        }


        return false;
    }



    private boolean CzyDobrac()
    {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Czy dobrac karty? 1 - tak, 0 - nie");
        int decyzja = scaner.nextInt();

        if(decyzja == 1)
            return true;
        else
            return false;
    }

    public void RozpocznijGre() {

        StworzUzytkownika();
        talia.UtworzTalie();


        while(PunktyUzytkownika.Wartosc > 0)
        {
            KartyUzytkownika.clear();
            Krupier.clear();


            PunktyNaReceUzytkownika = 0;
            PunktyNaRenceKrupiera = 0;
            boolean koniecRundyUzytkownika = false;
            boolean koniecRundyKrupiera = false;
            zaklad = Zaklad();
            DobierzPoDwieKarty();
            WyswietlKarty();


            if(SprawdzCzyKoniec())
                continue;

            while(!koniecRundyUzytkownika)
            {
                if(CzyDobrac()){
                    KartyUzytkownika.add(talia.DobierzKarte());
                    LiczRence();
                    WyswietlKarty();
                }
                else{
                    koniecRundyUzytkownika = true;
                }

                if(SprawdzCzyKoniec()){
                    koniecRundyUzytkownika = true;
                    koniecRundyKrupiera = true;
                }

            }

            while(!koniecRundyKrupiera)
            {
                if(PunktyNaRenceKrupiera <= PunktyNaReceUzytkownika){
                    Krupier.add(talia.DobierzKarte());
                    LiczRence();
                    WyswietlKarty();
                }
                if(SprawdzCzyKoniec())
                    koniecRundyKrupiera = true;

                if(PunktyNaRenceKrupiera > PunktyNaReceUzytkownika
                    && koniecRundyKrupiera == false)
                {
                    System.out.println("Przegrales!");
                    PunktyUzytkownika.Wartosc -= zaklad;
                    koniecRundyKrupiera = true;
                }

            }


            talia.ResetujTalie();
            for(int clear = 0; clear < 5; clear++)
            {
                System.out.println("\b") ;
            }
        }
    }

    public void ZakonczGre() {
        System.out.println("KONIEC GRY");
    }

    private void WyswietlKarty()
    {
        System.out.println("Karty krupiera: ");
        for(var n : Krupier)
        {
            System.out.print(n.wyswietl() + " " + n.Kolor + " ");
        }
        System.out.println();
        System.out.println("Suma kart krupiera: ");
        System.out.println(PunktyNaRenceKrupiera);


        System.out.print("Karty uztykownika: ");


        for(var n : KartyUzytkownika)
        {
            System.out.print(n.wyswietl() + " " + n.Kolor + " ");
        }

        System.out.println();
        System.out.println("Suma kart uzytkownika: ");
        System.out.println(PunktyNaReceUzytkownika);

        System.out.println();
    }
    private void DobierzPoDwieKarty()
    {
        System.out.println("Dobranie kart: ");
        Krupier.add(talia.DobierzKarte());
        Krupier.add(talia.DobierzKarte());

        KartyUzytkownika.add(talia.DobierzKarte());
        KartyUzytkownika.add(talia.DobierzKarte());

        LiczRence();
    }

    private void LiczRence()
    {
        PunktyNaRenceKrupiera = 0;
        PunktyNaReceUzytkownika = 0;
        for(var n : KartyUzytkownika)
        {
            PunktyNaReceUzytkownika += n.Wartosc;
        }
        for(var n : Krupier)
        {
            PunktyNaRenceKrupiera += n.Wartosc;
        }
    }
}
