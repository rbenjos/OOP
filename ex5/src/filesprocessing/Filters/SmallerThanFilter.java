package filesprocessing.Filters;


import filesprocessing.Exceptions.FilterErrorException;

import java.io.File;

/**
 * this class represents a filter that filters files who's size is bellow a given threshold
 */
public class SmallerThanFilter extends Filter {
    private static final int BYTES_IN_KILOBYTE = 1024;

    // data members

    private final double thresholdSize;

    /**
     * the only constructor, we need a threshold size to filter
     * the files that are smaller than that.
     * @param thresholdSize the size we are filtering for
     * @throws FilterErrorException if the number given was negative
     */
    public SmallerThanFilter(double thresholdSize)throws FilterErrorException {

        this.thresholdSize = thresholdSize* BYTES_IN_KILOBYTE;
        if (thresholdSize<0){throw new FilterErrorException();}

    }

    /**
     * @param file the file we are filtering
     * @return true if the file size is smaller than the threshold
     *          false if not
     */
    @Override
    public boolean filterFile(File file) {
        double fileSize = file.length();
        return (fileSize<thresholdSize);
    }
}
