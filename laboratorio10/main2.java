import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo: ");
        String filename = sc.nextLine();

        System.out.print("Digest MD5: ");
        byte[] md5 = Digest.getDigestFile("MD5", filename);
        Digest.imprimirHexa(md5);

        System.out.print("Digest SHA-1: ");
        byte[] sha1 = Digest.getDigestFile("SHA-1", filename);
        Digest.imprimirHexa(sha1);
    }
}
