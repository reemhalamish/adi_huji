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
        Filter returnFilter;

        try {
            switch (filterName) {
                case "greater_than":
                    returnFilter = new GreaterThanFilter(splitFilterLine[1]);
                    break;
                case "between":
                    returnFilter = new BetweenFilter(splitFilterLine[1], splitFilterLine[2]);
                    break;
                case "smaller_than":
                    returnFilter = new SmallerThanFilter(splitFilterLine[1]);
                    break;
                case "file":
                    returnFilter = new FileNameFilter(splitFilterLine[1]);
                    break;
                case "contains":
                    returnFilter = new ContainsFilter(splitFilterLine[1]);
                    break;
                case "prefix":
                    returnFilter = new PrefixFilter(splitFilterLine[1]);
                    break;
                case "suffix":
                    returnFilter = new SuffixFilter(splitFilterLine[1]);
                    break;
                case "writable":
                    returnFilter = new WritableFilter(splitFilterLine[1]);
                    break;
                case "executable":
                    returnFilter = new ExecutableFilter(splitFilterLine[1]);
                    break;
                case "hidden":
                    returnFilter = new HiddenFilter(splitFilterLine[1]);
                    break;
                case "all":
                    returnFilter = new AllFilter();
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
