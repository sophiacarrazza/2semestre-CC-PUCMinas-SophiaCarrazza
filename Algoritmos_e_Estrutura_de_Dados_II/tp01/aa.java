
import java.util.Random;

class aa {
	
//alteracao aleatoria

static Random gerador = new Random(4);

static char RandomNum(){
	return((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
}

static String Aaleatorio(String palavra){
	char letrain = RandomNum(); //letra inicial
	char letranew = RandomNum();
	String resp = "";
	int tam = palavra.length();
	for (int i=0; i<tam; i++){
		char letra = palavra.charAt(i);
		if (letra == letrain){
			resp += letranew;
		} else {
			resp += letra;
		}
	}
	return resp;
}

 public static void main (String args[]){
	
	while(true){
	String palavra = "";
	palavra = MyIO.readLine();
		if(palavra.charAt(0)=='F' && palavra.charAt(1)=='I' && palavra.charAt(2)=='M'){
			System.exit(0);
		} else{
		MyIO.println(Aaleatorio(palavra));
		}
	}
 
 }

}