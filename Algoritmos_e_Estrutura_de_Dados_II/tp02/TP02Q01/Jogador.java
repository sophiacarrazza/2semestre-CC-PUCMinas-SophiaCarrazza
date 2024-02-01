import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class Jogador{
		private int id, altura, peso, anoNascimento;
		private String nome, universidade, cidadeNascimento, estadoNascimento;
		private	Jogador player[] = new Jogador[3922]; //variavel de instancia da classe p/ ela ficar disponivel tanto pro metodo ler quanto pro imprimir
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
			arq.readLine();
			for (int i = 0; i<3922; i++){
                String str = arq.readLine();
                String [] atributos = str.split(",", -1);
                player [i] = new Jogador();
				
                if (atributos[0]!=""){
					player[i].setId(Integer.valueOf(atributos[0]));
				}
				if (atributos[1]!=""){
					player[i].setNome(atributos[1]);
				}
				if (atributos[2]!=""){
					player[i].setAltura(Integer.valueOf(atributos[2]));
				}
				if (atributos[3]!=""){
					player[i].setPeso(Integer.valueOf(atributos[3]));
				}
				if (atributos[4]!=""){
					player[i].setUniversidade(atributos[4]);
				}
				
				if (atributos[5]!=""){
					player[i].setAnoNascimento(Integer.valueOf(atributos[5]));
				}
				if (atributos[6]!=""){
					player[i].setCidadeNascimento(atributos[6]);
				}
				if (atributos[7]!=""){
					player[i].setEstadoNascimento(atributos[7]);
				}
				
				
				   
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		public void Imprimir(){
			Scanner leia = new Scanner(System.in);
			String in = leia.nextLine();
			
			while(!in.equals("FIM")){
				int numid = Integer.valueOf(in);
				System.out.println("[" + player[numid].getId() + " ## " + player[numid].getNome() + " ## " + player[numid].getAltura() + " ## " + player[numid].getPeso() + " ## " + player[numid].getAnoNascimento() + " ## " + player[numid].getUniversidade() + " ## "+ player[numid].getCidadeNascimento() + " ## " + player[numid].getEstadoNascimento() + "]");
				in = leia.nextLine();
			}
			leia.close();
		}
		public static void main(String[] args){	
			Jogador jogador = new Jogador();
			jogador.Ler();
			jogador.Imprimir();
			
		}

}