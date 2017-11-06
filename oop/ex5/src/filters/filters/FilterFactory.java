package filters.filters;

import exceptions.FilterException;


public class FilterFactory {
    private static final int INDEX_FIRST_VALUE = 1;
    private static final int INDEX_SECOND_VALUE = 2;
    private static final int INDEX_FILTER_NAME = 0;


    public static Filter createFilter (String filterLine) throws FilterException {
        String[] splitFilterLine = filterLine.split("#");
        if (splitFilterLine.length == 0) {
            // line is empty
            throw new FilterException();
        }

        // NOT condition should appear at the end of the filer line.
        boolean isNot = splitFilterLine[splitFilterLine.length - 1].equals("NOT");

        String filterName = splitFilterLine[INDEX_FILTER_NAME];
        Filter returnFilter;

        try {
            switch (filterName) {
                case "greater_than":
                    returnFilter = new GreaterThanFilter(splitFilterLine[INDEX_FIRST_VALUE]);
                    break;
                case "between":
                    returnFilter = new BetweenFilter(splitFilterLine[INDEX_FIRST_VALUE],
                            splitFilterLine[INDEX_SECOND_VALUE]);
                    break;
                case "smaller_than":
                    returnFilter = new SmallerThanFilter(splitFilterLine[INDEX_FIRST_VALUE]);
                    break;
                case "file":
                    returnFilter = new FileNameFilter(splitFilterLine[INDEX_FIRST_VALUE]);
                    break;
                case "contains":
                    returnFilter = new ContainsFilter(splitFilterLine[INDEX_FIRST_VALUE]);
                    break;
                case "prefix":
                    returnFilter = new PrefixFilter(splitFilterLine[INDEX_FIRST_VALUE]);
                    break;
                case "suffix":
                    returnFilter = new SuffixFilter(splitFilterLine[INDEX_FIRST_VALUE]);
                    break;
                case "writable":
                    returnFilter = new WritableFilter(splitFilterLine[INDEX_FIRST_VALUE]);
                    break;
                case "executable":
                    returnFilter = new ExecutableFilter(splitFilterLine[INDEX_FIRST_VALUE]);
                    break;
                case "hidden":
                    returnFilter = new HiddenFilter(splitFilterLine[INDEX_FIRST_VALUE]);
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

    /**
     * Creates the default filter
     * @return the default filter
     */
    public static Filter createDefaultFilter() {
        return new AllFilter();
    }
}
