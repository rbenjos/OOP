package filesprocessing.Filters;

import java.io.File;

/**
 * this class represents a filter who filters all files, returning true always
 */
public class AllFilter extends Filter {


    /**
     * @param file the file we are filtering
     * @return true if the file is actually a file
     *          false if not
     */
    @Override
    public boolean filterFile(File file) {
        return !file.isDirectory();
    }
}
