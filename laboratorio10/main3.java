import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main3 {
    private static final String ALGORITMO = "RSA/ECB/PKCS1Padding";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Leer mensaje de entrada
        System.out.println("Escriba un mensaje de texto:");
        String mensaje = scanner.nextLine();

        // Generar par de llaves RSA (1024 bits)
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(1024);
        KeyPair par = gen.generateKeyPair();
        PublicKey llavePublica = par.getPublic();
        PrivateKey llavePrivada = par.getPrivate();

        // Guardar llave pública en archivo
        try (FileOutputStream fos = new FileOutputStream("llavePublica.key");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(llavePublica);
        }

        // Guardar llave privada en archivo
        try (FileOutputStream fos = new FileOutputStream("llavePrivada.key");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(llavePrivada);
        }

        // Cifrar el mensaje con la llave pública
        byte[] cifrado = Asimetrico.cifrar(llavePublica, ALGORITMO, mensaje);

        // Guardar el texto cifrado en un archivo
        try (FileOutputStream fos = new FileOutputStream("mensajeCifrado.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(cifrado);
        }

        System.out.println(">> Llave pública (llavePublica.key), llave privada (llavePrivada.key)");
        System.out.println("   y mensaje cifrado (mensajeCifrado.dat) guardados.");
        scanner.close();
    }
}
