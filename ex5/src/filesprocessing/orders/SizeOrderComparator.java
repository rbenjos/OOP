package filesprocessing.orders;

import java.io.File;

/**
 * this class represents a comparator that compares files based on their size
 */
public class SizeOrderComparator extends OrderComparator {

    /**
     * this method compares 2 files based on their size
     *
     * @param file1 the first file being compared
     * @param file2 the second file being compared
     * @return 1 if the first file should be listed before the second
     * -1 if the second file should be listed before the first
     * 0 if their pathes have the same lexical value
     */
    @Override
    public int compare(File file1, File file2) {
        double file1Size = file1.length();
        double file2Size = file2.length();

        if (file1Size > file2Size) {
            return 1;
        } else if (file1Size < file2Size) {
            return -1;
        } else {
            AbsOrderComparator absOrderComparator = new AbsOrderComparator();
            return absOrderComparator.compare(file1, file2);
        }
    }
}
