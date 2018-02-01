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
public class DescendantSelectorTest {

    @Test(expected = NullPointerException.class)
    public void applyOnNull() {
        Selector selector = new DescendantSelector();

        selector.apply(null);
    }

    @Test
    public void apply() {
        Selector selector = new DescendantSelector();

        Element element11 = new BaseElement("div");
        Element element12 = new BaseElement("span");

        Element rootElement1 = new BaseElement("body");
        rootElement1.appendChildElement(element11);
        rootElement1.appendChildElement(element12);

        Element element211 = new BaseElement("p");

        Element element21 = new BaseElement("div");
        element21.appendChildElement(element211);
        Element element22 = new BaseElement("img");

        Element rootElement2 = new BaseElement("div");
        rootElement2.appendChildElement(element21);
        rootElement2.appendChildElement(element22);

        Set<Element> elements = new HashSet<>();
        elements.add(rootElement1);
        elements.add(rootElement2);

        Set<Element> selectedElements = selector.apply(elements);

        assertEquals(5, selectedElements.size());
        assertTrue(selectedElements.contains(element11));
        assertTrue(selectedElements.contains(element12));
        assertTrue(selectedElements.contains(element21));
        assertTrue(selectedElements.contains(element211));
        assertTrue(selectedElements.contains(element22));
    }
}
