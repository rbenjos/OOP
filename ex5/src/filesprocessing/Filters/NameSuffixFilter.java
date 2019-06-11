package filesprocessing.Filters;

import java.io.File;

/**
 * this class represents a filter who filters files who's name ends with a given string
 */
public class NameSuffixFilter extends Filter {

    // data members

    private final String wantedStringSuffix;

    /**
     * the only constructor,receives a wanted string to be located in the files name's end
     * @param wantedStringSuffix the string we are looking for in the files name's end
     *
     */
    public NameSuffixFilter(String wantedStringSuffix){
        this.wantedStringSuffix = wantedStringSuffix;
    }

    /**
     * @param file the file we are filtering
     * @return true if the file name contains the String given as a suffix
     *          false if not
     */
    @Override
    public boolean filterFile(File file) {
        String fileName = file.getName();
        return (fileName.endsWith(wantedStringSuffix));
    }
}
