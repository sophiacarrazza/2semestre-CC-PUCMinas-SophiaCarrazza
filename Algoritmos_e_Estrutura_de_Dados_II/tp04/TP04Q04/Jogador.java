import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

class NoAN {
  public boolean cor;
  public Jogador elemento;
  public NoAN esq, dir;

  public NoAN() {
    this(null);
  }

  public NoAN(Jogador elemento) {
    this(elemento, false, null, null);
  }

  public NoAN(Jogador elemento, boolean cor) {
    this(elemento, cor, null, null);
  }

  public NoAN(Jogador elemento, boolean cor, NoAN esq, NoAN dir) {
    this.cor = cor;
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
  }
}


class Alvinegra {
	private NoAN raiz; // Raiz da arvore.
 

	public Alvinegra() {
	   raiz = null;
	}
 

	public boolean pesquisar(Jogador elemento) {
	   System.out.print(" raiz ");
	   return pesquisar(elemento, raiz);
	}
 

	private boolean pesquisar(Jogador elemento, NoAN i) {
	   boolean resp;
	   if (i == null) {
		  resp = false;
	   } else if (elemento.nome.compareTo(i.elemento.nome) == 0) {
		  resp = true;
	   } else if (elemento.nome.compareTo(i.elemento.nome) < 0) {
		  System.out.print("esq ");
		  resp = pesquisar(elemento, i.esq);
	   } else {
		System.out.print("dir ");
		  resp = pesquisar(elemento, i.dir);
	   }
	   return resp;
	}

	public void inserir(Jogador elemento) throws Exception {
	   // Se a arvore estiver vazia
	   if (raiz == null) {
		  raiz = new NoAN(elemento);
 
	   // Senao, se a arvore tiver um elemento
	   } else if (raiz.esq == null && raiz.dir == null) {
		  if (elemento.nome.compareTo(raiz.elemento.nome) < 0) {
			 raiz.esq = new NoAN(elemento);
		  } else {
			 raiz.dir = new NoAN(elemento);
		  }
 
	   // Senao, se a arvore tiver dois elementos (raiz e dir)
	   } else if (raiz.esq == null) {
		  if (elemento.nome.compareTo(raiz.elemento.nome) < 0) {
			 raiz.esq = new NoAN(elemento);
 
		  } else if (elemento.nome.compareTo(raiz.dir.elemento.nome) < 0) {
			 raiz.esq = new NoAN(raiz.elemento);
			 raiz.elemento = elemento;
 
		  } else {
			 raiz.esq = new NoAN(raiz.elemento);
			 raiz.elemento = raiz.dir.elemento;
			 raiz.dir.elemento = elemento;
		  }
		  raiz.esq.cor = raiz.dir.cor = false;
 
	   // Senao, se a arvore tiver dois elementos (raiz e esq)
	   } else if (raiz.dir == null) {
		  if (elemento.nome.compareTo(raiz.elemento.nome) > 0) {
			 raiz.dir = new NoAN(elemento);
 
		  } else if (elemento.nome.compareTo(raiz.esq.elemento.nome) > 0) {
			 raiz.dir = new NoAN(raiz.elemento);
			 raiz.elemento = elemento;
 
		  } else {
			 raiz.dir = new NoAN(raiz.elemento);
			 raiz.elemento = raiz.esq.elemento;
			 raiz.esq.elemento = elemento;
		  }
		  raiz.esq.cor = raiz.dir.cor = false;
 
	   // Senao, a arvore tem tres ou mais elementos
	   } else {
		  inserir(elemento, null, null, null, raiz);
	   }
	   raiz.cor = false;
	}
 
