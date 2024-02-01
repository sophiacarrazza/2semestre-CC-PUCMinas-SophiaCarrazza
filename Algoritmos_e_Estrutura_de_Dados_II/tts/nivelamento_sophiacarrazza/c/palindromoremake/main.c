#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include<conio.h>

int main()
{
    char palavra [20];

    do{
    scanf("%s", palavra);

    bool resp = true;
    int tam = strlen(palavra);
    for(int i=0, j=tam-1; i<tam/2, j>=0; i++, j--){
        if(strcmp(palavra, "fim")==0){
            break;
        } else if(palavra[i]!=palavra[j]){
            resp = false;
            i=tam;
        }
    }
    if(resp == false){
        printf("NAO\n");
    } else {
        printf("SIM\n");
    }
    }while(strcmp(palavra, "fim")!=0);
    return 0;
}
