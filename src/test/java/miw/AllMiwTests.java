package miw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import miw.aspect.AspectTest;
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
    AspectTest.class,
    MainInjectonMessageServiceMockTest.class,
    SingletonMessageServiceInjectionTest.class,
    SingletonMessageServiceTest.class,

    AllPersistenceJpaDaosIntegrationsTests.class,
    AllPersistenceMongoRepositoryIntegrationsTests.class,
    LibraryIT.class,
    MainInjectonIT.class,

    AllResourcesFunctionalTests.class
})
public class AllMiwTests {

}
