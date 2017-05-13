import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {

    private static final long NANOSEC_TO_MILLISEC = 1000000;
    private static final int ITERATIONS_FOR_CONTAINS = 70000;
    private static final int ITERATIONS_FOR_CONTAINS_LINKED_LIST = 7000;

    private SimpleSetPerformanceAnalyzer() {
    }

    /**
     * adds all elements from data to set and checks the time it took
     *
     * @param set  the set to add the data into
     * @param data list of strings to add into set
     * @return the time it took to add all data (in milliseconds)
     */
    private static long addToSet(SimpleSet set, String[] data) {
        long start, end;
        start = System.nanoTime();
        for (String word : data) {
            set.add(word);
        }
        end = System.nanoTime();
        return end - start;
    }

    /**
     * prints out the time in milliseconds it took to add all elements from data1.txt into given set
     *
     * @param set the set to add elements to
     */
    private static void addData1ToSet(SimpleSet set) {
        String[] data = Ex3Utils.file2array("data1.txt");
        long timeElapsed = addToSet(set, data);
        System.out.println("Time to add elements from data1 to " + set + ": " +
                (timeElapsed / NANOSEC_TO_MILLISEC) + " ms");
    }

    /**
     * prints out the time in milliseconds it took to add all elements from data2.txt into given set
     *
     * @param set the set to add elements to
     */
    private static void addData2ToSet(SimpleSet set) {
        String[] data = Ex3Utils.file2array("data2.txt");
        long timeElapsed = addToSet(set, data);
        System.out.println("Time to add elements from data2 to " + set + ": " +
                (timeElapsed / NANOSEC_TO_MILLISEC) + " ms");
    }

    /**
     * iterates the contains method numOfIterations times
     *
     * @param set             the set to search in
     * @param word            the word to search for in set
     * @param numOfIterations number of times to apply contains method
     * @return the time it took for all iterations (in nanoseconds)
     */
    private static long iterateContains(SimpleSet set, String word, int numOfIterations) {
        long start, end;
        start = System.nanoTime();
        for (int i = 0; i < numOfIterations; i++) {
            set.contains(word);
            i++;
        }
        end = System.nanoTime();
        return end - start;
    }

    /**
     * measures the time for the contains method to run and prints it out
     *
     * @param set  the set in which to search for the word
     * @param word the word to search for in set
     */
    private static void measureTimeContains(SimpleSet set, String word) {
        // warm-up time
        iterateContains(set, word, ITERATIONS_FOR_CONTAINS);
        // now to check the real time
        long timeElapsed = iterateContains(set, word, ITERATIONS_FOR_CONTAINS);
        System.out.println("Time to check contains in " +
                set + ", for word ''" + word + "'': " + (timeElapsed / ITERATIONS_FOR_CONTAINS) + " ns");
    }

    /**
     * measures the time for the contains method to run in a LinkedList and prints it out
     *
     * @param set  the set in which to search for the word
     * @param word the word to search for in set
     */
    private static void measureTimeContainsLinkedList(SimpleSet set, String word) {
        long timeElapsed = iterateContains(set, word, ITERATIONS_FOR_CONTAINS_LINKED_LIST);
        System.out.println("Time to check contains in LinkedList): " +
                (timeElapsed / ITERATIONS_FOR_CONTAINS_LINKED_LIST) + "ns");
    }

    /**
     * adds all words from data1 into every set in the array
     *
     * @param sets array of sets
     */
    private static void addData1ToAllSets(SimpleSet[] sets) {
        for (SimpleSet set : sets) {
            addData1ToSet(set);
        }
    }

    /**
     * adds all words from data2 into every set in the array
     *
     * @param sets array of sets
     */
    private static void addData2ToAllSets(SimpleSet[] sets) {
        for (SimpleSet set : sets) {
            addData2ToSet(set);
        }
    }

    /**
     * searches for a value in every set from the array using contains method
     *
     * @param sets      an array of sets to seach each for given word
     * @param searchVal value to search for in each one of the sets
     */
    private static void performContainsOnSets(SimpleSet[] sets, String searchVal) {
        for (SimpleSet set : sets) {
            measureTimeContains(set, searchVal);
        }
    }

    public static void main(String[] args) {
        // create set objects for data1
        OpenHashSet openHashSetData1 = new OpenHashSet();
        ClosedHashSet closedHashSetData1 = new ClosedHashSet();
        CollectionFacadeSet treeSetData1 = new CollectionFacadeSet(new TreeSet<String>());
        CollectionFacadeSet hashSetData1 = new CollectionFacadeSet(new HashSet<String>());
        CollectionFacadeSet linkedListData1 = new CollectionFacadeSet(new LinkedHashSet<String>());
        SimpleSet[] setsForData1 = {openHashSetData1, closedHashSetData1, treeSetData1, hashSetData1};

        // tests on sets with data1
        addData1ToAllSets(setsForData1);
        performContainsOnSets(setsForData1, "hi");
        measureTimeContainsLinkedList(linkedListData1, "hi");
        performContainsOnSets(setsForData1,"-13170890158");
        measureTimeContainsLinkedList(linkedListData1, "-13170890158");

        // create set objects for data2
        OpenHashSet openHashSetData2 = new OpenHashSet();
        ClosedHashSet closedHashSetData2 = new ClosedHashSet();
        CollectionFacadeSet treeSetData2 = new CollectionFacadeSet(new TreeSet<String>());
        CollectionFacadeSet hashSetData2 = new CollectionFacadeSet(new HashSet<String>());
        CollectionFacadeSet linkedListData2 = new CollectionFacadeSet(new LinkedHashSet<String>());
        SimpleSet[] setsForData2 = {openHashSetData2, closedHashSetData2, treeSetData2, hashSetData2};

        // tests on sets with data2
        addData2ToAllSets(setsForData2);
        performContainsOnSets(setsForData2, "23");
        measureTimeContainsLinkedList(linkedListData1, "23");
        performContainsOnSets(setsForData2, "hi");
        measureTimeContainsLinkedList(linkedListData1, "hi");
    }
}
