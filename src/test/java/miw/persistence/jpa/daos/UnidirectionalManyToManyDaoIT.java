package miw.persistence.jpa.daos;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import miw.persistence.jpa.daos.AnyDao;
import miw.persistence.jpa.daos.UnidirectionalManyToManyDao;
import miw.persistence.jpa.entities.AnyEntity;
import miw.persistence.jpa.entities.UnidirectionalManyToManyEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnidirectionalManyToManyDaoIT {

    @Autowired
    private UnidirectionalManyToManyDao unidirectionalManyToManyDao;

    @Autowired
    private AnyDao anyDao;

    private List<AnyEntity> array;

    private List<AnyEntity> array2;

    private UnidirectionalManyToManyEntity entity;

    private UnidirectionalManyToManyEntity entity2;

    @Before
    public void before() {
        this.array = Arrays.asList(new AnyEntity[] {new AnyEntity("auno"), new AnyEntity("ados"), new AnyEntity("atres")});
        this.array2 = Arrays.asList(new AnyEntity[] {new AnyEntity("acuatro"), new AnyEntity("acinco")});
        this.entity = new UnidirectionalManyToManyEntity("Mi Nick", array);
        this.entity2 = new UnidirectionalManyToManyEntity("Mi Nick2", array2);
    }

    @Test
    public void testFindOne() {
        anyDao.save(array);
        anyDao.save(array2);
        unidirectionalManyToManyDao.save(entity);
        unidirectionalManyToManyDao.save(entity);
        assertEquals(3, unidirectionalManyToManyDao.findOne(entity.getId()).getAnyEntityList().size());
    }

    @After
    public void delete() {
        unidirectionalManyToManyDao.delete(entity);
        unidirectionalManyToManyDao.delete(entity2);
        anyDao.delete(array);
        anyDao.delete(array2);
    }

}
