package filesprocessing.orders;

import java.io.File;

/**
 * this class represents the "reverse" decorator, used to reverse the comparing method of other comparator, and
 * by that, reversing the order on the list we are ordering
 */
public class ReverseDecorator extends OrderComparator {

    // data members

    private OrderComparator orderComparator;

    /**
     * the only constructor, takes in another comparator
     *
     * @param orderComparator the comparator we are composing
     */
    public ReverseDecorator(OrderComparator orderComparator) {
        this.orderComparator = orderComparator;
    }

    /**
     * this method delegates the comparing proccess to the composed comparator, and reverses it by
     * multilying it by -1
     *
     * @param file1 the first file being compared
     * @param file2 the second file being compared
     * @return the opposite int the composed comparator would have given
     */
    @Override
    public int compare(File file1, File file2) {

        // reversing the comparing output by multiplying it by (-1)
        return orderComparator.compare(file1, file2) * (-1);

    }
}
