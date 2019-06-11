package filesprocessing.Filters;

import java.io.File;

/**
 * this class is a decorator class, it takes in a certain filter, and negates that filter's decision
 * whether we should or should'nt filter a certain file by delegation
 */
public class NegatorDecorator extends Filter {


    // data members

    private Filter filter;

    /**
     * the only constructor
     *
     * @param filter the filter we are negating
     */
    public NegatorDecorator(Filter filter) {

        this.filter = filter;
    }

    /**
     * this function delegates the filtering process of the fiven file to the filter we composed into
     * this decorator
     * @param file the file we are filtering
     * @return true if the composed filter decided not to filter our the given file
     *          false if it decided to filter it
     */
    @Override
    public boolean filterFile(File file) {

        // we will just return the opposite of our composed filter decides
        return (!filter.filterFile(file));
    }
}
