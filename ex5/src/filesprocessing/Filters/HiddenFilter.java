package filesprocessing.Filters;

import filesprocessing.Exceptions.FilterErrorException;

import java.io.File;

/**
 * this class filters files who are hidden
 */
public class HiddenFilter extends YesOrNoFilter {


    /**
     * the only constructor, uses the super class constructor
     * @param yesOrNoValue the YES/NO value of the filter
     * @throws FilterErrorException if the value given wasn't YES or NO
     */
    public HiddenFilter (String yesOrNoValue)throws FilterErrorException {
        super(yesOrNoValue);
    }

    /**
     * @param file the file we are filtering
     * @return true if the file's hidden value matches the parameter given
     *          false if not
     */
    @Override
    public boolean filterFile(File file) {
        if (yesOrNoValue.equals("YES")) {
            return file.isHidden();
        } else {
            return (!file.isHidden());
        }

    }
}
