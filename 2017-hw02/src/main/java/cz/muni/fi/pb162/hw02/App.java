package cz.muni.fi.pb162.hw02;

import cz.muni.fi.pb162.hw02.impl.QueryExecutor;
import cz.muni.fi.pb162.hw02.impl.parser.InvalidQueryException;
import cz.muni.fi.pb162.hw02.priv.ElementPrinter;
import cz.muni.fi.pb162.hw02.priv.HtmlParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * @author Karel Vaculik
 */
public class App {
    public static final String EXIT_QUERY = "exit";
    public static final String FILE_PATH = "src/main/resources/example-wiki.html";

    /**
     * Main method of the application. Starts testing console.
     *
     * @param args - the arguments from a console
     * @throws IOException if there is parsing problem
     */
    public static void main( String[] args ) throws IOException {
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

        Element body = HtmlParser.parse(FILE_PATH);

        while (true) {
            System.out.print(">>>");
            String query = consoleInput.readLine();

            if (query.toLowerCase().equals(EXIT_QUERY)) {
                System.exit(0);
            }

            try {
                Set<Element> resultElements = QueryExecutor.execute(query, body);

                printResults(resultElements);
            } catch (InvalidQueryException ex) {
                System.out.println("INVALID QUERY!");
            }
        }
    }

    private static void printResults(Set<Element> results) {
        System.out.println("RESULTS:");
        for(Element element : results) {
            ElementPrinter.print(element);
        }
    }
}
