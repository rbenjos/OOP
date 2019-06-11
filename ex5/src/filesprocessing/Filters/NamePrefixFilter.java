package filesprocessing.Filters;

import java.io.File;

/**
 * this class represents a filter who filters files who's name starts with a given string
 */
public class NamePrefixFilter extends Filter {

    // data members

    private final String wantedStringPrefix;

    /**
     * the only constructor,receives a wanted string to be located in the file's name's start
     *
     * @param wantedStringPrefix the string we are looking for in the files name's start
     */
    public NamePrefixFilter(String wantedStringPrefix) {

        this.wantedStringPrefix = wantedStringPrefix;
    }

    /**
     * @param file the file we are filtering
     * @return true if the file name contains the String given as a prefix
     * false if not
     */
    @Override
    public boolean filterFile(File file) {
        String fileName = file.getName();
        return (fileName.startsWith(wantedStringPrefix));
    }
}
