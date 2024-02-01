import java.util.ArrayList;
using System;
using System.Collections;


class Fila{
	ArrayList = arrayListr;
	public Fila(){
		arrayList = new ArrayList();
	}
	public void Inserir(int valor){
	arrayList.Add(valor);
	}
	public int Remover(){
	int resp = (int)arrayList[0];
	arrayList.RemoveAt(0);
	return resp;
	}
	public void Mostrar(){
	foreach(int i in arrayList){
	Console.WriteLine(i);
	}
	}
}

class ExComp01{
	public static voidmain(String args[]){
	Fila fila = new Fila();
	for(int i=0; i<10; i++){
	fila.Inserir(i);
	}
	Console.WriteLine(fila.Remover());
	fila.Inserir(10);
        Console.WriteLine(fila.Remover());
	fila.Inserir(11);
	Console.WriteLine(fila.Remover());
	fila.Inserir(12);

	Console.WriteLin("======= Mostrar");
	fila.Mostrar();
	}



}