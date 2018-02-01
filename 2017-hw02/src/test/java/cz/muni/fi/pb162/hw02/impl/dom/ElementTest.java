package cz.muni.fi.pb162.hw02.impl.dom;

import cz.muni.fi.pb162.hw02.Attribute;
import cz.muni.fi.pb162.hw02.Element;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Karel Vaculik
 */
public class ElementTest {

    @Test
    public void getName() {
        Element elem = new BaseElement("div");
        String name = elem.getName();

        assertEquals("div", name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNameAsNull() {
        new BaseElement(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithAttributesAsNull() {
        new BaseElement("div", null, new ArrayList<Element>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithChildElementsAsNull() {
        new BaseElement("div", new HashSet<Attribute>(), null);
    }

    @Test
    public void getAttributes() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("Attr");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());
        Set<Attribute> returnedAttributes = elem.getAttributes();

        assertEquals(1, returnedAttributes.size());
        assertTrue(returnedAttributes.contains(attr));
    }

    @Test
    public void getChildElements() {
        List<Element> childElements = new ArrayList<>();
        Element childElem = new BaseElement("span");
        childElements.add(childElem);

        Element elem = new BaseElement("div", new HashSet<>(), childElements);
        List<Element> returnedChildElements = elem.getChildElements();

        assertEquals(1, returnedChildElements.size());
        assertTrue(returnedChildElements.contains(childElem));
    }

    @Test
    public void addAttribute() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("Attr");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());

        Attribute newAttr = new BaseAttribute("Attr2");
        boolean isAdded = elem.addAttribute(newAttr);

        assertTrue(isAdded);

        Set<Attribute> returnedAttributes = elem.getAttributes();

        assertEquals(2, returnedAttributes.size());
        assertTrue(returnedAttributes.contains(newAttr));
    }

    @Test
    public void addAlreadyAddedAttribute() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("Attr");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());

        Attribute newAttr = new BaseAttribute("Attr");
        boolean isAdded = elem.addAttribute(newAttr);

        assertFalse(isAdded);

        Set<Attribute> returnedAttributes = elem.getAttributes();

        assertEquals(1, returnedAttributes.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullAttribute() {
        Element elem = new BaseElement("div");

        elem.addAttribute(null);
    }

    @Test
    public void findAttribute() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("Attr");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());

        Attribute returnedAttr = elem.findAttribute("Attr");

        assertEquals(attr, returnedAttr);
    }

    @Test
    public void findNotExistingAttribute() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("Attr");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());

        Attribute returnedAttr = elem.findAttribute("NotExistingAttr");

        assertNull(returnedAttr);
    }

    @Test
    public void deleteAttribute() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("Attr");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());

        boolean deleteResult = elem.deleteAttribute("Attr");
        Set<Attribute> attributesAfterDelete = elem.getAttributes();

        assertTrue(deleteResult);
        assertEquals(0, attributesAfterDelete.size());
    }

    @Test
    public void deleteNotExistingAttribute() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("Attr");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());

        boolean deleteResult = elem.deleteAttribute("Attr2");
        Set<Attribute> attributesAfterDelete = elem.getAttributes();

        assertFalse(deleteResult);
        assertEquals(1, attributesAfterDelete.size());
        assertTrue(attributesAfterDelete.contains(attr));
    }

    @Test
    public void appendChildElement() {
        Element rootElem = new BaseElement("div");
        Element child = new BaseElement("span");

        rootElem.appendChildElement(child);

        List<Element> childElements = rootElem.getChildElements();

        assertEquals(1, childElements.size());
        assertTrue(childElements.contains(child));
    }

    @Test
    public void appendMultipleChildElements() {
        Element rootElem = new BaseElement("div");
        Element child1 = new BaseElement("span");
        Element child2 = new BaseElement("div");
        Element child3 = new BaseElement("p");

        rootElem.appendChildElement(child1);
        rootElem.appendChildElement(child2);
        rootElem.appendChildElement(child3);

        List<Element> childElements = rootElem.getChildElements();

        assertEquals(3, childElements.size());
        assertEquals(child1, childElements.get(0));
        assertEquals(child2, childElements.get(1));
        assertEquals(child3, childElements.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void appendNullChildElement() {
        Element rootElem = new BaseElement("div");

        rootElem.appendChildElement(null);
    }

    @Test
    public void deleteChildElement() {
        Element rootElem = new BaseElement("div");
        Element child1 = new BaseElement("span");
        Element child2 = new BaseElement("div");

        rootElem.appendChildElement(child1);
        rootElem.appendChildElement(child2);

        boolean deleteResult = rootElem.deleteChildElement(child1);
        List<Element> childElements = rootElem.getChildElements();

        assertTrue(deleteResult);
        assertEquals(1, childElements.size());
        assertTrue(childElements.contains(child2));
    }

    @Test
    public void deleteNotAddedChildElement() {
        Element rootElem = new BaseElement("div");
        Element child1 = new BaseElement("span");
        Element child2 = new BaseElement("div");

        rootElem.appendChildElement(child1);

        boolean deleteResult = rootElem.deleteChildElement(child2);
        List<Element> childElements = rootElem.getChildElements();

        assertFalse(deleteResult);
        assertEquals(1, childElements.size());
    }

    @Test
    public void getAllClasses() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("class", "abc efg");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());
        Set<String> classes = elem.getAllClasses();

        assertEquals(2, classes.size());
        assertTrue(classes.contains("abc"));
        assertTrue(classes.contains("efg"));
    }

    @Test
    public void getAllClassesWithoutClassAttribute() {
        Element elem = new BaseElement("div");
        Set<String> classes = elem.getAllClasses();

        assertEquals(0, classes.size());
    }

    @Test
    public void containsClass() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("class", "abc");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());

        assertTrue(elem.containsClass("abc"));
    }

    @Test
    public void containsClassOfNotIncludedClass() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute attr = new BaseAttribute("class", "abc");
        attributes.add(attr);

        Element elem = new BaseElement("div", attributes, new ArrayList<>());

        assertFalse(elem.containsClass("efg"));
    }

    @Test
    public void getAllDescendants() {
        Element rootElem = new BaseElement("div");
        Element child1 = new BaseElement("span");
        Element child2 = new BaseElement("div");
        Element grandChild = new BaseElement("p");

        child2.appendChildElement(grandChild);
        rootElem.appendChildElement(child1);
        rootElem.appendChildElement(child2);

        Set<Element> allDescendants = rootElem.getAllDescendants();

        assertEquals(3, allDescendants.size());
        assertTrue(allDescendants.contains(child1));
        assertTrue(allDescendants.contains(child2));
        assertTrue(allDescendants.contains(grandChild));
    }

    @Test
    public void getAllDescendantsWithNoChildElements() {
        Element elem = new BaseElement("div");

        Set<Element> allDescendants = elem.getAllDescendants();

        assertEquals(0, allDescendants.size());
    }
}
