#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main() {
    char palavra[800]; 

    while (1) {
        scanf("%s", palavra);

        if (strcmp(palavra, "fim") == 0) {
            break;
        }

        bool resp = true;
        int tam = strlen(palavra);

        for (int i = 0, j = tam - 1; i < (tam / 2) && j >= 0; i++, j--) {
            if (palavra[i] != palavra[j]) {
                resp = false;
                i = tam;
            }
        }

        if (resp == false) {
            printf("NAO\n");
        } else {
            printf("SIM\n");
        }
    }

    return 0;
}