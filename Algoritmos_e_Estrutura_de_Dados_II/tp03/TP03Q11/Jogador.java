import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


class CelulaDupla {
	public Jogador elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	/**
	 * Construtor da classe.
	 */
	public CelulaDupla() {
		this(new Jogador());
	}


	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public CelulaDupla(Jogador elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}

class ListaDupla {
	public CelulaDupla primeiro;
	public CelulaDupla ultimo;




	public void inicializarLista() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }


	/**
	 * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(Jogador x) {
		CelulaDupla tmp = new CelulaDupla(x);

      tmp.ant = primeiro;
      tmp.prox = primeiro.prox;
      primeiro.prox = tmp;
      if(primeiro == ultimo){
         ultimo = tmp;
      }else{
         tmp.prox.ant = tmp;
      }
      tmp = null;
	}


	/**
	 * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirFim(Jogador x) {
		ultimo.prox = new CelulaDupla(x);
      ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}


	/**
	 * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Jogador removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

      CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		Jogador resp = primeiro.elemento;
      tmp.prox = primeiro.ant = null;
      tmp = null;
		return resp;
	}


	/**
	 * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Jogador removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
      Jogador resp = ultimo.elemento;
      ultimo = ultimo.ant;
      ultimo.prox.ant = null;
      ultimo.prox = null;
		return resp;
	}


	/**
    * Insere um elemento em uma posicao especifica considerando que o 
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
   public void inserir(Jogador x, int pos) throws Exception {

      int tamanho = tamanho();

      if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      } else if (pos == 0){
         inserirInicio(x);
      } else if (pos == tamanho){
         inserirFim(x);
      } else {
		   // Caminhar ate a posicao anterior a insercao
         CelulaDupla i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         CelulaDupla tmp = new CelulaDupla(x);
         tmp.ant = i;
         tmp.prox = i.prox;
         tmp.ant.prox = tmp.prox.ant = tmp;
         tmp = i = null;
      }
   }


	/**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Jogador remover(int pos) throws Exception {
      Jogador resp;
      int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

      } else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
      } else if (pos == 0){
         resp = removerInicio();
      } else if (pos == tamanho - 1){
         resp = removerFim();
      } else {
		   // Caminhar ate a posicao anterior a insercao
         CelulaDupla i = primeiro.prox;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         i.ant.prox = i.prox;
         i.prox.ant = i.ant;
         resp = i.elemento;
         i.prox = i.ant = null;
         i = null;
      }

		return resp;
	}


	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ "); // Comeca a mostrar.
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] "); // Termina de mostrar.
	}


	/**
	 * Mostra os elementos da lista de forma invertida 
    * e separados por espacos.
	 */
	public void mostrarInverso() {
		System.out.print("[ ");
		for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
			System.out.print(i.elemento + " ");
      }
		System.out.println("] "); // Termina de mostrar.
	}


	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(Jogador x) {
		boolean resp = false;
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
         if(i.elemento == x){
            resp = true;
            i = ultimo;
         }
		}
		return resp;
	}

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
   public int tamanho() {
      int tamanho = 0; 
      for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }

   // Método pra obter um elemento da lista duplamente encadeada na posição i
	  public Jogador getElemento(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= tamanho()) {
		   throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
		}
		CelulaDupla k = primeiro.prox;
		for(int a=0; a < i; a++){
		  k = k.prox;
		}
		return k.elemento;
	 }

	 
  public static CelulaDupla pivo(CelulaDupla esq, CelulaDupla dir) {
        Jogador pivo = dir.elemento;
        CelulaDupla i = esq;

        /*
         * Usando a Celula i = primeiro.prox, vou comparar com a Celula j, que percorre todo o array
         * Se o elemento da Celula j for menor que o pivo, eu troco o elemento da Celula i com o da Celula j
         * Desta forma a Celula i vai percorrer o array e terminar com o ultimo numero menor que o pivo
         * depois eu troco o elemento da Celula i com o da Celula dir, que é o pivo
         */

        for (CelulaDupla j = esq; j != dir; j = j.prox) {
            if (j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0){
        	//if ((j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0) || ((j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) > 0) && (j.elemento.getNome().compareTo(pivo.getNome()) < 0))) {

				Jogador tmp = i.elemento;
                i.elemento = j.elemento;
                j.elemento = tmp;
                i = i.prox;
            }
            if (j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) == 0){
        	//if ((j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) == 0)|| ((j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) != 0) && (j.elemento.getNome().compareTo(pivo.getNome()) == 0)) ){
 
				if (j.elemento.getNome().compareTo(pivo.getNome()) < 0) {
                    Jogador tmp = i.elemento;
                    i.elemento = j.elemento;
                    j.elemento = tmp;
                    i = i.prox;
                }
            }
        }

        Jogador tmp = i.elemento;
        i.elemento = dir.elemento;
        dir.elemento = tmp;

        return i;
    }

    public static void doQuick(CelulaDupla esq, CelulaDupla dir) {
        if (esq != null && dir != null && esq != dir && esq.ant != dir) {
            CelulaDupla pivo = pivo(esq, dir);
            doQuick(esq, pivo.ant);
            doQuick(pivo.prox, dir);
        }
    }

    public void quickSort() {
        doQuick(primeiro.prox, ultimo);
    }


}
class Jogador {
		private int id, altura, peso, anoNascimento;
		private String nome, universidade, cidadeNascimento, estadoNascimento;
		private	Jogador player[] = new Jogador[3922]; 
		ListaDupla lista;
		private static int comp=0;
		//Construtor vazio
		public Jogador(){
		lista = new ListaDupla();
		this.id = this.altura = this.peso = this.anoNascimento = -1;
		this.nome = this.universidade = this.cidadeNascimento = this.estadoNascimento = "nao informado";
		}
		
