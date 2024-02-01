import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

 class CelulaMatriz {

    public int elemento;
    public CelulaMatriz sup, inf, esq, dir;

    public CelulaMatriz() {
        this(0);
    }

    public CelulaMatriz(int elemento) {
        this.elemento = elemento;
        this.inf = this.sup = this.dir = this.esq = null;
    }

    public CelulaMatriz(int elemento, CelulaMatriz inf, CelulaMatriz sup, CelulaMatriz esq, CelulaMatriz dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

class Matriz {
    private CelulaMatriz inicio;
    private int linha, coluna;

    // Construtores
    public Matriz() {
        this(0, 0);
    }

    public Matriz(int l, int c) {
        this.linha = l;
        this.coluna = c;

        inicializarCelulas();
    }

    public void inicializarCelulas() {

        inicio = new CelulaMatriz();
        CelulaMatriz tmp = inicio;

        for (int i = 1; i < coluna; i++) {
            tmp.dir = new CelulaMatriz();
            tmp.dir.esq = tmp;
            tmp = tmp.dir;
        }
        tmp = inicio;
        for (int l = 1; l < linha; l++, tmp = tmp.inf) {

            CelulaMatriz i = tmp;

            i.inf = new CelulaMatriz();

            i.inf.sup = i;

            CelulaMatriz j = i.inf;

            for (int k = 1; k < coluna; k++) {
                i = i.dir;
                i.inf = new CelulaMatriz();
                i.inf.sup = i;
                i.inf.esq = j;
                j.dir = i.inf;
                j = j.dir;
            }

        }

    }

    public void setElementoMatriz(int linha, int coluna, int elemento) {
        CelulaMatriz atual = inicio;

        for (int i = 0; i < coluna; i++) {
            atual = atual.dir;
        }
        for (int i = 0; i < linha; i++) {
            atual = atual.inf;
        }

        atual.elemento = elemento;
    }

    public int getElementoMatriz(int linha, int coluna) {
        CelulaMatriz atual = inicio;

        for (int i = 0; i < coluna; i++) {
            atual = atual.dir;
        }
        for (int i = 0; i < linha; i++) {
            atual = atual.inf;
        }

        return atual.elemento;

    }

    public void mostrarDiagonalPrincipal() {
        for (int i = 0; i < this.linha; i++) {
            System.out.print(getElementoMatriz(i, i) + " ");
        }
    }

   public void mostrarDiagonalSecundaria() {
    for (int i = 0; i < this.linha; i++) {
        System.out.print(getElementoMatriz(i, this.coluna - i - 1) + " ");
    }
}

    public void mostrarMatriz() {
        for (int i = 0; i < this.linha; i++) {

            for (int j = 0; j < this.coluna; j++) {

                System.out.print(getElementoMatriz(i, j) + " ");
            }
            System.out.println();
        }
    }

    public Matriz soma(Matriz matriz) {
        Matriz somados = null;

        if (this.linha == matriz.linha && this.coluna == matriz.coluna) {
            somados = new Matriz(this.linha, this.coluna);

            for (int i = 0; i < this.linha; i++) {
                for (int j = 0; j < this.coluna; j++) {

                    int elemento = this.getElementoMatriz(i, j) + matriz.getElementoMatriz(i, j);
                    somados.setElementoMatriz(i, j, elemento);

                }
            }
        } 
        else {
            System.out.println("Matrizes Desiguais");
        }

        return somados;
    }

    public Matriz multiplicacao(Matriz matriz) {
        Matriz multiplicados = null;

        if (this.coluna == matriz.linha) {
            multiplicados = new Matriz(this.linha, matriz.coluna);

            for (int i = 0; i < this.linha; i++) {
                for (int j = 0; j < matriz.coluna; j++) {
                    int elemento = 0;
                    for (int k = 0; k < this.coluna; k++) {
                        elemento += this.getElementoMatriz(i, k) * matriz.getElementoMatriz(k, j);
                    }
                    multiplicados.setElementoMatriz(i, j, elemento);
                }
            }
        } else {
            System.out.println("Matrizes Desiguais");
        }

        return multiplicados;
    }

}

public class MatrizFlexivel {

    public static void main(String[] args) throws Exception {
        Scanner leia = new Scanner(System.in);

        int num = leia.nextInt();

        Matriz[] teste = new Matriz[num * 2];

        for (int k = 0; k < num * 2; k++) {
            // ler matriz
            int lin = leia.nextInt();
            int col = leia.nextInt();
            Matriz matriz = new Matriz(lin, col);
            leia.nextLine();
            for (int i = 0; i < lin; i++) {
                String entrada = leia.nextLine();
                String[] valores = entrada.split(" ");
                for (int j = 0; j < col; j++) {
                    matriz.setElementoMatriz(i, j, Integer.parseInt(valores[j]));
                }
            }
            teste[k] = matriz;
            matriz.mostrarMatriz();
        }

        for (int p = 0; p < num * 2 -1; p += 2) {
            teste[p].mostrarDiagonalPrincipal();
            System.out.println();
            teste[p].mostrarDiagonalSecundaria();

            Matriz somada = teste[p].soma(teste[p + 1]);
            if (somada != null) {
                System.out.println();
                somada.mostrarMatriz();
            } else {
                System.out.println("Não foi possível somar as matrizes");
            }

            Matriz result = teste[p].multiplicacao(teste[p + 1]);
            if (result != null) {
                result.mostrarMatriz();
            } else {
                System.out.println("Não foi possível multiplicar as matrizes");
            }
        }

        leia.close();
    }
}
