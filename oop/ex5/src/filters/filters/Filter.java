package filters.filters;

import filesprocessing.FileInfo;


public interface Filter {

    /**
     * Filters the  FileInfo by a certain filter.
     * @param toFilter FileInfo to filter
     * @return true if the FileInfo passes the filter, false otherwise.
     */
    boolean filter(FileInfo toFilter);
}
