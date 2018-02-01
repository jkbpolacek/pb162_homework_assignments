package cz.muni.fi.pb162.hw02.impl.dom;

import cz.muni.fi.pb162.hw02.Attribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karel Vaculik
 */
public class AttributeTest {

    @Test
    public void getName() {
        Attribute attr = new BaseAttribute("Attr", "abc");
        String name = attr.getName();

        assertEquals("Attr", name);
    }

    @Test
    public void getValue() {
        Attribute attr = new BaseAttribute("Attr", "abc");
        String value = attr.getValue();

        assertEquals("abc", value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNameAsNull() {
        new BaseAttribute(null, "abc");
    }

    @Test
    public void getStringRepresentationWithoutValue() {
        Attribute attr = new BaseAttribute("Attr");
        String strRepresentation = attr.toString();

        assertEquals("Attr", strRepresentation);
    }

    @Test
    public void getStringRepresentationWithValue() {
        Attribute attr = new BaseAttribute("Attr", "abc");
        String strRepresentation = attr.toString();

        assertEquals("Attr=\"abc\"", strRepresentation);
    }

    @Test
    public void compareEqualAttributes() {
        Attribute attr1 = new BaseAttribute("Attr", "abc");
        Attribute attr2 = new BaseAttribute("Attr", "efg");

        assertTrue(attr1.equals(attr2));
    }

    @Test
    public void compareWithDifferentClass() {
        Attribute attr = new BaseAttribute("Attr", "abc");

        assertFalse(attr.equals("Attr"));
    }

    @Test
    public void compareHashCodeOfEqualAttributes() {
        Attribute attr1 = new BaseAttribute("Attr", "abc");
        Attribute attr2 = new BaseAttribute("Attr", "efg");

        assertEquals(attr1.hashCode(), attr2.hashCode());
    }
}
