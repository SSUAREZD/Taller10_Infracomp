import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Main {
    private static final String ALGORITMO = "RSA/ECB/PKCS1Padding";

    public static void imprimir(byte[] contenido) {
        for (byte b : contenido) {
            System.out.print((b & 0xFF) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Leer y mostrar texto
        System.out.println("Escriba un mensaje de texto:");
        String mensaje = scanner.nextLine();
        System.out.println("Input en texto plano: " + mensaje);

        byte[] mensajeBytes = mensaje.getBytes("UTF-8");
        System.out.print("Input en bytes[]: ");
        imprimir(mensajeBytes);
        System.out.println();

        // Generar par de llaves RSA 1024 bits
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(1024);
        KeyPair par = gen.generateKeyPair();
        PublicKey llavePublica = par.getPublic();
        PrivateKey llavePrivada = par.getPrivate();

        // 1) Tiempo de cifrado
        long tInicioCif = System.nanoTime();
        byte[] cifrado = Asimetrico.cifrar(llavePublica, ALGORITMO, mensaje);
        long tFinCif = System.nanoTime();
        long tiempoCifrado = tFinCif - tInicioCif;

        System.out.print("Input cifrado en RSA con llaves de 1024 bits en byte[]: ");
        imprimir(cifrado);
        System.out.println("Tiempo de cifrado: " + tiempoCifrado + " ns");
        System.out.println();

        // 2) Tiempo de descifrado
        long tInicioDesc = System.nanoTime();
        byte[] descifrado = Asimetrico.descifrar(llavePrivada, ALGORITMO, cifrado);
        long tFinDesc = System.nanoTime();
        long tiempoDescifrado = tFinDesc - tInicioDesc;

        System.out.print("Input descifrado en byte[]: ");
        imprimir(descifrado);
        System.out.println("Tiempo de descifrado: " + tiempoDescifrado + " ns");
        System.out.println();

        // Convertir a String y mostrar
        System.out.println("Input descifrado convertido a texto plano: " 
                           + new String(descifrado, "UTF-8"));

        scanner.close();
    }
}
