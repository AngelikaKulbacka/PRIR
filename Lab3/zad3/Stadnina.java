import java.util.Random;

class Kon extends Thread 
{
    //definicja stanu konia
    static int STADNINA=1;
    static int START=2;
    static int BIEG=3;
    static int KONIEC_BIEGU=4;
    static int UPADEK=5;
    static int NAKARM=1000;
    static int OSTATKI_SIL=500;

    //zmienne pomocnicze
    int numer;
    int jedzenie;
    int stan;
    Stadnina s;
    Random rand;

    public Kon(int numer, int jedzenie, Stadnina s)
    {
        this.numer=numer;
        this.jedzenie=jedzenie;
        this.stan=BIEG;
        this.s=s;
        rand=new Random();
    }

    public void run()
    {
        while(true)
        {
            if(stan==STADNINA)
            {
                if(rand.nextInt(2)==1)
                {
                    stan=START;
                    jedzenie=NAKARM;
                    System.out.println("W stadninie prosze o pozwolenie na start, Kon "+numer);
                    stan=s.start(numer);
                }
                else
                {
                    System.out.println("Postoje sobie jeszcze troche");
                }
            }
            else if(stan==START)
            {
                System.out.println("Wystartowalem, kon "+numer);
                stan=BIEG;
            }
            else if(stan==BIEG)
            {
                jedzenie-=rand.nextInt(500);
                System.out.println("Kon "+numer+" w biegu");
                if(jedzenie<=OSTATKI_SIL)
                {
                    stan=KONIEC_BIEGU;
                }
                else try
                {
                    sleep(rand.nextInt(1000));
                }
                catch (Exception e)
                {}
            }
            else if(stan==KONIEC_BIEGU)
            {
                System.out.println("Prosze o pozowolenie na ladowanie "+numer+" ilosc paliwa "+jedzenie);
                stan=s.zatrzymaj();
                if(stan==KONIEC_BIEGU)
                {
                    jedzenie-=rand.nextInt(500);
                    System.out.println("OSTATKI_SIL "+jedzenie);
                    if(jedzenie<=0) stan=UPADEK;
                }
            }
            else if(stan==UPADEK)
            {
                System.out.println("UPADEK kon "+numer);
                s.zmniejsz();
            }
        }
    } 
}

public class Stadnina 
{
    static int STADNINA=1;
    static int START=2;
    static int BIEG=3;
    static int KONIEC_BIEGU=4;
    static int UPADEK=5;
    int ilosc_pasow;
    int ilosc_zajetych;
    int ilosc_koni;

    Stadnina(int ilosc_pasow,int ilosc_koni)
    {
        this.ilosc_pasow=ilosc_pasow;
        this.ilosc_koni=ilosc_koni;
        this.ilosc_zajetych=0;
    }

    synchronized int start(int numer)
    {
        ilosc_zajetych--;
        System.out.println("Pozwolenie na start konia "+numer);
        return START;
    }

    synchronized int zatrzymaj()
    {
        try
        {
            Thread.currentThread();
            Thread.sleep(1000);
        }
        catch(Exception ie)
        {}
        if(ilosc_zajetych<ilosc_pasow)
        {
        ilosc_zajetych++;
        System.out.println("Pozwolenie na zatrzymanie na pasie "+ilosc_zajetych);
        return STADNINA;
        }
        else
        {
            return KONIEC_BIEGU;
        }
    }

    synchronized void zmniejsz()
    {
        ilosc_koni--;
        System.out.println("ZABILEM");
        if(ilosc_koni==ilosc_pasow) System.out.println("Ilosc koni taka sama jak pasow");
    }
}