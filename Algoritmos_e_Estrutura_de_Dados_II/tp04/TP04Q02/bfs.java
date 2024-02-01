import java.util.Queue;
import java.util.Scanner;

class No{
    int valor;
    No esq;
    No dir;

    public No(int valor){
        this.valor = valor;
        this.esq = null;
        this.dir = null;
    }
}

public class bfs {

    public void BFS(int qnt){
        int [] vet = new int[qnt];
        int i = 0;
        Queue<No> fila = new LinkedList<No>();
        fila.add(this.raiz);

        while (!fila.isEmpty()){
            No aux = fila.remove();
            vet[i] = aux.valor;
            i++;
            if (aux.esq != null){
                fila.add(aux.esq);
            }
            if (aux.dir != null){
                fila.add(aux.dir);
            }
        }
        for (int j=0; j<qnt; j++){
            System.out.print(vet[j] + " ");
        }
        System.out.println();
        fila.add(raiz);
    }

    public static main (String[] args){
        Scanner sc = new Scanner(System.in);

        int cases = sc.nextInt();
        int numnodes = sc.nextInt();

        for (int i=0; i< cases; i++){
            
        }

    }
    
}
