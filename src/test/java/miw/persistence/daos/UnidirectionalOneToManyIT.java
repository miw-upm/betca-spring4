package miw.persistence.daos;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import miw.persistence.jpa.daos.UnidirectionalOneToManyDao;
import miw.persistence.jpa.entities.AnyEntity;
import miw.persistence.jpa.entities.UnidirectionalOneToManyEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class UnidirectionalOneToManyIT {

    @Autowired
    private UnidirectionalOneToManyDao unidirectionalOneToManyDao;

    private AnyEntity[] array = {new AnyEntity("uno"), new AnyEntity("dos"), new AnyEntity("tres")};

    private UnidirectionalOneToManyEntity entity;

    @Before
    public void before() {
        this.entity = new UnidirectionalOneToManyEntity("Mi Nick", Arrays.asList(array));
    }

    @Test
    public void testFindOne() {
        unidirectionalOneToManyDao.save(this.entity);
        UnidirectionalOneToManyEntity unidirectionalOneToManyEmbeddedEntity = unidirectionalOneToManyDao.findOne(this.entity.getId());
        assertEquals("Mi Nick", unidirectionalOneToManyEmbeddedEntity.getNick());
        assertEquals(3, unidirectionalOneToManyEmbeddedEntity.getAnyEntityList().size());
    }

    @After
    public void delete() {
        unidirectionalOneToManyDao.delete(entity.getId());
    }

}
