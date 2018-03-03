package miw.persistence.jpa.daos;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import miw.persistence.jpa.daos.AnyDao;
import miw.persistence.jpa.daos.UnidirectionalManyToOneJoinColumnDao;
import miw.persistence.jpa.entities.AnyEntity;
import miw.persistence.jpa.entities.UnidirectionalManyToOneJoinColumnEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnidirectionalManyToOneJoinColumnDaoIT {

    @Autowired
    private UnidirectionalManyToOneJoinColumnDao unidirectionalManyToOneJoinColumnDao;

    @Autowired
    private AnyDao anyDao;

    private AnyEntity anyEntity = new AnyEntity("daemon");

    private UnidirectionalManyToOneJoinColumnEntity entity;

    private UnidirectionalManyToOneJoinColumnEntity entity2;

    @Before
    public void before() {
        this.anyEntity = new AnyEntity("daemon");
        this.entity = new UnidirectionalManyToOneJoinColumnEntity("Mi Nick", anyEntity);
        this.entity2 = new UnidirectionalManyToOneJoinColumnEntity("Mi Nick 2", anyEntity);
    }

    @Test
    public void testFindOne() {
        this.anyDao.save(anyEntity);
        this.unidirectionalManyToOneJoinColumnDao.save(entity);
        this.unidirectionalManyToOneJoinColumnDao.save(entity2);
        int id = anyEntity.getId();
        assertEquals(id, this.unidirectionalManyToOneJoinColumnDao.findOne(entity.getId()).getAnyEntity().getId());
        assertEquals(id, this.unidirectionalManyToOneJoinColumnDao.findOne(entity2.getId()).getAnyEntity().getId());
    }

    @After
    public void delete() {
        unidirectionalManyToOneJoinColumnDao.delete(this.entity);
        unidirectionalManyToOneJoinColumnDao.delete(this.entity2);
        anyDao.delete(this.anyEntity);
    }

}
