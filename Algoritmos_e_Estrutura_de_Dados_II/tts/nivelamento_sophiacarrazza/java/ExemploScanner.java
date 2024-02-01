import java.util.Scanner;

public class ExemploScanner{
	public static void main(String args[]){
		Scanner ler = new Scanner(System.in); //o system.in representa o teclado

		System.out.println("Digite seu nome: ");
		String nome = ler.nextLine();

		System.out.println("Digite a letra do seu tipo sanguineo: ");
		char sanguineo = ler.next().charAt(0);

		System.out.println("Digite a sua idade: ");
		int idade = ler.nextInt();

		System.out.println("Digite a sua renda mensal: ");
		double renda = ler.nextDouble();

		ler.close();

		System.out.println("Seu nome é: " + nome);
		System.out.println("Seu tipo sanguineo é: " + sanguineo);
		System.out.println("Sua idade é: " + idade);
		System.out.println("Sua renda mensal é: " + renda);

	
	
	}
}