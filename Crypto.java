import java.io.*;

import org.apache.commons.io.*;

/**
 * Created by Admin on 24.03.2016.
 */
public class Crypto {

    public static final int BUFFER_SIZE = 64;

    private String sourceFile;
    private String encryptedFile;
    private String decryptedFile;
    private SwapManager swapManager;
    private KeyManager keyManager;

    public Crypto()
    {
        //Setting default values;
        sourceFile = "SourceText";
        encryptedFile = "Encrypted";
        decryptedFile = "Decrypted";
        this.swapManager = new SwapManager(16, BUFFER_SIZE);
        this.keyManager = new KeyManager();
     }

    public Crypto(String source, String encrypted, String decrypted)
    {
        sourceFile = source;
        encryptedFile = encrypted;
        decryptedFile = decrypted;
    }

    public void setSwapManager(SwapManager swapManager) {
        this.swapManager = swapManager;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void setEncryptedFile(String encryptedFile) {
        this.encryptedFile = encryptedFile;
    }

    public void setDecryptedFile(String decryptedFile) {
        this.decryptedFile = decryptedFile;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public String getEncryptedFile() {
        return encryptedFile;
    }

    public String getDecryptedFile() {
        return decryptedFile;
    }

    public void encrypt(String file1, String file2) throws Exception
    {
        FileInputStream fis = new FileInputStream("src//" + file1);
        FileOutputStream fos = new FileOutputStream("src//" + file2);

        byte[] buffer = new byte[BUFFER_SIZE];
        byte[] reverse = new byte[BUFFER_SIZE];

        while(fis.available() > 0)
        {
            int count = fis.read(buffer);
            if (count == 64)
            {
                reverse = swapManager.swapBytes(buffer);
                reverse = keyManager.addKeys(reverse, count);
            }
            else
            {
                reverse = keyManager.addKeys(buffer, count);
            }

            fos.write(reverse, 0, count);
        }

        fos.close();
        fis.close();
    }

    public void decrypt(String file1, String file2) throws Exception
    {
        FileInputStream fis = new FileInputStream("src//" + file1);
        FileOutputStream fos = new FileOutputStream("src//" + file2);

        byte[] buffer = new byte[BUFFER_SIZE];
        byte[] reverse = new byte[BUFFER_SIZE];


        while (fis.available() > 0)
        {
            int count = fis.read(buffer);
            if (count == 64)
            {
                reverse = swapManager.unswapBytes(buffer);
                reverse = keyManager.removeKeys(reverse, count);
            }
            else
            {
                reverse = keyManager.removeKeys(buffer, count);
            }
            fos.write(reverse, 0, count);
        }
        fos.close();
        fis.close();
    }

    public boolean checkEquality(String fileName1, String fileName2) throws Exception
    {
        File file1 = new File("src//" + fileName1);
        File file2 = new File("src//" + fileName2);
        boolean isTwoEqual = FileUtils.contentEquals(file1, file2);
        return isTwoEqual;
    }
}
