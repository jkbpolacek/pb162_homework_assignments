package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jakub Polacek
 */
public class IdSelector implements Selector {

    private String id;

    /**
     * simple constructor
     *
     * @param id name of id to look for
     */
    public IdSelector(String id) {
        this.id = id;
    }

    @Override
    public Set<Element> apply(Set<Element> elements) {

        if (id == null) {
            return new HashSet<>();
        }


        return elements.stream()
                .filter(elem -> elem != null &&
                        ((elem.findAttribute("id") != null) &&
                        id.equals(elem.findAttribute("id").getValue())))
                .collect(Collectors.toSet());
    }

}
