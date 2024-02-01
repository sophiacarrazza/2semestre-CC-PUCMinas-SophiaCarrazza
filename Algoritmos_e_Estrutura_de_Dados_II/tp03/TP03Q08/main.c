#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>



typedef char* String;

typedef struct Jogador {
    int id;
    String nome;
    int altura;
    int peso;
    String universidade;
    int anoNascimento;
    String cidadeNascimento;
    String estadoNascimento;
    struct Jogador* proximo;
    struct Jogador* anterior;
} Jogador;
/*
typedef struct ListaJogadores {
    Jogador* primeiro;
    Jogador* ultimo;
} ListaJogadores;
*/

#define bool   short
#define true   1
#define false  0

//TIPO CELULA ===================================================================
typedef struct CelulaDupla {
	Jogador elemento;        // Elemento inserido na celula.
	struct CelulaDupla* prox; // Aponta a celula prox.
   struct CelulaDupla* ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Jogador elemento) {
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = elemento;
   nova->ant = nova->prox = NULL;
   return nova;
}

//LISTA PROPRIAMENTE DITA =======================================================
CelulaDupla* primeiro;
CelulaDupla* ultimo;


/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start() {
   primeiro = novaCelulaDupla((Jogador){0, NULL, -1, -1, NULL, -1, NULL, NULL, NULL});
   ultimo = primeiro;
}


void inserirFim(Jogador x) {
   CelulaDupla* novaCelula = novaCelulaDupla(x);

   if (primeiro == ultimo) {
      // A lista est� vazia, ent�o inicializa primeiro e ultimo
      primeiro->prox = novaCelula;
      ultimo = novaCelula;
      novaCelula->ant = primeiro;
   } else {
      novaCelula->ant = ultimo;
      novaCelula->prox = NULL;
      ultimo->prox = novaCelula;
      ultimo = novaCelula;
   }
   //printf("ultimo: %d\n", ultimo->elemento.id);
}



/**
 * Remove um elemento da primeira posicao da lista.
 * @return resp int elemento a ser removido.
 */
Jogador removerInicio() {
   if (primeiro == ultimo) {
      printf("Erro ao remover (vazia)!");
   }

   CelulaDupla* tmp = primeiro;
   primeiro = primeiro->prox;
   Jogador resp = primeiro->elemento;
   tmp->prox = primeiro->ant = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
}


/**
 * Remove um elemento da ultima posicao da lista.
 * @return resp int elemento a ser removido.
 */
Jogador removerFim() {
   if (primeiro == ultimo) {
      printf("Erro ao remover (vazia)!");
   }
   Jogador resp = ultimo->elemento;
   ultimo = ultimo->ant;
   ultimo->prox->ant = NULL;
   free(ultimo->prox);
   ultimo->prox = NULL;
   return resp;
}


/**
 *  Calcula e retorna o tamanho, em numero de elementos, da lista.
 *  @return resp int tamanho
 */
int tamanho() {
   int tamanho = 0;
   CelulaDupla* i;
   for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
   return tamanho;
}




/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrar() {
   CelulaDupla* i;

   for (i = primeiro->prox; i != NULL; i = i->prox) {
      //printf("%d ", i->elemento.id);
        printf("[%d ## %s ## ", i->elemento.id, i->elemento.nome);

        if (i->elemento.altura != -1) {
            printf("%d", i->elemento.altura);
        } else {
            printf("nao informado");
        }

        printf(" ## ");

        if (i->elemento.peso != -1) {
            printf("%d", i->elemento.peso);
        } else {
            printf("nao informado");
        }

        printf(" ## ");

        if (i->elemento.anoNascimento != -1) {
            printf("%d", i->elemento.anoNascimento);
        } else {
            printf("nao informado");
        }

        printf(" ## %s ## %s ## %s]\n", i->elemento.universidade, i->elemento.cidadeNascimento, i->elemento.estadoNascimento);

   }



}
/*
CelulaDupla *Pivo(CelulaDupla *esq, CelulaDupla *dir);
void QuickSort(CelulaDupla *esq, CelulaDupla *dir);

CelulaDupla *Pivo(CelulaDupla *esq, CelulaDupla *dir) {
    CelulaDupla *slow = esq, *fast = esq->prox;

    while (fast != dir && fast->prox != dir) {
        slow = slow->prox;
        fast = fast->prox->prox;
    }

    Jogador pivo = slow->elemento;
    CelulaDupla *i = esq;

    for (CelulaDupla *j = esq; j != dir; j = j->prox) {
        int compareResult = strcmp(j->elemento.estadoNascimento, pivo.estadoNascimento);

        if (compareResult < 0 || (compareResult == 0 && strcmp(j->elemento.nome, pivo.nome) < 0)) {
            Jogador tmp = i->elemento;
            i->elemento = j->elemento;
            j->elemento = tmp;
            i = i->prox;
        }
    }

    Jogador tmp = i->elemento;
    i->elemento = dir->elemento;
    dir->elemento = tmp;

    return i;
}

void QuickSort(CelulaDupla *esq, CelulaDupla *dir) {
    if (esq != NULL && dir != NULL && esq != dir && esq->ant != dir && esq != dir->prox) {
        printf("Chamada recursiva: esq=%d, dir=%d\n", esq->elemento.id, dir->elemento.id);

        CelulaDupla *pivo = Pivo(esq, dir);

        printf("Pivo: %d\n", pivo->elemento.id);

        QuickSort(esq, pivo);
        QuickSort(pivo->prox, dir);
    }
}
*/
void swap(CelulaDupla *i, CelulaDupla *j) {
    Jogador tmp = i->elemento;
    i->elemento = j->elemento;
    j->elemento = tmp;
}

