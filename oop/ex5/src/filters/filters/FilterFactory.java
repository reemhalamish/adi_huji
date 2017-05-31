package filters.filters;

import exceptions.FilterException;

/**
 * Created by reem on 31/05/17.
 */
public class FilterFactory {

    // TODO: 31/05/17  factory calls filter constructors, so erase public from all onstructors.
    // implement case for all filters and change arguments to strings in constructors. do catch try for exceptions.
    // magic numers here

    public static Filter createFilter (String filterLine) throws FilterException {
        String[] splitFilterLine = filterLine.split("#");
        if (splitFilterLine.length == 0) { throw new FilterException(); } // line is empty
        boolean isNot = splitFilterLine[splitFilterLine.length - 1].equals("NOT");
        String filterName = splitFilterLine[0];
        Filter returnFilter = null;

        try {
            switch (filterName) {
                case "greater_than":
                    returnFilter = new GreaterThanFilter(splitFilterLine[1]);
                    break;
                case "between":
                    returnFilter = new BetweenFilter(splitFilterLine[1], splitFilterLine[2]);
                    break;
                default:
                    throw new FilterException();
            }

            if (isNot) { returnFilter = new NotFilter(returnFilter); }

            return returnFilter;

        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new FilterException();
        }
    }

    public static Filter createDefaultFilter() {
        return new AllFilter();
    }
}
