package filesprocessing.orders;

import java.io.File;
import java.util.Comparator;


/**
 * this class represents an abstract order comperator for files
 */
public abstract class OrderComparator implements Comparator<java.io.File> {

    /**
     * @param file1 the first file being compared
     * @param file2 the second file being compared
     * @return 1 if the first file should come before the second one
     * -1 if the second file shoud come before the first one
     * 0 if they have the same value when compared
     */
    public abstract int compare(File file1, File file2);
}
