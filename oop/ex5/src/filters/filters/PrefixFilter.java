package filters.filters;

import filesprocessing.FileInfo;


class PrefixFilter implements Filter {

    /* fields */
    private String prefix;

    /**
     * Creates a filter that filters all files who's name begin with the given prefix.
     * @param prefix a string of the prefix
     */
    PrefixFilter(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Filters through a files if it's name begins with the prefix
     * @param toFilter FileInfo to filter
     * @return true if the name begins with prefix, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.name.startsWith(prefix);
    }
}
