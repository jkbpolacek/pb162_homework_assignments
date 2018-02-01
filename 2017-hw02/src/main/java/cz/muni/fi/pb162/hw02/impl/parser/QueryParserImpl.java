package cz.muni.fi.pb162.hw02.impl.parser;


import cz.muni.fi.pb162.hw02.QueryParser;
import cz.muni.fi.pb162.hw02.Selector;
import cz.muni.fi.pb162.hw02.Utils;
import cz.muni.fi.pb162.hw02.impl.selector.ClassSelector;
import cz.muni.fi.pb162.hw02.impl.selector.DescendantSelector;
import cz.muni.fi.pb162.hw02.impl.selector.ElementSelector;
import cz.muni.fi.pb162.hw02.impl.selector.IdSelector;


/**
 * @author Jakub Polacek
 */

public class QueryParserImpl implements QueryParser {

    private String query;

    /**
     * Constructor for query impl
     *
     * @param query string which designates desired query
     * @throws IllegalArgumentException when query is null
     */
    public QueryParserImpl(String query) throws IllegalArgumentException {

        if (query == null) {
            throw new IllegalArgumentException("Cannot have null arguments.");
        }

        // regex matches whitespaces at start/end of line
        this.query = query.replaceAll("^\\s+|\\s+$", "");

        // regex matches all whitespaces, converting it to nicely parsable single space
        this.query = this.query.replaceAll("\\s+", " ");


    }

    /**
     * checks if given char is a selector char
     *
     * @param c given char
     * @return true or false
     */
    private boolean isCharSelectorChar(char c) {
        return (Character.isWhitespace(c) || c == '#' || c == '.');
    }

    /**
     * checks if char is a valid query char, otherwise throws error
     *
     * @param c char to check
     * @throws InvalidQueryException when c is invalid
     */
    private void checkIfValid(char c) throws InvalidQueryException {
        if (!Utils.isNameChar(c)) {
            throw new InvalidQueryException("Invalid char in query.");
        }
    }

    /**
     * Figure out a state given char
     *
     * @param c char designating stae
     * @return state gained
     */
    private QueryState getState(char c) {
        switch (c) {
            case '.':
                return QueryState.CLASS;
            case '#':
                return QueryState.ID;
            case ' ':
                return QueryState.DESCENDANT;
            default:
                return QueryState.ELEMENT;
        }
    }

    /**
     * Figures out which selector we want
     *
     * @param iteratorPointer where does the next selector start
     * @return desired selector
     */
    private Selector getSelector(int iteratorPointer, QueryState queryState) {
        switch (queryState) {
            case ELEMENT:
                return new ElementSelector(query.substring(0, iteratorPointer));


            case ID:
                return new IdSelector(query.substring(1, iteratorPointer));


            case CLASS:
                return new ClassSelector(query.substring(1, iteratorPointer));


            case DESCENDANT:
                return new DescendantSelector();

            default:
                // will never happen
                return null;

        }


    }

    @Override
    public Selector getNextSelector() throws InvalidQueryException {

        if (!hasNextSelector()) {
            return null;
        }


        int iteratorPointer = 1;

        QueryState queryState = getState(query.charAt(0));


        char c;

        if (queryState != QueryState.DESCENDANT) {

            // Until you find a selector char, check each char if it's okay.
            while (iteratorPointer < query.length() && !isCharSelectorChar(c = query.charAt(iteratorPointer))) {
                checkIfValid(c);
                iteratorPointer++;
            }
        }


        // represents "#." and ". " and "# " and ".#" errors
        if (queryState == QueryState.ID || queryState == QueryState.CLASS) {
            if (iteratorPointer == 1) {
                throw new InvalidQueryException("Must give name after ID/CLASS selection");
            }
        }

        Selector result = getSelector(iteratorPointer, queryState);

        query = query.substring(iteratorPointer);

        return result;

    }

    @Override
    public boolean hasNextSelector() {
        return (query.length() > 0);
    }

    private enum QueryState {
        ID, CLASS, ELEMENT, DESCENDANT;
    }
}
