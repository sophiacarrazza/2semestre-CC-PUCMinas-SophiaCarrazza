public class cdcj{

    public static String cifrar(String pala) {
        String temp = "";
        for (int i = 0; i < pala.length(); i++) {
            temp += (char) ((int) pala.charAt(i) + 3);
        }
    return temp;
    }

    public static void main(String[] args) {
    while(true){
    String palavra="";
        palavra = MyIO.readLine();
    if(palavra.charAt(0)=='F' && palavra.charAt(1)=='I' && palavra.charAt(2)=='M'){
        System.exit(0);
        }else{
        palavra = cifrar(palavra);
        MyIO.println(palavra);
        }
      }
    }
}