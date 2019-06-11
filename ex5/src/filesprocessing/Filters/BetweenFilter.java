package filesprocessing.Filters;

import filesprocessing.Exceptions.FilterErrorException;

import java.io.File;

/**
 * this class represents a filter that filters only files who's size is between 2 thresholds
 */
public class BetweenFilter extends Filter {

    //constants
    public static final int BYTES_IN_KILOBYTE = 1024;

    // data members

    private final double lowerThresholdSize;
    private final double upperThresholdSize;

    /**
     * the only constructor, we need an upper threshold and a lower threshold to filter the files
     * who's sizes are between those 2 thresholds
     * @param lowerThresholdSize  the lower size threshold
     * @param upperThresholdSize the uppper size threshold
     * @throws FilterErrorException if the numbers given were negative, or didnt fit the format
     */
    public BetweenFilter(double lowerThresholdSize,double upperThresholdSize)throws FilterErrorException {
        this.lowerThresholdSize = lowerThresholdSize* BYTES_IN_KILOBYTE;
        this.upperThresholdSize = upperThresholdSize*BYTES_IN_KILOBYTE;

        if (lowerThresholdSize<0 || upperThresholdSize<0 ||upperThresholdSize<lowerThresholdSize){
            throw new FilterErrorException();
        }


    }

    /**
     * @param file the file we are filtering
     * @return true if the file's size is between the 2 thresholds
     */
    @Override
    public boolean filterFile(File file) {
        double fileSize = file.length();
        return (lowerThresholdSize<=fileSize)&&(fileSize<=upperThresholdSize);
    }
}
