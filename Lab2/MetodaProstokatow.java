package Lab2;

public class MetodaProstokatow implements metody
{
    public static double oblicz(double poczatek, double koniec, int n)
    {
        double dx = (koniec - poczatek) / n;

        Funkcja[] tab = new Funkcja[n];
        for (int i=0; i<n; i++)
        {
            tab[i] = new Funkcja(dx*(i+1));
            tab[i].start();
        }

        double wynik = 0;
        for (int i=0; i<n; i++)
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

        wynik = wynik * dx;

        return wynik;
    }
}
