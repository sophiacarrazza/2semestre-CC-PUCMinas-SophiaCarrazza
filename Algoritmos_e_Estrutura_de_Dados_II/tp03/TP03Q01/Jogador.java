import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Lista {
   private Jogador[] array;
   private int n; //numero de elementos na lista


   public Lista () { //construtor sem especificar o tamanho (cria um de tamanho 127 por padrao)
      this(4000);
   }


   //construtor da classe especificando o tamanho
   public Lista (int tamanho){
      array = new Jogador[tamanho];
      n = 0;
   }

	//Insere um elemento na primeira posicao da lista e move os demais elementos para o fim da lista.
	// x = elemento a ser inserido
	// n = tamanho da lista
	// exception se a lista estiver cheia
   public void inserirInicio(Jogador x) throws Exception {

      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      } 

      //levar elementos para o fim do array (arredar eles pra direita pra caber um a mais
	  //(comeca do ultimo pois se for do inicio ele vai sobreescrever o prox elemento e assim por diante)
      for(int i = n; i > 0; i--){
         array[i] = array[i-1];
      }

      array[0] = x;
      n++; //aumenta o numero de elementos da lista
   }


   //Insere um elemento na ultima posicao da lista.
   public void inserirFim(Jogador x) throws Exception {

      //validar insercao
      if(n >= array.length){ //se a lista estiver cheia
         throw new Exception("Erro ao inserir!");
      }

      //insere o elemento na ultima posicao (n é igual ao num de elementos, entao se tem um no array[0], n = 1, entao o proximo elemento (o ultimo) vai ser inserido no array[1])
      array[n] = x; 
	  n++;
   }


   //Insere um elemento em uma posicao especifica e move os demais elementos para o fim da lista.
   //pos = posicao a ser inserido o elemento x
   public void inserir(Jogador x, int pos) throws Exception {

      //validar insercao
      if(n >= array.length || pos < 0 || pos > n){
         throw new Exception("Erro ao inserir!");
      }

      //leva os elementos a partir da posicao de insercao para o fim do array (de tras pra frente)
      for(int i = n; i > pos; i--){
         array[i] = array[i-1];
      }

      array[pos] = x;
      n++;
   }



   public Jogador removerInicio() throws Exception {

      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      Jogador resp = array[0];
      n--;

      for(int i = 0; i < n; i++){
         array[i] = array[i+1];
      }

      return resp;
   }

   public Jogador removerFim() throws Exception {

      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      return array[--n];
   }


   // Remove um elemento de uma posicao especifica da lista e movimenta os demais elementos para o inicio da mesma.

   public Jogador remover(int pos) throws Exception {

      //validar remocao
      if (n == 0 || pos < 0 || pos >= n) {
         throw new Exception("Erro ao remover!");
      }

      Jogador resp = array[pos];
      n--;

      for(int i = pos; i < n; i++){ //move os elementos após o "removido" pra esquerda pra sobrescrever ele
         array[i] = array[i+1];
      }

      return resp;
   }


   // Mostra os elementos do array separados por espacos.
   public void mostrar (){
      System.out.print("[ ");
      for(int i = 0; i < n; i++){
         System.out.print(array[i] + " ");
      }
      System.out.println("]");
   }

 // Método para obter o número de elementos na lista
   public int getN() {
      return n;
   }

   // Método para obter um elemento na posição i
   public Jogador getElemento(int i) throws IndexOutOfBoundsException {
      if (i < 0 || i >= n) {
         throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
      }
      return array[i];
   }
   
}

class Jogador {
		private int id, altura, peso, anoNascimento;
		private String nome, universidade, cidadeNascimento, estadoNascimento;
		private	Jogador player[] = new Jogador[3922]; 
		Lista lista = new Lista();
		//ArrayList<Jogador> lista = new ArrayList<Jogador>();

		private static int comp=0;
		//Construtor vazio
		public Jogador(){
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
				if(separar[0].equals("II")){
					int id = Integer.valueOf(separar[1]);
					for (int j = 0; j < player.length && player[j] != null; j++) {
						if (player[j].getId() == id) {
							lista.inserirInicio(player[j]);

							break;
						}
						comp++;
					}
				} else if(separar[0].equals("IF")){
					int id = Integer.valueOf(separar[1]);
					for (int j = 0; j < player.length && player[j] != null; j++) {
						if (player[j].getId() == id) {
							lista.inserirFim(player[j]);
							break;
						}
						comp++;
					}
				} else if(separar[0].equals("RI")){
					Jogador removido = lista.removerInicio();
            		System.out.println("(R) " + removido.getNome());

				} else if(separar[0].equals("RF")){
					Jogador removido = lista.removerFim();
            		System.out.println("(R) " + removido.getNome());

				} else if(separar[0].equals("I*")){
					int id = Integer.valueOf(separar[2]);
					int pos = Integer.valueOf(separar[1]);
					for (int j = 0; j < player.length && player[j] != null; j++) {
						if (player[j].getId() == id) {
							lista.inserir(player[j], pos);
							break;
						}
						comp++;
					}
				} else if(separar[0].equals("R*")){
					int pos = Integer.valueOf(separar[1]);
					Jogador removido = lista.remover(pos);
            		System.out.println("(R) " + removido.getNome());
				}
			}
			
			for (int i = 0; i < lista.getN(); i++) {
				Jogador jogador = lista.getElemento(i);
				System.out.println("[" + i + "]" + " ## " + jogador.getNome() + " ## " + jogador.getAltura() + " ## " + jogador.getPeso() + " ## " + jogador.getAnoNascimento() + " ## " + jogador.getUniversidade() + " ## " + jogador.getCidadeNascimento() + " ## " + jogador.getEstadoNascimento() + " ##");
			}
			leia.close();
			
		}

		
		public static void main(String[] args){	
			
		Jogador jogador = new Jogador();
            jogador.Ler();
			try {
				jogador.ListadeJogadores();
			} catch (Exception e) {
				e.printStackTrace();
			}
        
		
		}
		
		

}