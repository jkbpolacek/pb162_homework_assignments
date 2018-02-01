package cz.muni.fi.pb162.hw02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Karel Vaculik
 */
@RunWith(Suite.class)
@SuiteClasses({
        DomTests.class,
        SelectorTests.class,
        QueryTests.class,
})
public class AllTests {

}
