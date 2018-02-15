package miw.persistence.mongo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import miw.persistence.mongo.documents.AnyDocument;
import miw.persistence.mongo.documents.OneAndManyToManyDocument;
import miw.persistence.mongo.repositories.AnyRepository;
import miw.persistence.mongo.repositories.OneAndManyToManyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class OneAndManyToManyRepositoryIT {

    @Autowired
    private OneAndManyToManyRepository oneToManyRepository;

    @Autowired
    private AnyRepository anyRepository;

    @Before
    public void seedDb() {
        this.oneToManyRepository.deleteAll();
        this.anyRepository.deleteAll();
        List<AnyDocument> anyDocumentList = new ArrayList<>();
        anyDocumentList.add(new AnyDocument("any"));
        anyDocumentList.add(new AnyDocument("any2"));
        this.anyRepository.save(anyDocumentList);
        this.oneToManyRepository.save(new OneAndManyToManyDocument("nick", anyDocumentList));
        this.oneToManyRepository.save(new OneAndManyToManyDocument("nick2", anyDocumentList));
    }

    @Test
    public void test() {
        assertTrue(oneToManyRepository.count() > 0);
    }

}
