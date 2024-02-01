#include <stdio.h>
int main (void)
{
    int num;
    printf("Digite a quantidade de elementos do array\n");
    scanf("%d", &num);

    printf("Digite os numeros do array\n");
    int array[num];
    for (int i=0; i<num; i++){
     scanf("%d", &array[i]);
    }
    int maior = array[0];
    int menor = array[0];

    for (int i=0; i<num; i++){
     if (array[i] >= maior){
      maior = array[i];
     }
     if (array[i] <= menor){
      menor = array[i];
     }
    }

    printf("O maior numero deste array e %d e o menor e %d", maior, menor);
}
