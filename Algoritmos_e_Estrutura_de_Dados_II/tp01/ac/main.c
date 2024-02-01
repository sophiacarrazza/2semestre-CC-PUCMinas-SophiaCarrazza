#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE * arq = fopen("arq.txt", "w+");

    int n;
    float numf;

    scanf("%d",&n);

    for(int i=0; i<n; i++){
        scanf("%f",&numf);
        //fprintf(arq, "%f",numf);
        fwrite(&numf, sizeof(float), 1, arq);
    }

    fclose(arq);

    arq = fopen("arq.txt", "r");

    fseek(arq, 0, SEEK_END);
    long tam = ftell(arq);
    fseek(arq, -sizeof(float), SEEK_CUR);

    while (tam>=sizeof(float)){
        //fscanf(arq, "%f",&numf);
        fread(&numf, sizeof(float), 1, arq);
        printf("%g\n",numf);
        tam-=sizeof(float);
        fseek(arq, -2 * sizeof(float), SEEK_CUR);
    }

    fclose(arq);

    return 0;
}
