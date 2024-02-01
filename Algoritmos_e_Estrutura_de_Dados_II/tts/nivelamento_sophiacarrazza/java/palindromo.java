import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String palavra;

        do {
            palavra = scanner.next();
            boolean resp = true;
            int tam = palavra.length();

            for (int i = 0, j = tam - 1; i < tam / 2 && j >= 0; i++, j--) {
                if (palavra.equals("fim")) {
                    break;
                } else if (palavra.charAt(i) != palavra.charAt(j)) {
                    resp = false;
                    i = tam;
                }
            }

            if (!palavra.equals("fim")) {
                if (resp) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NÃO");
                }
            }
        } while (!palavra.equals("fim"));

        scanner.close();
    }
}