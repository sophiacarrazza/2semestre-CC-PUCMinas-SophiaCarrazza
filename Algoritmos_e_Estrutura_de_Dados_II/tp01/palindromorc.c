#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isPalindromo(char *palavra) {
    int tam = strlen(palavra);

    if (tam <= 1) {
        return true;
    }

    if (palavra[0] != palavra[tam-1]) {
        return false; 
    }
    return isPalindromo(palavra+1, tam-2);
}

int main() {
    char palavra[700];

    while (1) {
        scanf("%s", palavra);

        if (strcmp(palavra, "FIM") == 0) {
            break;
        }
        bool resp = isPalindromo(palavra);

        if (resp) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}