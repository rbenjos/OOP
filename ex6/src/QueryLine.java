import Exceptions.QueryFormatException;

/**
 * this class is a wrapper class for a query, holds the parsed query data members
 */
public class QueryLine {

    // data members

    // the actual text in the query
    private String queryText;

    // whether or not the query has a QUICK modifier
    private boolean hasQUICKModifier = false;

    // whether or not the query has a CASE modifier
    private boolean hasCASEModifier = false;

    /**
     * the only constructor for this objects
     *
     * @param rawQuery a string representing the whole text in the query line if exists
     * @throws QueryFormatException if the query given is at the wrong format
     */
    public QueryLine(String rawQuery) throws QueryFormatException {

        // first we will split our string with the # character
        String[] queryElements = rawQuery.split("#");
        int length = queryElements.length;

        // we should have between 1 and 3 arguments after splitting, if not, throw exception
        if (length < 1 || 3 < length) {
            throw new QueryFormatException();
        }
        queryText = queryElements[0];
        switch (length) {
            case 1:
                break;
            case 2:
                hasQUICKModifier = queryElements[1].equals("QUICK");
                hasCASEModifier = queryElements[1].equals("CASE");

                /* if we have an argument after the # which isnt one of them, we should throw
                 an exception */
                if (!(hasCASEModifier || hasQUICKModifier)) {
                    throw new QueryFormatException();
                }
            case 3:
                hasQUICKModifier = queryElements[1].equals("QUICK");
                hasCASEModifier = queryElements[2].equals("CASE");
                /* if the 2 arguments we have after the # aren't our modifiers we should
                 * throw an exception*/
                if (!(hasQUICKModifier && hasCASEModifier)) {
                    throw new QueryFormatException();
                }

        }
    }

    /**
     * @return the text of the query
     */
    public String getQueryText() {
        return queryText;
    }

    /**
     * @return true if the query has a CASE modifier
     * false if not
     */
    public boolean isHasCASEModifier() {
        return hasCASEModifier;
    }

    /**
     * @return true if the query has a QUICK modifier
     * false if not
     */
    public boolean isHasQUICKModifier() {
        return hasQUICKModifier;
    }
}

