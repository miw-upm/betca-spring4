package miw.persistence.mongo;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import miw.persistence.mongo.documents.AnyDocument;
import miw.persistence.mongo.documents.OneAndManyToOneDocument;
import miw.persistence.mongo.repositories.AnyRepository;
import miw.persistence.mongo.repositories.OneAndManyToOneRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class OneAndManyToOneRepositoryIT {

    @Autowired
    private OneAndManyToOneRepository unidirectionalOneToOneRepository;

    @Autowired
    private AnyRepository anyRepository;

    @Before
    public void populate() {
        this.unidirectionalOneToOneRepository.deleteAll();
        this.anyRepository.deleteAll();
        AnyDocument anyDocument = new AnyDocument("any");
        this.anyRepository.save(anyDocument);
        this.unidirectionalOneToOneRepository.save(new OneAndManyToOneDocument("nick", anyDocument));
    }

    @Test
    public void test() {
        assertTrue(unidirectionalOneToOneRepository.count() > 0);
    }

}
