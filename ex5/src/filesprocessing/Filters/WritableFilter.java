package filesprocessing.Filters;

import filesprocessing.Exceptions.FilterErrorException;

import java.io.File;

/**
 * this class filters files who are writable
 */
public class WritableFilter extends YesOrNoFilter {



    /**
     * the only constructor, uses the super class constructor
     * @param yesOrNoValue the YES/NO value of the filter
     * @throws FilterErrorException if the value given wasn't YES or NO
     */
    public WritableFilter (String yesOrNoValue) throws FilterErrorException {
        super(yesOrNoValue);
    }

    /**
     * @param file the file we are filtering
     * @return true if the file writablilty matches the parameter given
     *          false if not
     */
    @Override
    public boolean filterFile(File file) {
        if (yesOrNoValue.equals("YES")) {
            return file.canWrite();
        } else {
            return (!file.canWrite());
        }

    }
}
