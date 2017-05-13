
public abstract class SimpleHashSet implements SimpleSet{

    /* constants */
    private static final float DEFAULT_UPPER_LOAD_FACTOR = 0.75f;
    private static final float DEFAULT_LOWER_LOAD_FACTOR = 0.25f;
    protected static final int INITIAL_CAPACITY = 16;

    /* fields */
    protected float upperLoadFactor, lowerLoadFactor;
    protected int curNumOfElements = 0;

    /**
     * A default constructor.
     */
    protected SimpleHashSet (){
        upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
        lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    /**
     * fits a given expression into the table size
     * @param expression the expression to fit into the table size
     * @return a fitted index in the valid range of table indices
     */
    protected int clamp(long expression, int tableSize){
        return (int) expression&(tableSize - 1);
    }

    protected float getDefaultUpperLoadFactor(){
        return this.upperLoadFactor;
    }

    protected float getDefaultLowerLoadFactor(){
        return this.lowerLoadFactor;
    }

    public abstract boolean add(String newValue);
    public abstract boolean contains(String searchVal);
    public abstract boolean delete(String toDelete);
    public abstract int size();
}