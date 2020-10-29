import java.util.function.Function;

public class MetodaTrapezow
{
    public static double oblicz(double poczatek, double koniec, int iloscPodzialow, Function<Double, Double> funkcja)
    {
        M_Trapezow[] tab = new M_Trapezow[iloscPodzialow+1];
        double wynik = 0;

        for (int i=0; i<iloscPodzialow+1; i++)
        {
            M_Trapezow f = new M_Trapezow(poczatek, koniec, iloscPodzialow, i, funkcja);
            f.start();
            tab[i] = f;
        }

        for (int i=0; i<iloscPodzialow+1; i++)
        {
            try
            {
                tab[i].join();
                wynik = wynik + tab[i].getWynik();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
        return wynik;
    }
}
