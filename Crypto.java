import java.io.*;
import org.apache.commons.io.*;

/**
 * Created by Admin on 24.03.2016.
 */
public class Crypto {

    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Set source file:");
        String fileName1 = reader.readLine();
        System.out.println("Set file for storing Encrypted data:");
        String fileName2 = reader.readLine();
        System.out.println("Set file for Decryption:");
        String fileName3 = reader.readLine();
        reader.close();

        encrypt(fileName1, fileName2);
        decrypt(fileName2, fileName3);
        if (checkEquality(fileName1, fileName3))
        {
            System.out.println("Source file and decrypted file are equal");
        }
        else
        {
            System.out.println("Source file and decrypted file are different");
        }

    }

    public static void encrypt(String file1, String file2) throws Exception
    {
        FileInputStream fis = new FileInputStream("src//" + file1);
        FileOutputStream fos = new FileOutputStream("src//" + file2);

        byte[] buffer = new byte[64];
        byte[] reverse = new byte[64];

        while(fis.available() > 0)
        {
            int count = fis.read(buffer);
            int k = count - 1;
            for (int j = 0; j < count; j++)
            {
                byte a = buffer[k];
                a += (byte) 7;
                reverse[j] = a;
                k--;
            }
            fos.write(reverse, 0, count);
        }

        System.out.println("Encrypted successfully!");
        fos.close();
        fis.close();
    }

    public static void decrypt(String file1, String file2) throws Exception
    {
        FileInputStream fis = new FileInputStream("src//" + file1);
        FileOutputStream fos = new FileOutputStream("src//" + file2);

        byte[] buffer = new byte[64];
        byte[] reverse = new byte[64];

        while (fis.available() > 0)
        {
            int count = fis.read(buffer);
            int k = count - 1;

            for (int j = 0; j < count; j++)
            {
                byte a = buffer[k];
                a -= (byte) 7;
                reverse[j] = a;
                k--;
            }
            fos.write(reverse, 0, count);
        }

        System.out.println("Decrypted successfully!");
        fos.close();
        fis.close();
    }

    public static boolean checkEquality(String fileName1, String fileName2) throws Exception
    {
        File file1 = new File("src//" + fileName1);
        File file2 = new File("src//" + fileName2);
        boolean isTwoEqual = FileUtils.contentEquals(file1, file2);
        return isTwoEqual;
    }
}
