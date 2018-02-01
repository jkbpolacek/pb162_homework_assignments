package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * @author Jakub Polacek
 */

public class DescendantSelector implements Selector {


    @Override
    public Set<Element> apply(Set<Element> elements) {

        Set<Element> result = new HashSet<>();

        for (Iterator<Element> it = elements.iterator(); it.hasNext(); ) {

            Element element = it.next();
            if (element != null) {
                result.addAll(element.getAllDescendants());
            }
        }
        return result;
    }

}

