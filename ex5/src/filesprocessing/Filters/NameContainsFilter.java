package filesprocessing.Filters;

import java.io.File;

/**
 * this class represents a filter who filters files who's name contains a given string
 */
public class NameContainsFilter extends Filter {

    // data members

    private final String wantedStringInName;

    /**
     * the only constructor,receives a wanted string to be contained in the files name
     * @param wantedStringInName the string we are looking for in the files names
     *                          of the file we are filtering for
     */
    public NameContainsFilter(String wantedStringInName){
        this.wantedStringInName = wantedStringInName;
    }

    /**
     * @param file the file we are filtering
     * @return true if the file name contains to the wanted name
     *          false if not
     */
    @Override
    public boolean filterFile(File file) {
        String fileName = file.getName();
        return (fileName.contains(wantedStringInName));
    }
}
