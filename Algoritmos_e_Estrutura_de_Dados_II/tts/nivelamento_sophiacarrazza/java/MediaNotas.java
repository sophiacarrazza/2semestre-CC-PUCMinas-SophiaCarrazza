import java.util.Scanner;

class MediaNotas{
	public static Scanner ler = new Scanner(System.in);
	public static void main(String args[]){
		
		double media, notas=0;

		for (int i=0; i<5;i++){
			System.out.println("Digite a nota do aluno " + (i+1) + ":");
			
		notas += ler.nextDouble();
	}
		ler.close();
		System.out.println("A media de nota dos alunos e:" + notas/5);

}

}