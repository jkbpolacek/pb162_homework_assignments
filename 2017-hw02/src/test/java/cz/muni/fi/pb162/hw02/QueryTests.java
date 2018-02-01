package cz.muni.fi.pb162.hw02;

import cz.muni.fi.pb162.hw02.impl.QueryExecutorTest;
import cz.muni.fi.pb162.hw02.impl.parser.QueryParserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Karel Vaculik
 */
@RunWith(Suite.class)
@SuiteClasses({
        QueryParserTest.class,
        QueryExecutorTest.class,
})
public class QueryTests {

}
