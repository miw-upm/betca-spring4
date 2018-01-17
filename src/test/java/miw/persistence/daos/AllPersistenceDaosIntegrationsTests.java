package miw.persistence.daos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    UnidirectionalOneToOneEmbeddedIT.class,
    UnidirectionalOneToOneJoinColumnIT.class,
    UnidirectionalOneToManyEmbeddedIT.class,
    UnidirectionalOneToManyIT.class,
    UnidirectionalManyToOneJoinColumnIT.class,
    UnidirectionalManyToManyIT.class,
    UnRelatedIT.class
})
public class AllPersistenceDaosIntegrationsTests {

}
