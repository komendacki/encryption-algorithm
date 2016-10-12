import java.util.ArrayList;

/**
 * Created by Admin on 07.04.2016.
 */
public class KeyManager {

    public static final int KEY = 7;

    private ArrayList<byte[]> keyList;

    public KeyManager()
    {
        keyList = new ArrayList<>();
    }

    public byte[] removeKeys(byte[] source, int count)
    {
        byte[] destination = new byte[source.length];

        for (int i = 0; i < count; i++)
        {
            destination[i] = (byte)(source[i] - KEY);
            //destination[i] = (byte)(source[i] - KEYS[i]);
            //if (lol2 == 0)System.out.println("Removing key[" + i + "]: " + "destination = " + destination[i] + " = " + "source " + source[i] + "- key " + KEYS[i]);
        }

        return destination;
    }

    public byte[] addKeys(byte[] source, int count)
    {
        byte[] destination = new byte[source.length];

        for (int i = 0; i < count; i++)
        {
            destination[i] = (byte)(source[i] + KEY);
            //destination[i] = (byte) (source[i] + KEYS[i]);
            //if (lol1 == 0) System.out.println("Adding key[" + i + "]: " + "destination = " + destination[i] + " = " + "source " + source[i] + "+ key " + KEYS[i]);
        }

        return destination;
    }
}
