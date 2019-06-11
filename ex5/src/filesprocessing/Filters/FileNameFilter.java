package filesprocessing.Filters;

import java.io.File;

/**
 * this class represents a filter who filters files who's name match a given string
 */
public class FileNameFilter extends Filter {

    // data members

    private final String wantedFileName;

    /**
     * the only constructor, a wanted file name, to compare all the file names
     * to
     * @param wantedFileName the name of the file we are filtering for
     */
    public FileNameFilter(String wantedFileName){
        this.wantedFileName = wantedFileName;
    }

    /**
     * @param file the file we are filtering
     * @return true if the file name is equal to the wanted name
     *          false if not
     */
    @Override
    public boolean filterFile(File file) {
        String fileName = file.getName();
        return (fileName.equals(wantedFileName));
    }
}
