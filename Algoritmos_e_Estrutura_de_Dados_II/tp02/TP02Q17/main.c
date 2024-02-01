#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

typedef char* String;
int comp=0;

int stringToInt(String str){
    int res=0;
    int linha=1;
    for(int i=strlen(str)-1; i>=0; i--, linha*=10){
        res +=((int)(str[i])-48)*linha;
    }
    return res;
}

typedef struct Jogador{
int id;
String nome;
int altura;
int peso;
String universidade;
int anoNascimento;
String cidadeNascimento;
String estadoNascimento;
} Jogador;

void imprimir(Jogador jogador) {
    printf("[%d ## %s ## ", jogador.id, jogador.nome);

    if (jogador.altura != -1) {
        printf("%d", jogador.altura);
    } else {
        printf("nao informado");
    }
    comp++;

    printf(" ## ");

    if (jogador.peso != -1) {
        printf("%d", jogador.peso);
    } else {
        printf("nao informado");
    }
    comp++;

    printf(" ## ");

    if (jogador.anoNascimento != -1) {
        printf("%d", jogador.anoNascimento);
    } else {
        printf("nao informado");
    }
    comp++;

    printf(" ## %s ## %s ## %s]\n", jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);

    free(jogador.nome);
    free(jogador.universidade);
    free(jogador.cidadeNascimento);
    free(jogador.estadoNascimento);
}


