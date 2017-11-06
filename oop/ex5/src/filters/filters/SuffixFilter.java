package filters.filters;

import filesprocessing.FileInfo;


class SuffixFilter implements Filter {

    /* fields */
    private String suffix;

    /**
     * Creates a filter that filters all files who's name end with the given suffix.
     * @param suffix a string of the suffix
     */
     SuffixFilter(String suffix) {
         this.suffix = suffix;
    }

    /**
     * Filters through a files if it's name ends with the suffix
     * @param toFilter FileInfo to filter
     * @return true if the name ends with suffix, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.name.endsWith(suffix);
    }
}
