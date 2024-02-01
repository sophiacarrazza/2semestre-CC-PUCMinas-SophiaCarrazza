class Lista {
	int array[], n;

	Lista(){
		array = new int[6];
		n=0;
	}
	Lista(int tamanho){
		array = new int[tamanho];
		n=0;
	}

	void InserirInicio(int x) {
		if (n>= array.Length){
			Environment.Exit(0); //como se fosse um break
		}
	for (int i=n; i>0; i--){ //leva os elementos pro fim do array
		array[i] = array[i-1];
	}
	array[0] = x;
	n++;
	}
	void InserirFim(int x) {
		if (n>= array.Length){
			Environment.Exit(0); 
		}
	array[n] = x;
	n++;
	}
	void Inserir(int x, int pos) {
		if (n>= array.Length || pos<0 || pos>n){
			Environment.Exit(0); 
		}		
		for (int i=n; i>0; i--){ 
		array[i] = array[i-1];
		}
		array[pos]=x;
		n++;	
	}
	void RemoverInicio(int x) {
		if (n == 0){
			Environment.Exit(0); 
		}
	int novo = array[0];
	n--;
	for (int i=0; i<n; i++){ //leva os elementos pro inicio do array
		array[i] = array[i+1];
	}
	return novo;
	}
	void RemoverFim(int x) {
		if (n==0){
			Environment.Exit(0); 
		}
	return array[--n]; //retorna o array ja com menos um espaco (tirou o ultimo)
	}
	void Inserir(int pos) { //nao precisa inserir o x, pois ele ja esta la, so precisa informar sua posicao p retiralo

		if (n==0 || pos<0 || pos>=n){ //se pos=n, vao ter n-1 elementos disponiveis
			Environment.Exit(0); 
		}
		int novo = array[pos];
		n--;
		
		for (int i=pos; i<n; i++){ 
		array[i] = array[i+1];
		}
		return novo;	
	}
	void Mostrar(){
	Console.Write("[");
		for (int i=0; i<n; i++){ 
		Console.Write(array[i] + " ");
		}
	Console.Write("]");	
	}

}