import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Main2 {
    private static final String ALGORITMO = "RSA/ECB/PKCS1Padding";

    public static void imprimir(byte[] contenido) {
        for (byte b : contenido) {
            System.out.print((b & 0xFF) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Leer mensaje
        System.out.println("Escriba un mensaje de texto:");
        String mensaje = scanner.nextLine();
        System.out.println("Input en texto plano: " + mensaje);
        byte[] mensajeBytes = mensaje.getBytes("UTF-8");
        System.out.print("Input en bytes[]: ");
        imprimir(mensajeBytes);
        System.out.println();

        // Generar par de llaves RSA 1024
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(1024);
        KeyPair par = gen.generateKeyPair();
        PublicKey pub = par.getPublic();
        PrivateKey priv = par.getPrivate();

        // Cifrar con la llave PRIVADA
        byte[] cifradoPriv = Asimetrico.cifrar(priv, ALGORITMO, mensaje);
        System.out.print("Cifrado con llave privada (bytes[]): ");
        imprimir(cifradoPriv);
        System.out.println();

        // Descifrar con la llave PÚBLICA
        byte[] descifradoPub = Asimetrico.descifrar(pub, ALGORITMO, cifradoPriv);
        System.out.print("Descifrado con llave pública (bytes[]): ");
        imprimir(descifradoPub);
        System.out.println();

        // Reconstruir texto y mostrar
        String textoFinal = new String(descifradoPub, "UTF-8");
        System.out.println("Texto recuperado: " + textoFinal);

        scanner.close();
    }
}
