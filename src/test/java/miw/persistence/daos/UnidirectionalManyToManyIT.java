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

import miw.persistence.entities.AnyEntity;
import miw.persistence.entities.UnidirectionalManyToManyEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class UnidirectionalManyToManyIT {

    @Autowired
    private UnidirectionalManyToManyDao unidirectionalManyToManyDao;

    @Autowired
    private AnyDao anyDao;

    private AnyEntity[] array = {new AnyEntity("uno"), new AnyEntity("dos"), new AnyEntity("tres")};
    private AnyEntity[] array2 = {new AnyEntity("cuatro"), new AnyEntity("cinco")};

    private UnidirectionalManyToManyEntity entity;
    private UnidirectionalManyToManyEntity entity2;

    @Before
    public void before() {
        this.entity = new UnidirectionalManyToManyEntity("Mi Nick", Arrays.asList(array));
        this.entity2 = new UnidirectionalManyToManyEntity("Mi Nick2", Arrays.asList(array));
    }

    @Test
    public void testFindOne() {
        anyDao.save(Arrays.asList(this.array));
        anyDao.save(Arrays.asList(this.array2));
        unidirectionalManyToManyDao.save(entity);
        unidirectionalManyToManyDao.save(entity2);
        assertEquals(3, unidirectionalManyToManyDao.findOne(entity.getId()).getAnyEntityList().size());
    }

    @After
    public void delete() {
        unidirectionalManyToManyDao.delete(entity);
        unidirectionalManyToManyDao.delete(entity2);
        anyDao.delete(Arrays.asList(array));
        anyDao.delete(Arrays.asList(array2));
    }

}
