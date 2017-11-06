package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;


class WritableFilter implements Filter {

    private boolean isWritable;

    /**
     * Creates a filter that filters files that match the writable/non-writable condition
     * @param isWritable string indicating whether to filter through hidden or non-hidden files.
     * @throws FilterException throws an exception if given an illegal string. (i.e. not YES or NO)
     */
    WritableFilter(String isWritable) throws FilterException {
        if (isWritable.equals("NO")){
            this.isWritable = false;
        }
        if (isWritable.equals("YES")){
            this.isWritable = true;
        }
        else {throw new FilterException(); }
    }

    /**
     * Checks if the file that matches the fileInfo given is writable or not
     * @param toFilter FileInfo to filter
     * @return true if the file matches the writable condition set in the constructor, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.writable == isWritable;
    }
}
