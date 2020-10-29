import java.util.function.Function;

public class M_Prostokatow extends Thread
{
    private double wynik;
    private double poczatek;
    private double koniec;
    private int iloscPodzialow;
    private int numerPodzialu;
    private Function<Double, Double> funkcja;
    
    public M_Prostokatow(double poczatek, double koniec, int iloscPodzialow, int numerPodzialu, Function<Double, Double> funkcja)
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
        double xi = dx*(numerPodzialu+1);
        double fxi = funkcja.apply(xi);
        wynik = fxi * dx;
    }

    public double getWynik()
    {
        return wynik;
    }
}
