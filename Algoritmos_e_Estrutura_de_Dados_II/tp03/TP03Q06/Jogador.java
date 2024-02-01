import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Celula {
	public Jogador elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.


	/**
	 * Construtor da classe.
	 */
	public Celula() {
		this(new Jogador());
	}

	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public Celula(Jogador elemento) {
      this.elemento = elemento;
      this.prox = null;
	}
}

class Lista {
	private Celula primeiro;
	private Celula ultimo;


	/**
	 * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
	 */
	public void inicializarLista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }


	/**
	 * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirFim(Jogador x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
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

		// Caminhar ate a penultima celula:
      Celula i;
      for(i = primeiro; i.prox != ultimo; i = i.prox);

      Jogador resp = ultimo.elemento; 
      ultimo = i; 
      i = ultimo.prox = null;
      
		return resp;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] ");
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(Jogador x) {
		boolean resp = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
         if(i.elemento.equals(x)){
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
      for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }

      // Método para obter um elemento da lista encadeada na posição i
	  public Jogador getElemento(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= tamanho()) {
		   throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
		}
		Celula k = primeiro.prox;
		for(int a=0; a < i; a++){
		  k = k.prox;
		}
		return k.elemento;
	 }
}
   

class Jogador {
		private int id, altura, peso, anoNascimento;
		private String nome, universidade, cidadeNascimento, estadoNascimento;
		private	Jogador player[] = new Jogador[3922]; 
		Lista lista;
		//ArrayList<Jogador> lista = new ArrayList<Jogador>();

		private static int comp=0;
		//Construtor vazio
		public Jogador(){
		lista = new Lista();
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

		public void ListadeJogadores() throws Exception{
			Scanner leia = new Scanner(System.in);
			String in = leia.nextLine();
			
			while (!in.equals("FIM")) {
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

			int num_ins_rem = Integer.parseInt(leia.nextLine());

			for(int i=0; i< num_ins_rem; i++){
				String comando = leia.nextLine();

				//separar a string no espaco
				String [] separar = comando.split(" ");

				if(separar[0].equals("I")){
					int id = Integer.valueOf(separar[1]);
					for (int j = 0; j < player.length && player[j] != null; j++) {
						if (player[j].getId() == id) {
							lista.inserirFim(player[j]);
							break;
						}
						comp++;
					}
				} else if(separar[0].equals("R")){
					Jogador removido = lista.removerFim();
            		System.out.println("(R) " + removido.getNome());

				} 
			}
			
			for (int i = 0; i < lista.tamanho(); i++) {
				Jogador jogador = lista.getElemento(i);
				System.out.println("[" + i + "]" + " ## " + jogador.getNome() + " ## " + jogador.getAltura() + " ## " + jogador.getPeso() + " ## " + jogador.getAnoNascimento() + " ## " + jogador.getUniversidade() + " ## " + jogador.getCidadeNascimento() + " ## " + jogador.getEstadoNascimento() + " ##");
			}
			leia.close();
			
		}

		
		public static void main(String[] args){	
			
		Jogador jogador = new Jogador();
		jogador.lista.inicializarLista();
            jogador.Ler();
			try {
				jogador.ListadeJogadores();
			} catch (Exception e) {
				e.printStackTrace();
			}
        
		
		}
		
		

}