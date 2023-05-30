import java.util.ArrayList;
import java.util.Scanner;

public class Wojna extends Gra{
    Karta kartaKrupiera;
    boolean postawienie;

    public Wojna(Uzytkownik gracz) {
        super(gracz);
    }

    @Override
    public void RozpocznijGre() {
        super.RozpocznijGre();

        talia.UtworzTalieWojna();

        while(gracz.PunktyUzytkownika.Wartosc > 0)
        {

            gracz.KartyUzytkownika.clear();
            kartaKrupiera = talia.DobierzKarte();
            WyswietlKarty();
            zaklad = Zaklad();
            if(zaklad == -1)
                break;
            gracz.KartyUzytkownika.add(talia.DobierzKarte());
            WyswietlKarty();
            ZnajdzZwyciesce();
            CzyscEkran();


        }
        ZakonczGre();
    }

    @Override
    protected int Zaklad() {
        int tempZaklad = super.Zaklad();
        if(tempZaklad == -1)
            return -1;
        System.out.println("1. Postaw na krupiera \t2. Postaw na siebie");
        Scanner scanner = new Scanner(System.in);
        var result = scanner.nextInt();
        if(result == 1)
            postawienie = false;
        else if(result == 2)
            postawienie = true;
        else {
            System.out.println("Postawiono na krupiera");
            postawienie = false;
        }

        return tempZaklad;
    }

    private void WyswietlKarty()
    {
        if(gracz.KartyUzytkownika.size() == 0)
        {
            System.out.println("Karta wylosowana przez krupiera: " + kartaKrupiera.wyswietl()
                    + " " + kartaKrupiera.Kolor);
        }
        else
        {
            System.out.println("Karta wylosowana przez krupiera: " + kartaKrupiera.wyswietl()
                    + " " + kartaKrupiera.Kolor);
            System.out.println("Karta wylosowana przez gracza: " + gracz.KartyUzytkownika.get(0).wyswietl()
                    + " " + gracz.KartyUzytkownika.get(0).Kolor);
        }
    }

    private void SprawdzWyniki(int wynik)
    {
        if(wynik == 1)
        {
            if(postawienie == true)
            {
                System.out.println("Wygrales!");
                gracz.PunktyUzytkownika.Wartosc += zaklad;
            }
            else
            {
                System.out.println("Przegrales!");
                gracz.PunktyUzytkownika.Wartosc -= zaklad;
            }
        }
        else if (wynik == 2)
        {
            if(postawienie == false)
            {
                System.out.println("Wygrales!");
                gracz.PunktyUzytkownika.Wartosc += zaklad;
            }
            else
            {
                System.out.println("Przegrales!");
                gracz.PunktyUzytkownika.Wartosc -= zaklad;
            }
        }
        else
        {
            System.out.println("Remis!");
        }
    }
    private void ZnajdzZwyciesce()
    {
        if(kartaKrupiera.Wartosc < gracz.KartyUzytkownika.get(0).Wartosc){
            SprawdzWyniki(1);
        }
        else if(kartaKrupiera.Wartosc == gracz.KartyUzytkownika.get(0).Wartosc){
            SprawdzWyniki(3);
        }
        else{
            SprawdzWyniki(2);
        }

    }
}