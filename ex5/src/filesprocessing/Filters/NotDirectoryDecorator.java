package filesprocessing.Filters;

import java.io.File;




/**
 * this class is a decorator class, it takes in a certain filter, adds the condition
 * of not being a directory to that filter
 */
public class NotDirectoryDecorator extends Filter {


    // data members

    private Filter filter;

    /**
     * the only constructor
     *
     * @param filter the filter we are negating
     */
    public NotDirectoryDecorator(Filter filter) {

        this.filter = filter;
    }

    /**
     * this function delegates the filtering process of the given file to the filter we composed into
     * this decorator
     * @param file the file we are filtering
     * @return true if the filter given returns true and the file is not a directory
     * false otherwhise
     */
    @Override
    public boolean filterFile(File file) {

        //we will return whatever our composed filter returned and add the condition of not being a directory
        return (filter.filterFile(file)&&(!file.isDirectory()));
    }
}


