package reem_stuff;

import exceptions.FilterException;
import filters.filters.Filter;

/**
 * Created by reem on 31/05/17.
 */
public class FactoryExample {
    private static FactoryExample ourInstance = new FactoryExample();

    public static FactoryExample getInstance() {
        return ourInstance;
    }

    private FactoryExample() {}

    public Filter getFilter(String line, int lineNumber) throws FilterException {
        return null;
    }
}
