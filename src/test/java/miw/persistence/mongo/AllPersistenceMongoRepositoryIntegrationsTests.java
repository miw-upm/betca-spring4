package miw.persistence.mongo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    MobileRepositoryIT.class,
    VehicleRepositoryIT.class,
    UserRepositoryIT.class
})
public class AllPersistenceMongoRepositoryIntegrationsTests {

}
