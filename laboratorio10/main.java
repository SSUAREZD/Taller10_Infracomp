
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class main {
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
            
            byte[] mensajeCifrado = Simetrico.cifrar(secretKey, mensaje);
            imprimir(mensajeCifrado);
            System.out.println("/ln");

            byte[] mensajeDescifrado = Simetrico.descifrar(secretKey, mensajeCifrado);
            imprimir(mensajeDescifrado);
            System.out.println("/ln");
            String mensajeDescifradoString = new String(mensajeDescifrado);
            System.out.println("El mensaje descifrado es: " + mensajeDescifradoString);
        }
        catch (Exception e) {
            System.out.println("Error al generar la llave: " + e.getMessage());
        }
    }
}
