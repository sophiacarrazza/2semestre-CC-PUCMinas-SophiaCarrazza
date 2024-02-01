#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

typedef char* String;
//declarando comp
int comp = 0;
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

int stringToInt(String str){
    int res = 0;
    int linha = 1;
    for (int i = strlen(str) - 1; i >= 0; i--, linha *= 10) {
        res += ((int)(str[i]) - 48) * linha;
    }
    return res;
}
// criando um novo no Jogador
Jogador* novoJogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
    Jogador* jogador = (Jogador*)malloc(sizeof(Jogador));
    jogador->id = id;
    jogador->nome = strdup(nome);
    jogador->altura = altura;
    jogador->peso = peso;
    jogador->universidade = strdup(universidade);
    jogador->anoNascimento = anoNascimento;
    jogador->cidadeNascimento = strdup(cidadeNascimento);
    jogador->estadoNascimento = strdup(estadoNascimento);

    return jogador;
}

typedef struct No{
    char* elemento;     
    struct No *dir, *esq; 
    int nivel; // altura do no da arvore

} No;

typedef struct AVL { //comeca com o no da raiz e continua com o dir e esq do no
    No *raiz;
} AVL;

AVL* newAVL(){
    AVL* arvore = (AVL*)malloc(sizeof(AVL));
    arvore->raiz=NULL;
    return arvore;
}
No *novoNo(char* elemento){
    No *nova = (No *)malloc(sizeof(No));
    nova->elemento = elemento;
    nova->esq = nova->dir = NULL;
    nova->nivel = 1; // novo nó é inicialmente adicionado nas folhas
    return nova;
}

int getNivel(No *no) { //pega o nivel do no
    if (no == NULL){
        return 0;
    }
    else{
        return no->nivel;
    }

}

int max(int a, int b) {
    if (a > b)
        return (a);
    else
        return (b);
}

void setNivel(No* no){  //aumenta o nivel em 1
    no->nivel = max(getNivel(no->esq), getNivel(no->dir)) + 1;
}

int getBalanceamento(No *no){
    if (no == NULL)
        return 0;
    else 
        return getNivel(no->dir) - getNivel(no->esq);
}

No *rotacaoDireita(No *no){
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;
    noEsq->dir = no;
    no->esq = noEsqDir;

    setNivel(no);
    setNivel(noEsq);

    return noEsq;
}
No *rotacaoEsquerda(No *no){
    No *noDir = no->dir;
    No *noDirEsq = noDir->esq;
    noDir->esq = no;
    no->dir = noDirEsq;

    setNivel(no);
    setNivel(noDir);

    return noDir;
}

No * balancear(No* no){

    if(no!=NULL)
    {
        int fator = getNivel(no->dir) - getNivel(no->esq);
         if(abs(fator)<=1)
         {
            setNivel(no);
         }
         
         else if (fator == 2)
         {
             int nivelFilho = getNivel(no->dir->dir)- getNivel(no->dir->esq);

             if(nivelFilho == -1)
             {
                no->dir = rotacaoDireita(no->dir);
             }
             no=rotacaoEsquerda(no);
         }
        else if(fator == -2)
        {
            int nivelFilho = getNivel(no->esq->dir)- getNivel(no->esq->esq);

            if(nivelFilho == 1)
            {
                no->esq = rotacaoEsquerda(no->esq);
            }
           
            no=rotacaoDireita(no);
        }
        else{
            printf("Erro, fator = %d", fator);
        } 
    }
    return no;
}

No *inserir(char* string, No *i){

    if (i == NULL)
    {
        i = novoNo(string);
    }
    else if ((strcmp(string, (i)->elemento) < 0))
    {
        i->esq = inserir(string, i->esq);
    }
    else if ((strcmp(string, i->elemento) > 0))
    {
        i->dir = inserir(string, i->dir);
    }
    else
    {
        printf("Erro ao inserir!");
    }

    
    return  balancear(i);
}

bool pesquisar(char nome[], No *i){
    bool resp = false;

    if (i == NULL)
    {
        resp = false;
    }
    else if (strcmp(nome, i->elemento) < 0)
    {
        printf(" esq");
        resp = pesquisar(nome, i->esq);
    }
    else if ((strcmp(nome, i->elemento) > 0))
    {
        printf(" dir");
        resp = pesquisar(nome, i->dir);
    }
     else 
    {
        resp = true;
    }

    return resp;
}


Jogador ler(int nid){
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
    AVL* arvore = newAVL();

    FILE* arq = fopen("/tmp/players.csv", "r");
    //FILE* arq = fopen("players.csv", "r");
    if (arq == NULL) {
        printf("Abertura do arquivo nao sucedida\n");
        exit(1);
    }

    String pubin = (String)malloc(sizeof(char) * 5);
    scanf("%s", pubin);
    getchar();

    while (pubin[0] != 'F') {
        Jogador jogador = ler(stringToInt(pubin));
        //avl.inserir(jogador);
        arvore->raiz = inserir(jogador.nome, arvore->raiz);
        scanf(" %s", pubin);
        getchar();
        comp++;
    }

    String pesquisaj = (String)malloc(sizeof(char) * 30);
    scanf(" %[^\n]", pesquisaj);
    getchar();
    while (strncmp(pesquisaj, "FIM", 3) != 0) {

        printf("%s raiz", pesquisaj);
        if (pesquisar(pesquisaj, arvore->raiz)) {
            printf(" SIM\n");
        } else {
            printf(" NAO\n");
        }

        scanf(" %[^\n]", pesquisaj);
        getchar();
        comp++;
    }


    fclose(arq);

}

int main() {
    
    clock_t tempoInicial = clock();

    ListadeJogadores();

    FILE *escrever;
    escrever = fopen("00817611_avl.txt", "w");
    if (escrever == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }
    fprintf(escrever, "00817611 \t %ld \t %d\n", (clock() - tempoInicial), comp);
    fclose(escrever);
    return 0;
}
