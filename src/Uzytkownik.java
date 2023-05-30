import java.util.ArrayList;
import java.util.Scanner;

public class Uzytkownik {

    public String Imie;
    public Punkty PunktyUzytkownika;
    public int PunktyNaReceUzytkownika;
    public ArrayList<Karta> KartyUzytkownika;

    public Uzytkownik() {
        PunktyUzytkownika = new Punkty(0);
        KartyUzytkownika = new ArrayList<Karta>();
        PunktyNaReceUzytkownika = 0;
        PunktyUzytkownika.Wartosc = 1000;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public void DodajPunkty(int ilosc){
        PunktyUzytkownika.Wartosc += ilosc;
    }

    public void DodajPunkty(int ilosc, double podatek){
        PunktyUzytkownika.Wartosc += (ilosc * (1.0 - podatek));
    }

    public void StworzUzytkownika()
    {
        System.out.println("Podaj imie: ");
        Scanner scaner = new Scanner(System.in);
        Imie = scaner.next();
    }
}
