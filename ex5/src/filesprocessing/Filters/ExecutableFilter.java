package filesprocessing.Filters;

import filesprocessing.Exceptions.FilterErrorException;

import java.io.File;

/**
 * this class filters files who are executable
 */
public class ExecutableFilter extends YesOrNoFilter {

    /**
     * the only constructor, uses the super class constructor
     * @param yesOrNoValue the YES/NO value of the filter
     * @throws FilterErrorException if the value given wasn't YES or NO
     */
    public ExecutableFilter(String yesOrNoValue)throws FilterErrorException {
        super(yesOrNoValue);
    }

    /**
     * @param file the file we are filtering
     * @return true if the file executability matches the parameter given
     *          false if not
     */
    @Override
    public boolean filterFile(File file) {

        if (yesOrNoValue.equals("YES")) {
            return file.canExecute()&&!file.isDirectory();
        } else {
            return (!file.canExecute());
        }

    }
}
