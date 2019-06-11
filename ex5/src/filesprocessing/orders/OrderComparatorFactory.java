package filesprocessing.orders;

import filesprocessing.Exceptions.OrderErrorException;

/**
 * this class represents the order comparator factory, it is in charge of creating order comparator for our
 * lists of files
 */
public class OrderComparatorFactory {


    // constants
    public static final String REVERSE_SUFFIX = "REVERSE";
    public static final String SEPERATING_SYMBOL = "#";

    /**
     * this method parses the values given by the section and returns a comparator matching these values
     *
     * @param orderValues the values of the order subsection given by the section
     * @return a filter matching the values given
     * @throws OrderErrorException
     */
    public static OrderComparator makeOrderComparator(String orderValues) throws OrderErrorException {

        // first we will split these values by the symobl #
        String[] orderValuesArray = orderValues.split(SEPERATING_SYMBOL);
        int length = orderValuesArray.length;

        // and make a variable out of the following boolean condition
        boolean hasReverseSuffix = orderValuesArray[length - 1].equals(REVERSE_SUFFIX);

        // and return a comparator that matches these values
        return createOrderComparator(orderValuesArray[0], hasReverseSuffix);
    }

    /**
     * the only constructor, as all orders have no additional parameters
     *
     * @param comparatorName   a string indicating the name of the comparator we are trying to create
     * @param HasReverseSuffix a boolean indicating if we want to reverse the order enforced by the given comparator
     * @return an OrderComparator matches the requirements
     * @throws OrderErrorException
     */
    public static OrderComparator createOrderComparator(String comparatorName, boolean HasReverseSuffix)
            throws OrderErrorException {
        // the default comparator
        OrderComparator currentOrderComparator = new AbsOrderComparator();

        switch (comparatorName) {

            case "abs":
                break;

            case "type":
                currentOrderComparator = new TypeOrderComparator();
                break;

            case "size":
                currentOrderComparator = new SizeOrderComparator();
                break;

            // if its not one of them, the format is bad and we should throw an exception
            default:
                throw new OrderErrorException();
        }

        // if needed, we will decorate with the reverse deorator

        if (HasReverseSuffix) {
            currentOrderComparator = new ReverseDecorator(currentOrderComparator);
        }


        return currentOrderComparator;

    }
}