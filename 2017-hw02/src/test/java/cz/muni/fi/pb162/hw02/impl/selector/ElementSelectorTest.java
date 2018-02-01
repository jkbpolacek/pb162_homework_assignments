package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;
import cz.muni.fi.pb162.hw02.impl.dom.BaseElement;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karel Vaculik
 */
public class ElementSelectorTest {

    @Test(expected = NullPointerException.class)
    public void applyOnNull() {
        Selector selector = new ElementSelector("div");

        selector.apply(null);
    }

    @Test
    public void applyWithNullElementName() {
        Selector selector = new ElementSelector(null);

        Set<Element> elements = new HashSet<>();

        elements.add(new BaseElement("div"));

        Set<Element> selectedElements = selector.apply(elements);

        assertEquals(0, selectedElements.size());
    }

    @Test
    public void apply() {
        Element spanElement = new BaseElement("span");
        Element divElement1 = new BaseElement("div");
        Element divElement2 = new BaseElement("div");

        Set<Element> elements = new HashSet<>();
        elements.add(spanElement);
        elements.add(divElement1);
        elements.add(divElement2);

        Selector divSelector = new ElementSelector("div");

        Set<Element> divSelectedElements = divSelector.apply(elements);

        assertEquals(2, divSelectedElements.size());
        assertTrue(divSelectedElements.contains(divElement1));
        assertTrue(divSelectedElements.contains(divElement2));
    }

    @Test
    public void applyOnEmptySet() {
        Set<Element> elements = new HashSet<>();

        Selector divSelector = new ElementSelector("div");

        Set<Element> divSelectedElements = divSelector.apply(elements);

        assertEquals(0, divSelectedElements.size());
    }
}
