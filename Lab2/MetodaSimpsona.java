import java.util.ArrayList;
import java.util.function.Function;

public class MetodaSimpsona
{
    public static double oblicz(double poczatek, double koniec, int iloscPodzialow, Function<Double, Double> funkcja)
    {
        ArrayList <M_Simpsona> tab = new ArrayList<M_Simpsona>();        
        
        M_Simpsona f0 = new M_Simpsona(poczatek, koniec, iloscPodzialow, 0, 1, funkcja);
        M_Simpsona fn = new M_Simpsona(poczatek, koniec, iloscPodzialow, iloscPodzialow, 1, funkcja);
        f0.start();
        fn.start();
        tab.add(f0);
        tab.add(fn);

        for (int i=1; i<=iloscPodzialow; i++)
        {
            M_Simpsona f = new M_Simpsona(poczatek, koniec, iloscPodzialow, i, 2, funkcja);
            f.start();
            tab.add(f);
        }
        
        for (int i=1; i<=iloscPodzialow; i++)
        {
            M_Simpsona f = new M_Simpsona(poczatek, koniec, iloscPodzialow, i, 3, funkcja);
            f.start();
            tab.add(f);
        }
        
        double wynik = 0;
        try
        {
            for (int i=0; i<tab.size(); i++)
            {
                M_Simpsona f = tab.get(i);
                f.join();
                wynik = wynik + f.getWynik();
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        return wynik;
    }
}
