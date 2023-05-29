public class Karta {

    public int Wartosc;
    public String Kolor;
    public String Figura;

    public String wyswietl(){
        if(this.Figura != null)
            return Figura;
        else
            return Integer.toString(this.Wartosc);
    }

    public int getWartosc() {
        return Wartosc;
    }

    public void setWartosc(int wartosc) {
        Wartosc = wartosc;
    }

    public String getKolor() {
        return Kolor;
    }

    public void setKolor(String kolor) {
        Kolor = kolor;
    }


    public Karta(int wartosc, String kolor) {
        Wartosc = wartosc;
        Kolor = kolor;
        Figura = null;
    }

    public Karta(int wartosc, String kolor, String figura) {
        Wartosc = wartosc;
        Kolor = kolor;
        Figura = figura;
    }
}
