import java.util.LinkedList;


public class LinkedListWrapper {

    /* fields */
    private LinkedList<String> wrappedLinkedList;

    /**
     * constructor
     */
    public LinkedListWrapper (){
        wrappedLinkedList = new LinkedList<String>();
    }

    /** delegating */
    public boolean contains(String searchVal) { return wrappedLinkedList.contains(searchVal); }

    /** delegating */
    public void add(String newVal) {
        wrappedLinkedList.add(newVal);
    }

    /** delegating */
    public void delete(String toDelete) {
        wrappedLinkedList.remove(toDelete);
    }

    public int size() { return wrappedLinkedList.size(); }

    public String getElement(int i) { return wrappedLinkedList.get(i); }

    public Iterable<String> getList() { return wrappedLinkedList; }
}
