import java.util.Scanner;

public class Kasyno {
    private Gra gra;
    private Uzytkownik gracz;
    private Scanner scanner;
    public Kasyno() {
        gracz = new Uzytkownik();
        scanner = new Scanner(System.in);
    }

    public void OtworzKasyno()
    {
        System.out.println("Witamy w kasynie");
        gracz.StworzUzytkownika();

        while (true)
        {
            gra = WyborGry();
            if(gra == null)
            {
                System.out.println("Wybierz x jezeli na pewno checesz wyjsc z kasyna");
                if(scanner.next().equals("x"))
                    break;
                else
                    continue;
            }
            gra.RozpocznijGre();

            if(gracz.PunktyUzytkownika.Wartosc == 0)
                break;
        }

        System.out.println("Do widzenia!");
    }

    private Gra WyborGry()
    {

        System.out.println("Wybierz gre:\t1. Gra w oczko\t2. Gra w wojne");
        System.out.println("Wybierz:\t0. aby wyjsc z kasyna");
        var result = scanner.nextInt();
        switch (result)
        {
            case 1 -> {return new Oczko(gracz);}
            case 2 -> {return new Wojna(gracz);}
            default -> {return null;}

        }
    }
}
