import java.util.Scanner;

public class Gra {
    protected Uzytkownik gracz;
    protected TaliaKart talia;
    int zaklad;

    public Gra() {
        gracz = new Uzytkownik();
        this.talia = new TaliaKart();
        zaklad = 0;
    }

    public Gra(Uzytkownik nowyGracz) {
        gracz = nowyGracz;
        this.talia = new TaliaKart();
    }

    public void RozpocznijGre() {
        System.out.println("Rozpoczecie gry.");
    }

    public void ZakonczGre() {
        System.out.println("Koniec gry");
        CzyscEkran();
        CzyscEkran();
    }

    protected int Zaklad() {
        while (true) {
            System.out.println("Liczba posiadanych punktow: " + this.gracz.PunktyUzytkownika.getWartosc() + "\tWybierz: 0. aby zakonczyc gre");
            System.out.println("Postaw zaklad: ");
            Scanner scanner = new Scanner(System.in);
            int suma = scanner.nextInt();
            if (suma > gracz.PunktyUzytkownika.Wartosc) {
                System.out.println("Nie masz tyle punktow");
            } else if(suma == 0)
            {
                return -1;
            }
            else if (suma < 20) {
                System.out.println("Za mala kwota (min 20)");
            }
            else {
                return suma;
            }
        }

    }

    protected boolean CzyDobrac() {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Czy dobrac karty? 1 - tak, 0 - nie");
        int decyzja = scaner.nextInt();

        if (decyzja == 1)
            return true;
        else
            return false;
    }

    protected void CzyscEkran()
    {
        for (int clear = 0; clear < 5; clear++) {
            System.out.println("\b");
        }
    }
}