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
        FILE *arq = fopen(".\\tmp\\players.csv", "r");
      // FILE *arq = fopen("/tmp/players.csv", "r");
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
    #define MAXTAM    300
    #define bool      short
    #define true      1
    #define false     0

    Jogador** array;   // Elementos da pilha
    int n;               // Quantidade de array.

    void start(){
       n = 0;
    }


    //Insere um elemento na primeira posicao da lista e move os demais elementos para o fim
    void inserirInicio(Jogador*** array, int *n, Jogador x) {
    //validar insercao
    if (*n >= MAXTAM) {
        printf("Erro ao inserir!");
        exit(1);
    }

    // aloca espaço para o novo jogador
    Jogador* novo = (Jogador*)malloc(sizeof(Jogador));

    // levar elementos para o fim do array
    for (int i = *n; i > 0; i--) {
        (*array)[i] = (*array)[i - 1];
    }

    // copia os dados do jogador x para o novo jogador
    *novo = x;
    (*array)[0] = novo;
    (*n)++;
}


    //Insere um elemento na ultima posicao
    void inserirFim(Jogador*** array, int *n, Jogador x) {

       //validar insercao
       if(*n >= MAXTAM){
          printf("Erro ao inserir!");
          exit(1);
       }
        Jogador* novo = (Jogador*)malloc(sizeof(Jogador));

       *novo = x;
       (*array)[*n] = novo;
       (*n)++;
    }


    //Insere um elemento em uma posicao especifica e move os demais elementos para o fim
    void inserir(Jogador** array, int *n, Jogador x, int pos) {

       //validar insercao
       if(*n >= MAXTAM || pos < 0 || pos > *n){
          printf("Erro ao inserir!");
          exit(1);
       }

          // aloca espaço para o novo jogador
    Jogador* novo = (Jogador*)malloc(sizeof(Jogador));

    // levar elementos para o fim do array
    for (int i = *n; i > pos; i--) {
        (*array)[i] = (*array)[i - 1];
    }

    // copia os dados do jogador x para o novo jogador
    *novo = x;
    (*array)[pos] = novo;
    (*n)++;
    }


    //Remove um elemento da primeira posicao da lista e movimenta os demais elementos para o inicio da mesma.
    Jogador removerInicio(Jogador*** array, int* n) {
        Jogador removido;

        // validar remocao
        if (*n == 0) {
            printf("Erro ao remover!");
            exit(1);
        }

        removido = *((*array)[0]);
        free((*array)[0]);
        (*n)--;

        for (int i = 0; i < *n; i++) {
            (*array)[i] = (*array)[i + 1];
        }

        return removido;
    }
    // Remove um elemento da ultima posicao e da return resp int elemento a ser removido.
    Jogador removerFim(Jogador*** array, int* n) {
        // validar remocao
        if (*n == 0) {
            printf("Erro ao remover!");
            exit(1);
        }

        Jogador removido = *((*array)[(*n) - 1]);
        free((*array)[(*n) - 1]);
        (*n)--;

        return removido;
    }


    //Remove um elemento de uma posicao especifica da lista e movimenta os demais elementos para o inicio da mesma.
    Jogador remover(Jogador** array, int* n, int pos) {
        Jogador removido;

        // validar remocao
        if (*n == 0 || pos < 0 || pos >= *n) {
            printf("Erro ao remover!");
            exit(1);
        }

        removido = *((*array)[pos]);
        free((*array)[pos]);
        (*n)--;

        for (int i = pos; i < *n; i++) {
            (*array)[i] = (*array)[i + 1];
        }

        return removido;
    }

    void swap (Jogador *x, Jogador *y){
            Jogador temp = *x;
            *x = *y;
            *y = temp;

    }

    void ListadeJogadores(Jogador** array, int *n) {
       // FILE *arq = fopen("/tmp/players.csv", "r");
       FILE *arq = fopen(".\\tmp\\players.csv", "r");

        *array = (Jogador*)malloc(MAXTAM * sizeof(Jogador));
        if (arq == NULL) {
            printf("Abertura do arquivo nao sucedida\n");
            exit(1);
        }
        //Jogador array [500];
        int num_ins_rem, i=0;

        String pubin = (String)malloc(sizeof(char)*5);
        scanf("%s", pubin);
        getchar();

         while(pubin[0] != 'F'){
            (*array)[i] = ler(stringToInt(pubin));
            i++;
            scanf(" %s", pubin);
            getchar();
            comp++;
            *n++;
        }

        scanf("%d", &num_ins_rem);

        for (int i = 0; i < num_ins_rem; i++) {
            char a[5];
            int b, pos;
            scanf(" %s", a);

            if (strcmp(a, "II") == 0) {
                scanf("%d", &b);
                getchar();
                Jogador jogador = ler(b);
                inserirInicio(array, n, jogador);

            } else if (strcmp(a, "IF") == 0) {
                scanf("%d", &b);
                getchar();
                Jogador jogador = ler(b);
                inserirFim(array, n, jogador);

            } else if (strcmp(a, "RI") == 0) {
                    Jogador removido = removerInicio(array, n);
                    printf("(R) %s\n", removido.nome);
                    free(removido.nome);
                    free(removido.universidade);
                    free(removido.cidadeNascimento);
                    free(removido.estadoNascimento);

            } else if (strcmp(a, "I*") == 0) {
                    scanf("%d %d",&pos, &b);
                    getchar();
                    Jogador jogador = ler(b);
                    inserir (array, n, jogador, pos);

            } else if (strcmp(a, "R*") == 0) {
                    scanf("%d",&pos);
                    getchar();
                    Jogador removido = remover(array, n, pos);
                    printf("(R) %s\n", removido.nome);
                    free(removido.nome);
                    free(removido.universidade);
                    free(removido.cidadeNascimento);
                    free(removido.estadoNascimento);

            }
        }

        fclose(arq);

        for (int i = 0; i < *n; i++) {
            Jogador jogador = (*array)[i];
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
    }


    int main()
    {
        Jogador** array;
        start();
        ListadeJogadores(&array, &n);
        free(*array);  // Liberar a memória alocada para o array.
        free(array);
        return 0;
    }
