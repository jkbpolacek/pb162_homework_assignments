package cz.muni.fi.pb162.hw02;

import cz.muni.fi.pb162.hw02.impl.parser.InvalidQueryException;

/**
 * The class parses a query and transforms it into a stream of selectors. A syntax of the query
 * is similar to CSS selectors. It complies with these rules:
 *
 * - A name without a prefix is the selector by name of an element. For example, the query "div" returns
 *   all elements, which have name "div". The name is composed at least from one character. The character
 *   is valid name character, if satisfies the test defined in the method Utils.isNameChar(). The parser
 *   returns an instance of the class ElementSelector.
 *
 * - A name with the prefix "." is the selector by class of an element. For example, the query ".header"
 *   returns all elements, which have class "header". After the dot it must follow the name of a class, which
 *   is composed at least from one character. The parser returns an instance of the class ClassSelector.
 *
 * - A name with the prefix "#" is the selector by id of an element. For example, the query "#my-id" returns
 *   all elements, which have a value of the id attribute equal to "my-id". After the hash it must follow
 *   the value of an id, which is composed at least from one character. The parser returns an instance of the
 *   class IdSelector.
 *
 * - Whitespaces between two another selectors represent selector of all descendants. The selector after
 *   the whitespaces is applied to the descendants. For example, the query "div p" returns all elements with
 *   name "p", where every resulting element is a descendant of some "div" element. Whitespace is defined
 *   by test in method Character.isWhitespace(). The parser returns an instance of the class DescendantSelector.
 *
 * - Whitespaces on the beginning and on the end of the query are ignored.
 *
 * - The parser is greedy, which means that after detection of kind of next selector it reads as many as
 *   possible of characters valid for the selector.
 *
 * Examples:
 *  - Query "div#my-id.my-class" returns following sequence of selectors
 *    [ ElementSelector, IdSelector, ClassSelector ].
 *  - Query "  div.my-class  p#my-id  " returns following sequence of selectors
 *    [ ElementSelector, ClassSelector, DescendantSelector, ElementSelector, IdSelector ].
 *
 * @author Karel Vaculik
 */
public interface QueryParser {

    /**
     * Read and return next selector.
     *
     * @return the selector, if there is some to read, else null
     *
     * @throws InvalidQueryException if next sequence of characters is invalid
     */
    Selector getNextSelector() throws InvalidQueryException;

    /**
     * Check, if there is next selector available.
     *
     * @return true, if can be returned next selector, else false
     */
    boolean hasNextSelector();
}
