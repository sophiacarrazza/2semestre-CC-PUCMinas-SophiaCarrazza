class cdcr{
	
	public static String criptografar(String palavra){
		while(true){
		String palavrac = "";
		int tam = palavra.length();
		if(palavra.isEmpty()){
			return "";
		}
		//for(int i=0; i<tam; i++){
			int letra = (int)palavra.charAt(0);
			letra += 3;
			palavrac += (char)letra;
		
		return (char) letra + criptografar(palavra.substring(1)); //resultado do primeiro char + a criptografia do resto e assim por diante
		}
	}
	
	public static void main(String args[]){
	while(true){
	String palavra = "";
	palavra = MyIO.readLine();

		if(palavra.charAt(0)=='F' && palavra.charAt(1)=='I' && palavra.charAt(2)=='M'){
			System.exit(0);
		}else{
			palavra = criptografar(palavra);
			MyIO.println(palavra);
		}
	}
	}
}