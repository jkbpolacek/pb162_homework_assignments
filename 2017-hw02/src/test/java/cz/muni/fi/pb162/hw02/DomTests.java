package cz.muni.fi.pb162.hw02;

import cz.muni.fi.pb162.hw02.impl.dom.AttributeTest;
import cz.muni.fi.pb162.hw02.impl.dom.ElementTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Karel Vaculik
 */
@RunWith(Suite.class)
@SuiteClasses({
        AttributeTest.class,
        ElementTest.class,
})
public class DomTests {

}
