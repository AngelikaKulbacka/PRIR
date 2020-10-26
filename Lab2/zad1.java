package Lab2;

public class zad1
{
    public static void main(String[] args)
    {
        double wynikP = MetodaProstokatow.oblicz(0, Math.PI, 3000);
        System.out.println(wynikP);

        double wynikM = MetodaTrapezow.oblicz(0, Math.PI, 3000);
        System.out.println(wynikM);

        double wynikS = MetodaSimpsona.oblicz(0, Math.PI, 3000);
        System.out.println(wynikS);
    }
}