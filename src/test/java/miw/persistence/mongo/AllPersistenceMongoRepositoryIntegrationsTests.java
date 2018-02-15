package miw.persistence.mongo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    UnRelatedRepositoryIT.class,
    OneToOneEmbeddedRepositoryIT.class,
    OneAndManyToOneRepositoryIT.class,
    OneAndManyToManyRepositoryIT.class
})
public class AllPersistenceMongoRepositoryIntegrationsTests {

}
