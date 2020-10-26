package Lab2;

public class MetodaSimpsona implements metody
{
    public static double oblicz(double poczatek, double koniec, int n)
    {
        double dx = (koniec - poczatek) / n;
        double wynik = 0;

        Funkcja f0 = new Funkcja(poczatek);
        Funkcja fn = new Funkcja(koniec);
        f0.start();
        fn.start();

        Funkcja [] fi = new Funkcja[n-1];

        for (int i=1; i<=n-1; i++)
        {
            double x = poczatek + i * dx;
            fi[i-1] = new Funkcja(x);
            fi[i-1].start();
        }

        Funkcja [] fti = new Funkcja[n];

        for (int i=1; i<=n; i++)
        {
            double a = poczatek + (i-1) * dx;
            double b = poczatek + (i+1) * dx;
            double x = (a + b) / 2;
            fti[i-1] = new Funkcja(x);
            fti[i-1].start();
        }

        try
        {
            f0.join();
            wynik = wynik + f0.getWynik();
            fn.join();
            wynik = wynik + fn.getWynik();

            for (int i=0; i<fi.length; i++)
            {
                wynik = wynik + 2 * fi[i].getWynik();
            }

            for (int i=0; i<fti.length; i++)
            {
                wynik = wynik + 4 * fti[i].getWynik();
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        wynik = dx/6 * wynik;

        return wynik;
    }
}
