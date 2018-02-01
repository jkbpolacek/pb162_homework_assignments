package cz.muni.fi.pb162.hw02;

import java.util.List;
import java.util.Set;

/**
 * @author Karel Vaculik
 */
public interface Element {

    /**
     * Return a name of the element.
     *
     * @return the name of the element
     */
    String getName();

    /**
     * Add an attribute to the collection of attributes, if it's not in the collection yet.
     *
     * @param attribute - the attribute to be added
     * @return true, if the attribute was added, else false
     *
     * @throws IllegalArgumentException if the given attribute is null
     */
    boolean addAttribute(Attribute attribute);

    /**
     * Find and return an attribute with the given name.
     *
     * @param name - the name of the attribute
     * @return the attribute, if found, else null
     */
    Attribute findAttribute(String name);

    /**
     * Delete an attribute with the given name.
     *
     * @param name - the name of the attribute
     * @return true, if the attribute was deleted, else false
     */
    boolean deleteAttribute(String name);

    /**
     * Return the collection of all attributes.
     *
     * @return set of all attributes
     */
    Set<Attribute> getAttributes();

    /**
     * Add an element to the end of the collection of child elements.
     *
     * @param element - the element to be added
     *
     * @throws IllegalArgumentException if the given element is null
     */
    void appendChildElement(Element element);

    /**
     * Delete the first occurrence of the given element in the collection of child elements.
     *
     * @param element - the element to be deleted
     * @return true, the element was deleted, else false
     */
    boolean deleteChildElement(Element element);

    /**
     * Return the collection of child elements.
     *
     * @return list of the child elements
     */
    List<Element> getChildElements();

    /**
     * Return a collection of all classes of the element. The classes of the element are defined in
     * the value of an attribute "class". The classes are separated by one or more whitespaces, which
     * are specified by the regular expression "\\s+". For example the element
     * {@code <div class="intro header"></div> } has two classes "intro" and "header".
     * Whitespaces on the beginning and on the end of the value are ignored.
     *
     * @return set of the classes
     */
    Set<String> getAllClasses();

    /**
     * Check, if the element has the given CSS class.
     *
     * @param clazz - the CSS class
     * @return true, if the element has the given class, else false
     */
    boolean containsClass(String clazz);

    /**
     * Return a collection of all descendants of the element.
     *
     * @return set of all descendants
     */
    Set<Element> getAllDescendants();
}
