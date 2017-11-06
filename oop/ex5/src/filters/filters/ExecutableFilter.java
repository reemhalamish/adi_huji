package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;


class ExecutableFilter implements Filter {

    private boolean isExecutable;

    /**
     * Creates a filter that filters files by the condition if they are executable or not
     * @param isExecutable a string indicating whether to filter executable or non-executable files.
     * @throws FilterException if given an illegal string throws a filter exception
     */
    ExecutableFilter(String isExecutable) throws FilterException {
        if (isExecutable.equals("NO")){
            this.isExecutable = false;
        }
        if (isExecutable.equals("YES")){
            this.isExecutable = true;
        }
        else {throw new FilterException(); }
    }

    /**
     * Given a file info, filters the file through according to it's match to the boolean isExecutable set in
     * the constructor.
     * @param toFilter FileInfo to filter
     * @return true if file matches the executable condition, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.executable == isExecutable;
    }
}
