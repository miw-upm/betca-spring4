package miw.persistence.mongo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    OneAndManyToManyRepositoryIT.class,
    OneAndManyToOneRepositoryIT.class,
    OneToManyEmbeddedRepositoryIT.class,
    OneToOneEmbeddedRepositoryIT.class,
    UnRelatedRepositoryIT.class,
})
public class AllPersistenceMongoRepositoryIntegrationsTests {

}
