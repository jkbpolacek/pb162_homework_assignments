package cz.muni.fi.pb162.hw02.impl.dom;

import cz.muni.fi.pb162.hw02.Attribute;
import cz.muni.fi.pb162.hw02.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Jakub Polacek
 */
public class BaseElement implements Element {

    private String name;
    private Set<Attribute> attributes;
    private List<Element> elements;

    /**
     * This constructs base element with additional stuff
     *
     * @param name          name of element
     * @param attributes    attributes of element
     * @param childElements elements contained by element
     * @throws IllegalArgumentException thrown when any argument is null
     */
    public BaseElement(String name, Set<Attribute> attributes, List<Element> childElements)
            throws IllegalArgumentException {

        if (name == null || attributes == null || childElements == null) {
            throw new IllegalArgumentException("Can't have null arguments.");
        }

        this.name = name;
        this.elements = childElements;
        this.attributes = attributes;


    }

    /**
     * This constructs barebones basic element.
     *
     * @param name name of the element
     * @throws IllegalArgumentException thrown when name is null
     */
    public BaseElement(String name) throws IllegalArgumentException {
        this(name, new HashSet<>(), new ArrayList<>());


    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean addAttribute(Attribute attribute) throws IllegalArgumentException {


        if (attribute == null) {
            throw new IllegalArgumentException("Attribute cannot be null");
        }

        return attributes.add(attribute);
    }

    @Override
    public Attribute findAttribute(String name) {

        for (Attribute attribute : attributes) {

            if (attribute.getName().equals(name)) {
                return attribute;
            }
        }
        return null;


    }

    @Override
    public boolean deleteAttribute(String name) {


        Attribute attribute = findAttribute(name);
        if (attribute != null) {
            attributes.remove(attribute);
            return true;
        }

        return false;

    }

    @Override
    public Set<Attribute> getAttributes() {

        return Collections.unmodifiableSet(this.attributes);
    }

    @Override
    public void appendChildElement(Element element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        elements.add(element);
    }

    @Override
    public boolean deleteChildElement(Element element) {
        if (elements.contains(element)) {
            elements.remove(element);
            return true;
        }
        return false;
    }

    @Override
    public List<Element> getChildElements() {
        return Collections.unmodifiableList(elements);
    }

    @Override
    public Set<String> getAllClasses() {

        Set<String> result = new HashSet<>();
        Attribute classAttribute = findAttribute("class");

        if (classAttribute == null) {
            return result;
        }


        String[] semiResult = classAttribute.getValue().split("\\s+");

        if (semiResult != null) {
            Collections.addAll(result, semiResult);
        }

        return result;
    }

    @Override
    public boolean containsClass(String clazz) {
        return getAllClasses().contains(clazz);
    }

    @Override
    public Set<Element> getAllDescendants() {
        Set<Element> result = new HashSet<>();
        result.addAll(elements);
        for (Element elem : elements) {
            result.addAll(elem.getAllDescendants());
        }

        return result;

    }
}
