package cz.muni.fi.pb162.hw02.impl.parser;

import cz.muni.fi.pb162.hw02.impl.selector.DescendantSelector;
import cz.muni.fi.pb162.hw02.impl.selector.ClassSelector;
import cz.muni.fi.pb162.hw02.impl.selector.ElementSelector;
import cz.muni.fi.pb162.hw02.Selector;
import cz.muni.fi.pb162.hw02.impl.selector.IdSelector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Karel Vaculik
 */
public class QueryParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void createWithNameAsNull() {
        new QueryParserImpl(null);
    }

    @Test
    public void hasNextSelectorOnEmptyQuery() {
        QueryParserImpl parser = new QueryParserImpl("");

        assertFalse(parser.hasNextSelector());
    }

    @Test
    public void hasNextSelectorOnWhitespaces() {
        QueryParserImpl parser = new QueryParserImpl(" \t \n ");

        assertFalse(parser.hasNextSelector());
    }

    @Test
    public void hasNextSelector() {
        QueryParserImpl parser = new QueryParserImpl("div");

        assertTrue(parser.hasNextSelector());
    }

    @Test
    public void hasNextSelectorWithWhitespacesAround() {
        QueryParserImpl parser = new QueryParserImpl("  div ");

        assertTrue(parser.hasNextSelector());
    }

    @Test
    public void getNextSelectorOnEmptyQuery() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl("");

        assertNull(parser.getNextSelector());
    }

    @Test
    public void getNextSelectorOnWhitespaces() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl(" \n \t  ");

        assertNull(parser.getNextSelector());
    }

    @Test
    public void getNextElementSelector() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl("div");

        Selector selector = parser.getNextSelector();

        assertEquals(ElementSelector.class, selector.getClass());
        assertNull(parser.getNextSelector());
    }

    @Test
    public void getNextSelectorWithWhitespacesAround() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl(" div  ");

        Selector selector = parser.getNextSelector();

        assertEquals(ElementSelector.class, selector.getClass());
        assertNull(parser.getNextSelector());
    }

    @Test
    public void getNextIdSelector() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl("#the-id");

        Selector selector = parser.getNextSelector();

        assertEquals(IdSelector.class, selector.getClass());
        assertNull(parser.getNextSelector());
    }

    @Test
    public void getNextClassSelector() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl(".btn");

        Selector selector = parser.getNextSelector();

        assertEquals(ClassSelector.class, selector.getClass());
        assertNull(parser.getNextSelector());
    }

    @Test
    public void getNextSelectorCombinationWithChildren() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl("div .btn");

        Selector selector = parser.getNextSelector();

        assertEquals(ElementSelector.class, selector.getClass());
        selector = parser.getNextSelector();

        assertEquals(DescendantSelector.class, selector.getClass());
        selector = parser.getNextSelector();

        assertEquals(ClassSelector.class, selector.getClass());
        assertNull(parser.getNextSelector());
    }

    @Test
    public void getNextChildrenSelectorWithDifferentWhitespaces() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl("div\n \t .btn");

        Selector selector = parser.getNextSelector();

        assertEquals(ElementSelector.class, selector.getClass());
        selector = parser.getNextSelector();

        assertEquals(DescendantSelector.class, selector.getClass());
        selector = parser.getNextSelector();

        assertEquals(ClassSelector.class, selector.getClass());
        assertNull(parser.getNextSelector());
    }

    @Test
    public void parserComplexTest() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl(" \t div#the_id-1  span.btn p \n ");

        assertTrue(parser.hasNextSelector()); // div
        Selector selector = parser.getNextSelector();

        assertEquals(ElementSelector.class, selector.getClass());
        assertTrue(parser.hasNextSelector()); // #the_id-1
        selector = parser.getNextSelector();

        assertEquals(IdSelector.class, selector.getClass());
        assertTrue(parser.hasNextSelector()); // '  '
        selector = parser.getNextSelector();

        assertEquals(DescendantSelector.class, selector.getClass());
        assertTrue(parser.hasNextSelector()); // span
        selector = parser.getNextSelector();

        assertEquals(ElementSelector.class, selector.getClass());
        assertTrue(parser.hasNextSelector()); // .btn
        selector = parser.getNextSelector();

        assertEquals(ClassSelector.class, selector.getClass());
        assertTrue(parser.hasNextSelector()); // ' '
        selector = parser.getNextSelector();

        assertEquals(DescendantSelector.class, selector.getClass());
        assertTrue(parser.hasNextSelector()); // p
        selector = parser.getNextSelector();

        assertEquals(ElementSelector.class, selector.getClass());
        assertFalse(parser.hasNextSelector());
        selector = parser.getNextSelector();
        assertNull(selector);
    }

    @Test(expected = InvalidQueryException.class)
    public void getNextSelectorInvalidCharacterInside() throws InvalidQueryException {
        QueryParserImpl parser = new QueryParserImpl("di/v");

        parser.getNextSelector();
        parser.getNextSelector();
    }
}
