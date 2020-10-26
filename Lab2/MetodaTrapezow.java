package Lab2;

public class MetodaTrapezow implements metody
{
    public static double oblicz(double poczatek, double koniec, int n)
    {
        double dx = (koniec - poczatek) / n;
        Funkcja[] tab = new Funkcja[n+1];
        double wynik = 0;

        for (int i=0; i<n+1; i++)
        {
            double x = poczatek + dx * i;
            Funkcja f = new Funkcja(x);
            f.start();
            tab[i] = f;
        }

        for (int i=0; i<n+1; i++)
        {
            try
            {
                tab[i].join();
                if (i==0 || i==n)
                {
                    wynik = wynik + tab[i].getWynik()/2;
                }
                else
                {
                    wynik = wynik + tab[i].getWynik();
                }
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
        return wynik * dx;
    }
}
