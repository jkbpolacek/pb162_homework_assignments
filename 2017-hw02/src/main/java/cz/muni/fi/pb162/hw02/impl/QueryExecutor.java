package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.QueryParser;
import cz.muni.fi.pb162.hw02.impl.parser.InvalidQueryException;
import cz.muni.fi.pb162.hw02.impl.parser.QueryParserImpl;

import java.util.Set;

/**
 * @author Jakub Polacek
 */
public final class QueryExecutor {

    private QueryExecutor() {
    }

    /**
     * Execute query on given element logic
     *
     * @param query       query to execute
     * @param rootElement element to begin from
     * @return elements selected by query
     * @throws InvalidQueryException if query is incorrect
     */
    public static Set<Element> execute(String query, Element rootElement)
            throws InvalidQueryException {


        Set<Element> result = rootElement.getAllDescendants();
        result.add(rootElement);


        QueryParser parser = new QueryParserImpl(query);

        while (parser.hasNextSelector()) {

            result = parser.getNextSelector().apply(result);

        }

        return result;

    }

}
