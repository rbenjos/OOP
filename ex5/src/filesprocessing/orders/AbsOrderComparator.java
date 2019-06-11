package filesprocessing.orders;

import java.io.File;

/**
 * this class represents a comparator that compares files based on the lexical value of their absolute path
 */
public class AbsOrderComparator extends OrderComparator {

    /**
     * this method compares 2 files based on the lexical value of their absolute path
     *
     * @param file1 the first file being compared
     * @param file2 the second file being compared
     * @return 1 if the first file should be listed before the second
     * -1 if the second file should be listed before the first
     * 0 if their pathes have the same lexical value
     */
    public int compare(File file1, File file2) {
        String file1Path = file1.getAbsolutePath();
        String file2Path = file2.getAbsolutePath();

        return file1Path.compareTo(file2Path);

    }
}
