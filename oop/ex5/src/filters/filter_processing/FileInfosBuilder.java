package filters.filter_processing;

import filesprocessing.FileInfo;
import filters.filters.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reem on 31/05/17.
 */
public class FileInfosBuilder {
    private static List<FileInfo> buildInfosFromFolder(String sourceDirectory){
        //todo check in internet how to iterate through only files in directory. build fileInfos for all.
        return null;
    }

    public static List<FileInfo> buildInfosWithFilter(String sourceDirectory, Filter filter) {
        List<FileInfo> allInfos = buildInfosFromFolder(sourceDirectory);
        List<FileInfo> filtered = new ArrayList<>();
        for (FileInfo fileInfo: allInfos) if (filter.filter(fileInfo)) filtered.add(fileInfo);
        return filtered;
    }

}
