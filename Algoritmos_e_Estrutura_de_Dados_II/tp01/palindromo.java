//codigo para verificar se uma palavra é um palindromo
import java.util.Scanner;

class palindromo{
//public static Scanner ler = new Scanner(System.in);
public static boolean isPalindromo(String palavra){
	int tam = palavra.length();
	
        if(palavra.charAt(0)!=palavra.charAt(tam-1)){
            return false;
	
        }
		return isPalindromo(palavra.substring( 1, tam-1)); //pega o restante menos o 1 e o ultimo numeros (a palavra restante do meio)
 
}
public static void main(String args[]){
	String palavra = MyIO.readLine();

	while(!palavra.equalsIgnoreCase("fim")){
	boolean resp;
	
	if(resp == false){
        System.out.println("NAO");
    } else {
        System.out.println("SIM");
    }
	palavra = MyIO.readLine();
  	    
	}
}

}