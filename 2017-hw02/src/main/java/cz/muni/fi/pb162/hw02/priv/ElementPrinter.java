package cz.muni.fi.pb162.hw02.priv;

import cz.muni.fi.pb162.hw02.Attribute;
import cz.muni.fi.pb162.hw02.Element;

import java.util.List;
import java.util.Set;

/**
 * @author Karel Vaculik
 */
public class ElementPrinter {

    /**
     * Transform the given element to the HTML form and print it to a console.
     *
     * @param element - the element to be printed
     */
    public static void print(Element element) {
        System.out.println(elementToString(element));
    }

    private static String elementToString(Element element) {
        String name = element.getName();
        Set<Attribute> attributes = element.getAttributes();
        List<Element> childElements = element.getChildElements();

        StringBuilder result = new StringBuilder("<");
        result.append(name);

        for (Attribute attr : attributes) {
            result.append(" ");
            result.append(attr.toString());
        }
        result.append(">");
        result.append(System.lineSeparator());

        for (Element el : childElements) {
            result.append(indent(elementToString(el)));
            result.append(System.lineSeparator());
        }

        result.append("</");
        result.append(name);
        result.append(">");

        return result.toString();
    }

    private static String indent(String text) {
        return text.replaceAll("(?m)^", "\t");
    }
}
