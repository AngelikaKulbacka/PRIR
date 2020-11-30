#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<time.h>
#include"mpi.h"

#define OSTATKI_SIL 500
#define CHORY 100
#define STADNINA 1
#define START 2
#define BIEG 3
#define KONIEC_BIEGU 4
#define UPADEK 5
#define NAKARM 5000

int jedzenie = 5000;
int ZATRZYMAJ=1, NIE_ZATRZYMUJ=0;
int liczba_procesow;
int nr_procesu;
int ilosc_koni;
int ilosc_torow = 4;
int ilosc_zajetych_torow = 0;
int tag = 1;
int wyslij[2];
int odbierz[2];

MPI_Status mpi_status;

void Wyslij(int nr_konia, int stan)
{
    wyslij[0] = nr_konia;
    wyslij[1] = stan;
    MPI_Send(&wyslij, 2, MPI_INT, 0, tag, MPI_COMM_WORLD);
    sleep(1);
}

void Stadnina(int liczba_procesow)
{
    int nr_konia, status;
    ilosc_koni = liczba_procesow - 1;
    printf("Halo. Witam serdecznie. Nadajemy ze stadniny. \n");
    if(rand()%2==1)
    {
        printf("Mamy piękną pogodę sprzyjającą energicznym wyścigom. \n");
    }
    else
    {
        printf("NIestety pogoda nie sprzyja dzisiejszym biegom. \n");
    }
    printf("Zyczymy Panstwu, przyjemnego widowiska \n \n \n");
    printf("Dysponujemy %d torami startowymi\n", ilosc_torow);
    sleep(2);
    while(ilosc_torow<=ilosc_koni)
    {
        MPI_Recv(&odbierz,2,MPI_INT,MPI_ANY_SOURCE,tag,MPI_COMM_WORLD, &mpi_status);
        nr_konia=odbierz[0];
        status=odbierz[1];
        if(status==1)
        {
            printf("Koń %d stoi na torze, przynajmniej nie bedzie upadku\n", nr_konia);
        }
        if(status==2)
        {
            printf("Koń %d pozwolenie na start z toru nr %d\n", nr_konia, ilosc_zajetych_torow);
            ilosc_zajetych_torow--;
        }
        if(status==3)
        {
            printf("Koń %d BIEGA\n", nr_konia);
        }
        if(status==4)
        {
            if(ilosc_zajetych_torow < ilosc_torow)
            {
                ilosc_zajetych_torow++;
                MPI_Send(&ZATRZYMAJ, 1, MPI_INT, nr_konia, tag, MPI_COMM_WORLD);
            }
            else
            {
                MPI_Send(&NIE_ZATRZYMUJ, 1, MPI_INT, nr_konia, tag, MPI_COMM_WORLD);
            }
        }
        if(status==5)
        {
            ilosc_koni--;
            printf("Ilosc koni %d\n", ilosc_koni);
        }
    }
    printf("Program zakonczyl dzialanie: )\n");
}

void Kon()
{
    int stan, suma, i;
    stan = BIEG;
    while(1)
    {
        if(stan==1)
        {
            if(rand()%2==1)
            {
                stan = START;
                jedzenie = NAKARM; 
                printf("Prosze o pozwolenie na start, koń %d\n",nr_procesu);
                Wyslij(nr_procesu,stan);
            }
            else
            {
                Wyslij(nr_procesu,stan);
            }
        }
        else if(stan==2)
        {
            printf("Wystartował koń %d\n",nr_procesu);
            stan = BIEG;
            Wyslij(nr_procesu,stan);
        }
        else if(stan==3)
        {
            jedzenie-=rand()%500;
            if(jedzenie <= OSTATKI_SIL)
            {
                stan = KONIEC_BIEGU;
                printf("prosze o pozwolenie na zatrzymanie konia\n");
                Wyslij(nr_procesu,stan);
            }
            else if(jedzenie <= CHORY)
            {
                stan = KONIEC_BIEGU;
                printf("koń %d jest chory, potrzebuje weterynarza\n", nr_procesu);
            }
            else
            {
                for(i=0; rand()%10000;i++);
            }
        }
        else if(stan==4)
        {
            int temp;
            MPI_Recv(&temp, 1, MPI_INT, 0, tag, MPI_COMM_WORLD, &mpi_status);
            if(temp==ZATRZYMAJ)
            {
                stan = STADNINA;
                printf("Zatrzymalem, konia %d\n", nr_procesu);
            }
            else
            {
                jedzenie-=rand()%500;
                if(jedzenie>0)
                {
                    Wyslij(nr_procesu,stan);
                }
                else
                {
                    stan = UPADEK;
                    printf("Koń sie przewrocil\n");
                    Wyslij(nr_procesu,stan);
                    return;
                }
            }
        }
    }
}

int main(int argc, char *argv[])
{
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD,&nr_procesu);
    MPI_Comm_size(MPI_COMM_WORLD,&liczba_procesow);
    srand(time(NULL));
    if(nr_procesu == 0)
    {
        Stadnina(liczba_procesow);
    }
    else
    {
        Kon();   
    }
    MPI_Finalize();
    return 0;
}