import java.util.function.Function;

public class M_Simpsona extends Thread
{
    private double wynik;
    private double poczatek;
    private double koniec;
    private int iloscPodzialow;
    private int numerPodzialu;
    private Function<Double, Double> funkcja;
    private int rodzaj;
    
    public M_Simpsona(double poczatek, double koniec, int iloscPodzialow, int numerPodzialu, int rodzaj, Function<Double, Double> funkcja)
    {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.iloscPodzialow = iloscPodzialow;
        this.numerPodzialu = numerPodzialu;
        this.funkcja = funkcja;
        this.rodzaj = rodzaj;
    } 

    public void run()
    {
        double dx = (koniec - poczatek) / iloscPodzialow;
        double fti = 0;
        if (rodzaj==1)
        {
            double xi = 0;
            if(numerPodzialu==0)
            {
                xi = poczatek;
            }
            else if(numerPodzialu==iloscPodzialow)
            {
                xi = koniec;
            }
            fti = funkcja.apply(xi);
        }
        else if (rodzaj==2)
        {
            double xi = poczatek + numerPodzialu * dx;
            fti = funkcja.apply(xi) * 2;
        }
        else if (rodzaj==3)
        {
            double a = poczatek + (numerPodzialu-1) * dx;
            double b = poczatek + (numerPodzialu+1) * dx;
            double xi = (a + b) / 2;
            fti = funkcja.apply(xi) * 4;
        }
        else
        {
            wynik = 0;
            return;
        }
        wynik = dx/6 * fti;
    }

    public double getWynik()
    {
        return wynik;
    }
}