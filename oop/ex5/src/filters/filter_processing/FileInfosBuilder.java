package filters.filter_processing;

import filesprocessing.FileInfo;
import filters.filters.Filter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileInfosBuilder {

    /**
     * Iterates through all files in the source directory and builds a fileInfo that holds all the info of
     * every file for every file in the directory.(excluding sub-directories)
     * @param sourceDirectory path of the source directory
     * @return a list of all the infos
     */
    private static List<FileInfo> buildInfosFromFolder(String sourceDirectory){
        File directory = new File(sourceDirectory);
        File[] listOfFiles = directory.listFiles();
        List<FileInfo> allInfos = new ArrayList<>();
        for (File file: listOfFiles){
            if (file.isFile()){
                allInfos.add(new FileInfo(file));
            }
        }
        return allInfos;
    }

    /**
     * Builds a fileInfo for every file (excluding sub-directories) in source directory and filters through
     * fileInfos by the given filter.
     * @param sourceDirectory directory of files to filter
     * @param filter given filter to filter files
     * @return a list of the filtered fileIfos
     */
    public static List<FileInfo> buildInfosWithFilter(String sourceDirectory, Filter filter) {
        List<FileInfo> allInfos = buildInfosFromFolder(sourceDirectory);
        List<FileInfo> filtered = new ArrayList<>();
        for (FileInfo fileInfo: allInfos) if (filter.filter(fileInfo)) filtered.add(fileInfo);
        return filtered;
    }

}
