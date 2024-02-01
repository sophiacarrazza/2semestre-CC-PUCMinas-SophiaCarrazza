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
//   FILE *arq = fopen(".\\tmp\\players.csv", "r");
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
//compara��o pro quicksort
int compararJogadores(const void* a, const void* b) {
    return strcmp(((Jogador*)a)->nome, ((Jogador*)b)->nome);
    comp++;
}

// funcao p trocar elementos - (aux)
void trocar(Jogador* a, Jogador* b) {
    Jogador temp = *a;
    *a = *b;
    *b = temp;
}
void ordSelecao(Jogador lista[], int n, int i) {
        if (i==n-1){
            return;
        }
        //for (int i = 0; i < n - 1; i++) {
            int menorIndice = i;

            // Encontre o �ndice da menor string no restante da lista
            for (int j = i + 1; j < n; j++) {
                if (strcmp(lista[j].nome, lista[menorIndice].nome) < 0) {
                    menorIndice = j;
                }
                comp++;
            }

            // trocando a string atual com a menor string achada
            Jogador temp = lista[i]; // temp � o menor jogador encontrado
            lista[i] = lista[menorIndice]; //i = menor indice da lista
            lista[menorIndice] = temp; // menor indice � temp
        //}
        ordSelecao(lista, n, i+1);
		}


    void insercaoPorCor(Jogador array[], int n, int cor, int h){
    for (int i = (h + cor); i < n; i+=h) {
        int tmp = array[i].peso;
        String tmpn = array[i].nome;
        Jogador tmpj = array[i];

        int j = i - h;
        while (((j >= 0) && (array[j].peso > tmp)) || ((array[j].peso == tmp) && (strcmp(array[j].nome, tmpn) > 0))) {
            array[j + h] = array[j];
            j-=h;
        }
        array[j + h] = tmpj;
    }
}
    void shellsort(Jogador array[], int n) {
    int h = 1;

    do { h = (h * 3) + 1; } while (h < n);

    do {
        h /= 3;
        for(int cor = 0; cor < h; cor++){
            insercaoPorCor(array, n, cor, h);
        }
    } while (h != 1);
}
/*// quicksort
void quicksort(Jogador lista[], int esquerda, int direita) {
    if (esquerda < direita) {
        //pivo � o elemento do meio
        int meio = esquerda + (direita - esquerda) / 2;
        Jogador pivo = lista[meio];

        // particionou a lista em dois subarrays
        int i = esquerda, j = direita;
        while (i <= j) {
            while (compararJogadores(&lista[i], &pivo) < 0) {
                i++;
            }
            while (compararJogadores(&lista[j], &pivo) > 0) {
                j--;
            }
            if (i <= j) {
                trocar(&lista[i], &lista[j]);
                i++;
                j--;
            }
        }

        // recursao pra ordenar os elementos antes e depois do piv�
        quicksort(lista, esquerda, j);
        quicksort(lista, i, direita);
    }
}*/


void pesquisa(){
    Jogador lista [500];
    String pubin = (String)malloc(sizeof(char)*5);
    scanf("%s", pubin);
    getchar();

    int i=0;
    comp++;
    while(pubin[0] != 'F'){
        ler(stringToInt(pubin));
        lista[i] = ler(stringToInt(pubin));
        i++;
        scanf(" %s", pubin);
        getchar();
        comp++;
    }
 /*   for(int j=0; j<i; j++){
        printf("%s ",lista[j].nome);
    }*/

    //quicksort(lista, 0, i-1);
    //ordSelecao(lista, i, 0);

    shellsort(lista, i);



    for(int h=0; h< i; h++){
        imprimir(lista[h]);
    }



}

int main()
{
    clock_t tempoInicial = clock();

    pesquisa();

    FILE *escrever;
    escrever = fopen("00817611_shellsort.txt", "w");
    if (escrever == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }
    fprintf(escrever, "00817611 \t %ld \t %d\n", (clock() - tempoInicial), comp);
    fclose(escrever);

    return 0;
}
