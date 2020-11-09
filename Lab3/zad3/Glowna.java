public class Glowna 
{
    static int ilosc_koni=10;
    static int ilosc_pasow=5;
    static Stadnina stadnina;
    public Glowna()
    { }
    public static void main(String[] args) 
    {
        stadnina=new Stadnina(ilosc_pasow, ilosc_koni);
        for(int i=0;i<ilosc_koni;i++)
        new Kon(i,2000,stadnina).start();
    }
}