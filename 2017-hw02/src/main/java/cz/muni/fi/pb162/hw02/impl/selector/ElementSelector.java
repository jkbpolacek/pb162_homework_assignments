package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jakub Polacek
 */
public class ElementSelector implements Selector {

    private String elementName;

    /**
     * simple constructor
     *
     * @param elementName name of element to look for
     */
    public ElementSelector(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public Set<Element> apply(Set<Element> elements) {

        return elements.stream().filter(elem -> elem != null &&
                elem.getName().equals(elementName)).collect(Collectors.toSet());
    }
}
