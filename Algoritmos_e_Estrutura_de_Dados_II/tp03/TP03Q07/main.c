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

typedef struct FilaCircular {
    Jogador* frente;
    Jogador* tras;
    int tamanho;
} FilaCircular;

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
    //FILE* arq = fopen("players.csv", "r");
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

void inicializarFilaCircular(FilaCircular* fila) {
    fila->frente = fila->tras = NULL;
    fila->tamanho = 0;
}

int filaCircularVazia(FilaCircular* fila) {
    return (fila->frente == NULL);
}

Jogador* desenfileirar(FilaCircular* fila) {
    if (filaCircularVazia(fila)) {
        printf("Erro ao remover!");
        exit(1);
    }

    Jogador* removido = fila->frente;
    fila->frente = fila->frente->proximo;

    fila->tamanho--;
    //printf("apos remocao, tam: %d\n", fila->tamanho);

    return removido;
}

void enfileirar(FilaCircular* fila, Jogador jogador) {
    Jogador* novo = (Jogador*)malloc(sizeof(Jogador));
    novo->id = jogador.id;
    novo->nome = strdup(jogador.nome);
    novo->altura = jogador.altura;
    novo->peso = jogador.peso;
    novo->universidade = strdup(jogador.universidade);
    novo->anoNascimento = jogador.anoNascimento;
    novo->cidadeNascimento = strdup(jogador.cidadeNascimento);
    novo->estadoNascimento = strdup(jogador.estadoNascimento);
    novo->proximo = NULL;

    if (fila->tamanho < 5) {
        // Se a fila não atingiu o limite, apenas enfileire o novo jogador
        if (fila->frente == NULL) {
            fila->frente = fila->tras = novo;
        } else {
            fila->tras->proximo = novo;
            fila->tras = novo;
        }
        fila->tamanho++;
    } else {
        // Se a fila atingiu o limite, desenfileire o jogador mais antigo
        Jogador* removido = desenfileirar(fila);
        
        // Enfileire o novo jogador após a desenfileiração
        fila->tras->proximo = novo;
        fila->tras = novo;

        fila->tamanho++; // Incrementa o tamanho após a inserção
    }

    //printf("apos insercao, tam: %d\n", fila->tamanho);
}



void printMedia(FilaCircular* fila) {
    float mediaAlt = 0;
    int count = 0;

    Jogador* temp = fila->frente;

    while (temp != NULL) {
        mediaAlt += temp->altura;
        count++;
        temp = temp->proximo;
    }

    if (count > 0) {
        mediaAlt /= count;

        float mediaArredondada = mediaAlt;
        if ((mediaArredondada - floor(mediaArredondada)) >= 0.5) {
            mediaArredondada = ceil(mediaArredondada);
        } else {
            mediaArredondada = floor(mediaArredondada);
        }

        printf("%.0f\n", mediaArredondada);
    }
}


/*
void printMedia(FilaCircular* fila) {
    float mediaAlt = 0;
    int count = 0;
    Jogador* temp = fila->frente;

    while (temp != NULL) {
        mediaAlt += temp->altura;
        count++;
        temp = temp->proximo;
        desenfileirar(&temp);
    }

    if (count > 0) {
        mediaAlt /= count;
        mediaAlt += 0.5; // + 0.5 para arredondar direito

        printf("%d\n", (int)mediaAlt);
    }
}*/


void ListadeJogadores() {
    FilaCircular fila;
    inicializarFilaCircular(&fila);

    FILE* arq = fopen("/tmp/players.csv", "r");
    //FILE* arq = fopen("players.csv", "r");
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
        enfileirar(&fila, jogador);
        i++;

        printMedia(&fila);

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
            enfileirar(&fila, jogador);
            printMedia(&fila);
        } else if (strcmp(a, "R") == 0) {
            Jogador* removido = desenfileirar(&fila);
            printf("(R) %s\n", removido->nome);
            free(removido);
        }
    }

    fclose(arq);

    int a = 0;
    while (!filaCircularVazia(&fila)) {
        Jogador* jogador = desenfileirar(&fila);
        printf("[%d] ## %s ## ", a, jogador->nome);

        if (jogador->altura != -1) {
            printf("%d", jogador->altura);
        } else {
            printf("nao informado");
        }
        comp++;

        printf(" ## ");

        if (jogador->peso != -1) {
            printf("%d", jogador->peso);
        } else {
            printf("nao informado");
        }
        comp++;

        printf(" ## ");

        if (jogador->anoNascimento != -1) {
            printf("%d", jogador->anoNascimento);
        } else {
            printf("nao informado");
        }
        comp++;

        printf(" ## %s ## %s ## %s ##\n", jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);

        free(jogador->nome);
        free(jogador->universidade);
        free(jogador->cidadeNascimento);
        free(jogador->estadoNascimento);
        free(jogador);
        a++;
    }

}

int main() {
    ListadeJogadores();

    return 0;
}
