package filesprocessing;

import filesprocessing.Exceptions.FilterErrorException;
import filesprocessing.Exceptions.OrderErrorException;
import filesprocessing.Filters.AllFilter;
import filesprocessing.Filters.Filter;
import filesprocessing.Filters.FilterFactory;
import filesprocessing.orders.AbsOrderComparator;
import filesprocessing.orders.OrderComparator;
import filesprocessing.orders.OrderComparatorFactory;

import java.util.ArrayList;

/**
 * this class represents a section of commands, it has a filter subsection and an order subsection
 */
public class Section extends ArrayList<String> {

    // data members

    // our values as strings

    private String filterValues;
    private String orderValues;

    // the indices of the lines of the values
    private int filterLineIndex;
    private int orderLineIndex;

    // our filter and order comparator
    private Filter filter;
    private OrderComparator orderComparator;


    /**
     * the constructor builds a new section object
     * @param filterValues the values of the filter subsection
     * @param filterLineIndex the index of the filter subsection
     * @param orderValues the values of the order subsection
     * @param orderLineIndex the index of the order subsection
     */
    public Section(String filterValues, int filterLineIndex, String orderValues, int orderLineIndex) {
        this.filterValues = filterValues;
        this.orderValues = orderValues;
        this.filterLineIndex = filterLineIndex;
        this.orderLineIndex = orderLineIndex;

    }

    /**
     * @return the string representing the filter values
     */
    public String getFilterValues() {
        return filterValues;
    }

    /**
     * @return the string representing the order values
     */
    public String getOrderValues() {

        return orderValues;
    }

    /**
     * @return the index of the filter subsection
     */
    public int getFilterLineIndex() {

        return filterLineIndex;
    }

    /**
     * @return the index of the order subsection
     */
    public int getOrderLineIndex() {

        return orderLineIndex;
    }

    /**
     * makes a filter out of the filter values using the filter factory
     */
    public void makeFilter() {
        try {
            filter = FilterFactory.makeFilter(filterValues);
        } catch (FilterErrorException e) {
            filter = new AllFilter();
            System.err.println(warningMessage(filterLineIndex));
        }
    }

    /**
     * makes an order comparator out of the order values, using the order comparator factory
     */
    public void makeOrderComparator() {
        try {
            orderComparator = OrderComparatorFactory.makeOrderComparator(orderValues);
        } catch (OrderErrorException | NullPointerException e) {
            orderComparator = new AbsOrderComparator();
            System.err.println(warningMessage(orderLineIndex));
        }
    }

    /**
     * @return the filter object made by this section
     */
    public Filter getFilter() {

        return filter;
    }

    /**
     * @return the order comparator object made by this section
     */
    public OrderComparator getOrderComparator() {

        return orderComparator;
    }

    /**
     * this method makes a warning message with the line index given
     * @param lineIndex the index of the line that made the error
     * @return a string representing the message
     */
    private String warningMessage(int lineIndex) {
        String string = "Warning in line " + Integer.toString(lineIndex);
        return string;
    }

}
