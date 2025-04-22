import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class main2 {
    
    private final static String ALGORITMO = "AES";
    
    public static void imprimir(byte[] contenido) {
        int i = 0;
        for( ; i < contenido.length; i++) {
            System.out.print(contenido[i] + " ");
        }
    }
    public static void main(String[] args){

        System.out.println("Escriba mensaje a cifrar:");
        String mensaje = System.console().readLine();
        System.out.println("El mensaje es: "+ mensaje);
        
        byte[] mensajeBytes = mensaje.getBytes();
        imprimir(mensajeBytes);
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITMO);
            SecretKey k1 = keyGen.generateKey();
            SecretKey k2 = keyGen.generateKey();
            
            //Se pone como comentario el cifrado con k2 ya que no corre cuando se descifra con llave incorrecta

            byte[] tc1 = Simetrico.cifrar(k1, mensaje);

            byte[] tc1DescifradoK1 = Simetrico.descifrar(k1, tc1);
            //byte[] tc1DescifradoK2 = Simetrico.descifrar(k2, tc1);

            String tc1DescifradoStringK1 = new String(tc1DescifradoK1);
            //String tc1DescifradoStringK2 = new String(tc1DescifradoK2);

            System.out.println("Tc1 con k1 es: " + tc1DescifradoStringK1);
            //System.out.println("Tc1 con k2 es: " + tc1DescifradoStringK2);

        }
        catch (Exception e) {
            System.out.println("Error al generar la llave: " + e.getMessage());
        }
    }
}
