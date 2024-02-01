class ab{
public static boolean Or (String x){
	int tam = x.length();
	for(int i=0; i< tam; i++){
		if(x.charAt(i) == '1'){ //se tiver pelo menos 1 dos valores =1, é 1(true)
			return true;
		}
	}
	return false;
}
public static boolean And (String x){
	int tam = x.length();
	for(int i=0; i< tam; i++){
		if(x.charAt(i) == '0'){ //se tiver pelo menos 1 dos valores =0, é 0(false)
			return false;
		}
	}
	return true;
}
public static boolean Not (String x){
	int tam = x.length();
	if(x.charAt(0) == '1'){ //se o primeiro valor da expressao for 1 a negacao é falsa
		return false;
	}
	return true;
}

public static boolean ResolverEquacao(String x) {
    int n = x.charAt(0) - '0';
    String compacta = x.replaceAll("[, ]", "");
    
    for (int i=0; i<n; i++) {
        char variavel = (char) ('A' + (i+1)); //transformando a letra da variavel em um indice (B = A+1)
        compacta = compacta.replace(variavel + "", i + ""); //transforma em strings
    }

    while(compacta.contains("(")){ //enquanto tiver paranteses abertos a expressao nao acabou
        int abreindex = compacta.lastIndexOf('('); //posicao do ultimo paranteses aberto (inicio da subexpressao)
        int fechaindex = compacta.indexOf(')', abreindex);

        String subx = compacta.substring(abreindex + 1, fechaindex);
        char operador = compacta.charAt(abreindex - 1); //caracter antes do parentese aberto(anD, oR, ou noT)

        boolean resaux = false;

        switch (operador) {
            case 'd':
                resaux = And(subx); //chamando a funcao p avaliar a subexpressao
                break;
            case 'r':
                resaux = Or(subx);
                break;
            case 't':
                resaux = Not(subx);
                break;
        }

        compacta = compacta.substring(0, abreindex - 1) + (resaux ? "1" : "0") + compacta.substring(fechaindex + 1);
		//substitui as subexpressoes pelos resultados ate q n tenha mais parenteses
    }
	
	return compacta.equals("1") ? true : false;
}



public static void main (String args[]){
	String str = "";
	str = MyIO.readLine();
	
	boolean res = false;
	while(true){
		if(str.charAt(0) =='0'){
		break;
	    }
		res = ResolverEquacao(str); // resolve a equacao
	
		if(res == true){
			MyIO.println('1');
		} else{
			MyIO.println('0');
		}
		str = MyIO.readLine();
	}

}


}