package filesprocessing.orders;

import java.io.File;

/**
 * this class has only static methods, its only purpose is to sort files with a quicksort implementation
 */
public class FileSorter {


    /**
     * this method simplifies calling the quicksort method, as we will always start with the
     * same low and high indices
     *
     * @param files      the files we are sorting
     * @param comparator the comparator we are using to compare them
     */
    public static void sortFiles(File[] files, OrderComparator comparator) {
        int high = files.length - 1;
        quickSort(files, 0, files.length - 1, comparator);
    }


    /**
     * this method implements most of the work of the quicksort
     *
     * @param files      the files we are ordering
     * @param low        the low index of the subarray we are partitioning
     * @param high       the high index of the subarray we are partitioning
     * @param comparator the comparator we are using to compare them
     * @return the partition index for the next subbarays
     */
    private static int partition(File[] files, int low, int high, OrderComparator comparator) {

        // first we will make a pivot element of our final in the sublist
        File pivotElement = files[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            // if our current element is smaller or equal to the pivot element we will swap them
            if (comparator.compare(files[j], pivotElement) <= 0) {
                i++;

                File temp = files[i];
                files[i] = files[j];
                files[j] = temp;
            }
        }

        // and another final swap
        File temp = files[i + 1];
        files[i + 1] = files[high];
        files[high] = temp;

        return i + 1;
    }


    /**
     * this recursive method divides our arrays into subarrays, each time picking a pivot element, putts all the
     * elements bigger than him on one side, and all the elements smaller than him on the other side.
     * doing so recursively untill the array is ordered
     *
     * @param files      the files we are ordering
     * @param low        the low index of the subarray we are partitioning
     * @param high       the high index of the subarray we are partitioning
     * @param comparator the comparator we are using to compare them
     */
    public static void quickSort(File[] files, int low, int high, OrderComparator comparator) {
        if (low < high) {
            // order the elements in this subarray in divide them to subarray
            int partitionIndex = partition(files, low, high, comparator);

            // repeat on each subarray
            quickSort(files, low, partitionIndex - 1, comparator);
            quickSort(files, partitionIndex + 1, high, comparator);
        }
    }

}
