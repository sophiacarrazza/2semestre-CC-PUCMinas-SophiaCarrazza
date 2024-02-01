class stj{

	public static boolean isVogal(String x){
		String vogal = "AEIOUaeiou";
			if(x.isEmpty()){
				return true;
			}
			char letra = x.charAt(0);
			if (vogal.indexOf(letra) == -1){
				return false;
			}
			
		return isVogal(x.substring(1));
	}
	public static boolean isConsoante(String x){
		String consoante = "QWRTYPSDFGHJKLZXCVBNMqwrtypsdfghjklzxcvbnm";
			if(x.isEmpty()){
				return true;
			}
			char letra = x.charAt(0);
			if (consoante.indexOf(letra) == -1){ //o indexOf procura as consoantes na String letra  se nao encontrar retorna -1
				return false;
			}
		
		return isConsoante(x.substring(1));
	}
	public static boolean isInt(String x){
		try {
			Integer.parseInt(x); //converte a string em inteiro e retorna verdadeiro
			return true;
		} catch (NumberFormatException naoeinteiro){ //se a conversao nao deu certo ele entra na excecao
			return false;
		}
	}
	public static boolean isDouble(String x){
		try {
			Double.parseDouble(x); //converte a string em double e retorna verdadeiro
			return true;
		} catch (NumberFormatException naoedouble){ //se a conversao nao deu certo ele entra na excecao
			return false;
		}
	}
	public static void main(String args[]){
		
		String x = MyIO.readLine();
		while (true){
		if(x.charAt(0)=='F' && x.charAt(1)=='I' && x.charAt(2)=='M'){
			System.exit(0);
		} else{
		boolean lista[] = new boolean[4];
		lista[0] = isVogal(x);
		lista[1] = isConsoante(x);
		lista[2] = isInt(x);
		lista[3] = isDouble(x);
		
		for (int i=0; i<4; i++){
			if(lista[i] == true){
				System.out.print("SIM");
			} else {
				System.out.print("NAO");
			}
			if(i!=3){
				System.out.print(" ");
			}
		}
		System.out.println("");
		x = MyIO.readLine();
		}
		}
	}



}