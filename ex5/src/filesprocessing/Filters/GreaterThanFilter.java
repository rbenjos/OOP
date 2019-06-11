package filesprocessing.Filters;

import filesprocessing.Exceptions.FilterErrorException;

import java.io.File;

/**
 * this class represents a filter that filters files who's size is above a given threshold
 */
public class GreaterThanFilter extends Filter {

    //constants
    public static final int BYTES_IN_KILOBYTE = 1024;

    // data members

    private final double thresholdSize;

    /**
     * the only constructor, we need a threshold size to filter
     * the files that are bigger than that.
     * @param thresholdSize the size we are filtering for
     * @throws FilterErrorException if the value given didnt fit the conditions
     */
    public GreaterThanFilter(double thresholdSize) throws FilterErrorException{

        this.thresholdSize = thresholdSize* BYTES_IN_KILOBYTE;
        if (thresholdSize<0 ){throw new FilterErrorException();}

    }

    /**
     * @param file the file we are filtering
     * @return true if the file size is greater than the threshold
     *          false if not
     */
    @Override
    public boolean filterFile(File file) {
        double fileSize = file.length();
        return (fileSize>thresholdSize);
    }
}