	private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
	   // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
	   if (pai.cor == true) {
		  // 4 tipos de reequilibrios e acoplamento
		  if (pai.elemento.nome.compareTo(avo.elemento.nome) > 0) { // rotacao a esquerda ou direita-esquerda
			 if (i.elemento.nome.compareTo(pai.elemento.nome) > 0) {
				avo = rotacaoEsq(avo);
			 } else {
				avo = rotacaoDirEsq(avo);
			 }
		  } else { // rotacao a direita ou esquerda-direita
			 if (i.elemento.nome.compareTo(pai.elemento.nome) < 0) {
				avo = rotacaoDir(avo);
			 } else {
				avo = rotacaoEsqDir(avo);
			 }
		  }
		  if (bisavo == null) {
			 raiz = avo;
		  } else if (avo.elemento.nome.compareTo(bisavo.elemento.nome) < 0) {
			 bisavo.esq = avo;
		  } else {
			 bisavo.dir = avo;
		  }
		  // reestabelecer as cores apos a rotacao
		  avo.cor = false;
		  avo.esq.cor = avo.dir.cor = true;
	   } // if(pai.cor == true)
	}
 
	private void inserir(Jogador elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
	   if (i == null) {
		  if (elemento.nome.compareTo(pai.elemento.nome) < 0) {
			 i = pai.esq = new NoAN(elemento, true);
		  } else {
			 i = pai.dir = new NoAN(elemento, true);
		  }
		  if (pai.cor == true) {
			 balancear(bisavo, avo, pai, i);
		  }
	   } else {
		  // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
		  if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
			 i.cor = true;
			 i.esq.cor = i.dir.cor = false;
			 if (i == raiz) {
				i.cor = false;
			 } else if (pai.cor == true) {
				balancear(bisavo, avo, pai, i);
			 }
		  }
		  if (elemento.nome.compareTo(i.elemento.nome) < 0) {
			 inserir(elemento, avo, pai, i, i.esq);
		  } else if (elemento.nome.compareTo(i.elemento.nome) > 0) {
			 inserir(elemento, avo, pai, i, i.dir);
		  } else {
			 throw new Exception("Erro inserir (elemento repetido)!");
		  }
	   }
	}
 
	private NoAN rotacaoDir(NoAN no) {
	   NoAN noEsq = no.esq;
	   NoAN noEsqDir = noEsq.dir;
 
	   noEsq.dir = no;
	   no.esq = noEsqDir;
 
	   return noEsq;
	}
 
	private NoAN rotacaoEsq(NoAN no) {
	   NoAN noDir = no.dir;
	   NoAN noDirEsq = noDir.esq;
 
	   noDir.esq = no;
	   no.dir = noDirEsq;
	   return noDir;
	}
 
	private NoAN rotacaoDirEsq(NoAN no) {
	   no.dir = rotacaoDir(no.dir);
	   return rotacaoEsq(no);
	}
 
	private NoAN rotacaoEsqDir(NoAN no) {
	   no.esq = rotacaoEsq(no.esq);
	   return rotacaoDir(no);
	}
 }

class Jogador{
		private int id, altura, peso, anoNascimento;
		public String nome, universidade, cidadeNascimento, estadoNascimento;
		private	Jogador player[] = new Jogador[3922]; 
		Alvinegra avn = new Alvinegra();
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
		public void Pesquisa(){
			Scanner leia = new Scanner(System.in);
			String in = leia.nextLine();
			
			while(!in.equals("FIM")){
				int numid = Integer.valueOf(in);
				comp++;

            for (int i = 0; i < player.length && player[i] != null; i++) {
                if (player[i].getId() == numid) {
                    try {
						avn.inserir(player[i]);
					} catch (Exception e) {
						e.printStackTrace();
					}
                    break;
                }
				comp++;
            }
				in = leia.nextLine();
			}
			
			while (true) {
        String str = leia.nextLine();
        if (str.equals("FIM")) {
            break;
        }
		comp++;
		Jogador jogadorpesquisa = new Jogador();
		int achou = 0;
        for(int i=0; i < player.length && player[i] != null; i++){
            if(str.equals(player[i].nome)){
				System.out.print(player[i].nome);
                jogadorpesquisa = player[i];
				achou ++;
            }
        }
		if (achou == 0){
			System.out.print(str);
			jogadorpesquisa.setNome(str);
				
		}
		
        if(avn.pesquisar(jogadorpesquisa) == true){
            System.out.println("SIM");
        } else{
            System.out.println("NAO");
        }

    }
			
		}

		public static void main(String[] args){	
		long tempoInicial = System.currentTimeMillis();
		try {
            FileWriter escrever = new FileWriter("00817611_avinegra.txt");
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