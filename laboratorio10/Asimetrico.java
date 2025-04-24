// Asimetrico.java
import java.security.Key;
import javax.crypto.Cipher;

public class Asimetrico {

    public static byte[] cifrar(Key llave, String algoritmo, String texto) {
        try {
            // algoritmo debe ser "RSA/ECB/PKCS1Padding"
            Cipher cifrador = Cipher.getInstance(algoritmo);
            byte[] textoClaro = texto.getBytes("UTF-8");
            cifrador.init(Cipher.ENCRYPT_MODE, llave);
            return cifrador.doFinal(textoClaro);
        } catch (Exception e) {
            System.err.println("Excepción cifrar(): " + e.getMessage());
            return null;
        }
    }

    public static byte[] descifrar(Key llave, String algoritmo, byte[] texto) {
        try {
            Cipher cifrador = Cipher.getInstance(algoritmo);
            cifrador.init(Cipher.DECRYPT_MODE, llave);
            return cifrador.doFinal(texto);
        } catch (Exception e) {
            System.err.println("Excepción descifrar(): " + e.getMessage());
            return null;
        }
    }
}
