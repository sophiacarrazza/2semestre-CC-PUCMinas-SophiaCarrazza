#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main()
{
    char palavra[20];
    char ipalavra[20]  = {0};
    do
    {
        int n=0;

        scanf("%s", palavra);

       // for(int i=strlen(palavra)-1; i>=0 ; i--)
        //{
        //    palavra[i] = toupper(palavra[i]);
        //}

        for(int i=strlen(palavra)-1; i>=0 ; i--)
        {
            ipalavra[n] = palavra[i];
            n+=1;
        }
        ipalavra[n]= '\0';
//
        if ((strcmp(palavra, "FIM")== 0))
        {
            break;
        }
        else if(strcmp(palavra, ipalavra)== 0)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");

        }
    }
    while (strcmp(palavra,"FIM")!=0);

    return 0;
}
