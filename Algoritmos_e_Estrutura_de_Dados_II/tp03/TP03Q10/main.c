#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>

typedef char* String;

int comp = 0;

int stringToInt(String str) {
    int res = 0;
    int linha = 1;
    for (int i = strlen(str) - 1; i >= 0; i--, linha *= 10) {
        res += ((int)(str[i]) - 48) * linha;
    }
    return res;
}

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
} Jogador;

typedef struct PilhaFlexivel {
    Jogador* topo;
    int tamanho;
} PilhaFlexivel;

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

Jogador ler(int nid) {
    FILE* arq = fopen("/tmp/players.csv", "r");
    //FILE *arq = fopen("players.csv", "r");

    if (arq == NULL) {
        printf("Abertura do arquivo nao sucedida\n");
        exit(1);
    }
    comp++;

    String linha = (String)calloc(700, sizeof(char));
    Jogador player;
    int nlinha = 0;
    int j = 0;

    comp++;
    while (nlinha != nid + 2) {
        fscanf(arq, "%[^\n]", linha);
        fgetc(arq);
        nlinha++;
        comp++;
    }

    fclose(arq);

    String tmp = (String)calloc(400, sizeof(char));
    int caracter = 0;
    comp++;
    while (linha[caracter] != ',') {
        tmp[j] = linha[caracter];
        j++;
        caracter++;
        comp++;
    }
    player.id = stringToInt(tmp);

    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    if (linha[caracter] == ',') {
        strcpy(tmp, "nao informado");
    }
    j = 0;
    comp++;
    while (linha[caracter] != ',') {
        tmp[j] = linha[caracter];
        j++;
        caracter++;
        comp++;
    }
    player.nome = strdup(tmp);

    player.altura = -1;
    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    j = 0;
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
    j = 0;
    comp++;
    while (linha[caracter] != ',') {
        tmp[j] = linha[caracter];
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
    if (linha[caracter] == ',') {
        strcpy(tmp, "nao informado");
    }
    comp++;

    j = 0;
    comp++;
    while (linha[caracter] != ',') {
        tmp[j] = linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    player.universidade = strdup(tmp);

    player.anoNascimento = -1;
    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    j = 0;
    comp++;
    while (linha[caracter] != ',') {
        tmp[j] = linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    if (strlen(tmp) > 0) {
        player.anoNascimento = stringToInt(tmp);
    }
    comp++;

    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    if (linha[caracter] == ',') {
        strcpy(tmp, "nao informado");
    }
    comp++;

    j = 0;
    comp++;
    while (linha[caracter] != ',') {
        tmp[j] = linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    player.cidadeNascimento = strdup(tmp);

    caracter++;
    free(tmp);
    tmp = (String)calloc(400, sizeof(char));
    if (linha[caracter] == ',' || linha[caracter] == '\n' || linha[caracter] == '\0') {
        strcpy(tmp, "nao informado");
    }
    comp++;
    j = 0;
    comp++;
    while (linha[caracter] != ',' && linha[caracter] != '\n' && linha[caracter] != '\0') {
        tmp[j] = linha[caracter];
        caracter++;
        j++;
        comp++;
    }
    player.estadoNascimento = strdup(tmp);

    return player;
}

void inicializarPilhaFlexivel(PilhaFlexivel* pilha) {
    pilha->topo = NULL;
    pilha->tamanho = 0;
}

int pilhaFlexivelVazia(PilhaFlexivel* pilha) {
    return (pilha->topo == NULL);
}

void empilhar(PilhaFlexivel* pilha, Jogador jogador) {
    Jogador* novo = (Jogador*)malloc(sizeof(Jogador));
    novo->id = jogador.id;
    novo->nome = strdup(jogador.nome);
    novo->altura = jogador.altura;
    novo->peso = jogador.peso;
    novo->universidade = strdup(jogador.universidade);
    novo->anoNascimento = jogador.anoNascimento;
    novo->cidadeNascimento = strdup(jogador.cidadeNascimento);
    novo->estadoNascimento = strdup(jogador.estadoNascimento);
    novo->proximo = pilha->topo;

    pilha->topo = novo;
    pilha->tamanho++;
}

Jogador* desempilhar(PilhaFlexivel* pilha) {
    if (pilhaFlexivelVazia(pilha)) {
        printf("Erro ao remover!");
        exit(1);
    }

    Jogador* removido = pilha->topo;
    pilha->topo = pilha->topo->proximo;

    pilha->tamanho--;

    return removido;
}

void imprimirPilha(Jogador* topo, int a){

    if (topo == NULL) {
        return;
    }

    imprimirPilha(topo->proximo, a-1);

    printf("[%d] ## %s ## ", a, topo->nome);

    if (topo->altura != -1) {
        printf("%d", topo->altura);
    } else {
        printf("nao informado");
    }

    printf(" ## ");

    if (topo->peso != -1) {
        printf("%d", topo->peso);
    } else {
        printf("nao informado");
    }

    printf(" ## ");

    if (topo->anoNascimento != -1) {
        printf("%d", topo->anoNascimento);
    } else {
        printf("nao informado");
    }

    printf(" ## %s ## %s ## %s ##\n", topo->universidade, topo->cidadeNascimento, topo->estadoNascimento);

}

void ListadeJogadores() {
    PilhaFlexivel pilha;
    inicializarPilhaFlexivel(&pilha);

    FILE* arq = fopen("/tmp/players.csv", "r");
    //FILE *arq = fopen("players.csv", "r");
    if (arq == NULL) {
        printf("Abertura do arquivo nao sucedida\n");
        exit(1);
    }

    int num_ins_rem, i = 0;
    String pubin = (String)malloc(sizeof(char) * 5);

    scanf("%s", pubin);
    getchar();

    while (pubin[0] != 'F') {
        Jogador jogador = ler(stringToInt(pubin));
        empilhar(&pilha, jogador);
        i++;
        scanf(" %s", pubin);
        getchar();
        comp++;
    }

    scanf("%d", &num_ins_rem);
    for (int i = 0; i < num_ins_rem; i++) {
        char a[5];
        int b;
        scanf(" %s", a);

        if (strcmp(a, "I") == 0) {
            scanf("%d", &b);
            getchar();
            Jogador jogador = ler(b);
            empilhar(&pilha, jogador);
        } else if (strcmp(a, "R") == 0) {
            Jogador* removido = desempilhar(&pilha);
            printf("(R) %s\n", removido->nome);
            free(removido);
        }
    }

    fclose(arq);

    imprimirPilha(pilha.topo, pilha.tamanho-1);
}

int main() {
    ListadeJogadores();

    return 0;
}
