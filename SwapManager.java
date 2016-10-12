/**
 * Created by Admin on 07.04.2016.
 */
public class SwapManager {

    private int bufferSize;
    private int blockSize;

    public SwapManager(int blockSize, int bufferSize)
    {
        this.blockSize = blockSize;
        this.bufferSize = bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public byte[] swapBytes(byte[] source) throws Exception
    {
        //3142
        if (source.length != bufferSize) throw new Exception("Only allowed arrays with the size of " + bufferSize);

        byte[] destination = new byte[bufferSize];
        byte[] ar1 = new byte[blockSize];
        byte[] ar2 = new byte[blockSize];
        byte[] ar3 = new byte[blockSize];
        byte[] ar4 = new byte[blockSize];

        int k = 0;

        for (int i = 0; i < blockSize; i++)
        {
            ar3[i] = source[k];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            ar1[i] = source[k];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            ar4[i] = source[k];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            ar2[i] = source[k];
            k++;
        }

        k = 0;

        for (int i = 0; i < blockSize; i++)
        {
            destination[k] = ar1[i];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            destination[k] = ar2[i];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            destination[k] = ar3[i];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            destination[k] = ar4[i];
            k++;
        }

        return destination;
    }

    public byte[] unswapBytes(byte[] source) throws Exception
    {
        if (source.length != bufferSize) throw new Exception("Only allowed arrays with the size of 64");

        //3142 - 1234

        byte[] destination = new byte[bufferSize];
        byte[] ar1 = new byte[blockSize];
        byte[] ar2 = new byte[blockSize];
        byte[] ar3 = new byte[blockSize];
        byte[] ar4 = new byte[blockSize];

        int k = 0;

        for (int i = 0; i < blockSize; i++)
        {
            ar3[i] = source[k];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            ar1[i] = source[k];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            ar4[i] = source[k];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            ar2[i] = source[k];
            k++;
        }

        k = 0;

        for (int i = 0; i < blockSize; i++)
        {
            destination[k] = ar4[i];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            destination[k] = ar3[i];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            destination[k] = ar2[i];
            k++;
        }

        for (int i = 0; i < blockSize; i++)
        {
            destination[k] = ar1[i];
            k++;
        }

        return destination;
    }

}
