package filesprocessing.Filters;

import filesprocessing.Exceptions.FilterErrorException;

/**
 * this class represents the filter factory, it is in charge of creating filters
 */
public class FilterFactory {

    public static final String NOT = "NOT";
    public static final String SEPERATING_SYMBOL = "#";

    /**
     * this method first parases the values given by the section, and then creates a filter based on
     * these values and returns it
     *
     * @param filterValues the values given by the section
     * @return the filter that matches the name that was given
     * @throws FilterErrorException if the filter format wasn't legal
     */

    public static Filter makeFilter(String filterValues) throws FilterErrorException {

        // first we will split the string given to us using the # symbol
        String[] filterValuesArray = filterValues.split(SEPERATING_SYMBOL);
        int length = filterValuesArray.length;

        // and we will check if our last element was the NOT suffix
        boolean hasNotSuffix = filterValuesArray[length - 1].equals(NOT);


        switch (length) {
            /* based on the number of elements in the list, and whether or not the NOT suffix is present, we will
            *  use the relevant method*/
            case 1:
                return createFilter(filterValuesArray[0], hasNotSuffix);
            case 2:
                return createFilter(filterValuesArray[0], filterValuesArray[1], hasNotSuffix);
            case 3:
                if (hasNotSuffix) {
                    return (createFilter(filterValuesArray[0], filterValuesArray[1], true));
                } else {
                    return createFilter(filterValuesArray[0], filterValuesArray[1],
                            filterValuesArray[2], false
                    );
                }
            case 4:
                if (hasNotSuffix) {
                    return createFilter(filterValuesArray[0],
                            filterValuesArray[1], filterValuesArray[2], hasNotSuffix
                    );
                } else {
                    throw new FilterErrorException();
                }
            default:
                throw new FilterErrorException();
        }

    }


    /**
     * this method returns a filter if the filter parameters have 1 parameter
     * @param filterName the name of the filter
     * @param stringValue the value of the filter
     * @param hasNotSuffix whether or not the NOT suffix is present
     * @return a filter that matches the name and values
     * @throws FilterErrorException if the filter format is bad
     */
    private static Filter createFilter(String filterName, String stringValue, boolean hasNotSuffix)
            throws FilterErrorException {

        // our default filter
        Filter currentFilter = new AllFilter();

        // we will parse the value to double if needed
        double doubleValue;
        switch (filterName) {

            case "greater_than":
                doubleValue = Double.parseDouble(stringValue);
                currentFilter = new GreaterThanFilter(doubleValue);
                break;

            case "smaller_than":
                doubleValue = Double.parseDouble(stringValue);
                currentFilter = new SmallerThanFilter(doubleValue);
                break;

            case "file":
                currentFilter = new FileNameFilter(stringValue);
                break;

            case "contains":
                currentFilter = new NameContainsFilter(stringValue);
                break;

            case "prefix":
                currentFilter = new NamePrefixFilter(stringValue);
                break;

            case "suffix":
                currentFilter = new NameSuffixFilter(stringValue);
                break;

            case "writable":
                currentFilter = new WritableFilter(stringValue);
                break;

            case "executable":
                currentFilter = new ExecutableFilter(stringValue);
                break;

            case "hidden":
                currentFilter = new HiddenFilter(stringValue);
                break;


            default:
                throw new FilterErrorException();

        }


        // finally we will decorate our filter with the negator decorator if needed and the not directory decorator
        if (hasNotSuffix) {
            currentFilter = new NegatorDecorator(currentFilter);
        }

        currentFilter = new NotDirectoryDecorator(currentFilter);
        return currentFilter;
    }


    /**
     * this method returns a filter if the filter parameters have 2 parameters
     * @param filterName the name of the filter
     * @param stringValue1 the first value given
     * @param StringValue2 the second value given
     * @param hasNotSuffix whether or not the NOT suffix is present
     * @return a filter that matches the name and values
     * @throws FilterErrorException if the filter format is bad
     */
    private static Filter createFilter(String filterName, String stringValue1,
                                       String StringValue2, boolean hasNotSuffix)
            throws FilterErrorException {


        // make a default value
        Filter currentFilter = new AllFilter();


        switch (filterName) {
            case "between":


                // parse the values to double if needed
                double doubleValue1 = Double.parseDouble(stringValue1);
                double doubleValue2 = Double.parseDouble(StringValue2);

                currentFilter = new BetweenFilter(doubleValue1, doubleValue2);
                break;

            default:
                throw new FilterErrorException();
        }


        // finally we will decorate our filter with the negator decorator if needed and the not directory decorator
        if (hasNotSuffix) {
            currentFilter = new NegatorDecorator(currentFilter);
        }

        currentFilter = new NotDirectoryDecorator(currentFilter);
        return currentFilter;
    }


    /**
     * this method returns a filter if the filter parameters have 0 parameters
     * @param filterName the name of the filter
     * @param hasNotSuffix whether or not the NOT suffix is present
     * @return a filter that matches the name and values
     * @throws FilterErrorException if the filter format is bad
     */
    public static Filter createFilter(String filterName, boolean hasNotSuffix)
            throws FilterErrorException {

        // make a default filter
        Filter currentFilter = new AllFilter();

        switch (filterName) {
            case "all":
                break;
            default:
                throw new FilterErrorException();
        }

        // finally we will decorate our filter with the negator decorator if needed and the not directory decorator

        if (hasNotSuffix) {
            currentFilter = new NegatorDecorator(currentFilter);
        }

        currentFilter = new NotDirectoryDecorator(currentFilter);
        return currentFilter;
    }
}
