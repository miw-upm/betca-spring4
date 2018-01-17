package miw.resources;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    AdminResourceFunctionalTesting.class,
    SecurityResourceFunctionalTesting.class
})
public class AllResourcesFunctionalTestingTests {

}
