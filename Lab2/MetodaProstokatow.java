import java.util.function.Function;

public class MetodaProstokatow implements metody
{
    public static double oblicz(double poczatek, double koniec, int iloscPodzialow, Function<Double, Double> funkcja)
    {
        M_Prostokatow[] tab = new M_Prostokatow[iloscPodzialow];
        for (int i=0; i<iloscPodzialow; i++)
        {
            tab[i] = new M_Prostokatow(poczatek, koniec, iloscPodzialow, i, funkcja);
            tab[i].start();
        }

        double wynik = 0;
        for (int i=0; i<iloscPodzialow; i++)
        {
            try
            {
                tab[i].join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            wynik = wynik + tab[i].getWynik();
        }
        return wynik;
    }
}
