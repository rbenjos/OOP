package filesprocessing.Filters;

import java.io.File;
import java.util.ArrayList;

/**
 * this class has a single static method, the filterFiles method.
 * the method takes in an array of files and a filter object and returns
 * an  array of all the files that matched the filter's criteria
 */
public class FileFilterer {

    /**
     * @param files  the files we our filtering
     * @param filter our filter
     * @return an  array of all the files that matched the filter's criteria
     */
    public static File[] filterFiles(File[] files, Filter filter) {
        ArrayList<File> filteredFiles = new ArrayList<>();
        for (File currentFile : files) {
            if (filter.filterFile(currentFile)) {
                filteredFiles.add(currentFile);
            }
        }
        return filteredFiles.toArray(new File[0]);
    }
}
