package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.impl.dom.BaseAttribute;
import cz.muni.fi.pb162.hw02.impl.dom.BaseElement;
import cz.muni.fi.pb162.hw02.impl.parser.InvalidQueryException;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karel Vaculik
 */
public class QueryExecutorTest {

    @Test
    public void constructorIsPrivate() throws NoSuchMethodException {
        final Constructor<?> constructor = QueryExecutor.class.getDeclaredConstructor();

        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    public void classIsFinal() {
        Class<?> clazz = QueryExecutor.class;

        assertTrue(Modifier.isFinal(clazz.getModifiers()));
    }

    @Test
    public void executeWithNoSelectors() throws InvalidQueryException {
        Element element11 = new BaseElement("p");

        Element element1 = new BaseElement("div");
        element1.appendChildElement(element11);

        Element element2 = new BaseElement("img");

        Element rootElement = new BaseElement("div");
        rootElement.appendChildElement(element1);
        rootElement.appendChildElement(element2);

        Set<Element> resultElements = QueryExecutor.execute("", rootElement);

        assertEquals(4, resultElements.size());
        assertTrue(resultElements.contains(rootElement));
        assertTrue(resultElements.contains(element1));
        assertTrue(resultElements.contains(element11));
        assertTrue(resultElements.contains(element2));
    }

    @Test
    public void execute() throws InvalidQueryException {
        Element element11 = new BaseElement("p");

        Element element1 = new BaseElement("div");
        element1.appendChildElement(element11);

        Element element2 = new BaseElement("img");
        element2.addAttribute(new BaseAttribute("id", "theId"));

        Element rootElement = new BaseElement("div");
        rootElement.addAttribute(new BaseAttribute("class", "btn"));
        rootElement.appendChildElement(element1);
        rootElement.appendChildElement(element2);

        Set<Element> resultElements = QueryExecutor.execute("div.btn #theId", rootElement);

        assertEquals(1, resultElements.size());
        assertTrue(resultElements.contains(element2));
    }
}
