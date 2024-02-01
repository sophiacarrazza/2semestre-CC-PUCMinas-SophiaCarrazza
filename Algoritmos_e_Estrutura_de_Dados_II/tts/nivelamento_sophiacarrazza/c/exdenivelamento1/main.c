#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main()
{
    bool contido = false;
    int num, quant;
    printf("Digite a qnt de nums da lista\n");
    scanf("%d", &quant);
    printf("Digite a lista de numeros inteiros\n");
    int nums [quant];
    for (int i=0; i<=quant-1;i++){
       scanf("%d", &nums[i]);
    }

    printf("\nDigite um numero para ser verificado dentro da lista\n");
    scanf("%d", &num);
    for(int i=0; i<=sizeof(nums);i++){
        if(nums[i]==num){
            contido = true;
            break;
        }
    }

    if(contido==true){
        printf("O elemento %d esta contido na lista digitada.", num);
    } else {
        printf("O elemento %d nao esta contido na lista digitada.", num);
    }
    return 0;
}
