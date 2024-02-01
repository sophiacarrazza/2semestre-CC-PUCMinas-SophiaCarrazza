class copiaArq{
	public static void main (String[] args) {
		Arq.openRead("arquivo.txt");
		
		String ler = "";
		while(Arq.hasNext()==true) {
			ler += Arq.readChar();
		}
		Arq.close();
		
		Arq.openWrite("copia.txt");
		Arq.print(ler);
		Arq.close();
	}
}