package filters.filters;

import filesprocessing.FileInfo;

class ContainsFilter implements Filter {

    /* fields */
    private String stringContained;

    /**
     * Constructs a filter that filters files that the given string is contained in their name.
     * @param stringContained the string to be contained in the file name.
     */
    ContainsFilter(String stringContained) {
        this.stringContained = stringContained;
    }

    /**
     * Filters a file if it's name contains the stringContained
     * @param toFilter FileInfo to filter
     * @return true if the file name contains the string given in the constructor, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.name.contains(stringContained);
    }
}
