import java.util.function.Function;

public class M_Trapezow extends Thread
{
    private double wynik;
    private double poczatek;
    private double koniec;
    private int iloscPodzialow;
    private int numerPodzialu;
    private Function<Double, Double> funkcja;
    
    public M_Trapezow(double poczatek, double koniec, int iloscPodzialow, int numerPodzialu, Function<Double, Double> funkcja)
    {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.iloscPodzialow = iloscPodzialow;
        this.numerPodzialu = numerPodzialu;
        this.funkcja = funkcja;
    } 

    public void run()
    {
        double dx = (koniec - poczatek) / iloscPodzialow;
        double xi = poczatek + dx * numerPodzialu;
        double fxi = funkcja.apply(xi);
        if (numerPodzialu==0 || numerPodzialu==iloscPodzialow)
        {
            fxi = fxi/2;
        }
        wynik = fxi * dx;
    }

    public double getWynik()
    {
        return wynik;
    }
}