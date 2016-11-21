/**
Statable implementation.

@param <T> Element type.
*/
public class StatableArray<T> extends SimpleArray<T> implements Statable {

    private int numReads;
    private int numWrites;

    /**
     * array that tracks reads and writes.
     * @param n the length of the array
     * @param t the type of object to initialize
     * @throws LengthException if <= 0
     */
    public StatableArray(int n, T t) throws LengthException {
        super(n, t);
    }

    @Override
    public void resetStatistics() {
        this.numReads = 0;
        this.numWrites = 0;

    }

    @Override
    public int numberOfReads() {
        return this.numReads;
    }

    @Override
    public int numberOfWrites() {
        return this.numWrites;

    }

    @Override
    public T get(int i) throws IndexException {
        this.numReads++;
        try {
            return super.get(i);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexException();
        }
    }

    @Override
    public void put(int i, T t) throws IndexException {
        this.numWrites++;
        try {
            super.put(i, t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexException();
        }
    }

    @Override
    public int length() {
        this.numReads++;
        return super.length();
    }

}
