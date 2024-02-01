#include <stdio.h>
#include <stdlib.h>
#include <time.h>

char RandomNum() {
    return 'a' + (rand() % 26);
}

char *Aaleatorio(const char *palavra) {
    char letrain = RandomNum();
    char letranew = RandomNum();
    int tam = strlen(palavra);
    char *resp = (char *)malloc((tam + 1) * sizeof(char));

    if (resp == NULL) {
        fprintf(stderr, "Erro!\n");
        exit(1);
    }

    for (int i = 0; i < tam; i++) {
        char letra = palavra[i];
        if (letra == letrain) {
            resp[i] = letranew;
        } else {
            resp[i] = letra;
        }
    }

    resp[tam] = '\0';
    return resp;
}

int main() {
    srand(4);

    while (1) {
        char palavra[50];
        scanf("%s", palavra);

        if (palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M') {
            break;
        } else {
            char *resultado = Aaleatorio(palavra);
            printf("%s\n", resultado);
            free(resultado);
        }
    }

    return 0;
}
