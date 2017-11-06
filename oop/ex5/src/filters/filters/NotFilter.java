package filters.filters;

import filesprocessing.FileInfo;


 class NotFilter implements Filter{

    /* fields */
    private Filter innerFilter;

     /**
      * Creates a filter that reverses the condition of the inner filter
      * @param filter inner filter to reverse
      */
    NotFilter(Filter filter) {
        this.innerFilter = filter;
    }

     /**
      * reverses to boolean returned from the inner filter
      * @param toFilter FileInfo to filter
      * @return true if inner filter returns false, false if inner filter returns true.
      */
    @Override
    public boolean filter(FileInfo toFilter) {
        return !innerFilter.filter(toFilter);
    }
}
