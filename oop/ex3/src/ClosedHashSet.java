
public class ClosedHashSet extends SimpleHashSet {

    /* fields */
    private String[] hashTable;
    private boolean[] wasAValue;

    /* constructors */
    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        hashTable = new String[INITIAL_CAPACITY];
        wasAValue = new boolean[INITIAL_CAPACITY];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor
     * @param lowerLoadFactor
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        hashTable = new String[INITIAL_CAPACITY];
        wasAValue = new boolean[INITIAL_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored.
     * The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public ClosedHashSet(String[] data){
        wasAValue = new boolean[INITIAL_CAPACITY];
        hashTable = new String[INITIAL_CAPACITY];
        for (String word: data) { add(word); }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if (contains(newValue)) { return false; }
        if ((curNumOfElements + 1.0)/hashTable.length > upperLoadFactor) {
            reHash(2*hashTable.length);
        }
        int hashValue = newValue.hashCode();
        int numOfTries = 0;
        while (true){
            long expression = hashValue + (numOfTries + numOfTries*numOfTries)/2;
            int hashIndex = clamp(expression, hashTable.length);
            if (newValue.equals(hashTable[hashIndex])) { return false; }
            if (hashTable[hashIndex] == null) {
                hashTable[hashIndex] = newValue;
                wasAValue[hashIndex] = true;
                curNumOfElements ++;
                return true;
            }
            numOfTries ++;
        }
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        int numOfTries = 0;
        int hashValue = searchVal.hashCode();
        while (true){
         long expression = hashValue + (numOfTries + numOfTries*numOfTries)/2;
         int hashIndex = clamp(expression, hashTable.length);
         if (searchVal.equals(hashTable[hashIndex])) { return true; }
         if (hashTable[hashIndex] == null && wasAValue[hashIndex] == false) { return false; }
         if (numOfTries > hashTable.length) { return false; }
         numOfTries ++;
        }
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        if (!contains(toDelete)) { return false; }
        if ((curNumOfElements - 1.0)/hashTable.length < lowerLoadFactor) {
            reHash(hashTable.length/2);
        }
        int hashValue = toDelete.hashCode();
        int numOfTries = 0;
        while (true){
            long expression = hashValue + (numOfTries + numOfTries*numOfTries)/2;
            int hashIndex = clamp(expression,hashTable.length);
            if (toDelete.equals(hashTable[hashIndex])) {
                hashTable[hashIndex] = null;
                curNumOfElements--;
                return true;
            }
            numOfTries ++;
            // no need to check if numOfTries > hashTable.length because we already checked if the table
            // contains the string.
        }
    }

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() { return curNumOfElements; }

    /**
     * creates a new hash table with the given new size, the takes al elements in the old table and re-hashes
     * them into the new table.
     * @param newSize size of the new table to create.
     */
    private void reHash(int newSize){
        curNumOfElements = 0;
        String[] oldHashTable = hashTable;
        hashTable = new String[newSize];
        wasAValue = new boolean[newSize];
        for (String element: oldHashTable){
            if (element == null) { continue; }
            add(element);
        }
    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() { return hashTable.length; }
}
