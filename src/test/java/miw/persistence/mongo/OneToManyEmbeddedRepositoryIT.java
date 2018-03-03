package miw.persistence.mongo;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import miw.persistence.mongo.documents.EmbeddableDocument;
import miw.persistence.mongo.documents.OneToManyEmbeddedDocument;
import miw.persistence.mongo.repositories.OneToManyEmbeddedRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToManyEmbeddedRepositoryIT {

    @Autowired
    private OneToManyEmbeddedRepository unidirectionalOneToManyEmbeddedRepository;

    @Before
    public void seedDb() {
        this.unidirectionalOneToManyEmbeddedRepository.deleteAll();
        OneToManyEmbeddedDocument oneToManyEmbeddedDocument = new OneToManyEmbeddedDocument("nick");
        oneToManyEmbeddedDocument.getEmbeddableDocumentList().add(new EmbeddableDocument(1, "1"));
        oneToManyEmbeddedDocument.getEmbeddableDocumentList().add(new EmbeddableDocument(2, "2"));
        oneToManyEmbeddedDocument.getEmbeddableDocumentList().add(new EmbeddableDocument(3, "3"));
        this.unidirectionalOneToManyEmbeddedRepository.save(oneToManyEmbeddedDocument);
    }

    @Test
    public void test() {
        assertTrue(unidirectionalOneToManyEmbeddedRepository.count() > 0);
    }

}
