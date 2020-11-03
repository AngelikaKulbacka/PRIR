public class Glowna 
{
    static int ilosc_samolotow=10;
    static int ilosc_pasow=5;
    static Lotnisko lotnisko;
    public Glowna()
    { }
    public static void main(String[] args) 
    {
        lotnisko=new Lotnisko(ilosc_pasow, ilosc_samolotow);
        for(int i=0;i<ilosc_samolotow;i++)
        new Samolot(i,2000,lotnisko).start();
    }
}