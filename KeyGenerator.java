import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Admin on 07.04.2016.
 */
public class KeyGenerator {

    private static final int KEY_SIZE = 56;
    private static final int BUFF_SIZE = 64;

    private int modificator = 1;

    protected byte[] generateNextKey()
    {
        byte[] array = new byte[BUFF_SIZE];

        for (int i = 0; i < KEY_SIZE; i++)
        {
            array[i] = (byte) modificator;
        }

        modificator++;
        return array;
    }

    @Test
    public void testKeyGenerator(){
        KeyGenerator generator = new KeyGenerator();
        byte[] key = generator.generateNextKey();
        System.out.println(Arrays.toString(key));
        key = generator.generateNextKey();
        System.out.println(Arrays.toString(key));
        key = generator.generateNextKey();
        System.out.println(Arrays.toString(key));
    }
}
