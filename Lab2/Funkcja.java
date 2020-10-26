package Lab2;

public class Funkcja extends Thread
{
    private double wynik;
    private double x;
    
    public Funkcja(double x)
    {
        this.x = x;
    } 

    public void run()
    {
        wynik = Math.sin(x);
    }

    public double getWynik()
    {
        return wynik;
    }
}
