import java.util.function.Function;

public class zad1
{
    public static void main(String[] args)
    {
        Function<Double, Double> funkcja = (Double x) -> Math.sin(x);

        double wynikP = MetodaProstokatow.oblicz(0, Math.PI, 50, funkcja);
        System.out.println(wynikP);

        double wynikM = MetodaTrapezow.oblicz(0, Math.PI, 50, funkcja);
        System.out.println(wynikM);

        double wynikS = MetodaSimpsona.oblicz(0, Math.PI, 50, funkcja);
        System.out.println(wynikS);
    }
}