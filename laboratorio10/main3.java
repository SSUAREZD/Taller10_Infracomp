import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class main3 {


    private final static String ALGORITMO = "AES";
    
    public static void imprimir(byte[] contenido) {
        int i = 0;
        for( ; i < contenido.length; i++) {
            System.out.print(contenido[i] + " ");
        }
    }
    
    
    public static void main(String[] args) {
        System.out.println("Escriba mensaje a cifrar:");
        String mensaje = System.console().readLine();
        System.out.println("El mensaje es: "+ mensaje);
        
        byte[] mensajeBytes = mensaje.getBytes();
        imprimir(mensajeBytes);
        
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITMO);
            SecretKey secretKey = keyGen.generateKey();

            FileOutputStream archivo = new FileOutputStream("archivoEncriptado.txt");
            ObjectOutputStream oos = new ObjectOutputStream(archivo);
            oos.writeObject(secretKey);
            System.out.println("Llave: "+secretKey);
            System.out.println();

            byte[] mensajeCifrado = Simetrico.cifrar(secretKey, mensaje);
            imprimir(mensajeCifrado);
            oos.writeObject(mensajeCifrado);
        }
        catch (Exception e) {
            System.out.println("Error al generar la llave: " + e.getMessage());
        }
    }
}
    

