package filesprocessing.Filters;

import filesprocessing.Exceptions.FilterErrorException;

/**
 * this class represents an abstract YesOrNo filter, a filter that takes in only the YES/NO values as parameters
 */
public abstract class YesOrNoFilter extends Filter {

    // data Members

    // the YES/NO value
    protected String yesOrNoValue;


    /**
     * our constructor must check if the value given is indeed yes or no, if not, we must raise an exception
     * @param yesOrNoValue the YES/NO value given
     * @throws FilterErrorException if the value given wasnt yes or no
     */
    public YesOrNoFilter(String yesOrNoValue) throws FilterErrorException {
        if (yesOrNoValue.equals("YES") || (yesOrNoValue.equals("NO"))) {
            this.yesOrNoValue = yesOrNoValue;
        } else {
            throw new FilterErrorException();
        }

    }
}
