import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

class No {
	public int elemento; // alturaMod15
	public No esq, dir;  // Filhos esq e dir.
   public No2 outro;
	
	No(int elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
      this.outro = null;
	}


	No(int elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
      this.outro = null;
	}
}

class No2 {
	public String elemento; // Conteudo do no.
	public No2 esq, dir;  // Filhos esq e dir.

	No2(String elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
	}

	No2(String elemento, No2 esq, No2 dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}

class ArvoreArvore {
	public No raiz; // Raiz da arvore.


	public ArvoreArvore()  throws Exception{
	  raiz = null;
      //ordem de insercao  7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12 e 14 (mudança: 13 no 10 e 10 no 13)
      inserir(7);
      inserir(3);
      inserir(11);
      inserir(1);
      inserir(5);
      inserir(9);
      inserir(10);
      inserir(0);
      inserir(2);
      inserir(4);
      inserir(6);
      inserir(8);
      inserir(13);
      inserir(12);
      inserir(14);
	}
    public void inserir(int x) throws Exception {
        this.raiz = inserir(x, raiz);
       // System.out.println("Raiz: " + raiz.elemento);
       // System.out.println("Inserido: " + x);
    }
    
    public No inserir(int x, No i) throws Exception {
        if (i == null) {
            return new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir: esse no ja existe!");
        }
        return i;
    }
    
    public void inserir(Jogador jogador) throws Exception {
        this.raiz = inserir(jogador, raiz);
        //System.out.println("Raiz: " + raiz.elemento);
        //System.out.println("Inserido: " + jogador.getId() + " " + jogador.getAltura() % 15);
    }
    
    public No inserir(Jogador jogador, No i) throws Exception {
        int x = jogador.getAltura() % 15;
        if (i == null) {
            i = new No(x);
           // System.out.println("Inserido: " + jogador.getId() + " " + x);
            throw new Exception("Erro ao inserir: caractere invalido!");
        } else if (x < i.elemento) {
            i.esq = inserir(jogador, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(jogador, i.dir);
        } else {
            //System.out.println("indo para a arvore de nomes");
            i.outro = inserir(jogador, i.outro);
        }
        return i;
    }
    


	private No2 inserir(Jogador jogador, No2 i) throws Exception {
        String x = jogador.getNome();
		if (i == null) {
         i = new No2(x);
        // System.out.println("Inserido: " + x);

      } else if (x.compareTo(i.elemento) < 0) {
         i.esq = inserir(jogador, i.esq);

      } else if (x.compareTo(i.elemento) > 0) {
         i.dir = inserir(jogador, i.dir);

      } else {
         throw new Exception("Erro ao inserir: elemento existente!");
      }

		return i;
	}


    public boolean pesquisar(Jogador jogador) {
        System.out.print(" raiz ");
        return pesquisar(jogador, raiz);
    }

    private boolean pesquisar(Jogador jogador, No i) {
        boolean resp = false;
        int x = jogador.getAltura() % 15;
        String nome = jogador.getNome();

        if (i != null) {
            if (!resp) {
                resp = pesquisar(i.outro, nome);
            }
            if (!resp) {
                System.out.print("esq ");
                resp = pesquisar(jogador, i.esq);
            }
            if (!resp) {
                System.out.print("dir ");
                resp = pesquisar(jogador, i.dir);
            }
            
        }
    
        return resp;
    }

    private boolean pesquisar(No2 i, String nome) {
        boolean resp = false;
        if (i != null) {
            if (nome.equals(i.elemento)) {
                resp = true;
            }
            else{
                System.out.print("ESQ ");
                resp = pesquisar(i.esq, nome);
                
                if (!resp) {
                System.out.print("DIR ");
                resp = pesquisar(i.dir, nome);
                }
            }
            
        }
    
        return resp;
    }
    


}

class Jogador {
		private int id, altura, peso, anoNascimento;
		private String nome, universidade, cidadeNascimento, estadoNascimento;
		private	Jogador player[] = new Jogador[3922]; 
		
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
		public void Pesquisa()  throws Exception{
			Scanner leia = new Scanner(System.in);
			String in = leia.nextLine();
            ArvoreArvore ab = new ArvoreArvore();
     
			while(!in.equals("FIM")){
				int numid = Integer.valueOf(in);
				comp++;

            for (int i = 0; i < player.length && player[i] != null; i++) {
                if (player[i].getId() == numid) {
                    try {
						ab.inserir(player[i], ab.raiz);
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
        int achou = 0;
        Jogador jogadorpesquisa = new Jogador();
        for(int i=0; i < player.length && player[i] != null; i++){
            if(str.equals(player[i].nome)){
				System.out.print(player[i].nome);
                jogadorpesquisa = player[i];
                
            }
        }
       
        if (ab.pesquisar(jogadorpesquisa) == true) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }


    }
			
		}

		public static void main(String[] args)  throws Exception{	
		long tempoInicial = System.currentTimeMillis();
		try {
            FileWriter escrever = new FileWriter("00817611_arvoreArvore.txt");
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