void quicksortRec(CelulaDupla *primeiro, CelulaDupla *ultimo ) {
        //printf ("entrou");
        CelulaDupla *i = primeiro;
        CelulaDupla *j = ultimo;
        CelulaDupla *pivo = ultimo;
        while (i->ant != j && j->prox != i->ant) {
            while(strcmp(i->elemento.estadoNascimento, pivo->elemento.estadoNascimento) < 0 || (strcmp(i->elemento.estadoNascimento, pivo->elemento.estadoNascimento) == 0 && strcmp(i->elemento.nome, pivo->elemento.nome) < 0)){
                    i = i->prox;
            }
            while(strcmp(j->elemento.estadoNascimento, pivo->elemento.estadoNascimento) > 0 || (strcmp(j->elemento.estadoNascimento, pivo->elemento.estadoNascimento) == 0 && strcmp(j->elemento.nome, pivo->elemento.nome) > 0)){
                    j = j->ant;
            }
            if (i->ant != j && j->prox != i->ant ) {
                swap (i,j);
                i = i->prox;
                j = j->ant;
            }
        }
	if (primeiro != j && primeiro->ant != j)  quicksortRec(primeiro, j);
	if (i != ultimo && i!= ultimo->prox)  quicksortRec(i, ultimo);
    }

void quicksort(CelulaDupla *primeiro, CelulaDupla *ultimo) {
    quicksortRec(primeiro->prox, ultimo);
}



int stringToInt(String str) {
    int res = 0;
    int linha = 1;
    for (int i = strlen(str) - 1; i >= 0; i--, linha *= 10) {
        res += ((int)(str[i]) - 48) * linha;
    }
    return res;
}


int comp = 0;


Jogador ler(int nid){
  //  FILE *arq = fopen( "players.csv", "r");
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





void pesquisa() {
    /*ListaJogadores lista;
    inicializarLista(&lista);
    */
   start();

    String pubin = (String)malloc(sizeof(char) * 5);
    scanf("%s", pubin);
    getchar();

    int i = 0;
    comp++;
    while (pubin[0] != 'F') {
        Jogador jogador = ler(stringToInt(pubin));
        inserirFim(jogador);
        i++;
        //printf("%d %s\n", jogador.id, jogador.nome);
        scanf(" %s", pubin);
        getchar();
        comp++;
    }
    /*
    printf("no cabeca: %d\n", primeiro->elemento.id);
    printf("primeiro prox(primeiro): %d\n", primeiro->prox->elemento.id);
    printf("ultimo: %d\n", ultimo->elemento.id);
    printf("ultimo ant: %d\n", ultimo->ant->elemento.id);
    
    mostrar();

    printf("no cabeca: %d\n", primeiro->elemento.id);
    printf("primeiro prox(primeiro): %d\n", primeiro->prox->elemento.id);
    printf("ultimo: %d\n", ultimo->elemento.id);
    printf("ultimo ant: %d\n", ultimo->ant->elemento.id);
    */
    //CelulaDupla *q = primeiro->prox, *r = ultimo;
    //swap (q,r);
    quicksort(primeiro, ultimo);
    mostrar();

    //QuickSortListaDupla(&lista);


}


int main() {
    clock_t tempoInicial = clock();

    pesquisa();

    FILE* escrever;
    escrever = fopen("00817611_quicksort2.txt", "w");
    if (escrever == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }
    fprintf(escrever, "00817611 \t %ld \t %d\n", (clock() - tempoInicial), comp);
    fclose(escrever);

    return 0;
}
