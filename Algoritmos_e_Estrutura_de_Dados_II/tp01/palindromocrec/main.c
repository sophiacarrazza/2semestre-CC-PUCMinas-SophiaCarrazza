#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isPalindromo(char palavra[], int inicio, int fim){
        if(fim+1 <= 1){
            return true;//palavra vazia ou 1 caracter = palindromo (todas se anularam por serem iguais)
        }
        if(palavra[inicio] != palavra[fim]){
            return false;
        }

        return isPalindromo(palavra, inicio+1, fim-1);

}
int main() {
    char palavra[1000];
    while (1) {
        scanf(" %[^\n]", palavra);

        if (strcmp(palavra, "FIM") == 0) {
            break;
        }
        int tam = strlen(palavra);
        bool resp = isPalindromo(palavra, 0, tam-1);

        if (resp == false) {
            printf("NAO\n");
        } else {
            printf("SIM\n");
        }
    }

    return 0;
}
