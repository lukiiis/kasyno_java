import java.util.ArrayList;
import java.util.List;

public class TaliaKart {
    public TaliaKart() {
        Karty = new ArrayList<Karta>();
    }

    public ArrayList<Karta> Karty;

    public void ResetujTalie() {
        Karty.clear();
        UtworzTalie();
    }

    public void ResetujTalieWojna() {
        Karty.clear();
        UtworzTalie();
    }

    public void UtworzTalie() {
        for (int j = 0; j < 4; j++) {
            for (int i = 2; i < 11; i++) {
                String kolor = "";
                switch (j) {
                    case 0:
                        kolor = "pik";
                        break;
                    case 1:
                        kolor = "karo";
                        break;
                    case 2:
                        kolor = "trefl";
                        break;
                    case 3:
                        kolor = "kier";
                        break;
                }
                Karty.add(new Karta(i, kolor));
                if (i == 2 || i == 3 || i == 4 || i == 5) {
                    switch (i) {
                        case 2 -> Karty.add(new Karta(i, kolor, "JOPEK"));
                        case 3 -> Karty.add(new Karta(i, kolor, "DAMA"));
                        case 4 -> Karty.add(new Karta(i, kolor, "KROL"));
                        case 5 -> Karty.add(new Karta(11, kolor, "AS"));
                        default -> {
                            return;
                        }
                    }

                }
            }
        }

    }

    public void UtworzTalieWojna() {
        for (int j = 0; j < 4; j++) {
            for (int i = 2; i < 12; i++) {
                String kolor = "";
                switch (j) {
                    case 0:
                        kolor = "pik";
                        break;
                    case 1:
                        kolor = "karo";
                        break;
                    case 2:
                        kolor = "trefl";
                        break;
                    case 3:
                        kolor = "kier";
                        break;
                }
                Karty.add(new Karta(i, kolor));
                if (i == 2 || i == 3 || i == 4 || i == 5) {
                    switch (i) {
                        case 2 -> Karty.add(new Karta(11, kolor, "JOPEK"));
                        case 3 -> Karty.add(new Karta(12, kolor, "DAMA"));
                        case 4 -> Karty.add(new Karta(13, kolor, "KROL"));
                        case 5 -> Karty.add(new Karta(14, kolor, "AS"));
                        default -> {
                            return;
                        }
                    }

                }
            }
        }

    }

    public Karta DobierzKarte() {
        if (Karty.size() == 0)
            ResetujTalie();

        int random = (int) (Math.random() * Karty.toArray().length);
        var dobierana = Karty.remove(random);

        return dobierana;
    }
}