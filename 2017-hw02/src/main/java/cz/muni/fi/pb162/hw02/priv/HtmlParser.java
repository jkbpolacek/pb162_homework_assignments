package cz.muni.fi.pb162.hw02.priv;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.impl.dom.BaseAttribute;
import cz.muni.fi.pb162.hw02.impl.dom.BaseElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * @author Karel Vaculik
 */
public final class HtmlParser {
    private HtmlParser() {}

    /**
     * Parse a HTML file on the given file path and return the root element.
     *
     * @param filePath - the path to the HTML file
     * @return the root element
     *
     * @throws IOException if there is IO problem
     */
    public static Element parse(String filePath) throws IOException {
        File input = new File(filePath);
        Document doc = Jsoup.parse(input, "UTF-8");

        return  parseElement(doc.body());
    }

    private static Element parseElement(org.jsoup.nodes.Element jsoupElement) {
        Element parsedElement = new BaseElement(jsoupElement.tagName());

        for (org.jsoup.nodes.Element jsoupEl : jsoupElement.children()) {
            parsedElement.appendChildElement(parseElement(jsoupEl));
        }

        for (org.jsoup.nodes.Attribute attr : jsoupElement.attributes()) {
            String key = attr.getKey();
            String value = attr.getValue();

            parsedElement.addAttribute(new BaseAttribute(key, value));
        }

        return parsedElement;
    }
}
