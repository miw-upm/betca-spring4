package miw.persistence.jpa.daos.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import miw.persistence.jpa.daos.library.AuthorDao;
import miw.persistence.jpa.daos.library.BookDao;
import miw.persistence.jpa.daos.library.StyleDao;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class LibraryIT {

    @Autowired
    private DaosLibraryService daosLibraryServiceIntegrationTest;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private StyleDao styleDao;

    @Before
    public void populate() {
        daosLibraryServiceIntegrationTest.populate();
    }

    @Test
    public void testPopulate() {
        assertTrue(3 == bookDao.count());
    }

    @Test
    public void testFindByStyle() {
        assertEquals(2, authorDao.findByStyle(styleDao.findByNameIgnoreCase("Infantil")).size());
    }

    @Test
    public void testFindNameByStyleName() {
        assertEquals(2, authorDao.findNameByStyleName("Infantil").size());
        assertArrayEquals(new String[] {"Jesús", "Cris"}, authorDao.findNameByStyleName("Infantil").toArray());
    }
    
    @Test
    public void testFindDistinctNameByAnyBook(){
        assertEquals(3, authorDao.findDistinctNameByAnyBook().size());
        assertArrayEquals(new String[] {"Jesús", "Cris","Ana"}, authorDao.findDistinctNameByAnyBook().toArray());
    }
    
    @Test
    public void testFindNameByThemeName(){
        assertEquals(2, authorDao.findNameByThemeName("Suspense").size());
        assertArrayEquals(new String[] {"Cris","Ana"}, authorDao.findNameByThemeName("Suspense").toArray());
    }

    @After
    public void deleteAll() {
        daosLibraryServiceIntegrationTest.deleteAll();
    }

}
