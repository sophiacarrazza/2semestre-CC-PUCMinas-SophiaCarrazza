import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


class Jogador{
		private int id, altura, peso, anoNascimento;
		private String nome, universidade, cidadeNascimento, estadoNascimento;
		private	Jogador player[] = new Jogador[3922]; 
		ArrayList<Jogador> lista = new ArrayList<Jogador>();
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
		public static void ordSelecao(ArrayList<Jogador> lista) {
        int n = lista.size();

        for (int i = 0; i < n - 1; i++) {
            int menorIndice = i;

            // Encontre o índice da menor string no restante da lista
            for (int j = i + 1; j < n; j++) {
                if (lista.get(j).getNome().compareTo(lista.get(menorIndice).getNome()) < 0) {
                    menorIndice = j;
                }
            }

            // trocando a string atual com a menor string achada
            Jogador temp = lista.get(i); // temp é o menor jogador encontrado
            lista.set(i, lista.get(menorIndice)); //i = menor indice da lista
            lista.set(menorIndice, temp); // menor indice é temp
        }
		}
		/*public static void ordInsercao(ArrayList<Jogador> array) {
        int n = array.size();
        
        for (int i = 1; i < n; i++) {
            Jogador chave = array.get(i);
			String nchave = array.get(i).getNome();
            int j = i - 1;
            
            while (j >= 0 && array.get(j).getNome().compareTo(nchave) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }
            
            array.set(j + 1, chave);
        }
		}*/
		
		public void Pesquisa(){
			Scanner leia = new Scanner(System.in);
			String in = leia.nextLine();
			
			while(!in.equals("FIM")){
				int numid = Integer.valueOf(in);
				comp++;

            for (int i = 0; i < player.length && player[i] != null; i++) {
                if (player[i].getId() == numid) {
					lista.add(player[i]);
                    break;
                }
				comp++;
            }
            
				in = leia.nextLine();
			}
			ordSelecao(lista);
		 for (int i = 0; i < lista.size(); i++){
			 System.out.println("[" + lista.get(i).getId() + " ## " + lista.get(i).getNome() + " ## " + lista.get(i).getAltura() + " ## " + lista.get(i).getPeso() + " ## " + lista.get(i).getAnoNascimento() + " ## " + lista.get(i).getUniversidade() + " ## " + lista.get(i).getCidadeNascimento() + " ## " + lista.get(i).getEstadoNascimento() + "]");
		 }
			
			/*while (true) {
        String str = leia.nextLine();

        if (str.equals("FIM")) {
            break;
        }
		comp++;
		ordInsercao(lista);
		 for (int i = 0; i < lista.size(); i++){
			 System.out.println(System.out.println("[" + player[i].getId() + " ## " + player[i].getNome() + " ## " + player[i].getAltura() + " ## " + player[i].getPeso() + " ## " + player[i].getAnoNascimento() + " ## " + player[i].getUniversidade() + " ## " + player[i].getCidadeNascimento() + " ## " + player[i].getEstadoNascimento() + "]"););
		 }

        boolean encontrado = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equals(str)) {
                System.out.println("SIM");
                //System.out.println("[" + player[i].getId() + " ## " + player[i].getNome() + " ## " + player[i].getAltura() + " ## " + player[i].getPeso() + " ## " + player[i].getAnoNascimento() + " ## " + player[i].getUniversidade() + " ## " + player[i].getCidadeNascimento() + " ## " + player[i].getEstadoNascimento() + "]");
                encontrado = true;
                break;
            }
			comp++;
        }
        if (!encontrado) {
            System.out.println("NAO");
        }
		}*/
			
		}

		
		public static void main(String[] args){	
		long tempoInicial = System.currentTimeMillis();
		try {
            FileWriter escrever = new FileWriter("00817611_selecao.txt");
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