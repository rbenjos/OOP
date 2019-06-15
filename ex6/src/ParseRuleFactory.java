import Exceptions.ParseRuleNotFoundException;
import processing.parsingRules.IparsingRule;
import processing.parsingRules.STmovieParsingRule;
import processing.parsingRules.STtvSeriesParsingRule;
import processing.parsingRules.SimpleParsingRule;

/**
 * this class represents a ParseRule factory, it creates IparsingRule implementing objects
 * based on a given string
 */
public class ParseRuleFactory {


    /**
     * the only method in this class, creates IparsingRule implementing objects
     * based on a given string
     * @param parseName a string indicating the type of parsing rule we need to create
     * @return the appropriate parsing rule object
     * @throws ParseRuleNotFoundException if an unsupported string is given
     */
    public static IparsingRule createParseRule(String parseName) throws ParseRuleNotFoundException {

        IparsingRule parser;

        switch (parseName) {
            case "SIMPLE":
                parser = new SimpleParsingRule();
                break;
            case "ST_MOVIE":
                parser = new STmovieParsingRule();
                break;
            case "ST_TV":
                parser = new STtvSeriesParsingRule();
                break;
            default:
                throw new ParseRuleNotFoundException();
        }

        return parser;
    }
}
