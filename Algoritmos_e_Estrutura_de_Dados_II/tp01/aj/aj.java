import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
class aj {
static public void main (String args[]){
	try{
	Scanner ler = new Scanner(System.in);
	RandomAccessFile arqw = new RandomAccessFile("arq.txt", "rw");
	
	int num = ler.nextInt();
	ler.nextLine();
	for(int i=0; i<num; i++){
		String strf = ler.next();
		float numf = Float.parseFloat(strf);
	    arqw.writeFloat(numf);
	}
	arqw.close();
	RandomAccessFile arqr = new RandomAccessFile("arq.txt", "r");
	
	long tam = arqr.length();
	arqr.seek(tam-4); //move o ponteiro para o final do conteudo do arquivo
	
	while (tam>=4){
		float resp = arqr.readFloat();
		MyIO.println(resp);
		tam -= 4; //tamanho do float=4 bytes
		if(tam>=4){
		arqr.seek(tam-4);
		}
	}
	arqr.close();
	} catch (IOException erro){
		erro.printStackTrace();
	}
}


}