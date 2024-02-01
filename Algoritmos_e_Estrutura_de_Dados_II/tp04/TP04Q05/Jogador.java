import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


		class Hash {
			Jogador tabela[];
			int m1, m2, m, reserva;
			final int NULO = -1;
		 
			public Hash() {
			   this(21, 9);
			}
		 
			public Hash(int m1, int m2) {
			   this.m1 = m1;
			   this.m2 = m2;
			   this.m = m1 + m2;
			   this.tabela = new Jogador[this.m];
			   for (int i = 0; i < m1; i++) {
				  tabela[i] = new Jogador();
				  tabela[i].altura = NULO;
			   }
			   reserva = 0;
			}
		 
			public int h(Jogador elemento) {
			   return elemento.altura % m1;
			}
		 
			public boolean inserir(Jogador elemento) {
			   boolean resp = false;
			   if (elemento.altura != NULO) {
				  int pos = h(elemento);
				  if (tabela[pos].altura == NULO) {
					 tabela[pos] = elemento;
					 resp = true;
				  } else if (reserva < m2) {
					 tabela[m1 + reserva] = elemento;
					 reserva++;
					 resp = true;
				  }
			   }
			   return resp;
			}
		 
			public boolean pesquisar(Jogador elemento) {
			   boolean resp = false;
			   int pos = h(elemento);
			   if (tabela[pos] == elemento) {
				  resp = true;
			   } else if (tabela[pos].altura != NULO) {
				  for (int i = 0; i < reserva; i++) {
					 if (tabela[m1 + i] == elemento) {
						resp = true;
						i = reserva;
					 }
				  }
			   }
			   return resp;
			}
		
		 }

class Jogador{
		public int id, altura, peso, anoNascimento;
		private String nome, universidade, cidadeNascimento, estadoNascimento;
		private	Jogador player[] = new Jogador[3922]; 
		ArvoreBinaria ab = new ArvoreBinaria();
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

        class No {
            public Jogador elemento; // conteudo do no.
            public No esq, dir;  // filhos da esq e dir.

            public No(Jogador elemento) {
                this(elemento, null, null);
            }

            public No(Jogador elemento, No esq, No dir) {
                this.elemento = elemento;
                this.esq = esq;
                this.dir = dir;
            }
        }

        public class ArvoreBinaria {
            public No raiz; // Raiz da arvore.

            public ArvoreBinaria() {
                raiz = null;
            }

            public boolean pesquisar(String x) {
				System.out.print(" raiz ");
                return pesquisar(x, raiz);
            }

            public boolean pesquisar(String x, No i) {
            boolean resp;

                if (i == null) {
                resp = false;

            } else if (x.equals(i.elemento.nome)) {
                resp = true;

            } else if (x.compareTo(i.elemento.nome) < 0) {
				System.out.print("esq ");
                resp = pesquisar(x, i.esq);

            } else {
				System.out.print("dir ");
                resp = pesquisar(x, i.dir);

            }
            return resp;
            }

            public void caminharCentral() {
                caminharCentral(raiz);
            }

            public void caminharCentral(No i) {
                if (i != null) {
                    caminharCentral(i.esq); // Elementos da esquerda.
                    System.out.println(i.elemento.nome); // Conteudo do no.
                    caminharCentral(i.dir); // Elementos da direita.
                }
            }

            public void inserir(Jogador x) throws Exception {
                raiz = inserir(x, raiz);
            }

            public No inserir(Jogador x, No i) throws Exception {
                if (i == null) {
                i = new No(x);

            } else if (x.nome.compareTo(i.elemento.nome) < 0) {
                i.esq = inserir(x, i.esq);

            } else if (x.nome.compareTo(i.elemento.nome) > 0) {
                i.dir = inserir(x, i.dir);

            } else {
                throw new Exception("Erro ao inserir!");
            }

                return i;
            }

        public int getAltura(){
            return getAltura(raiz, 0);
        }

        public int getAltura(No i, int altura){
            if(i == null){
                altura--;
            } else {
                int alturaEsq = getAltura(i.esq, altura + 1);
                int alturaDir = getAltura(i.dir, altura + 1);
                altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
            }
            return altura;
        }


        public Jogador getRaiz() throws Exception {
            return raiz.elemento;
        }
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
                    try {
						ab.inserir(player[i]);
					} catch (Exception e) {
						e.printStackTrace();
					}
                    break;
                }
				comp++;
            }
            
				in = leia.nextLine();
			}
			ab.caminharCentral();
			
	
			
		}

		public static void main(String[] args){	
		long tempoInicial = System.currentTimeMillis();
		try {
            FileWriter escrever = new FileWriter("00817611_treesort.txt");
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