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
public class UserRepositoryIT {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private UserRepository userRepository;

    private Vehicle vehicle;

    private Mobile mobile;

    private User user;

    @Before
    public void populate() {
        this.mobile = new Mobile("111111111", Type.BASIC);
        this.mobileRepository.save(this.mobile);
        this.vehicle = new Vehicle("0000 zzz");
        this.vehicleRepository.save(vehicle);
        this.user = new User("unotest", "unooossss", new Address("Madrid", "Gran Via"), vehicle);
        this.user.getMobileList().add(mobile);
        this.userRepository.save(user);
    }

    @Test
    public void testFindOne() {
        assertEquals(this.user.getId(), userRepository.findByName("unotest").getId());
        assertEquals("Madrid", userRepository.findByName("unotest").getAddress().getCity());
        assertEquals("111111111", userRepository.findByName("unotest").getMobileList().get(0).getId());
    }

    @After
    public void deleteAll() {
        userRepository.delete(user);
        vehicleRepository.delete(vehicle);
        mobileRepository.delete(mobile);
    }

}