Jogador ler(int nid){
 //    FILE *arq = fopen(".\\tmp\\players.csv", "r");
  FILE *arq = fopen("/tmp/players.csv", "r");
    if (arq == NULL) {
        printf("Abertura do arquivo nao sucedida\n");
        exit(1);
    }
    comp++;

    String linha = (String)calloc(700, sizeof(char));
    Jogador player;
    int nlinha=0;
    int j=0;

    //tem q ler cada linha pra percorrer o arquivo
    //id+3 pq o primeiro jogador ta na linha 2, ent o while para antes do id+3
    comp++;
    while(nlinha != nid+2){
        fscanf(arq,"%[^\n]", linha);
        fgetc(arq);
        //printf("%s\n", linha);
        nlinha++;
        comp++;
    }

    fclose(arq);
//    puts(linha);

    String tmp = (String)calloc(400, sizeof(char));
    int caracter=0;
    comp++;
    while(linha[caracter] != ','){
        tmp[j]=linha[caracter];
        j++;
        caracter++;
        comp++;

    }
    player.id = stringToInt(tmp);
//    printf("%d", player.id);

    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    if(linha[caracter] == ','){
            strcpy(tmp, "nao informado");
        }
    j=0;
    comp++;
    while(linha[caracter] != ','){
        tmp[j]=linha[caracter];
        j++;
        caracter++;
        comp++;
    }
//    puts(tmp);
    player.nome = strdup(tmp);

    player.altura = -1; // inicializando como -1
    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    j=0;
    comp++;
    while (linha[caracter] != ',') {
        tmp[j] = linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    if (strlen(tmp) > 0) {
        player.altura = stringToInt(tmp);
    }
    comp++;

    player.peso = -1;
    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    j=0;
    comp++;
    while(linha[caracter] != ','){
        tmp[j]=linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    if (strlen(tmp) > 0) {
        player.peso = stringToInt(tmp);

    }
    comp++;

    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    if(linha[caracter] == ','){
            strcpy(tmp, "nao informado");
    }
    comp++;

    j=0;
    comp++;
    while(linha[caracter] != ','){
        tmp[j]=linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    player.universidade = strdup(tmp);


    player.anoNascimento = -1;
    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    j=0;
    comp++;
    while(linha[caracter] != ','){
        tmp[j]=linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    if (strlen(tmp) > 0){
        player.anoNascimento = stringToInt(tmp);
    }
    comp++;

    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    if(linha[caracter] == ','){
            strcpy(tmp, "nao informado");
        }
        comp++;
    j=0;
    comp++;
    while(linha[caracter] != ','){
        tmp[j]=linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    player.cidadeNascimento = strdup(tmp);

    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    if(linha[caracter] == ',' || linha[caracter] == '\n' || linha[caracter] == '\0'){
            strcpy(tmp, "nao informado");
        }
        comp++;
    j=0;
    comp++;
    while(linha[caracter] != ',' && linha[caracter] != '\n' && linha[caracter] != '\0'){
        tmp[j]=linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    player.estadoNascimento = strdup(tmp);

return player;

}

void swap (Jogador *x, Jogador *y){
        Jogador temp = *x;
        *x = *y;
        *y = temp;

}


void construir(Jogador array[], int tamHeap) {
    for (int i = tamHeap; i > 1; i--) {
        int j = i;
        while (j > 1) {
            if (array[j].altura > array[j / 2].altura || (array[j].altura == array[j / 2].altura && strcmp(array[j].nome, array[j / 2].nome) > 0) || (array[j].altura == array[j / 2].altura && strcmp(array[j].nome, array[j / 2].nome) == 0 && array[j].id > array[j / 2].id)) {
                swap(&array[j], &array[j / 2]);
                j /= 2;
            } else {
                break;
            }
        }
    }
}




int getMaiorFilho(Jogador array[], int i, int tamHeap){
    int filho;
    if ((2*i == tamHeap || array[2*i].altura > array[2*i+1].altura) || ((array[2*i].altura == array[2*i+1].altura) && (strcmp(array[2*i].nome, array[2*i+1].nome) > 0))){
        filho = 2*i;
        comp++;
    } else {
        filho = 2*i + 1;
    }
    return filho;
}


void reconstruir(Jogador array[], int tamHeap){
    int i = 1;
    while(i <= (tamHeap/2)){
            comp++;
        int filho = getMaiorFilho(array, i, tamHeap);
        if((array[i].altura < array[filho].altura) || ((array[i].altura == array[filho].altura) && (strcmp(array[i].nome, array[filho].nome) < 0))){
            swap(&array[i], &array[filho]);
            i = filho;
            comp++;
        }else{
            i = tamHeap;
        }
    }
}
void heapsort(Jogador array[], int n, int k) {

    // Alterar o vetor ignorando a posi��o zero
    Jogador arrayTmp[n + 1];
    for (int i = 0; i < n; i++) {
        arrayTmp[i + 1] = array[i];
        comp++;
    }

    // Constru��o do heap
    for (int tamHeap = 2; tamHeap <= k; tamHeap++) {
        construir(arrayTmp, tamHeap);
        comp++;
    }

    for (int i = k + 1; i <= n; i++)
        if (arrayTmp[i].altura < arrayTmp[1].altura || (arrayTmp[i].altura == arrayTmp[1].altura && strcmp(arrayTmp[i].nome, arrayTmp[1].nome) < 0)) {
        swap(&arrayTmp[i], &arrayTmp[1]);
        reconstruir(arrayTmp, k);
        comp++;
    }

    // Ordena��o propriamente dita
    int tamHeap = k;
        while (tamHeap > 1) {
        swap(&arrayTmp[1], &arrayTmp[tamHeap--]);
        reconstruir(arrayTmp, tamHeap);
        comp++;
    }

    // Alterar o vetor para voltar a posi��o zero
    for (int i = 0; i < n; i++) {
        array[i] = arrayTmp[i + 1];
        comp++;
    }

}




void pesquisa(){
    Jogador lista [500];
    String pubin = (String)malloc(sizeof(char)*5);
    scanf("%s", pubin);
    getchar();

    int i=0;
    int k = 10;
    comp++;
    while(pubin[0] != 'F'){
        lista[i] = ler(stringToInt(pubin));
        i++;
        scanf(" %s", pubin);
        getchar();
        comp++;
    }

    heapsort(lista, i, 10);

    for(int h=0; h< k; h++){
        imprimir(lista[h]);
    }



}

int main()
{
    clock_t tempoInicial = clock();

    pesquisa();

    FILE *escrever;
    escrever = fopen("00817611_heapsortparcial.txt", "w");
    if (escrever == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }
    fprintf(escrever, "00817611 \t %ld \t %d\n", (clock() - tempoInicial), comp);
    fclose(escrever);

    return 0;
}
