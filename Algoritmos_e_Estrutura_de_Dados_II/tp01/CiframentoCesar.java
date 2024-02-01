class CiframentoCesar{
	public static void main(String args[]){

	MyIO.setCharset("UTF-8");
	//String alfabeto ="abcdefghijklmnopqrstuvwxyz";
	String palavra = MyIO.readLine();
	while(!palavra.equalsIgnoreCase("fim")){
		
	int tam = palavra.length();
	//StringBuilder palavrac = new StringBuilder();
	String palavrac = "";
	for(int i=0; i<tam; i++){	
		int letra = (int)palavra.charAt(i);
		if(Character.isLetter(letra)){
		letra += 3;
		} 
		//else if (letra == ' '){
		//palavrac += '#';
		//continue; //pula o resto do loop pra nao ter adicionar o espaço mesmo tendo adicionado o #.
		}
		palavrac += (char)letra;
	//	int pos = alfabeto.indexOf(letra);
	//	int novapos = (pos + 3) % 26;
    //    char letrac = alfabeto.charAt(novapos);
    //    palavrac.append(String.valueOf(letrac));
	}
	System.out.println(palavrac);
	palavra = MyIO.readLine();
	}

}

//		public static boolean IsFim(String x){
//				if(x == "fim" || x == "FIM"){
//					return true;
//				}else{
//					return false;
//				}
//			
//		}
}