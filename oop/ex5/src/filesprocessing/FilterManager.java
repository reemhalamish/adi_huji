package filesprocessing;

import exceptions.FilterException;
import filters.filters.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adi on 25/05/17.
 */
public class FilterManager {

    private Filter createFilterFromString(String lineToCreateFilter) throws FilterException{

        return null;
    }

    public List<FileInfo> filterFiles(String sourcedir, String filterLine) throws FilterException {
        Filter sectionFilter = createFilterFromString(filterLine);
        List<FileInfo> filteredFileInfos = new ArrayList<>();
        //loop through files in sourcedir and filter each of them with sectionFilter. create a fileInfo object
        //for each file and add it to filteredFileInfos.


        return filteredFileInfos;
    }




}
