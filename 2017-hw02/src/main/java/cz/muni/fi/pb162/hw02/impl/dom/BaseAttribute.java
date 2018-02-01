package cz.muni.fi.pb162.hw02.impl.dom;

import cz.muni.fi.pb162.hw02.Attribute;

/**
 * @author Jakub Polacek
 */
public class BaseAttribute implements Attribute {


    private String name;
    private String value;

    /**
     * Constructor of base attribute
     *
     * @param name  name of attribute
     * @param value value of attribute
     * @throws IllegalArgumentException thrown when name is null
     */
    public BaseAttribute(String name, String value) throws IllegalArgumentException {

        if (name == null) {
            throw new IllegalArgumentException("Incorrect argument.");
        }

        this.name = name;
        this.value = value;


    }

    /**
     * Bare bones constructor
     *
     * @param name name of attribute
     */
    public BaseAttribute(String name) {
        this(name, "");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (o instanceof Attribute) {
            return ((Attribute) o).getName().equals(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {

        if (value.length() > 0) {
            return name + "=\"" + value + '"';
        } else {
            return name;
        }
    }

}
