package miw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import miw.injection.MainInjectonIntegrationTest;
import miw.injection.MainInjectonMessageServiceMockTest;
import miw.injection.SingletonMessageServiceInjectionTest;
import miw.injection.SingletonMessageServiceTest;
import miw.persistence.daos.AllPersistenceDaosIntegrationsTests;
import miw.persistence.daos.library.LibraryIT;

@RunWith(Suite.class)
@SuiteClasses({
    AllPersistenceDaosIntegrationsTests.class,
    LibraryIT.class,
    MainInjectonIntegrationTest.class,
    MainInjectonMessageServiceMockTest.class,
    SingletonMessageServiceInjectionTest.class,
    SingletonMessageServiceTest.class
})
public class AllMiwTests {

}
