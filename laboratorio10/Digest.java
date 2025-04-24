import java.security.MessageDigest;

public class Digest {
    
    public static byte[] getDigest(String algorithm, byte[] input) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        return md.digest(input);
    }

    public static void imprimirHexa(byte[] digest) {
        for (byte b : digest) {
            System.out.format("%02x", b);
        }
        System.out.println();
    }

    public static boolean verificar(byte[] d1, byte[] d2) {
        if (d1.length != d2.length) return false;
        for (int i = 0; i < d1.length; i++) {
            if (d1[i] != d2[i]) return false;
        }
        return true;
    }

    public static byte[] getDigestFile(String algorithm, String filename) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        java.io.FileInputStream fis = new java.io.FileInputStream(filename);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            md.update(buffer, 0, bytesRead);
        }
        fis.close();
        return md.digest();
    }
}
