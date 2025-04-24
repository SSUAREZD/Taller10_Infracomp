import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Main4 {
    private static final String ALGORITMO = "RSA/ECB/PKCS1Padding";

    public static void imprimir(byte[] contenido) {
        for (byte b : contenido) {
            System.out.print((b & 0xFF) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        // Recuperar llave pública
        FileInputStream fisPub = new FileInputStream("llavePublica.key");
        ObjectInputStream oisPub = new ObjectInputStream(fisPub);
        PublicKey llavePublica = (PublicKey) oisPub.readObject();
        oisPub.close();

        // Recuperar llave privada
        FileInputStream fisPriv = new FileInputStream("llavePrivada.key");
        ObjectInputStream oisPriv = new ObjectInputStream(fisPriv);
        PrivateKey llavePrivada = (PrivateKey) oisPriv.readObject();
        oisPriv.close();

        // Recuperar mensaje cifrado
        FileInputStream fisMsg = new FileInputStream("mensajeCifrado.dat");
        ObjectInputStream oisMsg = new ObjectInputStream(fisMsg);
        byte[] mensajeCifrado = (byte[]) oisMsg.readObject();
        oisMsg.close();

        // Mostrar ciphertext
        System.out.print("Mensaje cifrado (byte[]): ");
        imprimir(mensajeCifrado);
        System.out.println();

        // Descifrar con la llave privada
        byte[] descifrado = Asimetrico.descifrar(llavePrivada, ALGORITMO, mensajeCifrado);

        // Mostrar resultado
        System.out.print("Mensaje descifrado (byte[]): ");
        imprimir(descifrado);
        System.out.println("Mensaje descifrado a texto plano: " 
                           + new String(descifrado, "UTF-8"));
    }
}
