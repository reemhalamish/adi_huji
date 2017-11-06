package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;

class HiddenFilter implements Filter{

    private boolean isHidden;

    /**
     * Creates a filter that filters files that match the hidden/non-hidden condition
     * @param isHidden string indicating whether to filter through hidden or non-hidden files.
     * @throws FilterException throws an exception if given an illegal string. (i.e. not YES or NO)
     */
     HiddenFilter(String isHidden) throws FilterException {
        if (isHidden.equals("NO")){
            this.isHidden = false;
        }
        if (isHidden.equals("YES")){
            this.isHidden = true;
        }
        else {throw new FilterException(); }
    }


    /**
     * Checks if the file that matches the fileInfo given is hidden or not
     * @param toFilter FileInfo to filter
     * @return true if the file matches the hidden condition set in the constructor, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.hidden == isHidden;
    }
}
