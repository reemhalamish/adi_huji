import java.util.Collection;

public class CollectionFacadeSet implements SimpleSet{

    /* fields */
    protected Collection<String> collection;


    /* constructor */
    /**
     * Creates a new facade wrapping the specified collection.
     * @param collectionToWrap The Collection to wrap.
     */
    public CollectionFacadeSet(Collection<String> collectionToWrap) {
        collection = collectionToWrap;
    }

    /* delegating */
    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue  New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue) {
        return !collection.contains(newValue) && collection.add(newValue);
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal){
        return collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String toDelete){
        return collection.remove(toDelete);
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size(){
        return collection.size();
    }

    @Override
    public String toString() {
        return "Facade<" + collection.getClass().getSimpleName()+ ">";
    }
}