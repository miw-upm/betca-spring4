package miw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import miw.injection.MainInjectonIT;
import miw.injection.MainInjectonMessageServiceMockTest;
import miw.injection.SingletonMessageServiceInjectionTest;
import miw.injection.SingletonMessageServiceTest;
import miw.persistence.jpa.daos.AllPersistenceJpaDaosIntegrationsTests;
import miw.persistence.jpa.daos.library.LibraryIT;
import miw.persistence.mongo.AllPersistenceMongoRepositoryIntegrationsTests;
import miw.resources.AllResourcesFunctionalTests;

@RunWith(Suite.class)
@SuiteClasses({
    AllPersistenceJpaDaosIntegrationsTests.class,
    LibraryIT.class,
    MainInjectonIT.class,
    MainInjectonMessageServiceMockTest.class,
    SingletonMessageServiceInjectionTest.class,
    SingletonMessageServiceTest.class,
    AllResourcesFunctionalTests.class,
    AllPersistenceMongoRepositoryIntegrationsTests.class
})
public class AllMiwTests {

}