		//Construtor q recebe os parametros
		public Jogador(int id, int altura, int peso, int anoNascimento, String nome, String universidade, String cidadeNascimento, String estadoNascimento){
			this.id = id;
			this.nome = nome;
			this.altura = altura;
			this.peso = peso;
			this.universidade = universidade;
			this.anoNascimento = anoNascimento;
			this.cidadeNascimento = cidadeNascimento;
			this.estadoNascimento = estadoNascimento;
		}
		
		public Jogador clone(){
        return new Jogador(id, altura, peso, anoNascimento, nome, universidade, cidadeNascimento, estadoNascimento);

    }
	
		
		//get -> RETORNA o conteudo do atributo privado 
		//tem o mesmo tipo do seu atributo e nao recebe parametros
		public int getId(){
			return this.id;
		}
		
		//set -> ALTERA o conteudo do atributo privado 
		//retorna void e possui um parametro de entrada cujo tipo é o mesmo do atributo
		public void setId(int id){
			this.id = id;
		}
		
		public int getAltura(){
			return this.altura;
		}
		public void setAltura(int altura){
			this.altura = altura;
		}
		public int getPeso(){
			return this.peso;
		}
		public void setPeso(int peso){
			this.peso = peso;
		}
		public int getAnoNascimento(){
			return this.anoNascimento;
		}
		public void setAnoNascimento(int anoNascimento){
			this.anoNascimento = anoNascimento;
		}
		public String getNome(){
			return this.nome;
		}
		public void setNome(String nome){
			this.nome = nome;
		}
		public String getUniversidade(){
			return this.universidade;
		}
		public void setUniversidade(String universidade){
			this.universidade = universidade;
		}
		public String getCidadeNascimento(){
			return this.cidadeNascimento;
		}
		public void setCidadeNascimento(String cidadeNascimento){
			this.cidadeNascimento = cidadeNascimento;
		}
		public String getEstadoNascimento(){
			return this.estadoNascimento;
		}
		public void setEstadoNascimento(String estadoNascimento){
			this.estadoNascimento = estadoNascimento;
		}
	
		public void Ler(){
		
		try (BufferedReader arq = new BufferedReader(new FileReader("/tmp/players.csv"))){
		//try (BufferedReader arq = new BufferedReader(new FileReader("players.csv"))){
			arq.readLine();
			for (int i = 0; i<3922; i++){
                String str = arq.readLine();
                String [] atributos = str.split(",", -1);
                player [i] = new Jogador();
				
                if (atributos[0]!=""){
					player[i].setId(Integer.valueOf(atributos[0]));
				}
				comp++;
				if (atributos[1]!=""){
					player[i].setNome(atributos[1]);
				}
				comp++;
				if (atributos[2]!=""){
					player[i].setAltura(Integer.valueOf(atributos[2]));
				}
				comp++;
				if (atributos[3]!=""){
					player[i].setPeso(Integer.valueOf(atributos[3]));
				}
				comp++;
				if (atributos[4]!=""){
					player[i].setUniversidade(atributos[4]);
				}
				comp++;
				
				if (atributos[5]!=""){
					player[i].setAnoNascimento(Integer.valueOf(atributos[5]));
				}
				comp++;
				if (atributos[6]!=""){
					player[i].setCidadeNascimento(atributos[6]);
				}
				comp++;

				if (atributos[7]!=""){
					player[i].setEstadoNascimento(atributos[7]);
				}
				comp++;
				
				
				   
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}	

		public void Pesquisa(){
			Scanner leia = new Scanner(System.in);
			String in = leia.nextLine();
			
			while(!in.equals("FIM")){
				int numid = Integer.valueOf(in);
				comp++;

            for (int i = 0; i < player.length && player[i] != null; i++) {
                if (player[i].getId() == numid) {
					lista.inserirFim(player[i]);
                    break;
                }
				comp++;
            }
            
				in = leia.nextLine();
			}
			//quicksort(lista);
			lista.quickSort();
			
		 for (int i = 0; i < lista.tamanho(); i++) {
				Jogador jogador = lista.getElemento(i);
				System.out.println("[" + jogador.getId() + " ## " + jogador.getNome() + " ## " + jogador.getAltura() + " ## " + jogador.getPeso() + " ## " + jogador.getAnoNascimento() + " ## " + jogador.getUniversidade() + " ## " + jogador.getCidadeNascimento() + " ## " + jogador.getEstadoNascimento() + "]" );
			}
			
		}

		
		public static void main(String[] args){	
		long tempoInicial = System.currentTimeMillis();
		try {
            FileWriter escrever = new FileWriter("00817611_quicksort2.txt");
            Jogador jogador = new Jogador();
			jogador.lista.inicializarLista();

            jogador.Ler();
			jogador.Pesquisa();
            //jogador.Imprimir();
            
            escrever.write("00817611 \t " + String.valueOf(System.currentTimeMillis() - tempoInicial) + " \t " + comp);
            escrever.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		
		}
		
		

}