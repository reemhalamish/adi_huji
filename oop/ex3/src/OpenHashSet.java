
public class OpenHashSet extends SimpleHashSet {

    /* fields */
    private LinkedListWrapper[] hashTable;

    /* constructors */
    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        hashTable = new LinkedListWrapper[INITIAL_CAPACITY];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        hashTable = new LinkedListWrapper[INITIAL_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data an array of strings to put in the hash set
     */
    public OpenHashSet(String[] data) {
        hashTable = new LinkedListWrapper[INITIAL_CAPACITY];
        for ( String word: data) {
            add(word);
        }
    }

    /* methods */
    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue  New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        int hashValue = newValue.hashCode();
        int hashIndex = clamp(hashValue, hashTable.length); // cleans the hash to fit in table

        // checks if newValue already in table
        if (hashTable[hashIndex] != null && hashTable[hashIndex].contains(newValue)) { return false; }

        // if re-hashing needed, re-hashes all existing elements, and calculate the current element's new hashIndex
        if ((curNumOfElements + 1.0)/hashTable.length > upperLoadFactor ){
            reHash(2*hashTable.length);
            // recalculates the new hashIndex for newValue to fit in the new tableSize
            hashIndex = clamp(hashValue, hashTable.length);
        }

        // if cell is still null, create a new linkedList in cell
        if (hashTable[hashIndex] == null) { hashTable[hashIndex] = new LinkedListWrapper(); }

        // finally, adds the element.
        hashTable[hashIndex].add(newValue);
        curNumOfElements += 1;
        return true;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        int hashValue = toDelete.hashCode(

        );
        int hashIndex = clamp(hashValue, hashTable.length); // cleans the hash to fit in table
        if (hashTable[hashIndex] != null && hashTable[hashIndex].contains(toDelete)) {
            if ((curNumOfElements - 1.0)/hashTable.length < lowerLoadFactor) {
                reHash(hashTable.length / 2);
                hashIndex = clamp(hashValue, hashTable.length);
            }
            hashTable[hashIndex].delete(toDelete);
            curNumOfElements -= 1;
            return true;
        }
        return false;
    }

    /**
     *
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return curNumOfElements;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        int hashValue = searchVal.hashCode();
        int hashIndex = clamp(hashValue, hashTable.length);
        if (hashTable[hashIndex] != null && hashTable[hashIndex].contains(searchVal)) { return true; }
        return false;
    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return hashTable.length;
    }

    /**
     * creates a new hash table with the given new size, the takes al elements in the old table and re-hashes
     * them into the new table.
     * @param newSize size of the new table to create.
     */
    private void reHash(int newSize){
        curNumOfElements = 0;
        LinkedListWrapper[] oldHashTable = hashTable;
        hashTable = new LinkedListWrapper[newSize];
        for (LinkedListWrapper cell: oldHashTable){
            if (cell == null) { continue; }
            for(String element: cell.getList()) { add(element); }
        }
    }
}