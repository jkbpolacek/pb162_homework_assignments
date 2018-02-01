package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;
import cz.muni.fi.pb162.hw02.impl.dom.BaseAttribute;
import cz.muni.fi.pb162.hw02.impl.dom.BaseElement;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karel Vaculik
 */
public class IdSelectorTest {

    @Test(expected = NullPointerException.class)
    public void applyOnNull() {
        Selector selector = new IdSelector("theId");

        selector.apply(null);
    }

    @Test
    public void applyWithNullClassName() {
        Selector selector = new IdSelector(null);

        Element element = new BaseElement("div");
        element.addAttribute(new BaseAttribute("id", "theId"));

        Set<Element> elements = new HashSet<>();
        elements.add(element);

        Set<Element> selectedElements = selector.apply(elements);

        assertEquals(0, selectedElements.size());
    }

    @Test
    public void apply() {
        Selector selector = new IdSelector("theId");

        Element spanElement = new BaseElement("span");
        spanElement.addAttribute(new BaseAttribute("id", "abc"));

        Element divElement = new BaseElement("div");
        divElement.addAttribute(new BaseAttribute("id", "theId"));
        divElement.addAttribute(new BaseAttribute("ref", "efg.cz"));

        Element imgElement = new BaseElement("img");
        imgElement.addAttribute(new BaseAttribute("class", "btn"));

        Set<Element> elements = new HashSet<>();
        elements.add(spanElement);
        elements.add(divElement);
        elements.add(imgElement);

        Set<Element> selectedElements = selector.apply(elements);

        assertEquals(1, selectedElements.size());
        assertTrue(selectedElements.contains(divElement));
    }

    @Test
    public void applyOnEmptySet() {
        Selector selector = new IdSelector("theId");

        Set<Element> elements = new HashSet<>();

        Set<Element> selectedElements = selector.apply(elements);

        assertEquals(0, selectedElements.size());
    }
}
