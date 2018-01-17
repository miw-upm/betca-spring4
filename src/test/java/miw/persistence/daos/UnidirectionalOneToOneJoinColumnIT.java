package miw.persistence.daos;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import miw.persistence.entities.AnyEntity;
import miw.persistence.entities.UnidirectionalOneToOneJoinColumnEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class UnidirectionalOneToOneJoinColumnIT {

    @Autowired
    private UnidirectionalOneToOneJoinColumnDao unidirectionalOneToOneJoinColumnDao;

    private AnyEntity anyEntity;

    private UnidirectionalOneToOneJoinColumnEntity entity;

    @Before
    public void before() {
        anyEntity = new AnyEntity("daemon");
        entity = new UnidirectionalOneToOneJoinColumnEntity("Mi Nick", anyEntity);
    }

    @Test
    public void testFindOne() {
        unidirectionalOneToOneJoinColumnDao.save(entity);
        UnidirectionalOneToOneJoinColumnEntity unidirectionalOneToOneJoinColumnEntity = unidirectionalOneToOneJoinColumnDao
                .findOne(entity.getId());
        assertEquals("Mi Nick", unidirectionalOneToOneJoinColumnEntity.getNick());
        assertEquals("daemon", unidirectionalOneToOneJoinColumnEntity.getAnyEntity().getValue());
    }

    @After
    public void delete() {
        unidirectionalOneToOneJoinColumnDao.delete(entity);
    }

}
