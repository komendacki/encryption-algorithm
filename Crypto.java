import java.io.*;
import org.apache.commons.io.*;

/**
 * Created by Admin on 24.03.2016.
 */
public class Crypto {

    public static final int BUFFER_SIZE = 64;
    public static final int BLOCK_SIZE = 16;
    public static final int KEY = 7;

    public static void main(String[] args) throws Exception
    {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Set source file:");
//        String fileName1 = reader.readLine();
//        System.out.println("Set file for storing Encrypted data:");
//        String fileName2 = reader.readLine();
//        System.out.println("Set file for Decryption:");
//        String fileName3 = reader.readLine();
//        reader.close();

        String fileName1, fileName2, fileName3;
        fileName1 = "SourceText";
        fileName2 = "Encrypted";
        fileName3 = "Decrypted";

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

        byte[] buffer = new byte[BUFFER_SIZE];
        byte[] reverse = new byte[BUFFER_SIZE];

        while(fis.available() > 0)
        {
            int count = fis.read(buffer);
            if (count == 64)
            {
                reverse = swapBytes(buffer);
                reverse = addKeys(reverse, count);
            }
            else
            {
                reverse = addKeys(buffer, count);
            }

//            int k = count - 1;
//            for (int j = 0; j < count; j++)
//            {
//                byte a = buffer[k];
//                a += (byte) KEY;
//                reverse[j] = a;
//                k--;
//            }
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

        byte[] buffer = new byte[BUFFER_SIZE];
        byte[] reverse = new byte[BUFFER_SIZE];

        while (fis.available() > 0)
        {
            int count = fis.read(buffer);
            if (count == 64)
            {
                reverse = unswapBytes(buffer);
                reverse = removeKeys(reverse, count);
            }
            else
            {
                reverse = removeKeys(buffer, count);
            }
//            int k = count - 1;
//
//            for (int j = 0; j < count; j++)
//            {
//                byte a = buffer[k];
//                a -= (byte) KEY;
//                reverse[j] = a;
//                k--;
//            }
            fos.write(reverse, 0, count);
        }

        System.out.println("Decrypted successfully!");
        fos.close();
        fis.close();
    }

    public static byte[] swapBytes(byte[] source) throws Exception
    {
        //3142
        if (source.length != BUFFER_SIZE) throw new Exception("Only allowed arrays with the size of 64");

        byte[] destination = new byte[BUFFER_SIZE];
        byte[] ar1 = new byte[BLOCK_SIZE];
        byte[] ar2 = new byte[BLOCK_SIZE];
        byte[] ar3 = new byte[BLOCK_SIZE];
        byte[] ar4 = new byte[BLOCK_SIZE];

        int k = 0;

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            ar3[i] = source[k];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            ar1[i] = source[k];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            ar4[i] = source[k];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            ar2[i] = source[k];
            k++;
        }

        k = 0;

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            destination[k] = ar1[i];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            destination[k] = ar2[i];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            destination[k] = ar3[i];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            destination[k] = ar4[i];
            k++;
        }

        return destination;
    }

    public static byte[] unswapBytes(byte[] source) throws Exception
    {
        if (source.length != BUFFER_SIZE) throw new Exception("Only allowed arrays with the size of 64");

        //3142 - 1234

        byte[] destination = new byte[BUFFER_SIZE];
        byte[] ar1 = new byte[BLOCK_SIZE];
        byte[] ar2 = new byte[BLOCK_SIZE];
        byte[] ar3 = new byte[BLOCK_SIZE];
        byte[] ar4 = new byte[BLOCK_SIZE];

        int k = 0;

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            ar3[i] = source[k];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            ar1[i] = source[k];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            ar4[i] = source[k];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            ar2[i] = source[k];
            k++;
        }

        k = 0;

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            destination[k] = ar4[i];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            destination[k] = ar3[i];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            destination[k] = ar2[i];
            k++;
        }

        for (int i = 0; i < BLOCK_SIZE; i++)
        {
            destination[k] = ar1[i];
            k++;
        }

        return destination;
    }

    public static byte[] removeKeys(byte[] source, int count)
    {
        byte[] destination = new byte[source.length];

        for (int i = 0; i < count; i++)
        {
            destination[i] = (byte)(source[i] - KEY);
        }

        return destination;
    }

    public static byte[] addKeys(byte[] source, int count)
    {
        byte[] destination = new byte[source.length];

        for (int i = 0; i < count; i++)
        {
            destination[i] = (byte)(source[i] + KEY);
        }

        return destination;
    }

    public static boolean checkEquality(String fileName1, String fileName2) throws Exception
    {
        File file1 = new File("src//" + fileName1);
        File file2 = new File("src//" + fileName2);
        boolean isTwoEqual = FileUtils.contentEquals(file1, file2);
        return isTwoEqual;
    }
}
