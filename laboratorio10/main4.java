import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class main4 {    private final static String ALGORITMO = "AES";
    
    public static void imprimir(byte[] contenido) {
        int i = 0;
        for( ; i < contenido.length; i++) {
            System.out.print(contenido[i] + " ");
        }
    }
    
    
    public static void main(String[] args) {
        System.out.println("Decripción de mensaje cifrado simetrico");
        try{

            FileInputStream archivo = new FileInputStream("archivoEncriptado.txt");
            ObjectInputStream ois = new ObjectInputStream(archivo);
            SecretKey llave = (SecretKey) ois.readObject();
            System.out.println("Llave: "+ llave);
            System.out.println();

            byte[] mensajeCifrado = (byte[]) ois.readObject();
            ois.close();
            byte[] mensajeDescifrado = Simetrico.descifrar(llave, mensajeCifrado);
            String mensajeDescifradoString = new String(mensajeDescifrado);
            System.out.println("El mensaje descifrado es: " + mensajeDescifradoString);

        }
        catch (Exception e) {
            System.out.println("Error al generar la llave: " + e.getMessage());
        }
    }
    
}
