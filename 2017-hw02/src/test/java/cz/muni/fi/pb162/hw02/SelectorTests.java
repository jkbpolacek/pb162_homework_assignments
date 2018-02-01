package cz.muni.fi.pb162.hw02;

import cz.muni.fi.pb162.hw02.impl.selector.DescendantSelectorTest;
import cz.muni.fi.pb162.hw02.impl.selector.ClassSelectorTest;
import cz.muni.fi.pb162.hw02.impl.selector.ElementSelectorTest;
import cz.muni.fi.pb162.hw02.impl.selector.IdSelectorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Karel Vaculik
 */
@RunWith(Suite.class)
@SuiteClasses({
        DescendantSelectorTest.class,
        ElementSelectorTest.class,
        IdSelectorTest.class,
        ClassSelectorTest.class,
})
public class SelectorTests {

}
