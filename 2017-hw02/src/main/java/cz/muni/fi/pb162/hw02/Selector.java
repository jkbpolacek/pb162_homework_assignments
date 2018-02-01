package cz.muni.fi.pb162.hw02;

import java.util.Set;

/**
 * @author Karel Vaculik
 */
public interface Selector {

    /**
     * Apply the selector to the given collection of elements. The collection is transformed into
     * a new collection based on a type of the selector.
     *
     * @param elements - the input set of elements
     * @return set of selected elements
     */
    Set<Element> apply(Set<Element> elements);
}
