/**
 * Created by Admin on 07.04.2016.
 */
public class Main {

    public static void main(String[] args) throws Exception
    {
        Crypto encrypter = new Crypto();

        String sourceFile = encrypter.getSourceFile();
        String encryptedFile = encrypter.getEncryptedFile();
        String decryptionFile = encrypter.getDecryptedFile();

        encrypter.encrypt(sourceFile, encryptedFile);
        System.out.println("Encrypted successfully!");
        encrypter.decrypt(encryptedFile, decryptionFile);
        System.out.println("Decrypted successfully!");
        if (encrypter.checkEquality(sourceFile, decryptionFile)) System.out.println("Decryption is done correctly!");
        else System.out.println("Your algorithm is broken!");


    }
}
