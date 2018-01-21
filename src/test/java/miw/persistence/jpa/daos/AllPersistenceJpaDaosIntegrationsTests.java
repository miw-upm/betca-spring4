package miw.persistence.jpa.daos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    UnidirectionalOneToOneEmbeddedDaoIT.class,
    UnidirectionalOneToOneJoinColumnDaoIT.class,
    UnidirectionalOneToManyEmbeddedDaoIT.class,
    UnidirectionalOneToManyDaoIT.class,
    UnidirectionalManyToOneJoinColumnDaoIT.class,
    UnidirectionalManyToManyDaoIT.class,
    UnRelatedDaoIT.class
})
public class AllPersistenceJpaDaosIntegrationsTests {

}
