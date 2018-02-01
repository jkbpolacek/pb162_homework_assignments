package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jakub Polacek
 */

public class ClassSelector implements Selector {

    private String className;

    /**
     * Simple constructor
     *
     * @param className name of class to look for
     */
    public ClassSelector(String className) {
        this.className = className;
    }

    @Override
    public Set<Element> apply(Set<Element> elements) {


        return elements.stream().filter(elem -> elem != null &&
                elem.containsClass(className)).collect(Collectors.toSet());
    }

}
