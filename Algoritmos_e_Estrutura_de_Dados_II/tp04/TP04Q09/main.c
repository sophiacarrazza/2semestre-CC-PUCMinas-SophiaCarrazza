#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

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
} Jogador;

typedef struct ListaJogadores {
    Jogador* inicio;
    Jogador* fim;
} ListaJogadores;

typedef struct TabelaHash {
    ListaJogadores** tabela;
    int tamanho;
} TabelaHash;

int comp = 0;

int stringToInt(String str) {
    int res = 0;
    int linha = 1;
    for (int i = strlen(str) - 1; i >= 0; i--, linha *= 10) {
        res += ((int)(str[i]) - 48) * linha;
    }
    return res;
}

// fun��o para inicializar a tabela hash
void inicializarTabelaHash(TabelaHash* tabela, int tamanho) {
    tabela->tamanho = tamanho;
    tabela->tabela = (ListaJogadores**)malloc(tamanho * sizeof(ListaJogadores*));

    for (int i = 0; i < tamanho; i++) {
        tabela->tabela[i] = (ListaJogadores*)malloc(sizeof(ListaJogadores));
        tabela->tabela[i]->inicio = tabela->tabela[i]->fim = NULL;
    }
}

// Fun��o para enfileirar um jogador na tabela hash
void enfileirar(TabelaHash* tabela, Jogador jogador) {
    int indice = jogador.altura % tabela->tamanho;

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

    if (tabela->tabela[indice]->fim == NULL) {
        tabela->tabela[indice]->inicio = tabela->tabela[indice]->fim = novo;
    } else {
        tabela->tabela[indice]->fim->proximo = novo;
        tabela->tabela[indice]->fim = novo;
    }
}

// Fun��o para desenfileirar um jogador da tabela hash
Jogador* desenfileirar(TabelaHash* tabela, int id) {
    int indice = id % tabela->tamanho;

    if (tabela->tabela[indice]->inicio == NULL) {
        printf("Erro ao remover!\n");
        exit(1);
    }

    Jogador* removido = tabela->tabela[indice]->inicio;
    tabela->tabela[indice]->inicio = tabela->tabela[indice]->inicio->proximo;

    return removido;
}

bool pesquisarJogador(TabelaHash* tabela, String nome) {
    bool resp = false;
    //printf("pesquisando o nome: %s", nome);
    for (int i = 0; i < tabela->tamanho; i++) {
        Jogador* jogador = tabela->tabela[i]->inicio;
        while (jogador != NULL) {
            if (strncmp(jogador->nome, nome, strlen(nome)) == 0) {
                //printf("achamos o jogador %s", jogador->nome);
                return resp = true;
            }
            jogador = jogador->proximo;
        }

    }
     //printf("nao achamos o jogador");
    return resp;

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

// fun��o principal para listar jogadores
void ListadeJogadores() {
    TabelaHash tabela;
    inicializarTabelaHash(&tabela, 25); // tamanho da tabela

    FILE* arq = fopen("/tmp/players.csv", "r");
    //FILE* arq = fopen("players.csv", "r");
    if (arq == NULL) {
        printf("Abertura do arquivo nao sucedida\n");
        exit(1);
    }
    //char charProblema[2] = {13};

    String pubin = (String)malloc(sizeof(char) * 5);
    scanf("%s", pubin);
    pubin[(int)strcspn(pubin, "\n\r")] = '\0';
    getchar();
    //pubin[strcspn(pubin, charProblema)] = '\0';
    
    while (pubin[0] != 'F') {
        Jogador jogador = ler(stringToInt(pubin));
        enfileirar(&tabela, jogador);
        scanf(" %s", pubin);
        pubin[(int)strcspn(pubin, "\n\r")] = '\0';
        getchar();
        //pubin[strcspn(pubin, charProblema)] = '\0';
        comp++;
    }

    String pesquisaj = (String)malloc(sizeof(char) * 30);
    scanf(" %[^\n]", pesquisaj);
    pesquisaj[(int)strcspn(pesquisaj, "\n\r")] = '\0';
    getchar();
    while (strncmp(pesquisaj, "FIM", 3) != 0) {
        bool jogadorEncontrado = pesquisarJogador(&tabela, pesquisaj);

        if (jogadorEncontrado == true) {
            printf("%s SIM\n", pesquisaj);
        } else {
            printf("%s NAO\n", pesquisaj);
        }

        scanf(" %[^\n]", pesquisaj);
        pesquisaj[(int)strcspn(pesquisaj, "\n\r")] = '\0';

        getchar();
        comp++;
    }


    fclose(arq);

}

int main() {
    ListadeJogadores();

    return 0;
}
