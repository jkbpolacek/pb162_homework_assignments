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
public class ClassSelectorTest {

    @Test(expected = NullPointerException.class)
    public void applyOnNull() {
        Selector selector = new ClassSelector("btn");

        selector.apply(null);
    }

    @Test
    public void apply() {
        Selector selector = new ClassSelector("btn");

        Element spanElement = new BaseElement("span");
        spanElement.addAttribute(new BaseAttribute("class", "btn"));

        Element divElement = new BaseElement("div");
        divElement.addAttribute(new BaseAttribute("class", "abc"));
        divElement.addAttribute(new BaseAttribute("src", "efg.cz"));

        Element imgElement = new BaseElement("img");
        imgElement.addAttribute(new BaseAttribute("ref"));

        Set<Element> elements = new HashSet<>();
        elements.add(spanElement);
        elements.add(divElement);
        elements.add(imgElement);

        Set<Element> selectedElements = selector.apply(elements);

        assertEquals(1, selectedElements.size());
        assertTrue(selectedElements.contains(spanElement));
    }

    @Test
    public void applyOnEmptySet() {
        Selector selector = new ClassSelector("btn");

        Set<Element> elements = new HashSet<>();

        Set<Element> selectedElements = selector.apply(elements);

        assertEquals(0, selectedElements.size());
    }


    @Test
    public void applyWithDifferentWhitespaces() {
        Selector selector = new ClassSelector("btn");

        Element element = new BaseElement("div");
        element.addAttribute(new BaseAttribute("class", " \t\n btn \n abc \t"));

        Set<Element> elements = new HashSet<>();
        elements.add(element);

        Set<Element> selectedElements = selector.apply(elements);

        assertEquals(1, selectedElements.size());
        assertTrue(selectedElements.contains(element));
    }
}
