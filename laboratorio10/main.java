import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un texto: ");
        String input = sc.nextLine();

        byte[] inputBytes = input.getBytes();

        System.out.print("MD5: ");
        byte[] md5 = Digest.getDigest("MD5", inputBytes);
        Digest.imprimirHexa(md5);

        System.out.print("SHA-1: ");
        byte[] sha1 = Digest.getDigest("SHA-1", inputBytes);
        Digest.imprimirHexa(sha1);
    }
}

