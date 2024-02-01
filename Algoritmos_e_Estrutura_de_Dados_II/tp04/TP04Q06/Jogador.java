import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

		class No {
			public char elemento;
			public final int tamanho = 255;
			public No[] prox;
			public boolean folha;
			
			public No (){
			   this(' ');
			}
		 
			public No (char elemento){
			   this.elemento = elemento;
			   prox = new No [tamanho];
			   for (int i = 0; i < tamanho; i++) prox[i] = null;
			   folha = false;
			}
		 
			public static int hash (char x){
			   return (int)x;
			}
		 }

		class ArvoreTrie {
			public No raiz;
		
			public ArvoreTrie(){
				raiz = new No();
			}
		
		
			public boolean pesquisar(String s) throws Exception {
				return pesquisar(s, raiz, 0);
			}
		
			public boolean pesquisar(String s, No no, int i) throws Exception {
				boolean resp;
				if(no.prox[s.charAt(i)] == null){
					resp = false;
				} else if(i == s.length() - 1){
					resp = (no.prox[s.charAt(i)].folha == true);
				} else if(i < s.length() - 1 ){
					resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
				} else {
					throw new Exception("Erro ao pesquisar!");
				}
				return resp;
			}
		
			public void inserir(String s) throws Exception {
				inserir(s, raiz, 0);
			}
		
			private void inserir(String s, No no, int i) throws Exception {
				//System.out.print("\nEM NO(" + no.elemento + ") (" + i + ")");
				if(no.prox[s.charAt(i)] == null){
					//System.out.print("--> criando filho(" + s.charAt(i) + ")");
					no.prox[s.charAt(i)] = new No(s.charAt(i));
		
					if(i == s.length() - 1){
						//System.out.print("(folha)");
						no.prox[s.charAt(i)].folha = true;
					}else{
						inserir(s, no.prox[s.charAt(i)], i + 1);
					}
		
				} else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
					inserir(s, no.prox[s.charAt(i)], i + 1);
		
				} else {
					throw new Exception("Erro ao inserir!");
				} 
			}
		
			public void mostrar(){
				mostrar("", raiz);
			}
		
			public void mostrar(String s, No no) {
				if(no.folha == true){
					System.out.println("Palavra: " + (s + no.elemento));
				} else {
					for(int i = 0; i < no.prox.length; i++){
						if(no.prox[i] != null){
							System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
							mostrar(s + no.elemento, no.prox[i]);
						}
					}
				}
			}

			public void merge(ArvoreTrie other) {
				merge(this.raiz, other.raiz);
			}
			
			private void merge(No thisNode, No otherNode) {
				if (otherNode == null) {
					return;
				}
			
				for (int i = 0; i < otherNode.prox.length; i++) {
					if (otherNode.prox[i] != null) {
						if (thisNode.prox[i] == null) {
							thisNode.prox[i] = new No(otherNode.prox[i].elemento);
						}
						merge(thisNode.prox[i], otherNode.prox[i]);
					}
				}
			
				if (otherNode.folha) {
					thisNode.folha = true;
				}
			}
						
			
	
		}
class Jogador{
		private int id, altura, peso, anoNascimento;
		private String nome, universidade, cidadeNascimento, estadoNascimento;
		private	Jogador player[] = new Jogador[3922]; 
		ArvoreTrie trie1 = new ArvoreTrie();
		ArvoreTrie trie2 = new ArvoreTrie();
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
		public void Pesquisa() throws Exception{
			Scanner leia = new Scanner(System.in);

			String in = leia.nextLine();
			while(!in.equals("FIM")){
				int numid = Integer.valueOf(in);
				comp++;
            for (int i = 0; i < player.length && player[i] != null; i++) {
                if (player[i].getId() == numid) {
                    try {
						trie1.inserir(player[i].nome);
					} catch (Exception e) {
						e.printStackTrace();
					}
                    break;
                }
				comp++;
            }
				in = leia.nextLine();
			}

			in = leia.nextLine();
			while(!in.equals("FIM")){
				int numid = Integer.valueOf(in);
				comp++;
            for (int i = 0; i < player.length && player[i] != null; i++) {
                if (player[i].getId() == numid) {
                    try {
						trie2.inserir(player[i].nome);
					} catch (Exception e) {
						e.printStackTrace();
					}
                    break;
                }
				comp++;
            }
				in = leia.nextLine();
			}

			trie1.merge(trie2);
			
			while (true) {
        String str = leia.nextLine();
        if (str.equals("FIM")) {
            break;
        }
		comp++;
        String nomepesquisa = "";
        for(int i=0; i < player.length && player[i] != null; i++){
            if(str.equals(player[i].nome)){
				System.out.print(player[i].nome);
                nomepesquisa = player[i].nome;
            } else if ((str + "*").equals(player[i].nome)){
				System.out.print(str);
				nomepesquisa = str;
			}
        }
		
        if(trie1.pesquisar(nomepesquisa) == true){
            System.out.println(" SIM");
        } else{
            System.out.println(" NAO");
        }

    }
			
		}

		public static void main(String[] args) throws Exception{	
		long tempoInicial = System.currentTimeMillis();
		try {
            FileWriter escrever = new FileWriter("00817611_arvoreTrie.txt");
            Jogador jogador = new Jogador();
            jogador.Ler();
			jogador.Pesquisa();
            //jogador.Imprimir();
            
            escrever.write("00817611 \t " + String.valueOf(System.currentTimeMillis() - tempoInicial) + " \t " + comp);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		
		}
		
		

}