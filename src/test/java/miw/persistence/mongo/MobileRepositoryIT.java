package miw.persistence.mongo;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class MobileRepositoryIT {

    @Autowired
    private MobileRepository mobileRepository;

    private Mobile mobile;

    @Before
    public void populate() {
        this.mobile = new Mobile("066666666", Type.BASIC);
        mobileRepository.save(this.mobile);

    }

    @Test
    public void testFindOne() {
        assertEquals(this.mobile.getId(), mobileRepository.findOne("066666666").getId());
        assertEquals(this.mobile.getType(), mobileRepository.findOne("066666666").getType());
    }

    @After
    public void deleteAll() {
        mobileRepository.delete(this.mobile);
    }

}
