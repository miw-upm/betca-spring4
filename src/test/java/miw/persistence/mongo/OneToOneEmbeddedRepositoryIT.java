package miw.persistence.mongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import miw.persistence.mongo.documents.EmbeddableDocument;
import miw.persistence.mongo.documents.OneToOneEmbeddedDocument;
import miw.persistence.mongo.repositories.OneToOneEmbeddedRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class OneToOneEmbeddedRepositoryIT {

    @Autowired
    private OneToOneEmbeddedRepository unidirectionalOneToOneEmbeddedRepository;

    @Before
    public void populate() {
        this.unidirectionalOneToOneEmbeddedRepository.deleteAll();
        this.unidirectionalOneToOneEmbeddedRepository.save(new OneToOneEmbeddedDocument("nick", new EmbeddableDocument(1, "1")));
        this.unidirectionalOneToOneEmbeddedRepository.save(new OneToOneEmbeddedDocument("nick", new EmbeddableDocument(2, "2")));
        this.unidirectionalOneToOneEmbeddedRepository.save(new OneToOneEmbeddedDocument("nick", new EmbeddableDocument(3, "2")));
    }

    @Test
    public void test() {
        assertTrue(unidirectionalOneToOneEmbeddedRepository.count() > 0);
    }

    @Test
    public void testFindFirstByEmbeddableDocumentValue() {
        assertNotNull(unidirectionalOneToOneEmbeddedRepository.findFirstByEmbeddableDocumentValue("1"));
        assertNull(unidirectionalOneToOneEmbeddedRepository.findFirstByEmbeddableDocumentValue("NOT"));
    }

    @Test
    public void testFindFirst10ByEmbeddableDocumentValue() {
        assertEquals(2, unidirectionalOneToOneEmbeddedRepository.findFirst10ByEmbeddableDocumentValue("2").size());
    }

}
